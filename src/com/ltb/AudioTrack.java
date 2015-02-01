package com.ltb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AudioTrack {
	private String info;
	private String language;
	private static HashSet<String> languages = new HashSet<String>();
	
	public AudioTrack(String info) {
		this.info = info;
		findLanguage(info);
	}

	private void findLanguage(String info) {
		// Example info Stream #0.7(eng): Subtitle: tx3g / 0x67337874
		int pos1 = info.indexOf("(");
		if (pos1==-1) {
			this.language = "unknown";
		} else {
			int pos2 = info.indexOf(")");
			this.language = info.substring(pos1+1, pos2);
		}
		languages.add(language);
	}
	
	public static void returnListLanguages() {
		Iterator<String> it = languages.iterator();
		while(it.hasNext()) {
			String language = it.next();
			System.out.println(language);
		}
	}
	
}
