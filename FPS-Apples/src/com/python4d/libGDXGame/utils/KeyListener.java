package com.python4d.libGDXGame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.entities.EntityPlayer;
import com.python4d.libGDXGame.gui.GuiInGame;
import com.python4d.libGDXGame.worldbox2D.worldbox2d;

public class KeyListener implements InputProcessor {
	libGDXGame app;

	public KeyListener(libGDXGame app) {
		this.app = app;
	}

	public boolean keyDown(int keycode) {
		EntityPlayer p = app.getGUIingame().getPlayer();
		switch (keycode) {
		case Keys.ESCAPE:
		case Keys.BACK:
			app.quit();
			break;
		case Keys.SPACE:
			break;
		case Keys.LEFT:
		case Keys.Q:
			p.setDx(-200);
			break;
		case Keys.RIGHT:
		case Keys.D:
			p.setDx(200);
			break;

		}
		return false;
	}

	public boolean keyUp(int keycode) {
		EntityPlayer p = app.getGUIingame().getPlayer();
		switch (keycode) {
		case Keys.LEFT:
		case Keys.Q:
			p.setDx(0);
			break;
		case Keys.RIGHT:
		case Keys.D:
			p.setDx(0);
			break;
		}
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	float PointerTest = 1;
	Vector2 SaveXY = new Vector2(0, 0);

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		SaveXY = new Vector2(screenX, screenY);
		MyGestureListener.zoomEnCours=false;
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		app.getGUIingame().getWorldbox2d().pacman.getBody().applyForceToCenter(
				(float) (screenX - SaveXY.x)
						* (worldbox2d.BOX_TO_WORLD / 10.0f),
				-(float) (screenY - SaveXY.y)
						* (worldbox2d.BOX_TO_WORLD / 10.0f), true);
		Gdx.app.log("Force to pacman body", "(" + (screenX - SaveXY.x) + ";"
				+ (screenY - SaveXY.y) + ")");

		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		EntityPlayer p = app.getGUIingame().getPlayer();
		Gdx.app.log("TEST BACO", "PointerTest=" + PointerTest + "-Pointer="
				+ pointer + "-ScreenTouch(" + screenX + "-" + screenY
				+ ") - Player(" + p.getX() + "-" + p.getY() + ")"
				+ ") - ratio(" + libGDXGame.RatioRealScreenX + "-"
				+ libGDXGame.RatioRealScreenY + ")");

		if (MyGestureListener.zoomEnCours)
			return false;
		

		float rPosX = app.getGUIingame().getCamPosX();
		float zoom = app.getGUIingame().getCamera().zoom;
		
		p.setX((int) (screenX * libGDXGame.RatioRealScreenX * zoom + rPosX - p
				.getWidth() / 2));
		p.setY((int) ((Gdx.graphics.getHeight() - screenY)
				* libGDXGame.RatioRealScreenY * zoom - p.getHeight() / 2));
		app.getGUIingame().getWorldbox2d().pacman
				.getBody()
				.setTransform(
						(screenX * libGDXGame.RatioRealScreenX * zoom + rPosX - p.getWidth() / 2)
								* worldbox2d.WORLD_TO_BOX,
						((Gdx.graphics.getHeight() - screenY)
								* libGDXGame.RatioRealScreenY * zoom - p
								.getHeight() / 2) * worldbox2d.WORLD_TO_BOX, 0);

		app.getGUIingame().getWorldbox2d().pacman.getBody().setLinearVelocity(new Vector2(0,0));
		app.getGUIingame().getWorldbox2d().pacman.getBody().setAngularVelocity(0);//.setType(BodyType.StaticBody);

		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	public boolean scrolled(int amount) {
		app.getGUIingame().getCamera().zoom += (amount) / 2.0f;
		return false;
	}

	public void tick() {

	}

}
