package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class Info extends Actor {

	World world;
	boolean touched = false;

	public Info(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 26f * world.ppuX);
			setY(world.paddingY + 26f * world.ppuY);
		} else {
			setX(world.paddingX + 26f * world.ppuX);
			setY(world.paddingY + 308f * world.ppuY);
		}

		setWidth(662f * world.ppuX);
		setHeight(662f * world.ppuY);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.infoOn == true) {
			batch.draw(Assets.info, getX(), getY(), getWidth(), getHeight());
		}
	}
}