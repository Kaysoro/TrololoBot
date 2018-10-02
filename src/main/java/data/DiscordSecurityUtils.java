package data;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;

import java.util.EnumSet;

public class DiscordSecurityUtils {

    /**
     * @param guild Guild to test
     * @return true if the admin role is not given, false in other case
     */
    public static boolean isSecured(IGuild guild){
        return ! PermissionUtils.hasPermissions(guild, DiscordClient.DISCORD().getOurUser(),
                EnumSet.of(Permissions.ADMINISTRATOR));
    }
}
