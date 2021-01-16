/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;

/**
 * @author nathaniel.morihara
 */
public interface Action {

  void act(WorldState worldState);
  float getWeight();
}
