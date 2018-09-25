package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import util.ClientConfig;
import view.Display;

import javax.swing.*;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class ReadyListener {
    private final static Logger LOG = LoggerFactory.getLogger(ReadyListener.class);

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        long time = System.currentTimeMillis();

        LOG.info("Opening GUI...");
        SwingUtilities.invokeLater(() -> Display.getInstance().setVisible(true));

        LOG.info("Adding listeners...");
        ClientConfig.DISCORD().getDispatcher().registerListener(new GuildCreateListener());
        ClientConfig.DISCORD().getDispatcher().registerListener(new GuildLeaveListener());

        LOG.info("Listening messages...");
        ClientConfig.DISCORD().getDispatcher().registerListener(new MessageReceivedListener());
        ClientConfig.DISCORD().getDispatcher().registerListener(new MessageSendListener());

        LOG.info("UP in " + (System.currentTimeMillis() - time) + "ms");
    }
}
