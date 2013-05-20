package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grappleunit.christmas.Assets;

public class Cell extends Actor {

	World world;
	String name = new String();
	int i, j, number, currentNumber;
	boolean touched = false;

	public Cell(World world, int i, int j, int number, int currentNumber) {
		this.world = world;
		this.i = i;
		this.j = j;
		this.number = number;
		this.currentNumber = currentNumber;

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
		float startX, startY, level = (float)world.level;

		if (world.cameraAspectRatio > 1) {
			startX = world.paddingX + 26f * world.ppuX;
			startY = world.paddingY + 26f * world.ppuY;
		} else {
			startX = world.paddingX + 26f * world.ppuX;
			startY = world.paddingY + 308f * world.ppuY;
		}

		setWidth(662f / level * world.ppuX - (1f - 1f / level));
		setHeight(662f / level * world.ppuY - (1f - 1f / level));

		setX(startX + i * getWidth() + i);
		setY(startY + j * getHeight() + j);
	}

	public void increment() {
		this.currentNumber++;
		if (this.currentNumber == 5) {
			this.currentNumber = 1;
		}
	}

	public boolean checkCell() {
		if (currentNumber != number) return false;
		if (i > 0 && world.cells[i - 1][j].currentNumber == 0) return false;
		if (i < world.level - 1 && world.cells[i + 1][j].currentNumber == 0) return false;
		if (j > 0 && world.cells[i][j - 1].currentNumber == 0) return false;
		if (j < world.level - 1 && world.cells[i][j + 1].currentNumber == 0) return false;

		return true;
	}

	private void processInput(float x, float y) {
		if (!(world.pauseGame == false && world.infoOn == false && world.championsOn == false)) {
			return;
		}

		if(currentNumber == 0) {

			if (world.soundOn) {
				switch (number) {
					case 1: Assets.cellSound1.play(); break;
					case 2: Assets.cellSound2.play(); break;
					case 3: Assets.cellSound3.play(); break;
					case 4: Assets.cellSound4.play(); break;
				}
			}

			currentNumber = 1;

			if (i > 0 && world.cells[i - 1][j].currentNumber > 0) {
				world.cells[i - 1][j].increment();
				if (world.cells[i - 1][j].checkCell()) world.points += 3;
			}

			if (i < world.level - 1 && world.cells[i + 1][j].currentNumber > 0) {
				world.cells[i + 1][j].increment();
				if (world.cells[i + 1][j].checkCell()) world.points += 3;
			}

			if (j > 0 && world.cells[i][j - 1].currentNumber > 0) {
				world.cells[i][j - 1].increment();
				if (world.cells[i][j - 1].checkCell()) world.points += 3;
			}

			if (j < world.level - 1 && world.cells[i][j + 1].currentNumber > 0) {
				world.cells[i][j + 1].increment();
				if (world.cells[i][j + 1].checkCell()) world.points += 3;
			}

			if (world.cells[i][j].checkCell()) world.points += 3;

			world.history[world.opened] = new int[]{i, j};
			world.opened++;

			if (world.opened == world.level * world.level) {
				world.toCheck = true;
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.pauseGame == false && world.infoOn == false && world.championsOn == false) {
			if (currentNumber == 0) {
				name = "number".concat(String.valueOf(number));
			} else if (currentNumber != number) {
				name = "red".concat(String.valueOf(currentNumber));
			}  else {
				name = "green".concat(String.valueOf(currentNumber));
			}

			batch.draw(Assets.textureRegions.get(name), getX(), getY(), getWidth(), getHeight());

			if (currentNumber != 0) {
				BitmapFont font;

				switch (world.level) {
					case 2: font = Assets.fontCell1; break;
					case 3: font = Assets.fontCell2; break;
					case 4: font = Assets.fontCell3; break;
					case 5: font = Assets.fontCell4; break;
					case 6: font = Assets.fontCell5; break;
					case 7: font = Assets.fontCell6; break;
					case 8: font = Assets.fontCell7; break;
					case 9: case 10: case 11: case 12: case 13: font = Assets.fontCell8; break;
					default: font = Assets.fontCell9; break;
				}

				switch (number) {
					case 1: font.setColor(0, 146f / 255, 63f / 255, 1); break;
					case 2: font.setColor(202f / 255, 24f / 255, 222f / 255, 1); break;
					case 3: font.setColor(254f / 255, 120f / 255, 120f / 255, 1); break;
					case 4: font.setColor(120f / 255, 120f / 255, 254f / 255, 1); break;
				}

				float w = font.getBounds(String.valueOf(number)).width;
				float h = font.getBounds(String.valueOf(number)).height;

				font.draw(batch, String.valueOf(number), getX() + .95f * getWidth() - w, getY() + .95f * getHeight() + .33f * h);
			}
		}
	}
}
