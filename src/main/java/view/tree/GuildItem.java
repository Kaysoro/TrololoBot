package view.tree;

import data.DiscordSceneConstants;
import data.DiscordSecurityUtils;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;

public class GuildItem extends AbstractItem {

    private IGuild guild;

    private GuildItem(IGuild guild){
        super();
        this.guild = guild;
        if (DiscordSecurityUtils.isSecured(guild))
            node = new ImageView(DiscordSceneConstants.guildsIcon);
        else
            node = new ImageView(DiscordSceneConstants.guildnsIcon);

        MenuItem copyID = new MenuItem("Copy Guild ID");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(guild.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        MenuItem connect = new MenuItem("Get invites");
        connect.setOnAction(event -> {
            guild.getExtendedInvites().stream().map(elem -> elem.getCode() + "\n");
        });
        if (! PermissionUtils.hasPermissions(guild, DiscordClient.DISCORD().getOurUser(), Permissions.MANAGE_SERVER))
            connect.setDisable(true);

        menu.getItems().add(copyID);
        menu.getItems().add(connect);
    }

    public static DiscordItem of(IGuild guild){
        return new GuildItem(guild);
    }

    @Override
    public String getName() {
        return guild.getName();
    }
}
