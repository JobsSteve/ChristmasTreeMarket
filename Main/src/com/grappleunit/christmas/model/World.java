package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.grappleunit.christmas.Assets;
import com.grappleunit.christmas.screens.GameScreen;

import java.util.*;

public class World extends Stage {

	public float cameraWidth, cameraHeight, cameraAspectRatio;
	public Cell[][] cells;
	public float paddingX = 0, paddingY = 0;
	public float ppuX, ppuY;
	public int[][] history;
	public int level, points, elapsed, opened = 0;
	public boolean
		toCheck = false,
		newGame = false, resetGame = false, pauseGame = false,
		soundOn = true, infoOn = false, championsOn = false;

	GameScreen gameScreen;
	Frame frameImage;
	Pause pauseImage;
	Info infoImage;
	Champions championsImage;
	Elapsed elapsedImage;
	Points pointsImage;
	CloseButton closeButton;
	InfoButton infoButton;
	LevelButton levelButton;
	NewGameButton newGameButton;
	PauseButton pauseButton;
	SoundButton soundButton;
	UndoButton undoButton;

	boolean completed = false;
	float timer, time = 0;

	public World(
		GameScreen gameScreen, int x, int y, boolean b,
		SpriteBatch spriteBatch,
		int level, int points, int elapsed, boolean soundOn
	){
		super(x, y, b, spriteBatch);

		this.gameScreen = gameScreen;
		this.level = level;
		this.points = points;
		this.elapsed = elapsed;
		this.soundOn = soundOn;

		timer = System.nanoTime();

		history = new int[level * level][2];

		int[][] cn;
		Integer[] tmp = new Integer[level * level];
		int i, j, k;

		cells = new Cell[level][level];
		cn = new int[level][level];

		for (i = 0; i < level; i++) {
			for (j = 0; j < level; j++) {
				cn[i][j] = 0;
			}
		}

		for (k = 0; k < level * level; k++) {
			tmp[k] = k;
		}

		Collections.shuffle(Arrays.asList(tmp));

		for (k = 0; k < level * level; k++) {
			i = tmp[k] % level;
			j = MathUtils.floor((float) tmp[k] / level);

			cn[i][j] = 1;

			if (i > 0 && cn[i - 1][j] > 0) {
				cn[i - 1][j]++;
				if (cn[i - 1][j] == 5) cn[i - 1][j] = 1;
			}

			if (i < level - 1 && cn[i + 1][j] > 0) {
				cn[i + 1][j]++;
				if (cn[i + 1][j] == 5) cn[i + 1][j] = 1;
			}

			if (j > 0 && cn[i][j - 1] > 0) {
				cn[i][j - 1]++;
				if (cn[i][j - 1] == 5) cn[i][j - 1] = 1;
			}

			if (j < level - 1 && cn[i][j + 1] > 0) {
				cn[i][j + 1]++;
				if (cn[i][j + 1] == 5) cn[i][j + 1] = 1;
			}
		}

		for (i = 0; i < level; i++) {
			for (j = 0; j < level; j++) {
				cells[i][j] = new Cell(this, i, j, cn[i][j], 0);
			}
		}

		frameImage = new Frame(this);
		pauseImage = new Pause(this);
		infoImage = new Info(this);
		newGameButton = new NewGameButton(this);
		undoButton = new UndoButton(this);
		pauseButton = new PauseButton(this);
		levelButton = new LevelButton(this);
		soundButton = new SoundButton(this);
		infoButton = new InfoButton(this);
		closeButton = new CloseButton(this);
		pointsImage = new Points(this);
		elapsedImage = new Elapsed(this);
		championsImage = new Champions(this);

		resize();

		addActor(frameImage);
		addActor(pauseImage);
		addActor(infoImage);
		addActor(championsImage);

		for (i = 0; i < level; i++) {
			for (j = 0; j < level; j++) {
				addActor(cells[i][j]);
			}
		}

		addActor(newGameButton);
		addActor(undoButton);
		addActor(pauseButton);
		addActor(levelButton);
		addActor(soundButton);
		addActor(infoButton);
		addActor(closeButton);
		addActor(pointsImage);
		addActor(elapsedImage);
	}

