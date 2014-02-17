package com.python4d.libGDXGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.gui.GuiInGame;
import com.python4d.libGDXGame.utils.word.Region;

public class EntityPlayer extends Entity{

	protected Sprite sprite_left,sprite_right;
	protected Texture TextureG,TextureD;
	private Region region;
	protected int oldx;

	private final int speed = 10;
	public EntityPlayer (GuiInGame gui, int width, int height){
		super(gui);
		this.region=gui.getRegion();
		TextureG=new Texture(Gdx.files.internal("texture/fantome_gauche.png"));
		TextureD=new Texture(Gdx.files.internal("texture/fantome_droit.png"));
		this.sprite_left = new Sprite(TextureG);
		this.sprite_right = new Sprite(TextureD);
		this.height=height;
		this.width=width;
		spawn(region.getPosX()+libGDXGame.WIDTH/2-width/2, 100);
		this.oldx=0;
		
		
	}
	public void spawn(int x, int y){
		this.x=x;
		this.y=y;
	}
	public float getScaleX() {
		return sprite_left.getScaleX();
	}



	public float getScaleY() {
		return sprite_left.getScaleY();
	}

	public void render(float delta, OrthographicCamera camera, SpriteBatch batch) {

		int rPosX= region.getPosX();
		int mapWidth = region.getMapWitdh();

		float zoom=gui.getCamera().zoom;
	
		x+=dx*delta*this.speed*zoom;
		if (dx!=0)
			direction=dx/Math.abs(dx);
		
		if (x<0) 
			x=0;
		if (x+width>region.getMapWitdh()) 
			x=region.getMapWitdh()-width;
		
		if ((x-rPosX+width)>libGDXGame.WIDTH*zoom){
			if(rPosX<mapWidth-libGDXGame.WIDTH*zoom)
				region.setPosX(rPosX+this.speed);
		}
		if ((x-rPosX)<0){
			if(rPosX>0)
				region.setPosX(rPosX-this.speed);
		}
			


		//batch.setProjectionMatrix(camera.combined);
		if (this.oldx-this.x>0)
			batch.draw(sprite_left, x, y, width, height);
		else 
			batch.draw(sprite_right, x, y, width, height);
		this.oldx=this.x;


	}
	@Override
	public void dispose() {
		super.dispose();
		TextureD.dispose();
		TextureG.dispose();
		
	}
}
