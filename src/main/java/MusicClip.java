package main.java;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class MusicClip {
	
	private URL url;
	private AudioClip song;
	
	//constructor of MusicClip
	public MusicClip(String url){
		try {
			this.url = new URL("file:" + url);
			song = Applet.newAudioClip(this.url);
		} catch (MalformedURLException e) {
			System.out.println("Loading " + url + " has somthing wrong.");
		}
	}
	
	//play the music
	public void play(){
		song.play();
	}
	
}
