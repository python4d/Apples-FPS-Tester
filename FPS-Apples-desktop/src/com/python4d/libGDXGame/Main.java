package com.python4d.libGDXGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = libGDXGame.VERSION+" "+libGDXGame.NAME;
		cfg.useGL20 = true;
		cfg.width = libGDXGame.WIDTH;
		cfg.height = libGDXGame.HEIGHT;
		
		new LwjglApplication(new libGDXGame(), cfg);
	}
}
