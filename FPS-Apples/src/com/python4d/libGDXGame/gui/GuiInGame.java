package com.python4d.libGDXGame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.entities.EntityPlayer;
import com.python4d.libGDXGame.entities.GameObject;
import com.python4d.libGDXGame.utils.word.Region;
import com.python4d.libGDXGame.worldbox2D.objectbox2d;
import com.python4d.libGDXGame.worldbox2D.worldbox2d;

public class GuiInGame extends GuiScreen {

	private SpriteBatch batch;
	private Stage stage;
	private TextureAtlas mAtlas;
	private OrthographicCamera camera,HUDcam;
	private float CamPosX;
	public ImageButton ButtonPlus,ButtonMoins;
	TextureRegionDrawable drawableUp,drawableMoins ;
	ButtonStyle btnStyle, btnStylem;
	protected Region region;
	//Objects
	private EntityPlayer player;
	private Array<GameObject> gameObjects=new Array<GameObject>();
	private double coeffApple=20;
	protected float xPacman,yPacman,xoldPacman;
	protected Sprite pactex;
	
//BOX2D
	protected worldbox2d box2d;
	
	public GuiInGame(libGDXGame app) {
		super(app);
		
		batch = new SpriteBatch();

		region = new Region(this);
		player = new EntityPlayer(this, 200, 400);
		gameObjects.add(player);
		box2d = new worldbox2d(this);	
		
        mAtlas=new TextureAtlas(Gdx.files.internal("texture/buttonplusmoins.pack")) ;
        drawableUp = new TextureRegionDrawable( mAtlas.findRegion("buttonplus_unchecked") );        
        ButtonPlus = new ImageButton(drawableUp); 
        ButtonPlus.getImage().setOrigin(ButtonPlus.getWidth()/2, ButtonPlus.getHeight()/2);
        ButtonPlus.getImage().addAction(forever(sequence(scaleTo(1.05f, 1.05f,0.5f),scaleTo(0.95f, 0.95f, 0.5f))));
        this.stage = new Stage( );
		stage.addActor(ButtonPlus);
	
		drawableMoins = new TextureRegionDrawable( mAtlas.findRegion("buttonmoins_unchecked") );
        ButtonMoins = new ImageButton(drawableMoins); 
        ButtonMoins.getImage().setOrigin(ButtonMoins.getWidth()/2, ButtonMoins.getHeight()/2);
        ButtonMoins.getImage().addAction(forever(sequence(scaleTo(0.95f, 0.95f,0.5f),scaleTo(1.05f, 1.05f, 0.5f))));
		stage.addActor(ButtonMoins);

		InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
		im.addProcessor(stage);
		Gdx.input.setInputProcessor(im);
        
		camera = new OrthographicCamera(libGDXGame.WIDTH,libGDXGame.HEIGHT);
		HUDcam = new OrthographicCamera(Gdx.graphics.getWidth() ,Gdx.graphics.getHeight());
		stage.setCamera(HUDcam);
		
		font.setScale(1.0f,1.0f);
		camera.zoom=2.0f;
		CamPosX=0;
		pactex=box2d.pactexd;
		
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public void show() {
		
	}

	public void render(float delta) {
		HUDcam.setToOrtho(false,Gdx.graphics.getWidth() ,Gdx.graphics.getHeight() );
		camera.setToOrtho(false,libGDXGame.WIDTH,libGDXGame.HEIGHT);
		Gdx.gl.glClearColor(40/255f, 180/255f, 220/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		CamPosX=(region.getMapWitdh()/2-libGDXGame.WIDTH*camera.zoom/2);
		Gdx.app.log("camera", "zoom="+camera.zoom+"CamPosx="+CamPosX+"-gdxWidth="+Gdx.graphics.getWidth());
		camera.translate(CamPosX,0);
		camera.update();
		region.render(delta, camera, batch);
	
		if (ButtonPlus.isPressed() && (box2d.nbApples < 3000)){
			
			
			if  (coeffApple%10==0) box2d.nbApples+=coeffApple/20;
			coeffApple++;
			
		}
		else if (ButtonMoins.isPressed() && (box2d.nbApples>0)){
			if  (coeffApple%10==0) box2d.nbApples-=coeffApple/20;
			if (box2d.nbApples<0) box2d.nbApples=0;
			coeffApple++;
		}
		else {
			coeffApple = 20;
		}
		box2d.render(delta, camera, batch);
		batch.begin();
			float ratio=worldbox2d.BOX_TO_WORLD;
			xPacman=box2d.pacman.getBody().getPosition().x*ratio;
			yPacman=box2d.pacman.getBody().getPosition().y*ratio;
			if (xoldPacman-xPacman<-1)
				 pactex=box2d.pactexd;
			else if (xoldPacman-xPacman>1)
				 pactex=box2d.pactexg;
			pactex.setPosition(box2d.pacman.getBody().getPosition().x*ratio, box2d.pacman.getBody().getPosition().y*ratio);
			pactex.setRotation(box2d.pacman.getBody().getAngle() * MathUtils.radiansToDegrees);
			pactex.draw(batch);
			xoldPacman=xPacman;
			Sprite ballsprite=box2d.ballsprite;
			for (objectbox2d o: box2d.getBalls() ){
				ballsprite.setPosition(o.getBody().getPosition().x*ratio-ballsprite.getWidth()/2, o.getBody().getPosition().y*ratio-ballsprite.getHeight()/2);
				ballsprite.setRotation(o.getBody().getAngle() * MathUtils.radiansToDegrees);
				ballsprite.draw(batch);	
			}
		batch.end();

		batch.setProjectionMatrix(HUDcam.combined);
	    stage.draw();
	    stage.act();

	    batch.begin();
	    	font.drawMultiLine(batch,""+"Nb of Apples="
										+box2d.getBalls().size+"/"+box2d.nbApples
										+"\nFPS="
										+Gdx.graphics.getFramesPerSecond()
										+"/60\n+/- to add Apples\nZoom/unZoom",
										10.0f,Gdx.graphics.getHeight()-10.0f);
    	batch.end();



	}

	public void resize(int width, int height) {
		// resize the stage
        stage.setViewport( Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-100, true );
        ButtonPlus.setPosition(Gdx.graphics.getWidth()-500f,Gdx.graphics.getHeight()-300.0f);
        ButtonMoins.setPosition(Gdx.graphics.getWidth()-250f,Gdx.graphics.getHeight()-300.0f);
	}

	public void hide() {
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

	public void dispose() {
		mAtlas.dispose();
		font.dispose();
		stage.dispose();
		batch.dispose();
	
		
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public  OrthographicCamera getCamera() {
		return camera;
	}
	public  float getCamPosX() {
		return CamPosX;
	}

	public Region getRegion() {
		return region;
	}

	public  libGDXGame getApp() {
		return app;
	}
	public  worldbox2d getWorldbox2d() {
		return box2d;
	}
	public  void setCamPosX(float camPosX) {
		CamPosX = camPosX;
	}
}
