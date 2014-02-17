package com.python4d.libGDXGame.worldbox2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.python4d.libGDXGame.gui.GuiInGame;
public class objectbox2d {

	private float x;
	private float y;
	private Object id;
	private int nextid=0;
	private Body body;


	
	public Body getBody() {
		return body;
	}


	public objectbox2d(World w,String name,float ... param) {

		this.x = param[0];
		this.y = param[1];
		this.id=nextid++;
		if (name.equals("ball") ){
			this.body=ball(w,x,y,param[2]);
		}else if (name.equals("ground") ){
			this.body=ground (w,param[0],param[1],param[2],param[3]);
		}else if (name.equals("pacman") ){
			this.body=pacman (w,param[0],param[1],param[2]);
		}
	}

	private Body ground(World w,float x,float y,float width,float height){
		//OrthographicCamera c = GuiInGame.getCamera();
		
		// Create our body definition
		BodyDef groundBodyDef =new BodyDef();  
		// Set its world position
		groundBodyDef.position.set(new Vector2(x, y));  

		// Create a body from the defintion and add it to the world
		Body groundBody = w.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 120 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(width, height);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.5f); 
		// Clean up after ourselves
		groundBox.dispose();
		return groundBody;
	}
	
	private Body pacman(World w,float x,float y,float width) {
	    // 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/pacman"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = BodyType.DynamicBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 10;

	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    Body pacman = w.createBody(bd);
	 
	    // 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(pacman, "Pacman", fd, width);
	    
	    return(pacman);
	}
	private Body ball (World w,float x2,float y2,float param){

		
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(x2,y2);

		// Create our body in the world using our body definition
		Body body = w.createBody(bodyDef);
		this.body = body;
		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(param);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1.0f; 
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.8f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		body.createFixture(fixtureDef);

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		return body;	
		
	}


	public Object getId() {
		return id;
	}



}
