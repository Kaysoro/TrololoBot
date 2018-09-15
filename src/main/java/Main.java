import data.Constants;
import listeners.ReadyListener;
import util.ClientConfig;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class Main {

    public static void main(String[] args) {
        LoggerFactory.getLogger(Main.class).info("================================================\n" +
                "                                ___  __   __        __        __   __   __  ___ \n" +
                "                                 |  |__) /  \\ |    /  \\ |    /  \\ |__) /  \\  |\n" +
                "                                 |  |  \\ \\__/ |___ \\__/ |___ \\__/ |__) \\__/  |     Version " + Constants.version + "\n" +
        "                                ================================================");
      IDiscordClient client = ClientConfig.DISCORD();
      client.getDispatcher().registerListener(new ReadyListener());
    }
}
