/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.learn;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.decide.FleeTrainerDecider;

/**
 * @author nathaniel.morihara
 */
public class FleeTrainerLearner implements Learner {

  FleeTrainerDecider fleeTrainerDecider;

  public FleeTrainerLearner(
      FleeTrainerDecider fleeTrainerDecider) {
    this.fleeTrainerDecider = fleeTrainerDecider;
  }

  @Override
  public void learn(WorldState worldState) {

  }
}
