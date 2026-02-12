package inf1009.p63.yappybird.lwjgl3;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


// refactored code to avoid memory leaks and simplify usage

public class SoundManager {
    
    // Singleton Instance
    private static SoundManager instance;
    
    private HashMap<String, Sound> sounds;
    private HashMap<String, Music> music;

    private SoundManager() {
        sounds = new HashMap<>();
        music = new HashMap<>();
    }

    public static SoundManager getInstance(Object o) {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void loadSound(String name, String path) {
        if (!sounds.containsKey(name)) {
            sounds.put(name, Gdx.audio.newSound(Gdx.files.internal(path)));
        }
    }

    public void playSound(String name) {
        if (sounds.containsKey(name)) {
            sounds.get(name).play();
        }
    }

    public void loadMusic(String name, String path) {
        if (!music.containsKey(name)) {
            music.put(name, Gdx.audio.newMusic(Gdx.files.internal(path)));
        }
    }

    public void playMusic(String name, boolean looping) {
        if (music.containsKey(name)) {
            Music m = music.get(name);
            m.setLooping(looping);
            m.play();
        }
    }
    
    // Good practice: Add a dispose method to clean up audio too!
    public void dispose() {
        for (Sound s : sounds.values()) s.dispose();
        for (Music m : music.values()) m.dispose();
    }
}



/**
 * SoundManager handles all audio playback in the game.
 * Manages both sound effects and background music with volume control.
 
public class SoundManager {
    
    // Audio assets storage
    private Map<String, Sound> soundEffects;
    private Map<String, Music> musicTracks;
    
    // Volume control
    private float volume;
    
    // Currently playing music
    private Music currentMusic;

    private static SoundManager instance;
    */
   
    /**
     * Constructor initializes the sound manager with a given audio context.
     * @param context The audio context (LibGDX handles this internally)
     
    public SoundManager(Object context) {
        this.soundEffects = new HashMap<>();
        this.musicTracks = new HashMap<>();
        this.volume = 1.0f; // Default volume at 100%
        this.currentMusic = null;
    }
    
    /**
     * Gets the singleton instance of SoundManager for the given context.
     * @param context The audio context
     * @return SoundManager instance
     
    public static SoundManager getInstance(Object context) {
        if (instance == null) {
        instance = new SoundManager(context);
        }
        return instance;
            
    }
    
    /**
     * Plays a sound effect.
     * @param soundKey The identifier for the sound to play
     * @return long The sound instance ID
     
    public long playSound(String soundKey) {
        Sound sound = soundEffects.get(soundKey);
        if (sound != null) {
            return sound.play(volume);
        }
        System.err.println("Sound not found: " + soundKey);
        return -1;
    }
    
    /**
     * Stops a currently playing sound.
     * @param soundKey The identifier for the sound to stop
     
    public void stopSound(String soundKey) {
        Sound sound = soundEffects.get(soundKey);
        if (sound != null) {
            sound.stop();
        }
    }
    
    /**
     * Gets the current volume level.
     * @return float The current volume (0.0 to 1.0)
     
    public float getVolume() {
        return volume;
    }
    
    /**
     * Sets the volume level for all audio.
     * @param volume The new volume level (0.0 to 1.0)
     
    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume));
        
        // Update currently playing music volume
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.setVolume(this.volume);
        }
    }
    
    /**
     * Loads a sound effect into memory.
     * @param key The identifier for the sound
     * @param filePath The file path to the sound asset
     
    public void loadSound(String key, String filePath) {
        try {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
            soundEffects.put(key, sound);
        } catch (Exception e) {
            System.err.println("Failed to load sound: " + filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Loads a music track into memory.
     * @param key The identifier for the music
     * @param filePath The file path to the music asset
     
    public void loadMusic(String key, String filePath) {
        try {
            Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
            musicTracks.put(key, music);
        } catch (Exception e) {
            System.err.println("Failed to load music: " + filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Plays a music track with looping.
     * @param musicKey The identifier for the music to play
     * @param loop Whether the music should loop
     
    public void playMusic(String musicKey, boolean loop) {
        // Stop current music if playing
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.stop();
        }
        
        // Get and play new music
        Music music = musicTracks.get(musicKey);
        if (music != null) {
            currentMusic = music;
            currentMusic.setLooping(loop);
            currentMusic.setVolume(volume);
            currentMusic.play();
        } else {
            System.err.println("Music not found: " + musicKey);
        }
    }
    
    /**
     * Stops the currently playing music.
     
    public void stopMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.stop();
        }
    }
    
    /**
     * Pauses the currently playing music.
     
    public void pauseMusic() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.pause();
        }
    }
    
    /**
     * Resumes paused music.
     
    public void resumeMusic() {
        if (currentMusic != null) {
            currentMusic.play();
        }
    }
    
    /**
     * Disposes of all audio resources.
     * Should be called when the game is closing.
     
    public void dispose() {
        // Dispose all sound effects
        for (Sound sound : soundEffects.values()) {
            sound.dispose();
        }
        soundEffects.clear();
        
        // Dispose all music tracks
        for (Music music : musicTracks.values()) {
            music.dispose();
        }
        musicTracks.clear();
        
        currentMusic = null;
    }
}
*/
