/**
 * Copyright (C) 2020 Urban Compass, Inc.
 */
package com.nathanielmorihara.traininggameprototype.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;

/**
 * @author nathaniel.morihara
 */
public class OldPlayerView {

  // TODO Should these have a default?
  public static float FRAME_WIDTH, FRAME_HEIGHT;

  private static final int FRAME_COLS = 3, FRAME_ROWS = 3;
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
    walkSheet = new Texture(Gdx.files.internal("Trainer.png"));

    FRAME_WIDTH = walkSheet.getWidth() / FRAME_COLS;
    FRAME_HEIGHT = walkSheet.getHeight() / FRAME_ROWS;

    // Use the split utility method to create a 2D array of TextureRegions. This is
    // possible because this sprite sheet contains frames of equal size and they are
    // all aligned.
    TextureRegion[][] tmp = TextureRegion.split(walkSheet, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);

    still = tmp[0][1];

    walkDown = new TextureRegion[4];
    walkDown[0] = tmp[0][1];
    walkDown[1] = tmp[0][0];
    walkDown[2] = tmp[0][1];
    walkDown[3] = tmp[0][2];
    // Initialize the Animation with the frame interval and array of frames
    walkDownAnimation = new Animation<TextureRegion>(0.1f, walkDown);

    walkUp = new TextureRegion[4];
    walkUp[0] = tmp[1][1];
    walkUp[1] = tmp[1][0];
    walkUp[2] = tmp[1][1];
    walkUp[3] = tmp[1][2];
    walkUpAnimation = new Animation<TextureRegion>(0.1f, walkUp);

    walkLeft = new TextureRegion[4];
    walkLeft[0] = tmp[2][1];
    walkLeft[1] = tmp[2][0];
    walkLeft[2] = tmp[2][1];
    walkLeft[3] = tmp[2][2];
    walkLeftAnimation = new Animation<TextureRegion>(0.1f, walkLeft);

    walkRight = new TextureRegion[4];
    // Need to make copies for flipping
    walkRight[0] = new TextureRegion(walkSheet, (int) FRAME_WIDTH * 1, (int) FRAME_HEIGHT * 2, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);
    walkRight[1] = new TextureRegion(walkSheet, (int) FRAME_WIDTH * 2, (int) FRAME_HEIGHT * 2, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);
    walkRight[2] = new TextureRegion(walkSheet, (int) FRAME_WIDTH * 1, (int) FRAME_HEIGHT* 2, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);
    walkRight[3] = new TextureRegion(walkSheet, (int) FRAME_WIDTH * 0, (int) FRAME_HEIGHT* 2, (int) FRAME_WIDTH, (int) FRAME_HEIGHT);
    walkRight[0].flip(true, false);
    walkRight[1].flip(true, false);
    walkRight[2].flip(true, false);
    walkRight[3].flip(true, false);
    walkRightAnimation = new Animation<TextureRegion>(0.1f, walkRight);
  }

  public static void dispose() { // SpriteBatches and Textures must always be disposed
    walkSheet.dispose();
  }

  public void draw(SpriteBatch spriteBatch, float time, PlayerModel playerModel) {
    // Get current frame of animation for the current time
    // TODO Should be refactored
    TextureRegion currentFrame = still;
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      currentFrame = walkUpAnimation.getKeyFrame(time, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      currentFrame = walkDownAnimation.getKeyFrame(time, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      currentFrame = walkLeftAnimation.getKeyFrame(time, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      currentFrame = walkRightAnimation.getKeyFrame(time, true);
    }
    spriteBatch.draw(
        currentFrame,
        playerModel.body.getPosition().x - playerModel.width / 2,
        playerModel.body.getPosition().y - playerModel.height / 2,
        playerModel.width,
        playerModel.height);
  }
}
