package com.ltb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;

public class Main {
	private static int audio = 0;
	
	public static void main(String[] args) throws Exception {
		File node = new File("//home//juanma//Videos//DSVideo//Cartoons");
		
		displayIt(node);
		
		AudioTrack.returnListLanguages();
		
	    System.out.println("# Audio tracks "+audio);
	    	    
	    System.exit(0);
	}
	
	public static void displayIt(File node) throws IOException, InterruptedException{
		String filePath =  node.getAbsoluteFile().getAbsolutePath();
		System.out.println(filePath);
 
		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				displayIt(new File(node, filename));
			}
		} else {
			
			//Process pr = Runtime.getRuntime().exec("avconv -i //home//juanma//Videos//License.avi"); // Working fine
			//Process pr = Runtime.getRuntime().exec(outLine); // Working fine without spaces
			String[] args1 = {"avconv", "-i", filePath};
			Process pr = Runtime.getRuntime().exec(args1); 
			//filePath = filePath.replace("/" , "//");
			//filePath = filePath.replace(" " , "\\ ");
			//String commandline ="avconv -i \""+filePath+"\"";
			//String commandline ="avconv -i "+filePath;
			//Process pr = Runtime.getRuntime().exec(commandline);
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		    //BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		    String line;
		    // Start FilmFactory
		    String extension = FilenameUtils.getExtension(node.getName());
		    if(isFilmExtension(extension)==false) {
		    	return;
		    } else {
			    
			    Film film = new Film(node.getName(), filePath);
			    while ((line = in.readLine()) != null) {
			    	System.out.println(line);
			    	if(line.contains("Invalid data")) {
			    		// TODO Most probably no film - Change method below
			    		film.isNotFilm();
			    	} else 	if(line.contains("Audio")) {
			    		film.addAudioTrack(line);
			    	} else if(line.contains("No such file")) {
			    		System.out.println("Error?");
			    	}
			    		
			        
			    }
			    pr.waitFor();
			    in.close();
		    }
		}
 
	}

	/** 
	 * Check extension of file to check whether is a right file movie
	 * TODO: To be improved 
	 * @param extension
	 * @return
	 */
	private static boolean isFilmExtension(String extension) {
		if(extension.equalsIgnoreCase("mkv") || extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("m4v")
				|| extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("m2ts") || extension.equalsIgnoreCase("mpg")) {
			return true;
		} else {
			return false;
		}
	}
}
