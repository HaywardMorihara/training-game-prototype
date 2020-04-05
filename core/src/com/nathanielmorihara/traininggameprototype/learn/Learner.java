/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.learn;

import com.nathanielmorihara.traininggameprototype.WorldState;

/**
 * @author nathaniel.morihara
 */
public interface Learner {

  void learn(WorldState worldState);
}
