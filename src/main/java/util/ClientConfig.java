package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ClientConfig {
    
    private static ClientConfig instance = null;
    private final static Logger LOG = LoggerFactory.getLogger(ClientConfig.class);
    private final static String FILENAME = "config.properties";
    private IDiscordClient DISCORD;

    private ClientConfig(){
        super();
        Properties prop = new Properties();
        String config = System.getProperty("user.dir") + File.separator + FILENAME;

        try (FileInputStream file = new FileInputStream(URLDecoder.decode(config, "UTF-8"))){
            prop.load(file);

            try {
                DISCORD = new ClientBuilder()
                        .withToken(prop.getProperty("discord.token"))
                        .withRecommendedShardCount()
                        .login();
            } catch(DiscordException e) {
                LOG.error("Impossible to be connected to Discord : verify your token and your connexion.");
            }
        } catch(FileNotFoundException e){
            LOG.error("Configuration file not found.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private static synchronized ClientConfig getInstance(){
        if (instance == null)
            instance = new ClientConfig();
        return instance;
    }

    public static IDiscordClient DISCORD() {
        return getInstance().DISCORD;
    }
}