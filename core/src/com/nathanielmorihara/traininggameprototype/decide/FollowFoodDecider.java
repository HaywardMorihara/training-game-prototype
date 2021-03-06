/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.FollowFoodAction;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.MurphyModel;

/**
 * @author nathaniel.morihara
 */
public class FollowFoodDecider implements Decider {

  @Override
  public Action decide(WorldState worldState) {
    MurphyModel murphyModel = worldState.getMurphyModel();
    CherryModel cherryModel = worldState.getCherryModel();

    if (cherryModel == null) {
      return new FollowFoodAction(0);
    }

    // Desire to get food (score based on distance and tendency)
    float cherryPenguinDistance = murphyModel.body.getPosition().dst(cherryModel.body.getPosition());
    float followFoodScore = 1 / cherryPenguinDistance;
    return new FollowFoodAction(followFoodScore);
  }
}
