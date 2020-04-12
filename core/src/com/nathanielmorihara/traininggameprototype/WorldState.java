/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.Model;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class WorldState {

  World world;
  PlayerModel playerModel;
  PenguinModel penguinModel;
  CherryModel cherryModel;

  List<Body> bodiesToDestroy;

  public WorldState(World world,
      PlayerModel playerModel,
      PenguinModel penguinModel,
      CherryModel cherryModel) {
    this.world = world;
    this.playerModel = playerModel;
    this.penguinModel = penguinModel;
    this.cherryModel = cherryModel;

    this.bodiesToDestroy = new LinkedList<>();
  }


  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
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

  public List<Body> getBodiesToDestroy() {
    return bodiesToDestroy;
  }

  public void addBodiesToDestory(List<Body> bodiesToDestroy) {
    this.bodiesToDestroy.addAll(bodiesToDestroy);
  }

  public void clearBodiesToDestroy() {
    this.bodiesToDestroy.clear();
  }
}
