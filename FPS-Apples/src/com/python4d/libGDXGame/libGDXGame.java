package com.python4d.libGDXGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;
import com.python4d.libGDXGame.entities.EntityPlayer;
import com.python4d.libGDXGame.entities.GameObject;
import com.python4d.libGDXGame.gui.GuiInGame;
import com.python4d.libGDXGame.utils.KeyListener;
import com.python4d.libGDXGame.utils.MyGestureListener;
import com.python4d.libGDXGame.utils.word.Region;
import com.python4d.libGDXGame.worldbox2D.worldbox2d;

public class libGDXGame extends Game {
	public static final String NAME="libGDXGame";
	public static final String VERSION = "0.0.1";
	public static final int SCALE = 2;
	public static final int WIDTH = 480*SCALE;
	public static final int HEIGHT = WIDTH*9/16;
	public static float RatioRealScreenX,RatioRealScreenY;
	public static FPSLogger fpsLogger ;
	

	//Screen
	protected GuiInGame GUIingame;
	//Sound
	public soundandmusic sound;

	//Entrée
	private KeyListener keyListener;
	private MyGestureListener gestureListener;
	

	public void create() {		
		fpsLogger = new FPSLogger();
		Gdx.input.setCatchBackKey(true);
		InputMultiplexer im = new InputMultiplexer();
        gestureListener= new MyGestureListener(this);
		keyListener=new KeyListener(this);
        im.addProcessor(keyListener);
        im.addProcessor(new GestureDetector(gestureListener));
		Gdx.input.setInputProcessor(im);
	
		sound=new soundandmusic(this);
		GUIingame=new GuiInGame(this);
		setScreen(GUIingame);
		

	}



	public void render() {	
		super.render();
		fpsLogger.log();
	}


	public void resize(int width, int height) {
		super.resize(width, height);
		RatioRealScreenX=(float)WIDTH/(float)Gdx.graphics.getWidth();
		RatioRealScreenY=(float)HEIGHT/(float)Gdx.graphics.getHeight();
	}

	public void dispose() {	
		super.dispose();	
		GUIingame.dispose();
		GUIingame=null;
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void pause() {
		super.pause();
	}
	
	public void resume() {
		super.resume();
	}
	
	public void quit() {
		
		Gdx.app.exit();
	}
	
	public GuiInGame getGUIingame() {
		return GUIingame;
	}



}
