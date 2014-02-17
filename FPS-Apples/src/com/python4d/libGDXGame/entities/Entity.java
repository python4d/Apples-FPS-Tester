package com.python4d.libGDXGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.gui.GuiInGame;

public abstract class Entity implements GameObject {
	
	protected GuiInGame gui;
	protected int x,y,dx,dy;
	protected int width,height;
	protected int direction;
	protected float ScaleX,ScaleY;
	




	public Entity(GuiInGame gui) {
		this.gui = gui;

		direction=1;
		
	}
	
	

	@Override
	public void dispose() {
		
	}



	public abstract void render (float delta, OrthographicCamera camera, SpriteBatch batch);


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}
	public int getDx() {
		return dx;
	}


	public void setDx(int dx) {
		this.dx = dx;
	}


	public int getDy() {
		return dy;
	}


	public void setDy(int dy) {
		this.dy = dy;
	}	
	
	public int getDirection() {
		return direction;
	}



	public void setDirection(int direction) {
		this.direction = direction;
	}
}



