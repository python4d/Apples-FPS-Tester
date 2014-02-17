package com.python4d.libGDXGame.utils.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.entities.GameObject;
import com.python4d.libGDXGame.gui.GuiInGame;

public class Region implements GameObject{
	
	public static int nextId = 0;
	private int id;
	private GuiInGame gui;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private int posX=0;
	private int mapPixelWidth;
	private int mapPixelHeight;
	
	public Region( GuiInGame gui) {
		this.id = nextId++;
		this.gui = gui;
		//utilisation de TILED pour faire une map
		this.map = new TmxMapLoader().load("world/regions/main/map.tmx");
		MapProperties prop = map.getProperties();
		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);
		this.mapPixelWidth = mapWidth * tilePixelWidth;
		this.mapPixelHeight = mapHeight * tilePixelHeight;
		renderer = new OrthogonalTiledMapRenderer(map, gui.getBatch());
		posX=Gdx.graphics.getHeight()/2-mapPixelWidth/2;
	}
	@Override
    public void dispose() {
		map.dispose();
	}
	public void render(float delta, OrthographicCamera camera, SpriteBatch batch) {
		renderer.setView(camera);
		renderer.render();
	}

	
	public int getId() {
		return id;
	}


	public int getMapWitdh() {
		return mapPixelWidth;
	}
	public int getMapHeight() {
		return mapPixelHeight;
	}

	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

}
