package com.nameless.nameless_game.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.nameless.nameless_game.render.ScreenRenderer;

/**
 * PanicHostileWithTarget is a hostile which moves slowly and randomly around
 * the map. If the hostile is within a certain range of the target however, it
 * will panic and move fast in a random direction.
 * 
 * @author Isaac Arvestad
 * @version 2016-05-11
 */
public class PanicHostileWithTarget extends HostileWithTarget {

	private Random random = new Random();

	private float panicDistance;

	public PanicHostileWithTarget(float x, float y, float width, float height, Texture texture, World world, Entity target) {
		super(x, y, width, height, texture, world);
		
		this.target = target;
		panicDistance = ScreenRenderer.pixelToMeter(200);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		double dx = body.getPosition().x - target.getBody().getPosition().x;
		double dy = body.getPosition().y - target.getBody().getPosition().y;
		float distance = (float) Math.sqrt(dx * dx + dy * dy);

		if (distance < panicDistance) {
			float x = (float) (random.nextInt(2000) - 1000) / 500f;
			float y = (float) (random.nextInt(2000) - 1000) / 500f;
			body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
		} else {
			float x = (float) (random.nextInt(2000) - 1000) / 5000f;
			float y = (float) (random.nextInt(2000) - 1000) / 5000f;
			body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
		}
	}
}