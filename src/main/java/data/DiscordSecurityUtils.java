package data;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.PermissionUtils;
import util.DiscordClient;

import java.util.EnumSet;

import static sx.blah.discord.handle.obj.Permissions.*;

public class DiscordSecurityUtils {

    private static final EnumSet<Permissions> CRITICAL_PERMISSIONS = EnumSet.of(ADMINISTRATOR);

    private static final EnumSet<Permissions> DANGEROUS_PERMISSIONS = EnumSet.of(MANAGE_SERVER, MANAGE_CHANNELS,
            MANAGE_MESSAGES, MANAGE_ROLES, MANAGE_WEBHOOKS, KICK, BAN);

    /**
     * @param guild Guild to test
     * @return the security level linked to the guild
     */
    public static SECURITY_LEVEL getSecurityLevel(IGuild guild){
        if (PermissionUtils.hasPermissions(guild, DiscordClient.DISCORD().getOurUser(), CRITICAL_PERMISSIONS))
            return SECURITY_LEVEL.CRITICAL;

        for(Permissions perm : DANGEROUS_PERMISSIONS)
            if (PermissionUtils.hasPermissions(guild, DiscordClient.DISCORD().getOurUser(), perm))
                return SECURITY_LEVEL.DANGEROUS;

        return SECURITY_LEVEL.SECURED;
    }

    public static boolean isSecured(IGuild guild) {
        SECURITY_LEVEL level = getSecurityLevel(guild);
        return level == SECURITY_LEVEL.SECURED;
    }
}
