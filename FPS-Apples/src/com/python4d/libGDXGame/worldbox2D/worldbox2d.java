package com.python4d.libGDXGame.worldbox2D;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.python4d.libGDXGame.libGDXGame;
import com.python4d.libGDXGame.gui.GuiInGame;

public class worldbox2d {
	protected GuiInGame gui;
	// Texture des balles
	public final Texture tex = new Texture(
			Gdx.files.internal("texture/pomme.png"));

	public final Sprite pactexd = new Sprite(new Texture(
			Gdx.files.internal("texture/fantome_droit.png")));
	public final Sprite pactexg = new Sprite(new Texture(
			Gdx.files.internal("texture/fantome_gauche.png")));
	public Array<objectbox2d> balls = new Array<objectbox2d>();
	public final Sprite ballsprite = new Sprite(tex);
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_TO_WORLD = 100f;
	protected World worldbox2d;
	protected Box2DDebugRenderer debugRenderer;
	public objectbox2d pacman;
	protected int CountDelta = 0;
	public int nbApples = 10, Apples = 10;

	public worldbox2d(final GuiInGame gui) {
		this.gui = gui;
		this.worldbox2d = new World(new Vector2(0, -9.8f), true);
		this.debugRenderer = new Box2DDebugRenderer(false, false, false, false, false, false);
		//this.debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
		pactexg.setScale(0.5f);
		pactexg.setOrigin(0, 0);
		pactexd.setScale(0.5f);
		pactexd.setOrigin(0, 0);
		pacman = new objectbox2d(worldbox2d, "pacman", 500 / BOX_TO_WORLD,
				130 / BOX_TO_WORLD, pactexd.getWidth() / BOX_TO_WORLD / 2);

		new objectbox2d(worldbox2d, 
				"ground", 
				gui.getRegion().getMapWitdh()/ 2.0f / BOX_TO_WORLD, 
				120 / BOX_TO_WORLD,gui.getRegion().getMapWitdh()	/ 2 / BOX_TO_WORLD,
				10.0f / BOX_TO_WORLD);

		ballsprite.setScale(0.2f);
		ballsprite.setOrigin(ballsprite.getWidth() / 2f,
				ballsprite.getHeight() / 2f);
		for (int i = 0; i < 10; i++) {
			objectbox2d ball = new objectbox2d(worldbox2d, "ball",
					(new Random().nextInt(1600)) / BOX_TO_WORLD,
					(new Random().nextInt(2000) + 130) / BOX_TO_WORLD,
					20.0f / BOX_TO_WORLD);
			balls.add(ball);
		}
/*		this.worldbox2d.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Gdx.app.log("beginContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
                gui.getApp().sound.rain1.play(0.1f);
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Gdx.app.log("endContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
            }

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}



        });*/
	}
	


	public  Array<objectbox2d> getBalls() {
		return balls;
	}

	public void render(float delta, OrthographicCamera camera, SpriteBatch batch) {
		int index = 0;
		for (objectbox2d o : balls) {
			if ((o.getBody().getPosition().y < 0) && (!worldbox2d.isLocked())
					&& o.getBody() != null) {
				worldbox2d.destroyBody(o.getBody());
				balls.removeIndex(index);
			}
			index++;
		}
		if (nbApples > balls.size) {
			for (int i = 0; i < nbApples - balls.size; i++) {
				objectbox2d ball = new objectbox2d(worldbox2d, "ball",
						(new Random().nextInt(1600)) / BOX_TO_WORLD,
						(new Random().nextInt(2000) + Gdx.graphics.getHeight())
								/ BOX_TO_WORLD, 20.0f / BOX_TO_WORLD);
				balls.add(ball);
			}
		}


		Gdx.app.log("Number of BALLS?", "" + balls.size + "- demandées="
				+ nbApples);

	
			this.worldbox2d.step(1.0f / 60.0f, 10, 10);
			Matrix4 MatZoom = new Matrix4();//.combined.cpy();
			debugRenderer.render(this.worldbox2d, MatZoom.scl(BOX_TO_WORLD));



	}

	public void dispose(){
		tex.dispose();
		debugRenderer.dispose();
		worldbox2d.dispose();
		pactexd.getTexture().dispose();
		pactexg.getTexture().dispose();
	}
	public void renderball(float delta, OrthographicCamera camera,
			SpriteBatch batch) {
	};

	public World getWorldbox2d() {
		return worldbox2d;
	}
}
