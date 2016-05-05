package com.nameless.nameless_game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Entity describes a base entity which has a physics body and a texture for
 * rendering.
 * 
 * @author Isaac Arvestad, Henrik Lagebrand
 * @version 2016-05-04
 */
public class Entity {
	protected Body body;
	protected Texture texture;

	/**
	 * Creates an entity with a static physics body and a texture.
	 * 
	 * @param x
	 *            Center on x-axis
	 * @param y
	 *            Center on y-axis
	 * @param width
	 * @param height
	 * @param texture
	 *            Texture to use.
	 * @param world
	 *            Physics world to add body to.
	 */
	public Entity(float x, float y, float width, float height, Texture texture, World world) {
		body = createStaticBody(x, y, width, height, world);

		this.texture = texture;
	}

	/**
	 * Creates an entity with a texture.
	 * 
	 * @param texture
	 *            The texture.
	 */
	public Entity(Texture texture) {
		this.texture = texture;
	}

	/**
	 * createStaticBody creates a rectangular, static physics body, adds it to the
	 * physics world and returns it.
	 * 
	 * @param x
	 *            the x center of the body
	 * @param y
	 *            the y center of the body
	 * @param width
	 *            the width of the body
	 * @param height
	 *            the height of the body
	 * @param world
	 *            the world to add the body to
	 * @return the physics body
	 */
	private Body createStaticBody(float x, float y, float width, float height, World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);

		Body physicsBody = world.createBody(bodyDef);

		PolygonShape rectShape = new PolygonShape();
		rectShape.setAsBox(width / 2, height / 2);

		physicsBody.createFixture(rectShape, 0.0f);

		rectShape.dispose(); // no idea

		return physicsBody;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
