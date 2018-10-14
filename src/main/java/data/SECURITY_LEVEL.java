package data;

import javafx.scene.image.Image;

public enum SECURITY_LEVEL {
    SECURED(DiscordSceneConstants.guildSecuredIcon, "Security level: Secured"),
    DANGEROUS(DiscordSceneConstants.guildDangerousIcon, "Security level: Dangerous\n"
            + "The following rights can be owned:\n"
            + "- Manage server\n- Manage channels\n- Manage messages\n- Manage roles\n- Manage webhooks\n- Kick\n- Ban"),
    CRITICAL(DiscordSceneConstants.guildCriticalIcon, "Security level: Critical\nAdministrator rights");

    private Image image;
    private String toolTipText;

    SECURITY_LEVEL(Image image, String toolTipText){
        this.image = image;
        this.toolTipText = toolTipText;
    }

    public Image getImage() { return image; }

    public String getToolTipText() { return toolTipText; }
}
