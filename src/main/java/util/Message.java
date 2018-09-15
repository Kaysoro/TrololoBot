package util;

import data.Constants;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
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
                new MessageBuilder(ClientConfig.DISCORD())
                        .withChannel(channel)
                        .withContent(content)
                        .build();
            } catch(RateLimitException e){
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(),e );
                throw e;
            } catch (DiscordException e){
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            } catch(MissingPermissionsException e){
                LoggerFactory.getLogger(Message.class).warn(Constants.name + " has not enough permission to do this request.");
            } catch(Exception e){
                LoggerFactory.getLogger(Message.class).error(e.getMessage(),e );
            }
            return null;
        });
    }

    public static void sendImage(IChannel channel, BufferedImage image, String imageName) {
        RequestBuffer.request(() -> {
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                new MessageBuilder(ClientConfig.DISCORD())
                        .withChannel(channel)
                        .withFile(is, imageName)
                        .build();
            } catch (RateLimitException e) {
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(), e);
                throw e;
            } catch (DiscordException e) {
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            } catch (MissingPermissionsException e) {
                LoggerFactory.getLogger(Message.class).warn(Constants.name + " has not enough permission to do this request.");
            } catch (Exception e) {
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            }
            return null;
        });
    }

    public static void sendFile(IChannel channel, InputStream file, String fileName) {
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(ClientConfig.DISCORD())
                        .withChannel(channel)
                        .withFile(file, fileName)
                        .build();
            } catch (RateLimitException e) {
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(), e);
                throw e;
            } catch (DiscordException e) {
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            } catch (MissingPermissionsException e) {
                LoggerFactory.getLogger(Message.class).warn(Constants.name + " has not enough permission to do this request.");
            } catch (Exception e) {
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            }
            return null;
        });
    }

    public static void sendEmbed(IChannel channel, EmbedObject content){
        RequestBuffer.request(() -> {
            try {
                new MessageBuilder(ClientConfig.DISCORD())
                        .withChannel(channel)
                        .withEmbed(content)
                        .build();
            } catch(RateLimitException e){
                LoggerFactory.getLogger(Message.class).warn(e.getMessage(), e);
                throw e;
            } catch (DiscordException e){
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            } catch(MissingPermissionsException e){
                LoggerFactory.getLogger(Message.class).warn(Constants.name + " has not enough permission to do this request.");
            } catch(Exception e){
                LoggerFactory.getLogger(Message.class).error(e.getMessage(), e);
            }
            return null;
        });
    }
}