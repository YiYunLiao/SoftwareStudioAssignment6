package main.java;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class MinimPlayer {

	private Minim minim;
	private AudioPlayer song;
	
	//constructor of MinimPlayer
	public MinimPlayer(PApplet parent, String url){
		minim = new Minim(parent);
		song = minim.loadFile(this.getClass().getResource(url).getPath());
	}

	//play loop of music
	public void loop(){
		song.loop();;
	}
}
