package com.grappleunit.christmas;

import com.badlogic.gdx.Game;
import com.grappleunit.christmas.screens.GameScreen;

public class MyGame extends Game {

	public GameScreen gameScreen;

	@Override
	public void create() {
		Assets.load();
		gameScreen = new GameScreen();
		setScreen(gameScreen);
		Assets.forestMusic.setLooping(true);
		Assets.forestMusic.setVolume(.5f);
		Assets.forestMusic.play();
	}
}