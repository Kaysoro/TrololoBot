package util;

import data.DiscordSceneConstants;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import sx.blah.discord.handle.obj.IMessage;

import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DiscordViewUtils {

    private final static DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime( FormatStyle.LONG )
                    .withLocale( Locale.UK )
                    .withZone( ZoneId.systemDefault() );

    private final static Map<String, Image> imageStore = new HashMap<>();

    public static Image getImage(String stringUrl){
        if (!imageStore.containsKey(stringUrl))
            try {
                URL url = new URL(stringUrl);
                URLConnection co = url.openConnection();
                co.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                co.connect();
                BufferedInputStream in = new BufferedInputStream(co.getInputStream());
                Image image = SwingFXUtils.toFXImage(ImageIO.read(in), null);
                imageStore.put(stringUrl, image);
            } catch (IOException | NullPointerException e) {
                imageStore.put(stringUrl, DiscordSceneConstants.noIcon);
            }
        return imageStore.get(stringUrl);
    }

    public static String format(Instant instant){
        return formatter.format(instant);
    }

    public static Node getMessageView(IMessage message){
        // Content
        StringBuilder st = new StringBuilder(message.getContent());
        message.getAttachments().forEach(attachment ->
                st.append("\n[FILE: ").append(attachment.getUrl()).append("]"));
        message.getEmbeds().forEach(embed -> st.append("\n[EMBED: ").append(embed.getTitle()).append("]"));
        TextFlow area = new TextFlow(new Text(st.toString().trim()));

        // Name
        Label name = new Label();
        name.setText(message.getAuthor().getName() + "#" + message.getAuthor().getDiscriminator());
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        if (message.getAuthor().equals(DiscordClient.DISCORD().getOurUser()))
            name.setTextFill(Color.web("#3578e5"));

        // Date
        Label date = new Label();
        date.setText(format(message.getCreationDate()));
        date.setFont(Font.font("Verdana", FontPosture.ITALIC, 11));
        date.setTextFill(Color.GRAY);

        // Panel
        BorderPane id = new BorderPane();
        id.setTop(new Separator());
        id.setLeft(name);
        id.setRight(date);

        BorderPane pane = new BorderPane();
        pane.setTop(id);
        pane.setCenter(area);
        return pane;
    }
}
