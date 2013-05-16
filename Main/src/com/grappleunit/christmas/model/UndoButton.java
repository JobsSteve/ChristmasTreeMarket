package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grappleunit.christmas.Assets;

public class UndoButton extends Actor {

	World world;
	BitmapFont fontButton;
	boolean touched = false;

	public UndoButton(World world) {
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
			setX(world.paddingX + 855f * world.ppuX);
			setY(world.paddingY + 427f * world.ppuY);
			setWidth(90f * world.ppuX);
			setHeight(37f * world.ppuY);
		} else {
			setX(world.paddingX + 253f * world.ppuX);
			setY(world.paddingY + 220f * world.ppuY);
			setWidth(135f * world.ppuX);
			setHeight(63f * world.ppuY);
		}
	}

	private void processInput(float x, float y) {
		if (world.opened > 0 && world.completed == false && world.pauseGame == false && world.time == 0) {
			if (world.soundOn) Assets.resetSound.play();

			int[] step = world.history[world.opened - 1];
			int i = step[0], j = step[1];

			if (i > 0 && world.cells[i - 1][j].currentNumber > 0) {
				if (world.cells[i - 1][j].checkCell()) world.points -= 3;
				world.cells[i - 1][j].currentNumber--;
				if (world.cells[i - 1][j].currentNumber == 0) {
					world.cells[i - 1][j].currentNumber = 4;
				}
			}

			if (i < world.level - 1 && world.cells[i + 1][j].currentNumber > 0) {
				if (world.cells[i + 1][j].checkCell()) world.points -= 3;
				world.cells[i + 1][j].currentNumber--;
				if (world.cells[i + 1][j].currentNumber == 0) {
					world.cells[i + 1][j].currentNumber = 4;
				}
			}

			if (j > 0 && world.cells[i][j - 1].currentNumber > 0) {
				if (world.cells[i][j - 1].checkCell()) world.points -= 3;
				world.cells[i][j - 1].currentNumber--;
				if (world.cells[i][j - 1].currentNumber == 0) {
					world.cells[i][j - 1].currentNumber = 4;
				}
			}

			if (j < world.level - 1 && world.cells[i][j + 1].currentNumber > 0) {
				if (world.cells[i][j + 1].checkCell()) world.points -= 3;
				world.cells[i][j + 1].currentNumber--;
				if (world.cells[i][j + 1].currentNumber == 0) {
					world.cells[i][j + 1].currentNumber = 4;
				}
			}

			if (world.cells[i][j].checkCell()) world.points -= 3;
			world.cells[i][j].currentNumber--;

			world.points -= 1;
			if (world.points < 0) world.points = 0;

			world.opened--;
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
		String name = "Отмена";
		float w = fontButton.getBounds(name).width;
		float h = fontButton.getBounds(name).height;
		if (touched && world.opened > 0 && world.completed == false && world.pauseGame == false && world.time == 0) {
			fontButton.setColor(253f / 255, 250f / 255, 0f / 255, 1);
		} else {
			fontButton.setColor(79f / 255, 53f / 255, 49f / 255, 1);
		}
		fontButton.draw(batch, name, getX() + (getWidth() - w) / 2f, getY() + (getHeight() + h) / 2f);
	}
}
