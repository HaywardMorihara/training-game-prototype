/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * @author nathaniel.morihara
 */
public class PlayerController {

  public void update(PlayerModel playerModel) {
    // TODO Refactor
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      playerModel.body.setLinearVelocity(0, playerModel.speed);
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      playerModel.body.setLinearVelocity(0, -playerModel.speed);
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      playerModel.body.setLinearVelocity(-playerModel.speed, 0);
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      playerModel.body.setLinearVelocity(playerModel.speed, 0);
    } else {
      playerModel.body.setLinearVelocity(0,0);
    }
  }
}
