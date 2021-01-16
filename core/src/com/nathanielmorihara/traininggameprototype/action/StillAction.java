/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.action;

import com.nathanielmorihara.traininggameprototype.WorldState;

/**
 * @author nathaniel.morihara
 */
public class StillAction implements Action {

  @Override
  public void act(WorldState worldState) {
   worldState.getMurphyModel().body.setLinearVelocity(0, 0);
  }

  @Override
  public float getWeight() {
    return 0;
  }
}
