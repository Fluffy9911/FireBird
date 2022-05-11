package main.fire.game.assets;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.fire.core.debug.Debug;

public class Sound {
	URL url;
	AudioFormat format;
	DataLine.Info info;
	Clip audioClip;
	AudioInputStream audio;

	public Sound(URL url) {
		this.url = url;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			format = audio.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void playSound(float volume) {
		try {

			audioClip.open(audio);
			FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			audioClip.start();
			long audioFileLength = audioClip.getMicrosecondLength();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
			Debug.printInfo(
					"Played sound file:" + audioClip.toString() + " with a length in seconds of:" + durationInSeconds);
			Thread.sleep((long) (durationInSeconds * 1000));

		} catch (LineUnavailableException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		audioClip.close();
		try {
			audio.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
