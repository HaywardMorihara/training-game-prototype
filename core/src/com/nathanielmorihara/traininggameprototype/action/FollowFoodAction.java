/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;

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
    PenguinModel penguinModel = worldState.getPenguinModel();
    CherryModel cherryModel = worldState.getCherryModel();

    float linearVelocityX = 0;
    float linearVelocityY = 0;

    // TODO Refactor, diagonals shouldn't be faster
    if (cherryModel.body.getPosition().x > penguinModel.body.getPosition().x) {
      linearVelocityX = penguinModel.speed;
    }
    if (cherryModel.body.getPosition().x < penguinModel.body.getPosition().x) {
      linearVelocityX = -penguinModel.speed;
    }
    if (cherryModel.body.getPosition().y > penguinModel.body.getPosition().y) {
      linearVelocityY = penguinModel.speed;
    }
    if (cherryModel.body.getPosition().y < penguinModel.body.getPosition().y) {
      linearVelocityY = -penguinModel.speed;
    }

    penguinModel.body.setLinearVelocity(linearVelocityX, linearVelocityY);
  }

  @Override
  public float getWeight() {
    return weight;
  }
}
