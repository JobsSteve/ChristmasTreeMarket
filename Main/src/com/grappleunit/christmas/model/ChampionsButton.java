package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grappleunit.christmas.Assets;

public class ChampionsButton extends Actor {

	World world;
	boolean touched = false;

	public ChampionsButton(World world) {
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
			setX(world.paddingX + 845f * world.ppuX);
			setY(world.paddingY + 666f * world.ppuY);
		} else {
			setX(world.paddingX + 588f * world.ppuX);
			setY(world.paddingY + 1090f * world.ppuY);
		}

		setWidth(50f * world.ppuX);
		setHeight(50f * world.ppuY);
	}

	private void processInput(float x, float y) {
		if (world.pauseGame == false && world.completed == false && world.time == 0) {
			if (world.soundOn) Assets.infoSound.play();
			if (world.championsOn == false) {
				world.championsOn = true;
				world.timer = System.nanoTime() - world.timer;
			} else {
				world.championsOn = false;
				world.timer = System.nanoTime() - world.timer;
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
//		batch.draw(Assets.frame, getX(), getY(), getWidth(), getHeight());
		String name =
			(touched || world.championsOn)
			&& world.pauseGame == false
			&& world.championsOn == false
			&& world.completed == false
			&& world.time == 0
				? "infoOver" : "info";
		batch.draw(Assets.textureRegions.get(name), getX(), getY(), getWidth(), getHeight());
	}
}