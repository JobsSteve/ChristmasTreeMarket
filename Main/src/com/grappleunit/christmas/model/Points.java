package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class Points extends Actor {

	World world;
	BitmapFont fontPoints;
	boolean touched = false;

	public Points(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 710f * world.ppuX);
			setY(world.paddingY + 635f * world.ppuY);
			setWidth(100f * world.ppuX);
			setHeight(50f * world.ppuY);
		} else {
			setX(world.paddingX + 40f * world.ppuX);
			setY(world.paddingY + 110f * world.ppuY);
			setWidth(125f * world.ppuX);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.cameraAspectRatio > 1) {
			fontPoints = Assets.fontPoints2;
		} else {
			fontPoints = Assets.fontPoints1;
		}
		String name = String.valueOf(world.points);
		float w = fontPoints.getBounds(name).width;
		float h = fontPoints.getBounds(name).height;
		fontPoints.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		fontPoints.draw(batch, name, getX() + (getWidth() - w) / 2f, getY());
	}
}