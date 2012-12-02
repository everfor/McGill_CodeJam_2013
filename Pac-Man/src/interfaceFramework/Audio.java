package interfaceFramework;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * This class handles getting audio files and playing them. This is used for all
 * parts of gameplay where sound is needed.
 */
public class Audio {
	static File path, audio;
	static SourceDataLine line;
	static AudioFormat audioFormat;
	static DataLine.Info DataLineInfo;

	/**
	 * This method uses the filename to get an audio file with the same name and
	 * play it It is used by other classes to play audio during gameplay.
	 * 
	 * @param fileName
	 *            this is the name of the audio file to be played
	 */
	public static void SoundPlayer(String fileName) {

		File path = new File("").getAbsoluteFile();
		File audio = new File(path + "\\res\\raw\\" + fileName);
		AudioInputStream audioInputStream = null;

		try {
			line.close();
		} catch (Exception e) {

		}

		try {
			audioInputStream = AudioSystem.getAudioInputStream(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		audioFormat = audioInputStream.getFormat();
		line = null;
		DataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

		try {
			line = (SourceDataLine) AudioSystem.getLine(DataLineInfo);
			line.open(audioFormat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		line.start();

		int bytesRead = 0;
		byte[] data = new byte[128000];
		while (bytesRead != -1) {

			try {
				bytesRead = audioInputStream.read(data, 0, data.length);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (bytesRead >= 0) {
				int numberOfBytesWritten = line.write(data, 0, bytesRead);
			}
		}
	}
}
