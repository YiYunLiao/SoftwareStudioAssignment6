package main.java;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class MinimClass extends PApplet{

	private static final long serialVersionUID = 1L;
	MainApplet parent;
	Minim minim;
	AudioPlayer song;
	public MinimClass(MainApplet parent, String url){
		this.parent = parent;
		minim = new Minim(parent);
		song = minim.loadFile(this.getClass().getResource(url).getPath());
	}
	
	public void play(){
		song.play();
	}
}
