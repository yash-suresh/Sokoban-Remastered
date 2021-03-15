package sample.utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * this is the singleton class which allows us to create the functionality for
 * playing in-game music
 * @author Yash Suresh
 */
public class SoundFX
{
    private static SoundFX m_single_instance;
    private MediaPlayer m_player;
    private final String m_filePath = "src/sample/resources/puzzle_theme.wav";
    private final Media m_media = new Media(Paths.get(getM_filePath()).toUri().toString());
    private boolean isMusic = true;


    /**
     * creates a singleton class
     * @return m_single_instance the instance of the singleton class
     */
    public static SoundFX getInstance()
    {
        if (m_single_instance == null)
            m_single_instance = new SoundFX();

        return m_single_instance;
    }

    /**
     * getter for the media player
     * @return m_player instance of MediaPlayer
     */
    public MediaPlayer getM_player()
    {
        return m_player;
    }

    /**
     * setter for MediaPlayer
     * @param m_player stores the Media player instance
     */
    public void setM_player(MediaPlayer m_player)
    {

        this.m_player = m_player;
    }

    /**
     * getter for the file path
     * @return m_filePath which is the file path stored as a String
     */
    public String getM_filePath()
    {
        return m_filePath;
    }

    /**
     * tells us whether music is playing or not
     * @return isMusic the boolean for whether the music is playing or not
     */
    public boolean isMusic()
    {
        return isMusic;
    }

    /**
     * sets the boolean for whether music ir playing or not
     * @param music stores the boolean for music
     */
    public void setMusic(boolean music)
    {
        isMusic = music;
    }




    /**
     * this particular function plays the music using the path file we have provided
     * i have added a function which plays it endlessly.
     */
    public void Play()
    {
        setM_player(new MediaPlayer(m_media));
        getM_player().setCycleCount(MediaPlayer.INDEFINITE);
        getM_player().play();
    }


    /**
     * this function pauses the music when the player toggles the button
     */
    public void Pause()
    {
        getM_player().pause();
    }

    /**
     * this function unpauses it. we don't use the original 'Play' function because it will create a new instance
     */
    public void UnPause() {
        getM_player().play();
    }


    /**
     * this function toggles between on or off, depending in whether the
     * boolean 'isMusic' is true or not
     */
    public void Toggle()
    {

        if (isMusic())
        {
            setMusic(false);
            Pause();
        } else if (!isMusic())
        {
            setMusic(true);
            UnPause();
        }
    }



}