	public List<Map<String, String>> getChampions() {
		return gameScreen.champions;
	}

	public Map<String, String> getCurrentRecord() {
		return gameScreen.currentRecord;
	}

	public void resize() {
		int i, j;
		float aspectRatio = getWidth() / getHeight(), scale;

		if (aspectRatio > 1) {
			cameraWidth = 960f;
			cameraHeight = 720f;
		} else {
			cameraWidth = 720f;
			cameraHeight = 1280f;
		}

		cameraAspectRatio = cameraWidth / cameraHeight;

		paddingX = 0;
		paddingY = 0;

		if (aspectRatio > cameraAspectRatio) {
			scale = getHeight() / cameraHeight;
			paddingX = (getWidth() - cameraWidth * scale) / 2f;
		} else if (aspectRatio < cameraAspectRatio) {
			scale = getWidth() / cameraWidth;
			paddingY = (getHeight() - cameraHeight * scale) / 2f;
		} else {
			scale = getWidth() / cameraWidth;
		}

		ppuX = scale;
		ppuY = scale;

		Assets.fontButton1.setScale(ppuX, ppuY);
		Assets.fontButton2.setScale(ppuX, ppuY);
		Assets.fontPoints1.setScale(ppuX, ppuY);
		Assets.fontPoints2.setScale(ppuX, ppuY);
		Assets.fontCell1.setScale(ppuX, ppuY);
		Assets.fontCell2.setScale(ppuX, ppuY);
		Assets.fontCell3.setScale(ppuX, ppuY);
		Assets.fontCell4.setScale(ppuX, ppuY);
		Assets.fontCell5.setScale(ppuX, ppuY);
		Assets.fontCell6.setScale(ppuX, ppuY);
		Assets.fontCell7.setScale(ppuX, ppuY);
		Assets.fontCell8.setScale(ppuX, ppuY);
		Assets.fontCell9.setScale(ppuX, ppuY);

		frameImage.setRegion();
		pauseImage.setRegion();
		infoImage.setRegion();
		championsImage.setRegion();
		newGameButton.setRegion();
		undoButton.setRegion();
		pauseButton.setRegion();
		levelButton.setRegion();
		soundButton.setRegion();
		infoButton.setRegion();
		closeButton.setRegion();
		pointsImage.setRegion();
		elapsedImage.setRegion();

		for (i = 0; i < level; i++) {
			for (j = 0; j < level; j++) {
				cells[i][j].setRegion();
			}
		}
	}

	public void checkCells() {
		int i, j;

		toCheck = false;
		completed = true;

		for (i = 0; i < level; i++) {
			for (j = 0; j < level; j++) {
				if (cells[i][j].currentNumber != cells[i][j].number) {
					completed = false;
				}
			}
		}
	}

	public void update(float delta) {
		if (toCheck) {
			checkCells();
		} else if (completed) {
			if (soundOn) Assets.completeSound.play();
			time = System.nanoTime();
			completed = false;
		} else if (time > 0 && (System.nanoTime() - time) / 1000000000f > .75f) {
			time = 0;
			newGame = true;
		} else if (newGame == true) {
			newGame = false;
			gameScreen.level = level + 1;
			gameScreen.points = points;
			gameScreen.elapsed = elapsed;
			gameScreen.soundOn = soundOn;
			gameScreen.writeRecord();
			gameScreen.show();
		} else if (resetGame == true) {
			resetGame = false;
			gameScreen.level = level;
			gameScreen.points = 0;
			gameScreen.elapsed = 0;
			gameScreen.soundOn = soundOn;
			gameScreen.show();
		}

		if (
			completed == false
			&& time == 0
			&& newGame == false
			&& resetGame == false
			&& pauseGame == false
			&& infoOn == false
			&& championsOn == false
			&& (System.nanoTime() - timer) / 1000000000f > 1f
		) {
			elapsed += 1;
			timer = System.nanoTime();
			if (elapsed == 3600) {
				resetGame = true;
			}
		}
	}

	public void gameOver() {
		System.exit(0);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer, button);
		return true;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		super.touchDragged(x, y, pointer);
		return true;
	}
}
