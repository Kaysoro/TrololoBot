package util;

import data.DiscordSceneConstants;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DiscordViewUtils {

    private final static DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime( FormatStyle.LONG )
                    .withLocale( Locale.UK )
                    .withZone( ZoneId.systemDefault() );

    public static Image getImage(String stringUrl){
        try {
            URL url = new URL(stringUrl);
            URLConnection co = url.openConnection();
            co.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            co.connect();
            BufferedInputStream in = new BufferedInputStream(co.getInputStream());
            return SwingFXUtils.toFXImage(ImageIO.read(in), null);
        } catch (IOException e) {
            return DiscordSceneConstants.noIcon;
        }
    }

    public static String format(Instant instant){
        return formatter.format(instant);
    }
}
