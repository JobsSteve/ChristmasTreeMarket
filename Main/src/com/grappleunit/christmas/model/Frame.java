package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class Frame extends Actor {

	World world;
	boolean touched = false;

	public Frame(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX);
			setY(world.paddingY);
		} else {
			setX(world.paddingX + 0f);
			setY(world.paddingY + 280f * world.ppuY);
		}

		setWidth(720f * world.ppuX);
		setHeight(720f * world.ppuY);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		int w = Assets.fon.getWidth();
		int h = Assets.fon.getHeight();
		int x = MathUtils.ceil(world.getWidth() / w);
		int y = MathUtils.ceil(world.getHeight() / h);
		int i, j;
		for (i = 0; i < x; i++) {
			for (j = 0; j < y; j++) {
				batch.draw(
					Assets.fon,
					i * w, j * h,
					w, h
				);
			}
		}

		if (world.cameraAspectRatio > 1) {
			batch.draw(
				Assets.back960x720,
				world.paddingX, world.paddingY,
				world.cameraWidth * world.ppuX, world.cameraHeight * world.ppuY
			);
		} else {
			batch.draw(
				Assets.back720x1280,
				world.paddingX, world.paddingY,
				world.cameraWidth * world.ppuX, world.cameraHeight * world.ppuY
			);
		}

		if (world.pauseGame == false) {
			batch.draw(Assets.frame, getX(), getY(), getWidth(), getHeight());
		}
	}
}