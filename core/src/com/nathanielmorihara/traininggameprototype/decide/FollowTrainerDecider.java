/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.FollowTrainerAction;
import com.nathanielmorihara.traininggameprototype.model.MurphyModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class FollowTrainerDecider implements Decider {

  // TODO
  @Override
  public Action decide(WorldState worldState) {
    MurphyModel murphyModel = worldState.getMurphyModel();
    PlayerModel playerModel = worldState.getPlayerModel();

    // Desire to get food (score based on distance and tendency)
    float followTrainerScore = -murphyModel.body.getPosition().dst(playerModel.body.getPosition());
    return new FollowTrainerAction(followTrainerScore);
  }
}
