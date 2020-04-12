/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.controller;

import java.util.HashSet;
import java.util.Set;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;
import com.nathanielmorihara.traininggameprototype.action.StillAction;
import com.nathanielmorihara.traininggameprototype.decide.Decider;
import com.nathanielmorihara.traininggameprototype.decide.FleeTrainerDecider;
import com.nathanielmorihara.traininggameprototype.decide.FollowFoodDecider;
import com.nathanielmorihara.traininggameprototype.decide.FollowTrainerDecider;
import com.nathanielmorihara.traininggameprototype.learn.FleeTrainerLearner;
import com.nathanielmorihara.traininggameprototype.learn.FollowTrainerLearner;
import com.nathanielmorihara.traininggameprototype.learn.Learner;

/**
 * @author nathaniel.morihara
 */
public class PenguinController {

  public FollowTrainerLearner followTrainerLearner;

  Set<Decider> deciders;


  public PenguinController() {
    FleeTrainerDecider fleeTrainerDecider = new FleeTrainerDecider();
    FollowTrainerDecider followTrainerDecider = new FollowTrainerDecider();
    FollowFoodDecider followFoodDecider = new FollowFoodDecider();

    deciders = new HashSet<>();
    deciders.add(fleeTrainerDecider);
    deciders.add(followTrainerDecider);
    deciders.add(followFoodDecider);

    followTrainerLearner = new FollowTrainerLearner(followTrainerDecider);
  }

  public void update(WorldState worldState) {
    Action nextAction = new StillAction();
    for (Decider decider : deciders) {
      Action potentialAction = decider.decide(worldState);
      if (potentialAction.getWeight() > nextAction.getWeight()) {
        nextAction = potentialAction;
      }
    }

    nextAction.act(worldState);
  }
}
