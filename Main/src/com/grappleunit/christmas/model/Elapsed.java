package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class Elapsed extends Actor {

	World world;
	BitmapFont fontPoints;
	boolean touched = false;

	public Elapsed(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 820f * world.ppuX);
			setY(world.paddingY + 635f * world.ppuY);
			setWidth(150f * world.ppuX);
			setHeight(50f * world.ppuY);
		} else {
			setX(world.paddingX + 180f * world.ppuX);
			setY(world.paddingY + 110f * world.ppuY);

			setWidth(150f * world.ppuX);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.cameraAspectRatio > 1) {
			fontPoints = Assets.fontPoints2;
		} else {
			fontPoints = Assets.fontPoints1;
		}
		int min = MathUtils.floor(world.elapsed / 60f);
		int sec = world.elapsed % 60;
		String name = String.format("%02d:%02d", min, sec);
		float w = fontPoints.getBounds("00:00").width;
		float h = fontPoints.getBounds("00:00").height;
		fontPoints.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		fontPoints.draw(batch, name, getX() + (getWidth() - w) / 2f, getY());
	}
}