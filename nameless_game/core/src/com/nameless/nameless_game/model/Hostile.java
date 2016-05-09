package com.nameless.nameless_game.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nameless.nameless_game.render.ScreenRenderer;

/**
 * The entity model for the hostile entities in the game world.
 * 
 * @author Isaac Arvestad, Henrik Lagebrand
 * @version 2015-05-06
 *
 */
public class Hostile extends Entity {

	private Random random = new Random(); // TODO Use LibGDX own random.

	public Hostile(float x, float y, float width, float height, Texture texture, World world) {
		super(texture);

		body = createDynamicBody(ScreenRenderer.pixelToMeter(x), ScreenRenderer.pixelToMeter(y),
				ScreenRenderer.pixelToMeter(width), ScreenRenderer.pixelToMeter(height), world);
	}
	
	/**
	 * The empty constructor.
	 */
	public Hostile(){
		// Intentionally left empty.
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		float impulseX = ((float) random.nextInt(2000) - 1000) / 50000;
		float impulseY = ((float) random.nextInt(2000) - 1000) / 50000;

		body.applyLinearImpulse(new Vector2(impulseX, impulseY), body.getLocalCenter(), true);
	}

	/**
	 * createBody creates a rectangular, dynamic physics body, adds it to the
	 * physics world and returns it.
	 * 
	 * @param x
	 *            The x center of the body in physics meters.
	 * @param y
	 *            The y center of the body in physics meters.
	 * @param width
	 *            The width of the body in physics meters.
	 * @param height
	 *            The height of the body in physics meters.
	 * @param world
	 *            the world to add the body to
	 * @return the physics body
	 */
	private static Body createDynamicBody(float x, float y, float width, float height, World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = false;

		Body physicsBody = world.createBody(bodyDef);

		PolygonShape rectShape = new PolygonShape();
		rectShape.setAsBox(width / 2, height / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectShape;
		fixtureDef.density = 10f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		fixtureDef.filter.categoryBits = Entity.NPC_ENTITY;
		fixtureDef.filter.maskBits = Entity.PLAYER_ENTITY | Entity.NPC_ENTITY;
		
		physicsBody.createFixture(fixtureDef);

		rectShape.dispose(); // openGL

		return physicsBody;
	}
}
