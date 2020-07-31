package com.nathanielmorihara.traininggameprototype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadMapLogicTemp {

    // Example from https://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx

    TiledMap tiledMap;
    Viewport viewport;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    float height;
    float width;
    float unitScale;

    public void create () {

        tiledMap = new TmxMapLoader().load("MyFirstMap.tmx");

        MapProperties mapProperties = tiledMap.getProperties();
        Integer imageheight = (Integer) mapProperties.get("height");
        Integer imagewidth = (Integer) mapProperties.get("width");
        Integer tileheight = (Integer) mapProperties.get("tileheight");
        Integer tilewidth = (Integer) mapProperties.get("tilewidth");

        // TODO assert the tilewidth and tileheight are the same

        height = (float) imageheight;
        width = (float) imagewidth;
        unitScale = (float) 1 / tileheight;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, imagewidth, imageheight);
        viewport = new FitViewport(imagewidth, imageheight, camera);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render () {
        // Clear the screen
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.translate(1,1);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void resize (int w, int h) {
        this.viewport.update(w, h);
    }
}
