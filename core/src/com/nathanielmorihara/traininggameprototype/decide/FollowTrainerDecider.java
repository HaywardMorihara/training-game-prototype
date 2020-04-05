/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.FollowTrainerAction;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class FollowTrainerDecider implements Decider {

  @Override
  public Action decide(WorldState worldState) {
    PenguinModel penguinModel = worldState.getPenguinModel();
    PlayerModel playerModel = worldState.getPlayerModel();

    // Desire to get food (score based on distance and tendency)
    float followTrainerScore = -penguinModel.body.getPosition().dst(playerModel.body.getPosition());
    return new FollowTrainerAction(followTrainerScore);
  }
}
