/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;

/**
 * @author nathaniel.morihara
 */
public class CherryView {

  static Texture img;

  public static void load() {
    img = new Texture("Cherry.png");
  }

  public void draw (SpriteBatch spriteBatch, CherryModel cherryModel) {
    spriteBatch.draw(img, cherryModel.x, cherryModel.y);
  }
}
