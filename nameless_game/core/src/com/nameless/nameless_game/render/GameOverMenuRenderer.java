package com.nameless.nameless_game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Renders the game over menu.
 * 
 * @author Henrik Lagebrand, Isaac Arvestad
 * @version 2016-05-24
 * 
 */
public class GameOverMenuRenderer {

	private SpriteBatch batch;
	private BitmapFont font;
	private Texture background;

	/**
	 * Creates a new GameOverMenuRenderer.
	 */
	public GameOverMenuRenderer() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		background = new Texture(Gdx.files.local("simple_game_over_menu.png"));
	}

	/**
	 * Renders the game over menu with the score of the most recently played
	 * game.
	 */
	public void render(int score) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		String gameOverMessage;
		if (score == 1) {
			gameOverMessage = "You got " + score + " point!";
		} else {
			gameOverMessage = "You got " + score + " points!";
		}

		batch.begin();
		batch.draw(background, 0, 0);
		font.draw(batch, gameOverMessage, Gdx.graphics.getWidth() / 2 - 50,
				Gdx.graphics.getHeight() / 2);
		batch.end();
	}
}
