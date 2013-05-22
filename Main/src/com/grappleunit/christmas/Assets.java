package com.grappleunit.christmas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class Assets {

	public static Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();
	public static Texture fon, back720x1280, back960x720, frame, pause240, pause180, elki, knopki, info;
	public static BitmapFont
		fontButton1, fontButton2, fontPoints1, fontPoints2,
		fontCell1, fontCell2, fontCell3, fontCell4, fontCell5,
		fontCell6, fontCell7, fontCell8, fontCell9;
	public static Sound
		newgameSound, resetSound, pauseSound,
		cellSound1, cellSound2, cellSound3, cellSound4,
		infoSound;
	public static Music forestMusic, completeSound;

	public static void load() {
		Texture.setEnforcePotImages(false);

		fon = new Texture(Gdx.files.internal("images/fon.png"));
		back720x1280 = new Texture(Gdx.files.internal("images/back720x1280.png"));
		back960x720 = new Texture(Gdx.files.internal("images/back960x720.png"));
		frame = new Texture(Gdx.files.internal("images/frame.png"));
		pause240 = new Texture(Gdx.files.internal("images/pause240.png"));
		pause180 = new Texture(Gdx.files.internal("images/pause180.png"));
		elki = new Texture(Gdx.files.internal("images/elki.png"));
		knopki = new Texture(Gdx.files.internal("images/knopki.png"));
		info = new Texture(Gdx.files.internal("images/info.png"));

		fontButton1 = new BitmapFont(Gdx.files.internal("fonts/lazy-32.fnt"), false);
		fontButton2 = new BitmapFont(Gdx.files.internal("fonts/lazy-24.fnt"), false);
		fontPoints1 = new BitmapFont(Gdx.files.internal("fonts/chiller-64.fnt"), false);
		fontPoints2 = new BitmapFont(Gdx.files.internal("fonts/chiller-36.fnt"), false);
		fontCell1 = new BitmapFont(Gdx.files.internal("fonts/chiller-128.fnt"), false);
		fontCell2 = new BitmapFont(Gdx.files.internal("fonts/chiller-78.fnt"), false);
		fontCell3 = new BitmapFont(Gdx.files.internal("fonts/chiller-68.fnt"), false);
		fontCell4 = new BitmapFont(Gdx.files.internal("fonts/chiller-56.fnt"), false);
		fontCell5 = new BitmapFont(Gdx.files.internal("fonts/chiller-48.fnt"), false);
		fontCell6 = new BitmapFont(Gdx.files.internal("fonts/chiller-40.fnt"), false);
		fontCell7 = new BitmapFont(Gdx.files.internal("fonts/chiller-32.fnt"), false);
		fontCell8 = new BitmapFont(Gdx.files.internal("fonts/chiller-24.fnt"), false);
		fontCell9 = new BitmapFont(Gdx.files.internal("fonts/chiller-18.fnt"), false);

		forestMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/forest.mp3"));
		completeSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/complete.mp3"));
		cellSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/click1.wav"));
		cellSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/click2.wav"));
		cellSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/click3.wav"));
		cellSound4 = Gdx.audio.newSound(Gdx.files.internal("sounds/click4.wav"));
		newgameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/newgame.wav"));
		resetSound = Gdx.audio.newSound(Gdx.files.internal("sounds/reset.wav"));
		pauseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pause.wav"));
		infoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/info.wav"));

		TextureRegion tmp[][] = TextureRegion.split(elki, elki.getWidth() / 4, elki.getHeight() / 3);

		textureRegions.put("red1", tmp[0][0]);
		textureRegions.put("red2", tmp[0][1]);
		textureRegions.put("red3", tmp[0][2]);
		textureRegions.put("red4", tmp[0][3]);
		textureRegions.put("green1", tmp[1][0]);
		textureRegions.put("green2", tmp[1][1]);
		textureRegions.put("green3", tmp[1][2]);
		textureRegions.put("green4", tmp[1][3]);
		textureRegions.put("number1", tmp[2][0]);
		textureRegions.put("number2", tmp[2][1]);
		textureRegions.put("number3", tmp[2][2]);
		textureRegions.put("number4", tmp[2][3]);

		TextureRegion tmp2[][] = TextureRegion.split(knopki, knopki.getWidth() / 4, knopki.getHeight() / 3);

		textureRegions.put("sound", tmp2[0][0]);
		textureRegions.put("info", tmp2[0][1]);
		textureRegions.put("close", tmp2[0][2]);
		textureRegions.put("champions", tmp2[0][3]);
		textureRegions.put("soundOver", tmp2[1][0]);
		textureRegions.put("infoOver", tmp2[1][1]);
		textureRegions.put("closeOver", tmp2[1][2]);
		textureRegions.put("championsOver", tmp2[1][3]);
		textureRegions.put("soundOff", tmp2[2][0]);
		textureRegions.put("soundOffOver", tmp2[2][1]);
	}
}
