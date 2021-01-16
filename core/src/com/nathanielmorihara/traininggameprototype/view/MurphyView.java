/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nathanielmorihara.traininggameprototype.model.MurphyModel;

/**
 * @author nathaniel.morihara
 */
public class MurphyView {

  // TODO Should these have a default?
  public static float FRAME_WIDTH, FRAME_HEIGHT;

  private static final int FRAME_COLS = 6, FRAME_ROWS = 2;
  private static Texture walkSheet;
  private static TextureRegion[] walkDown;
  private static TextureRegion[] walkUp;
  private static TextureRegion[] walkLeft;
  private static TextureRegion[] walkRight;
  // Must declare frame type (TextureRegion)
  private static TextureRegion still;
  private static Animation<TextureRegion> walkDownAnimation;
  private static Animation<TextureRegion> walkUpAnimation;
  private static Animation<TextureRegion> walkLeftAnimation;
  private static Animation<TextureRegion> walkRightAnimation;

  public static void load() {
    // Load the sprite sheet as a Texture
    walkSheet = new Texture(Gdx.files.internal("PenguinSpritesheet.png"));

    FRAME_WIDTH = walkSheet.getWidth() / FRAME_COLS;
    FRAME_HEIGHT = walkSheet.getHeight() / FRAME_ROWS;

    // Use the split utility method to create a 2D array of TextureRegions. This is
    // possible because this sprite sheet contains frames of equal size and they are
    // all aligned.
    TextureRegion[][] tmp = TextureRegion.split(walkSheet, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);

    still = tmp[0][1];

    walkDown = new TextureRegion[4];
    walkDown[0] = tmp[0][0];
    walkDown[1] = tmp[0][1];
    walkDown[2] = tmp[0][0];
    walkDown[3] = tmp[0][2];
    // Initialize the Animation with the frame interval and array of frames
    walkDownAnimation = new Animation<TextureRegion>(0.1f, walkDown);

    walkUp = new TextureRegion[4];
    walkUp[0] = tmp[0][3];
    walkUp[1] = tmp[0][4];
    walkUp[2] = tmp[0][3];
    walkUp[3] = tmp[0][5];
    walkUpAnimation = new Animation<TextureRegion>(0.1f, walkUp);

    walkLeft = new TextureRegion[4];
    walkLeft[0] = tmp[1][3];
    walkLeft[1] = tmp[1][4];
    walkLeft[2] = tmp[1][3];
    walkLeft[3] = tmp[1][5];
    walkLeftAnimation = new Animation<TextureRegion>(0.1f, walkLeft);

    walkRight = new TextureRegion[4];
    walkRight[0] = tmp[1][0];
    walkRight[1] = tmp[1][1];
    walkRight[2] = tmp[1][0];
    walkRight[3] = tmp[1][2];
    walkRightAnimation = new Animation<TextureRegion>(0.1f, walkRight);
  }

  public static void dispose() { // SpriteBatches and Textures must always be disposed
    walkSheet.dispose();
  }

  public void draw(SpriteBatch spriteBatch, float time, MurphyModel murphyModel) {
    // Get current frame of animation for the current time
    // TODO Should be refactored
    TextureRegion currentFrame = still;
    if (murphyModel.body.getLinearVelocity().y > 0) {
      currentFrame = walkUpAnimation.getKeyFrame(time, true);
    }
    if (murphyModel.body.getLinearVelocity().y < 0) {
      currentFrame = walkDownAnimation.getKeyFrame(time, true);
    }
    if (murphyModel.body.getLinearVelocity().x < 0) {
      currentFrame = walkLeftAnimation.getKeyFrame(time, true);
    }
    if (murphyModel.body.getLinearVelocity().x > 0) {
      currentFrame = walkRightAnimation.getKeyFrame(time, true);
    }
    spriteBatch.draw(
        currentFrame,
        murphyModel.body.getPosition().x - murphyModel.width / 2,
        murphyModel.body.getPosition().y - murphyModel.height / 2,
        murphyModel.width,
        murphyModel.height);
  }
}
