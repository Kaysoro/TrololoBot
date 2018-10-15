package view.tree;

import data.DiscordSecurityUtils;
import data.SECURITY_LEVEL;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.shape.Circle;
import sx.blah.discord.handle.obj.IExtendedInvite;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;
import util.DiscordViewUtils;

public class GuildItem extends AbstractItem {

    private IGuild guild;
    private MenuItem extendedInvites;

    private GuildItem(IGuild guild, TreeItem<DiscordItem> tree){
        super(guild.getLongID(), tree);
        this.guild = guild;
        this.tooltip = new Tooltip();
        checkVulnerability();

        MenuItem showInfo = new MenuItem("Show Informations");
        showInfo.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guild informations");
            alert.setHeaderText(guild.getName());
            alert.setContentText("Member count: " + guild.getTotalMemberCount() + "\n"
                + "Creation date: " + DiscordViewUtils.format(guild.getCreationDate()) + "\n"
                + "Owner: " + guild.getOwner().getName() + "#" + guild.getOwner().getDiscriminator());
            ImageView view = new ImageView(DiscordViewUtils.getImage(guild.getIconURL()));
            view.setClip(new Circle(view.getLayoutBounds().getMaxX() / 2,
                    view.getLayoutBounds().getMaxY() / 2, view.getLayoutBounds().getMaxX() / 2));
            alert.setGraphic(view);
            alert.showAndWait();
        });

        extendedInvites = new MenuItem("Get invites");
        extendedInvites.setOnAction(event -> {
            String invites = guild.getExtendedInvites().stream()
                    .map(IExtendedInvite::getCode)
                    .reduce((a, b) -> a + "\n" + b)
                    .orElse("nope");
            // TODO
            System.out.println(invites);
        });
        if (! PermissionUtils.hasPermissions(guild, DiscordClient.DISCORD().getOurUser(), Permissions.MANAGE_SERVER))
            extendedInvites.setDisable(true);

        MenuItem copyID = new MenuItem("Copy the identifier");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(guild.getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().addAll(showInfo, extendedInvites, new SeparatorMenuItem(), copyID);
    }

    public static DiscordItem of(IGuild guild, TreeItem<DiscordItem> tree){
        return new GuildItem(guild, tree);
    }

    @Override
    public String getName() {
        return guild.getName() + " (" + guild.getTotalMemberCount() + " users)";
    }

    @Override
    public ContextMenu getMenu(){
        return super.getMenu();
    }

    @Override
    public void checkIntegrity() {
        extendedInvites.setDisable(! PermissionUtils
                .hasPermissions(guild, DiscordClient.DISCORD().getOurUser(), Permissions.MANAGE_SERVER));
        checkVulnerability();
    }

    @Override
    public Class getDiscordClass() {
        return IGuild.class;
    }

    private void checkVulnerability(){
        SECURITY_LEVEL level = DiscordSecurityUtils.getSecurityLevel(guild);
        node = new ImageView(level.getImage());
        tooltip.setText(level.getToolTipText());
    }
}
