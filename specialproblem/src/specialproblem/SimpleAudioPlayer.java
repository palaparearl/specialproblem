package specialproblem;

//Java program to play an Audio 
//file using Clip Object 
import java.io.IOException; 

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{ 

	// to store current position 
	Long currentFrame; 
	Clip clip; 
	
	// current status of clip 
	String status; 
	
	AudioInputStream audioInputStream; 
	static String filePath; 

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer(String path) 
		throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException 
	{ 
		filePath = path;
		
		// create AudioInputStream object 
		audioInputStream = 
				AudioSystem.getAudioInputStream(SimpleAudioPlayer.class.getResource(filePath)); 
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 

//		if(path.equals("/sounds/bgMusic.wav")) {
//			clip.loop(Clip.LOOP_CONTINUOUSLY);
//		}
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// Method to play the audio 
	public void play() 
	{ 
		//start the clip 
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		status = "play"; 
	} 
	
	// Method to pause the audio 
	public void pause() 
	{ 
		if (status.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		this.currentFrame = 
		this.clip.getMicrosecondPosition(); 
		clip.stop(); 
		status = "paused"; 
	} 
	
	// Method to resume the audio 
	public void resumeAudio() throws UnsupportedAudioFileException, 
								IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) 
		{ 
			System.out.println("Audio is already "+ 
			"being played"); 
			return; 
		} 
		clip.close(); 
		resetAudioStream(); 
		clip.setMicrosecondPosition(currentFrame); 
		this.play(); 
	} 
	
	// Method to restart the audio 
	public void restart() throws IOException, LineUnavailableException, 
											UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
	// Method to stop the audio 
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException 
	{ 
		currentFrame = 0L; 
		clip.stop(); 
		clip.close(); 
	} 
	
	// Method to jump over a specific part 
	public void jump(long c) throws UnsupportedAudioFileException, IOException, 
														LineUnavailableException 
	{ 
		if (c > 0 && c < clip.getMicrosecondLength()) 
		{ 
			clip.stop(); 
			clip.close(); 
			resetAudioStream(); 
			currentFrame = c; 
			clip.setMicrosecondPosition(c); 
			this.play(); 
		} 
	} 
	
	// Method to reset audio stream 
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioInputStream = AudioSystem.getAudioInputStream( 
		SimpleAudioPlayer.class.getResourceAsStream(filePath)); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

} 
