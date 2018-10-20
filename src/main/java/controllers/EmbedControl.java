package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.LoggerFactory;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;
import util.DiscordClient;
import util.Message;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by kaysoro on 20/10/2018.
 */
public class EmbedControl implements Initializable {

    private EmbedBuilder builder;
    private List<FieldStore> fields;
    private int count;

    @FXML
    private VBox fieldsPane;
    @FXML
    private TextField  title;
    @FXML
    private TextArea description;
    @FXML
    private TextField  titleUrl;
    @FXML
    private ColorPicker color;
    @FXML
    private TextField  thumbnailUrl;
    @FXML
    private TextField  imageUrl;
    @FXML
    private TextField  author;
    @FXML
    private TextField authorUrl;
    @FXML
    private TextField  authorIcon;
    @FXML
    private TextField  footer;
    @FXML
    private TextField  footerIcon;

    public EmbedControl(){
        builder = new EmbedBuilder();
        fields = new ArrayList<>();
        count = 0;
    }

    @FXML
    private void send() {
        try {
            builder.withTitle(title.getText())
                    .withUrl(titleUrl.getText())
                    .withDesc(description.getText())
                    .withColor(new Color((float) color.getValue().getRed(),
                            (float) color.getValue().getGreen(),
                            (float) color.getValue().getBlue(),
                            (float) color.getValue().getOpacity()))
                    .withFooterText(footer.getText())
                    .withFooterIcon(footerIcon.getText())
                    .withThumbnail(thumbnailUrl.getText())
                    .withImage(imageUrl.getText())
                    .withAuthorName(author.getText())
                    .withAuthorIcon(authorIcon.getText())
                    .withAuthorUrl(authorUrl.getText());

            fields.stream()
                    .filter(FieldStore::isAvailable)
                    .forEach(fs -> builder.appendField(fs.title.getText(), fs.description.getText(), fs.inline.isSelected()));

            Platform.runLater(() ->
                RequestBuffer.request(() -> {
                    try {
                        new MessageBuilder(DiscordClient.DISCORD())
                                .withChannel(ChannelControl.getChannel())
                                .withEmbed(builder.build())
                                .build();
                        Platform.runLater(() -> title.getScene().getWindow().hide());
                    } catch(RateLimitException e){
                        LoggerFactory.getLogger(Message.class).warn(e.getMessage(), e);
                        throw e;
                    } catch (Exception e) {
                        ExceptionControl.throwException("Send embed - Error", e);
                    }
                }));
        } catch (Exception e) {
            ExceptionControl.throwException("Send embed - Error", e);
        }
    }

    @FXML
    private void addField() {
        Platform.runLater(() -> {
            TextField title = new TextField();
            title.setPromptText("Title");
            TextArea description = new TextArea();
            description.setPromptText("Description");
            description.setPrefRowCount(2);
            description.setWrapText(true);
            CheckBox inline = new CheckBox();
            inline.setText("Inline");

            VBox components = new VBox();
            components.getChildren().addAll(title, description, inline);

            BorderPane bPane = new BorderPane();
            bPane.setLeft(new Label("Field #" + ++count));
            Button closeField = new Button("X");
            bPane.setRight(closeField);
            bPane.prefWidthProperty().bind(fieldsPane.getScene().widthProperty().subtract(65));

            TitledPane pane = new TitledPane();
            pane.setCollapsible(false);
            pane.setContent(components);
            pane.setPadding(new Insets(5, 5, 5, 5));
            pane.setGraphic(bPane);
            fieldsPane.getChildren().add(pane);
            FieldStore store = FieldStore.of(title, description, inline);
            fields.add(store);

            closeField.setOnAction(event -> {
                fields.remove(store);
                fieldsPane.getChildren().remove(pane);
            });
        });
    }

    @FXML
    private void cancel() {
        title.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private static class FieldStore {
        private TextField title;
        private TextArea description;
        private CheckBox inline;

        private FieldStore(TextField title, TextArea description, CheckBox inline){
            super();
            this.title = title;
            this.description = description;
            this.inline = inline;
        }

        private static FieldStore of(TextField title, TextArea description, CheckBox inline){
            return new FieldStore(title, description, inline);
        }

        private static boolean isAvailable(FieldStore store){
            return store.title.getText() != null && ! store.title.getText().trim().isEmpty() &&
            store.description.getText() != null && ! store.description.getText().trim().isEmpty();
        }
    }
}