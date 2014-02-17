package com.python4d.libGDXGame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.python4d.libGDXGame.libGDXGame;

public abstract class GuiScreen implements Screen {
	protected libGDXGame app;
	protected final BitmapFont font=new BitmapFont(Gdx.files.internal("font/berlin.fnt"),
	         Gdx.files.internal("font/berlin.png"), false);

	protected GuiScreen (libGDXGame app){
		this.app = app;
	}
	public abstract void show();
	
	public abstract void render(float delta);

	public abstract void resize(int width, int height);
	
	public abstract void hide();

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

}
