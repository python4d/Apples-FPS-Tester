package com.python4d.libGDXGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class soundandmusic {

	protected libGDXGame app;
	public Sound rain1;
		 public soundandmusic(libGDXGame app){
		 this.app=app;
		 this.rain1=Gdx.audio.newSound(Gdx.files.internal("sound/rain1.wav"));
		 
	 }
	 public void dispose(){
		 rain1.dispose();
	 }
}
