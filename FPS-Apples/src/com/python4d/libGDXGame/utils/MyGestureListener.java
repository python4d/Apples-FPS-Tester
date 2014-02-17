package com.python4d.libGDXGame.utils;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.gui.GuiInGame;
import com.python4d.libGDXGame.utils.word.Region;

public class MyGestureListener implements GestureListener {

	
	protected libGDXGame app;
	public MyGestureListener(libGDXGame app){
		this.app=app;
	}
	protected float initialScale;
	public static boolean zoomEnCours=false;

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {        
		if (pointer == 1)
			initialScale =app.getGUIingame().getCamera().zoom;
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {

		float newzoom=initialScale-(distance-initialDistance)/100.0f;
		zoomEnCours=true;
		if (newzoom>0){
			app.getGUIingame().getCamera().zoom=newzoom;
		}
		
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
