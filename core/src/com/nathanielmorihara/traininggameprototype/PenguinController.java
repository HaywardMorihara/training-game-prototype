/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype;

/**
 * @author nathaniel.morihara
 */
public class PenguinController {

  public void update(PenguinModel penguinModel, PlayerModel playerModel, CherryModel cherryModel) {
    // Learn (based on events that happend)
    // TODO 

    // Decide (based on inputs)
    boolean getFood = false;
    boolean fleeHuman = false;
    // Desire to get food (score based on distance and tendency)
    float getFoodScore = -penguinModel.body.getPosition().dst(cherryModel.x, cherryModel.y);
    // Desire to flee human (score based on distance and tendency)
    float fleeHumanScore = -penguinModel.body.getPosition().dst(playerModel.body.getPosition());
    // Highest score will yield the action
    if (getFoodScore > fleeHumanScore) {
      getFood = true;
    }
    if (fleeHumanScore > getFoodScore && Math.abs(fleeHumanScore) < 100) {
      fleeHuman = true;
    }

    // Act (based on decisions)
    penguinModel.body.setLinearVelocity(0, 0);
    if (fleeHuman) {
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
    if (getFood) {
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
  }

  private void basicBehavior(PenguinModel penguinModel, PlayerModel playerModel) {
    penguinModel.body.setLinearVelocity(0, 0);
    if (playerModel.body.getLinearVelocity().y > 0) {
      penguinModel.body.setLinearVelocity(0, penguinModel.speed);
    }
    if (playerModel.body.getLinearVelocity().y < 0) {
      penguinModel.body.setLinearVelocity(0, -penguinModel.speed);
    }
    if (playerModel.body.getLinearVelocity().x < 0) {
      penguinModel.body.setLinearVelocity(-penguinModel.speed, 0);
    }
    if (playerModel.body.getLinearVelocity().x > 0) {
      penguinModel.body.setLinearVelocity(penguinModel.speed, 0);
    }
  }
}
