package main;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import utils.LoadSave;

public class SoundManager {
    private Clip clip;
    private AudioInputStream ais;

    public SoundManager() {
    }

    public void PlayAudio(String fileName) {
        try {
            this.ais = AudioSystem.getAudioInputStream(LoadSave.GetSoundURL(fileName));
            this.clip = AudioSystem.getClip();
            this.clip.open(this.ais);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var3) {
            var3.printStackTrace();
        }

        this.clip.start();
    }
}
