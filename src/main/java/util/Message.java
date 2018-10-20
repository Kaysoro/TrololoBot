package util;

import controllers.ExceptionControl;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class Message {

    public static void sendText(IChannel channel, String content){
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(DiscordClient.DISCORD())
                        .withChannel(channel)
                        .withContent(content)
                        .build();
            } catch(RateLimitException e){
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(),e );
                throw e;
            } catch (Exception e) {
                ExceptionControl.throwException("Send text - Error", e);
            }
        });
    }

    public static void sendImage(IChannel channel, BufferedImage image, String imageName) {
        RequestBuffer.request(() -> {
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                new MessageBuilder(DiscordClient.DISCORD())
                        .withChannel(channel)
                        .withFile(is, imageName)
                        .build();
            } catch (RateLimitException e) {
                throw e;
            } catch (Exception e) {
                ExceptionControl.throwException("Send image - Error", e);
            }
        });
    }

    public static void sendFile(IChannel channel, InputStream file, String fileName) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(DiscordClient.DISCORD())
                        .withChannel(channel)
                        .withFile(file, fileName)
                        .build();
            } catch (RateLimitException e) {
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(), e);
                throw e;
            } catch (Exception e) {
                ExceptionControl.throwException("Send file - Error", e);
            }
        });
    }
}