package com.nathanielmorihara.traininggameprototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.nathanielmorihara.traininggameprototype.controller.PenguinController;
import com.nathanielmorihara.traininggameprototype.controller.PlayerController;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;
import com.nathanielmorihara.traininggameprototype.view.CherryView;
import com.nathanielmorihara.traininggameprototype.view.PenguinView;
import com.nathanielmorihara.traininggameprototype.view.PlayerView;

public class TrainingGamePrototypeGame extends ApplicationAdapter {
	SpriteBatch batch;

	PlayerModel playerModel;
	PlayerView playerView;
	PlayerController playerController;

	PenguinModel penguinModel;
	PenguinView penguinView;
	PenguinController penguinController;

	CherryModel cherryModel;
	CherryView cherryView;

	private World world; // This just holds everything and manages physics. Nothing to do with the view
	private float time;

	@Override
	public void create () {
		time = 0f;
		Box2D.init();
		world = new World(new Vector2(0, 0), true);

		playerController = new PlayerController();
		playerView = new PlayerView();
		PlayerView.load();
		playerModel = new PlayerModel(world, 1, 150, 200);

		penguinController = new PenguinController();
		penguinView = new PenguinView();
		PenguinView.load();
		penguinModel = new PenguinModel(world, 1, 400, 200);

		CherryView.load();
		cherryView = new CherryView();
		cherryModel = new CherryModel(200, 200);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		WorldState worldState = new WorldState(playerModel, penguinModel, cherryModel);

		playerController.update(playerModel);
		penguinController.update(worldState);

		world.step(1/60f, 6, 2);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		playerView.draw(batch, time, playerModel);
		penguinView.draw(batch, time, penguinModel);
		cherryView.draw(batch, cherryModel);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		PlayerView.dispose();
		PenguinView.dispose();
	}
}
