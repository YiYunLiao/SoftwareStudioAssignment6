package main.java;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicClip {
	
	private URL url;
	private AudioClip song;
	
	public MusicClip(String url){
		this.url = this.getClass().getResource(url);
		song = Applet.newAudioClip(this.url);
	}
	
	public void play(){
		song.play();
	}
	
	public void loop(){
		song.loop();;
	}
}
