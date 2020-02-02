package de.TntTastisch.BlackNerux.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;
import java.util.UUID;

public class JobNPC {

    public static void forceEmote(Player receiver, UUID npcUUID, int emoteId ) {
        // List of all forced emotes
        JsonArray array = new JsonArray();

        // Emote and target NPC
        JsonObject forcedEmote = new JsonObject();
        forcedEmote.addProperty( "uuid", npcUUID.toString() );
        forcedEmote.addProperty( "emote_id", emoteId );
        array.add(forcedEmote);

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage(receiver, "emote_api", array );
    }

}
