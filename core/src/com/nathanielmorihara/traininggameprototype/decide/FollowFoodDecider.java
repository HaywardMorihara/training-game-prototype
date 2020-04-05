/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.FollowFoodAction;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;

/**
 * @author nathaniel.morihara
 */
public class FollowFoodDecider implements Decider {

  @Override
  public Action decide(WorldState worldState) {
    PenguinModel penguinModel = worldState.getPenguinModel();
    CherryModel cherryModel = worldState.getCherryModel();

    // Desire to get food (score based on distance and tendency)
    float cherryPenguinDistance = penguinModel.body.getPosition().dst(cherryModel.x, cherryModel.y);
    float followFoodScore = 1 / cherryPenguinDistance;
    return new FollowFoodAction(followFoodScore);
  }
}
