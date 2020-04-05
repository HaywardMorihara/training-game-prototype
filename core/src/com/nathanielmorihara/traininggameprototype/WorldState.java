/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype;

import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class WorldState {

  PlayerModel playerModel;
  PenguinModel penguinModel;
  CherryModel cherryModel;

  public WorldState(PlayerModel playerModel,
      PenguinModel penguinModel,
      CherryModel cherryModel) {
    this.playerModel = playerModel;
    this.penguinModel = penguinModel;
    this.cherryModel = cherryModel;
  }

  public PlayerModel getPlayerModel() {
    return playerModel;
  }

  public void setPlayerModel(PlayerModel playerModel) {
    this.playerModel = playerModel;
  }

  public PenguinModel getPenguinModel() {
    return penguinModel;
  }

  public void setPenguinModel(PenguinModel penguinModel) {
    this.penguinModel = penguinModel;
  }

  public CherryModel getCherryModel() {
    return cherryModel;
  }

  public void setCherryModel(CherryModel cherryModel) {
    this.cherryModel = cherryModel;
  }
}
