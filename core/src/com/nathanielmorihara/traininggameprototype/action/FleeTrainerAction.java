/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class FleeTrainerAction implements Action {

  float weight;

  public FleeTrainerAction(float weight) {
    this.weight = weight;
  }

  @Override
  public void act(WorldState worldState) {
    PlayerModel playerModel = worldState.getPlayerModel();
    PenguinModel penguinModel = worldState.getPenguinModel();

    if (playerModel.body.getPosition().x > penguinModel.body.getPosition().x) {
      penguinModel.body.setLinearVelocity(-penguinModel.speed, 0);
    }
    if (playerModel.body.getPosition().x < penguinModel.body.getPosition().x) {
      penguinModel.body.setLinearVelocity(penguinModel.speed, 0);
    }
    if (playerModel.body.getPosition().y > penguinModel.body.getPosition().y) {
      penguinModel.body.setLinearVelocity(0, -penguinModel.speed);
    }
    if (playerModel.body.getPosition().y < penguinModel.body.getPosition().y) {
      penguinModel.body.setLinearVelocity(0, penguinModel.speed);
    }
  }

  @Override
  public float getWeight() {
    return weight;
  }
}
