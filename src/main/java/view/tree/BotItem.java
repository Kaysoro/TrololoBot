package view.tree;

import data.Constants;
import data.DiscordSceneConstants;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.shape.Circle;
import sx.blah.discord.api.IDiscordClient;
import util.DiscordViewUtils;

public class BotItem extends AbstractItem {

    private IDiscordClient client;

    private BotItem(IDiscordClient client, TreeItem<DiscordItem> tree){
        super(client.getOurUser().getLongID(), tree);
        this.client = client;
        node = new ImageView(DiscordSceneConstants.robotIcon);

        MenuItem showInfo = new MenuItem("Show Informations");
        showInfo.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bot informations");
            alert.setHeaderText(client.getApplicationName() + "\n" + client.getApplicationDescription());
            alert.setContentText("Guild count: " + client.getGuilds().size() + "\n"
                    + "User count: " + client.getUsers().size() + "\n"
                    + "Client ID: " + client.getApplicationClientID() + "\n"
                    + "Token: " + client.getToken() + "\n"
                    + "Owner: " + client.getApplicationOwner().getName() + "#" + client.getApplicationOwner().getDiscriminator());
            ImageView view = new ImageView(DiscordViewUtils.getImage(client.getApplicationIconURL()));
            view.setClip(new Circle(view.getLayoutBounds().getMaxX() / 2,
                    view.getLayoutBounds().getMaxY() / 2, view.getLayoutBounds().getMaxX() / 2));
            alert.setGraphic(view);
            alert.showAndWait();
        });

        MenuItem getBotInvite = new MenuItem("Copy the bot invite");
        getBotInvite.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(Constants.botInvite.replace("{clientId}", client.getApplicationClientID()));
            Clipboard.getSystemClipboard().setContent(content);
        });

        MenuItem copyToken = new MenuItem("Copy the token");
        copyToken.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(client.getToken());
            Clipboard.getSystemClipboard().setContent(content);
        });

        MenuItem copyID = new MenuItem("Copy the bot ID");
        copyID.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(client.getOurUser().getStringID());
            Clipboard.getSystemClipboard().setContent(content);
        });

        menu.getItems().addAll(showInfo, getBotInvite, copyToken, copyID);
    }

    public static DiscordItem of(IDiscordClient client, TreeItem<DiscordItem> tree){
        return new BotItem(client, tree);
    }

    @Override
    public String getName() {
        return client.getApplicationName();
    }

    @Override
    public void checkIntegrity() {
        // Nothing to do
    }

    @Override
    public Class getDiscordClass() {
        return IDiscordClient.class;
    }
}
