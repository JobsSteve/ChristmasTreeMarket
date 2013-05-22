package com.grappleunit.christmas.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grappleunit.christmas.model.World;
import com.grappleunit.christmas.utils.Sscanf;

import java.io.*;
import java.util.*;

public class GameScreen implements Screen, InputProcessor {

	public OrthographicCamera cam;
	public World world;
	public int width;
	public int height;
	public List<Map<String, String>> champions = new ArrayList<Map<String, String>>();
	public int level = 2, points = 0, elapsed = 0;
	public boolean soundOn = true;
	public Map<String, String> currentRecord = new HashMap<String, String>();

	final String CHAMPIONS = "champions.txt";

	SpriteBatch spriteBatch;

	public GameScreen() {
		readRecord();
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();

		world = new World(
			this, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false,
			spriteBatch, level, points, elapsed, soundOn
		);

		this.cam = new OrthographicCamera(world.cameraWidth, world.cameraHeight);
		SetCamera(world.cameraWidth / 2f, world.cameraHeight / 2f);

		Gdx.input.setInputProcessor(world);
	}

	public void SetCamera(float x, float y){
		this.cam.position.set(x, y, 0);
		this.cam.update();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		world.update(delta);
		world.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		world.setViewport(width, height, true);
		world.resize();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) return false;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) return false;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		return true;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void readRecord() {

		try {

			FileHandle file = Gdx.files.local(CHAMPIONS);
			String data = file.readString();

			String[] strings = data.split("\n");

			int i = 0;

			for (String line : strings) {
				if (line.length() > 17) {
					Object output[] = Sscanf.scan(line, "%s %16s", "1", "2");
					Map<String, String> champion = new HashMap<String, String>();
					champion.put("points", output[0].toString());
					champion.put("date", output[1].toString());
					champions.add(i, champion);
					i++;
				}
			}

			/*
			for (Map<String, String> champion : champions) {
				System.out.println(champion.get("points")+" - "+champion.get("date"));
			}
			*/

		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	public void writeRecord() {

		try {

			Calendar c = new GregorianCalendar();
			BufferedWriter fout = null;
			int i = 0;
			boolean newRecord = false;

			FileHandle file = Gdx.files.local(CHAMPIONS);
			file.writeString("", false);

			String date = String.format(
				"%04d-%02d-%02d %02d:%02d",
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH) + 1,
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR) + (c.get(Calendar.AM_PM) == Calendar.PM ? 12 : 0),
				c.get(Calendar.MINUTE)
			);

			for (Map<String, String> champion : champions) {
				System.out.println("file: "+champion.get("points")+", game: "+points);

				if (Integer.parseInt(champion.get("points")) < points && newRecord == false) {
					currentRecord.put("points", String.valueOf(points));
					currentRecord.put("date", date);

					String str = String.format("%s %s\n", String.valueOf(points), date);
					file.writeString(str, true);
					newRecord = true;
					i++;
				}

				if (i < 5) {
					String str = String.format("%s %s\n", champion.get("points"), champion.get("date"));
					file.writeString(str, true);
					i++;
				}
			}

			if (i < 5 && newRecord == false) {
				currentRecord.put("points", String.valueOf(points));
				currentRecord.put("date", date);
				String str = String.format("%s %s\n", String.valueOf(points), date);
				file.writeString(str, true);
				newRecord = true;
				i++;
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
