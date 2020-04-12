/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * @author nathaniel.morihara
 */
public class ContactListenerImpl implements ContactListener {

  WorldState worldState;

  public ContactListenerImpl(WorldState worldState) {
    this.worldState = worldState;
  }

  @Override
  public void beginContact(Contact contact) {

  }

  @Override
  public void endContact(Contact contact) {
    Fixture fixtureA = contact.getFixtureA();
    Fixture fixtureB = contact.getFixtureB();

    List<Body> bodiesToDestroy = new LinkedList<>();

    if (fixtureA.getBody().getUserData().equals("penguin") && fixtureB.getBody().getUserData().equals("cherry")) {
      if (!worldState.getBodiesToDestroy().contains(fixtureB.getBody())) {
        bodiesToDestroy.add(fixtureB.getBody());
      }
    }
    if (fixtureA.getBody().getUserData().equals("cherry") && fixtureB.getBody().getUserData().equals("penguin")) {
      if (!worldState.getBodiesToDestroy().contains(fixtureA.getBody())) {
        bodiesToDestroy.add(fixtureA.getBody());
      }
    }

    if (bodiesToDestroy.size() > 0) {
      worldState.addBodiesToDestory(bodiesToDestroy);
    }
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {

  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {

  }
}
