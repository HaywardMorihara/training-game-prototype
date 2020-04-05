/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.learn;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.decide.FollowTrainerDecider;

/**
 * @author nathaniel.morihara
 */
public class FollowTrainerLearner implements Learner {

  FollowTrainerDecider followTrainerDecider;

  public FollowTrainerLearner(
      FollowTrainerDecider followTrainerDecider) {
    this.followTrainerDecider = followTrainerDecider;
  }

  @Override
  public void learn(WorldState worldState) {
    // TODO
  }
}
