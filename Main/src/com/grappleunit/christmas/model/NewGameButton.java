package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grappleunit.christmas.Assets;

public class NewGameButton extends Actor {

	World world;
	BitmapFont fontButton;
	boolean touched = false;

	public NewGameButton(World world) {
		this.world = world;

		addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				touched = true;
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				processInput(x, y);
				touched = false;
			}
		});
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 708f * world.ppuX);
			setY(world.paddingY + 427f * world.ppuY);
			setWidth(140f * world.ppuX);
			setHeight(38f * world.ppuY);
		} else {
			setX(world.paddingX + 26f * world.ppuX);
			setY(world.paddingY + 220f * world.ppuY);
			setWidth(217f * world.ppuX);
			setHeight(67f * world.ppuY);
		}
	}

	private void processInput(float x, float y) {
		if (world.completed == false && world.pauseGame == false && world.time == 0) {
			if (world.soundOn) Assets.newgameSound.play();
			world.resetGame = true;
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
		String name = "Новая игра";
		float w = fontButton.getBounds(name).width;
		float h = fontButton.getBounds(name).height;
		if (touched && world.completed == false && world.pauseGame == false && world.time == 0) {
			fontButton.setColor(253f / 255, 250f / 255, 0f / 255, 1);
		} else {
			fontButton.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		}
		fontButton.draw(batch, name, getX() + (getWidth() - w) / 2f, getY() + (getHeight() + h) / 2f);
	}
}