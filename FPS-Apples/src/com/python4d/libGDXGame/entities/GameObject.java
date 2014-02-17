package com.python4d.libGDXGame.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public interface  GameObject {

		public void render(float delta, OrthographicCamera camera, SpriteBatch batch);

		void dispose();
}
