/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.model.MurphyModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class FollowTrainerAction implements Action {

  float weight;

  public FollowTrainerAction(float weight) {
    this.weight = weight;
  }

  @Override
  public void act(WorldState worldState) {
    PlayerModel playerModel = worldState.getPlayerModel();
    MurphyModel murphyModel = worldState.getMurphyModel();

    if (playerModel.body.getPosition().x > murphyModel.body.getPosition().x) {
      murphyModel.body.setLinearVelocity(murphyModel.speed, 0);
    }
    if (playerModel.body.getPosition().x < murphyModel.body.getPosition().x) {
      murphyModel.body.setLinearVelocity(-murphyModel.speed, 0);
    }
    if (playerModel.body.getPosition().y > murphyModel.body.getPosition().y) {
      murphyModel.body.setLinearVelocity(0, murphyModel.speed);
    }
    if (playerModel.body.getPosition().y < murphyModel.body.getPosition().y) {
      murphyModel.body.setLinearVelocity(0, -murphyModel.speed);
    }
  }

  @Override
  public float getWeight() {
    return weight;
  }
}
