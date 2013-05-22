package com.grappleunit.christmas.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grappleunit.christmas.Assets;

import java.util.List;
import java.util.Map;

public class Champions extends Actor {

	World world;
	BitmapFont font;
	boolean touched = false;

	public Champions(World world) {
		this.world = world;
	}

	public void setRegion() {
		if (world.cameraAspectRatio > 1) {
			setX(world.paddingX + 26f * world.ppuX);
			setY(world.paddingY + 26f * world.ppuY);
		} else {
			setX(world.paddingX + 26f * world.ppuX);
			setY(world.paddingY + 308f * world.ppuY);
		}

		setWidth(662f * world.ppuX);
		setHeight(662f * world.ppuY);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (world.championsOn == true) {
			List<Map<String, String>> champions = world.getChampions();
			Map<String, String> currentRecord = world.getCurrentRecord();
			int i = 0;
			boolean myRecord = false;

			font = Assets.fontPoints1;

			font.setColor(253f / 255, 250f / 255, 0f / 255, 1);

			String title = "DOCKA PEKOPDOB";
			float w0 = font.getBounds(title).width;
			font.draw(batch, title, getX() + (getWidth() - w0) / 2f, getY() + getHeight());

			for (Map<String, String> champion : champions) {

				if (
					currentRecord.get("points") != null
					&& Integer.parseInt(champion.get("points")) < Integer.parseInt(currentRecord.get("points"))
					&& myRecord == false
				) {
					font.setColor(255 / 255, 0 / 255, 0 / 255, 1);

					String str1 = String.format("%05d", Integer.parseInt(currentRecord.get("points")));
					String str2 = " ... "+currentRecord.get("date");

					float w1 = font.getBounds(str1).width;
					float w2 = font.getBounds(" ... 2013-05-13 13:13").width;
					float h = font.getBounds(str2).height;

					font.draw(batch, str1, getX() + getWidth() - w2 - w1 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);
					font.draw(batch, str2, getX() + getWidth() - w2 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);

					myRecord = true;
					i++;
				}

				if (i < 5) {
					font.setColor(255 / 255, 255 / 255, 255 / 255, 1);

					String str1 = String.format("%05d", Integer.parseInt(champion.get("points")));
					String str2 = " ... "+champion.get("date");

					float w1 = font.getBounds(str1).width;
					float w2 = font.getBounds(" ... 2013-05-13 13:13").width;
					float h = font.getBounds(str2).height;

					font.draw(batch, str1, getX() + getWidth() - w2 - w1 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);
					font.draw(batch, str2, getX() + getWidth() - w2 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);

					i++;
				}
			}

			if (i < 5 && myRecord == false && currentRecord.get("points") != null) {
				font.setColor(1, 0, 0, 1);

				String str1 = String.format("%05d", Integer.parseInt(currentRecord.get("points")));
				String str2 = " ... "+currentRecord.get("date");

				float w1 = font.getBounds(str1).width;
				float w2 = font.getBounds(" ... 2013-05-13 13:13").width;
				float h = font.getBounds(str2).height;

				font.draw(batch, str1, getX() + getWidth() - w2 - w1 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);
				font.draw(batch, str2, getX() + getWidth() - w2 - 30f * world.ppuX, getY() + getHeight() - (i + 1) * h * 2);

				myRecord = true;
				i++;
			}
		}
	}

}
