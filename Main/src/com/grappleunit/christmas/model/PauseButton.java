package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grappleunit.christmas.Assets;

public class PauseButton extends Actor {

	World world;
	BitmapFont fontButton;
	boolean touched = false;

	public PauseButton(World world) {
		this.world = world;

		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				touched = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				processInput(x, y);
				touched = false;
			}
		});
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 710f * world.ppuX);
			setY(world.paddingY + 380f * world.ppuY);
			setWidth(85f * world.ppuX);
			setHeight(38f * world.ppuY);
		} else {
			setX(world.paddingX + 40f * world.ppuX);
			setY(world.paddingY + 148f * world.ppuY);
			setWidth(125f * world.ppuX);
			setHeight(55f * world.ppuY);
		}
	}

	private void processInput(float x, float y) {
		if (world.completed == false && world.time == 0 && world.infoOn == false) {
			if (world.soundOn) Assets.pauseSound.play();
			if (world.pauseGame == false) {
				world.pauseGame = true;
				world.timer = System.nanoTime() - world.timer;
			} else {
				world.pauseGame = false;
				world.timer = System.nanoTime() - world.timer;
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
//		batch.draw(Assets.frame, getX(), getY(), getWidth(), getHeight());
		if (world.cameraAspectRatio > 1) {
			fontButton = Assets.fontButton2;
		} else {
			fontButton = Assets.fontButton1;
		}
		String name = "Пауза";
		float w = fontButton.getBounds(name).width;
		float h = fontButton.getBounds(name).height;
		if ((touched || world.pauseGame) && world.completed == false && world.time == 0 && world.infoOn == false) {
			fontButton.setColor(253f / 255, 250f / 255, 0f / 255, 1);
		} else {
			fontButton.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		}
		fontButton.draw(batch, name, getX() + (getWidth() - w) / 2f, getY() + (getHeight() + h) / 2f);
	}
}