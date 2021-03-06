/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class PlayerController {

  public void update(WorldState worldState) {
    PlayerModel playerModel = worldState.getPlayerModel();

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

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && worldState.getCherryModel() == null) {
      worldState.setCherryModel(new CherryModel(worldState.getWorld(), 1, playerModel.body.getPosition().x, playerModel.body.getPosition().y));
    }
  }
}
