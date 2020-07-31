package com.nathanielmorihara.traininggameprototype;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nathanielmorihara.traininggameprototype.controller.PenguinController;
import com.nathanielmorihara.traininggameprototype.controller.PlayerController;
import com.nathanielmorihara.traininggameprototype.model.PenguinModel;
import com.nathanielmorihara.traininggameprototype.model.PlayerModel;
import com.nathanielmorihara.traininggameprototype.view.CherryView;
import com.nathanielmorihara.traininggameprototype.view.PlayerView;
import com.nathanielmorihara.traininggameprototype.view.PenguinView;

// TODO https://expertise.jetruby.com/creating-android-game-from-scratch-with-libgdx-box2d-45c381d84268
// TODO https://riptutorial.com/libgdx/example/17831/create-box2d-bodies-from-tiled-map

public class TrainingGamePrototypeGame extends ApplicationAdapter {


	PlayerController playerController;
	PlayerView playerView;

//	PenguinView penguinView;
//	PenguinController penguinController;
//
//	CherryView cherryView;

	TiledMap tiledMap;
	private World world; // This just holds everything and manages physics. Nothing to do with the view
	private float time;
	WorldState worldState; // Storing objects in the world at a given point


	OrthographicCamera camera;
	Viewport viewport;
	TiledMapRenderer tiledMapRenderer;
	Box2DDebugRenderer debugRenderer;
	SpriteBatch batch;

	float unitScale;

	@Override
	public void create () {
	    debugRenderer = new Box2DDebugRenderer();

		tiledMap = new TmxMapLoader().load("MyFirstMap.tmx");

		MapProperties mapProperties = tiledMap.getProperties();
		Integer mapHeight = (Integer) mapProperties.get("height");
		Integer mapWidth = (Integer) mapProperties.get("width");
		Integer tileHeight = (Integer) mapProperties.get("tileheight");
		Integer tileWidth = (Integer) mapProperties.get("tilewidth");
		// TODO Assert tiled heigh and width as expected
		unitScale = (float) 1 / tileHeight;

		camera = new OrthographicCamera();
		float viewportWidthTiles = 32;
		float viewportHeightTiles = 24;
		viewport = new FitViewport(viewportWidthTiles, viewportHeightTiles, camera);

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

		time = 0f;
		Box2D.init();
		world = new World(new Vector2(0, 0), true);

		// TODO Don't allow player on water
		// TODO Could optimize by only making Box2D objects for border tiles?
		// TODO Tiled map animations?

		MapObjects objects = tiledMap.getLayers().get(1).getObjects();
		float playerX = 0;
		float playerY = 0;
		for (MapObject object: objects) {
			RectangleMapObject point = (RectangleMapObject) object;
			playerX = point.getRectangle().x * unitScale;
			playerY = point.getRectangle().y * unitScale;
		}

		playerController = new PlayerController();
		PlayerView.load();
		playerView = new PlayerView();
		// TODO This has to come after the view code, because it's based on the sprite or something...should change that
		PlayerModel playerModel = new PlayerModel(world, unitScale, playerX, playerY);

//		penguinController = new PenguinController();
//		penguinView = new PenguinView();
//		PenguinView.load();
//		PenguinModel penguinModel = new PenguinModel(world, 1, 400, 200);

//		CherryView.load();
//		cherryView = new CherryView();

		worldState = new WorldState(world, playerModel, null, null);
		world.setContactListener(new ContactListenerImpl(worldState));

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		playerController.update(worldState);

//		penguinController.update(worldState);
//
//		// TODO There's some weird concurrency stuff going on here that should probably get fixed
//		List<Body> bodiesToDestroy = new LinkedList<>(worldState.getBodiesToDestroy());
//		Iterator<Body> iterator = bodiesToDestroy.iterator();
//		while (iterator.hasNext()) {
//			Body body = iterator.next();
//			if (body.getUserData().equals("cherry")) {
//				// TODO This really should be in the contactlistener or osmething
//				penguinController.followTrainerLearner.learn(worldState);
//
//				world.destroyBody(body);
//				worldState.setCherryModel(null);
//			}
//		}
//		worldState.clearBodiesToDestroy();

		world.step(1/60f, 6, 2);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = worldState.playerModel.body.getPosition().x;
        camera.position.y = worldState.playerModel.body.getPosition().y;
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.begin();
		playerView.draw(batch, camera, viewport, time, worldState.getPlayerModel());
//		penguinView.draw(batch, time, worldState.getPenguinModel());
//		if (worldState.getCherryModel() != null) {
//			cherryView.draw(batch, worldState.getCherryModel());
//		}
		batch.end();

        // Create a copy of camera projection matrix
        Matrix4 debugMatrix;
        debugMatrix=new Matrix4(camera.combined);
        //Scale it by 100 as our box physics bodies are scaled down by 100
        debugMatrix.scale(1f, 100f, 1f);
        debugRenderer.render(world, camera.combined);
	}

	@Override
	public void resize (int width, int height) {
		this.viewport.update(width, height);
		this.camera.update();
	}

	@Override
	public void dispose () {
		batch.dispose();
		PlayerView.dispose();
		PenguinView.dispose();
		debugRenderer.dispose();
	}
}
