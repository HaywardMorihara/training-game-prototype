package com.nathanielmorihara.traininggameprototype;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.nathanielmorihara.traininggameprototype.controller.PenguinController;
import com.nathanielmorihara.traininggameprototype.controller.PlayerController;
import com.nathanielmorihara.traininggameprototype.model.CherryModel;
import com.nathanielmorihara.traininggameprototype.model.Model;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;
import com.nathanielmorihara.traininggameprototype.view.CherryView;
import com.nathanielmorihara.traininggameprototype.view.PenguinView;
import com.nathanielmorihara.traininggameprototype.view.PlayerView;

public class TrainingGamePrototypeGame extends ApplicationAdapter {
	SpriteBatch batch;

	PlayerView playerView;
	PlayerController playerController;

	PenguinView penguinView;
	PenguinController penguinController;

	CherryView cherryView;

	private World world; // This just holds everything and manages physics. Nothing to do with the view
	private float time;

	WorldState worldState;

	@Override
	public void create () {
		time = 0f;
		Box2D.init();
		world = new World(new Vector2(0, 0), true);

		playerController = new PlayerController();
		playerView = new PlayerView();
		PlayerView.load();
		PlayerModel playerModel = new PlayerModel(world, 1, 150, 200);

		penguinController = new PenguinController();
		penguinView = new PenguinView();
		PenguinView.load();
		PenguinModel penguinModel = new PenguinModel(world, 1, 400, 200);

		CherryView.load();
		cherryView = new CherryView();

		worldState = new WorldState(world, playerModel, penguinModel, null);
		world.setContactListener(new ContactListenerImpl(worldState));

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		playerController.update(worldState);
		penguinController.update(worldState);

		// TODO There's some weird concurrency stuff going on here that should probably get fixed
		List<Body> bodiesToDestroy = new LinkedList<>(worldState.getBodiesToDestroy());
		Iterator<Body> iterator = bodiesToDestroy.iterator();
		while (iterator.hasNext()) {
			Body body = iterator.next();
			if (body.getUserData().equals("cherry")) {
				world.destroyBody(body);
				worldState.setCherryModel(null);
			}
		}
		worldState.clearBodiesToDestroy();

		world.step(1/60f, 6, 2);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		playerView.draw(batch, time, worldState.getPlayerModel());
		penguinView.draw(batch, time, worldState.getPenguinModel());
		if (worldState.getCherryModel() != null) {
			cherryView.draw(batch, worldState.getCherryModel());
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		PlayerView.dispose();
		PenguinView.dispose();
	}
}