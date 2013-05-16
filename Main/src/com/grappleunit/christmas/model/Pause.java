package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class Pause extends Actor {

	World world;
	boolean touched = false;

	public Pause(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 120f * world.ppuX);
			setY(world.paddingY + 450f * world.ppuY);
			setWidth(473f * world.ppuX);
			setHeight(180f * world.ppuY);
		} else {
			setX(world.paddingX + 45f * world.ppuX);
			setY(world.paddingY + 700f * world.ppuY);
			setWidth(630f * world.ppuX);
			setHeight(240f * world.ppuY);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.pauseGame == true) {
			batch.draw(Assets.pause240, getX(), getY(), getWidth(), getHeight());
		}
	}
}