/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nathanielmorihara.traininggameprototype.view.PlayerView;

/**
 * @author nathaniel.morihara
 */
public class PlayerModel extends Model {

  // TODO Play around with these values
  public static final float unscaledSpeed = 100f;
  public static final float density = 0.5f;
  public static final float friction = 0.4f;
  public static final float restitution = 0.6f;

  public float width;
  public float height;
  public float speed;

  public Body body;

  String userData = "player";

  public PlayerModel(World world, float scale, float x, float y) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x, y); // TODO Why doesn't this need to be scaled?
    body = world.createBody(bodyDef);
    body.setUserData(userData);

    // TODO This is some coupling that probably shouldn't be there?
    width = PlayerView.FRAME_WIDTH * scale;
    height = PlayerView.FRAME_HEIGHT * scale;
    speed = unscaledSpeed * scale; // TODO Should I be scaling this? Or just make it a smaller number...

    // Create a circle shape and set its radius to 6
    CircleShape circle = new CircleShape();
    circle.setRadius(width / 2);

    // Create a fixture definition to apply our shape to
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = circle;
    fixtureDef.density = density;
    fixtureDef.friction = friction;
    fixtureDef.restitution = restitution;

    body.createFixture(fixtureDef);

    // Remember to dispose of any shapes after you're done with them!
    // BodyDef and FixtureDef don't need disposing, but shapes do.
    circle.dispose();
  }
}
