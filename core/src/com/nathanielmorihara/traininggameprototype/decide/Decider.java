/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.decide;

import com.nathanielmorihara.traininggameprototype.WorldState;
import com.nathanielmorihara.traininggameprototype.action.Action;

/**
 * @author nathaniel.morihara
 */
public interface Decider {
  Action decide(WorldState worldState);
}
