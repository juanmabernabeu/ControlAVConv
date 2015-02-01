package com.ltb;

import java.util.ArrayList;

public class Film {
	private String fileName;
	private String filePath;
	private String title;
	private ArrayList<AudioTrack> audioTrackList = new ArrayList<AudioTrack>();
	private ArrayList<String> infoList = new ArrayList<String>();
	private boolean isFilm = true;
	
	public Film(String fileName, String filePath) {
 		this.fileName = fileName;
 		this.filePath = filePath;
	}
	
	public AudioTrack addAudioTrack(String info) {
		AudioTrack track = new AudioTrack(info);
		this.audioTrackList.add(track);
		return track;
	}
	
	public void addInfo(String info) {
		infoList.add(info);
	}

	public void isNotFilm() {
		this.isFilm = false;
		
	}
} 
