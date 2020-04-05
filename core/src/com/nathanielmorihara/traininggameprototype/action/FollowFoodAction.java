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

    if (cherryModel.x > penguinModel.body.getPosition().x) {
      penguinModel.body.setLinearVelocity(penguinModel.speed, 0);
    }
    if (cherryModel.x < penguinModel.body.getPosition().x) {
      penguinModel.body.setLinearVelocity(-penguinModel.speed, 0);
    }
    if (cherryModel.y > penguinModel.body.getPosition().y) {
      penguinModel.body.setLinearVelocity(0, penguinModel.speed);
    }
    if (cherryModel.y < penguinModel.body.getPosition().y) {
      penguinModel.body.setLinearVelocity(0, -penguinModel.speed);
    }
  }

  @Override
  public float getWeight() {
    return weight;
  }
}
