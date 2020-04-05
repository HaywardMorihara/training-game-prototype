/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.FleeTrainerAction;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class FleeTrainerDecider implements Decider {

  @Override
  public Action decide(WorldState worldState) {
    PlayerModel playerModel = worldState.getPlayerModel();
    PenguinModel penguinModel = worldState.getPenguinModel();

    float playerPenguinDistance = penguinModel.body.getPosition().dst(playerModel.body.getPosition());
    float fleeTrainerScore = 0;
    if (playerPenguinDistance > 100) {
      fleeTrainerScore = 0;
    } else {
      fleeTrainerScore = 1 / playerPenguinDistance;
    }
    return new FleeTrainerAction(fleeTrainerScore);
  }
}
