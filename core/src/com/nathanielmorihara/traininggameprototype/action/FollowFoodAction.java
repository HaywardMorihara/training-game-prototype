/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.MurphyModel;

/**
 * @author nathaniel.morihara
 */
public class FollowFoodAction implements Action {

  float weight;

  public FollowFoodAction(float weight) {
    this.weight = weight;
  }

  @Override
  public void act(WorldState worldState) {
    MurphyModel murphyModel = worldState.getMurphyModel();
    CherryModel cherryModel = worldState.getCherryModel();

    float linearVelocityX = 0;
    float linearVelocityY = 0;

    // TODO Refactor, diagonals shouldn't be faster
    if (cherryModel.body.getPosition().x > murphyModel.body.getPosition().x) {
      linearVelocityX = murphyModel.speed;
    }
    if (cherryModel.body.getPosition().x < murphyModel.body.getPosition().x) {
      linearVelocityX = -murphyModel.speed;
    }
    if (cherryModel.body.getPosition().y > murphyModel.body.getPosition().y) {
      linearVelocityY = murphyModel.speed;
    }
    if (cherryModel.body.getPosition().y < murphyModel.body.getPosition().y) {
      linearVelocityY = -murphyModel.speed;
    }

    murphyModel.body.setLinearVelocity(linearVelocityX, linearVelocityY);
  }

  @Override
  public float getWeight() {
    return weight;
  }
}
