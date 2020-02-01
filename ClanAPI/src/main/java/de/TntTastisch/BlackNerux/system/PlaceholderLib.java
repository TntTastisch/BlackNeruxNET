package de.TntTastisch.BlackNerux.system;

import de.TntTastisch.BlackNerux.ClanAPI;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

public class PlaceholderLib extends EZPlaceholderHook {

    public PlaceholderLib() {
        super(ClanAPI.getPlugin(ClanAPI.class), "clans");
    }

    public String onPlaceholderRequest(final Player player, final String identifier) {
        if(identifier.contains("player_")) {
            final String identifierPlayer = player.getUniqueId().toString();
            if (player == null) {
                return "PlayerNotFound";
            }
            if (identifier.endsWith("clanname")) {
                if (ClanSQL.getClanName(identifierPlayer) != "null") {
                    return ClanSQL.getClanColor(identifierPlayer) + ClanSQL.getClanName(identifierPlayer);
                } else {
                    return "&cKein Clan";
                }
            }
            if (identifier.endsWith("tabPrefix")) {
                if (ClanSQL.getClanName(identifierPlayer) != "null") {
                    return "&8[" + ClanSQL.getClanTag(identifierPlayer) + "&8]";
                } else {
                    return "&8";
                }
            }
        }
        return "§cPlaceHolderNotFound";
    }
}
