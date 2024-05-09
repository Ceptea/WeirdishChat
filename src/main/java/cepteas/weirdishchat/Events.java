package cepteas.weirdishchat;

import net.kyori.adventure.Adventure;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class Events implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        User user = User.getUser(player);
        user.selfMsg.add(WeirdMessage.get("You can't speak without doing /c <whatever you want to say>"));
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        UserManager.addUser(e.getPlayer());
        for (User user: UserManager.users) {
            user.selfMsg.add(WeirdMessage.get(String.format("§e%s joined the game", e.getPlayer().getName())));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        User user = User.getUser(e.getPlayer());
        if (user.currentMessage !=null) {
            if (user.currentMessage.empty) {
                user.currentMessage = null;
            }
        }

    }


    @EventHandler
    public void onPlayerLeft(PlayerKickEvent e) {
        UserManager.users.remove(User.getUser(e.getPlayer()));
        for (User user: UserManager.users) {
            user.selfMsg.add(WeirdMessage.get(String.format("§e%s left the game (due to being kicked)", e.getPlayer().getName())));
        }
    }
    @EventHandler
    public void onPlayerLeft2(PlayerQuitEvent e) {
        UserManager.users.remove(User.getUser(e.getPlayer()));
        for (User user: UserManager.users) {
            user.selfMsg.add(WeirdMessage.get(String.format("§e%s left the game", e.getPlayer().getName())));
        }
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().contains("plugins")) {
            e.getPlayer().performCommand("weirdishpl");
            e.setCancelled(true);

        }
    }

}
