package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class SoundCommand {

    private final static Logger LOG = LoggerFactory.getLogger(SoundCommand.class);
    private static List<File> sounds;

    private void playSound(IVoiceChannel voice, IMessage message, File file) {
        try {
            voice.join();
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).queue(file).getMetadata()
                    .put(file.getName(), file.toString());
        } catch (IOException | UnsupportedAudioFileException e) {
            LOG.error("playSound", e);
        }
    }

    private List<File> getSounds(){
        if (sounds == null) {
            File file = new File(System.getProperty("user.dir") + File.separator + "sounds");
            FilenameFilter filter = (File dir, String name) -> name.toLowerCase().endsWith(".mp3");

            File[] files = file.listFiles(filter);
            if (files != null)
                sounds = Arrays.asList(files);
            else
                sounds = new ArrayList<>();
        }
        Collections.sort(sounds);
        return sounds;
    }
}
