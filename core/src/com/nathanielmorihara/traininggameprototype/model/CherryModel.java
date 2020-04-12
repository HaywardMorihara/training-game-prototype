/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nathanielmorihara.traininggameprototype.view.CherryView;

/**
 * @author nathaniel.morihara
 */
public class CherryModel extends Model {

  public float width;
  public float height;

  public Body body;

  String userData = "cherry";

  public CherryModel(World world, float scale, float x, float y) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(x, y);
    body = world.createBody(bodyDef);
    body.setUserData(userData);

    width = CherryView.FRAME_WIDTH * scale;
    height = CherryView.FRAME_HEIGHT * scale;

    // Create a circle shape and set its radius to 6
    CircleShape circle = new CircleShape();
    circle.setRadius(width / 2);

    // Create a fixture definition to apply our shape to
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = circle;

    body.createFixture(fixtureDef);

    // Remember to dispose of any shapes after you're done with them!
    // BodyDef and FixtureDef don't need disposing, but shapes do.
    circle.dispose();
  }
}
