package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

public class LevelButton extends Actor {

	World world;
	BitmapFont fontButton;
	boolean touched = false;

	public LevelButton(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 800f * world.ppuX);
			setY(world.paddingY + 375f * world.ppuY);
			setWidth(150f * world.ppuX);
			setHeight(40f * world.ppuY);
		} else {
			setX(world.paddingX + 180f * world.ppuX);
			setY(world.paddingY + 140f * world.ppuY);
			setWidth(220f * world.ppuX);
			setHeight(65f * world.ppuY);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
//		batch.draw(Assets.frame, getX(), getY(), getWidth(), getHeight());
		String name = "Уровень ".concat(String.valueOf(world.level - 1));
		if (world.cameraAspectRatio > 1) {
			fontButton = Assets.fontButton2;
		} else {
			fontButton = Assets.fontButton1;
		}
		float w = fontButton.getBounds(name).width;
		float h = fontButton.getBounds(name).height;
		if (true) fontButton.setColor(253f / 255, 250f / 255, 0f / 255, 1);
		else fontButton.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		fontButton.draw(batch, name, getX() + (getWidth() - w) / 2f, getY() + (getHeight() + h) / 2f);
	}
}