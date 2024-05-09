package cepteas.weirdishchat;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.chat.message.ChatMessage;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.adventure.AdventureNBTSerializer;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChatMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSystemChatMessage;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentLinkedQueue;

public final class WeirdishChat extends JavaPlugin {
    public static ConcurrentLinkedQueue<WeirdMessage> chatMessages = new ConcurrentLinkedQueue<>();
    int a =0;

    public static double chatRemovalTime = 10000;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(),this);
        Bukkit.getPluginCommand("chat").setExecutor(new ChatCommand());
        Bukkit.getPluginCommand("chat").setTabCompleter(new ChatCommandCompletions());
        Bukkit.getPluginCommand("weirdishpl").setExecutor(new PluginsCommand());
        for (Player player: Bukkit.getOnlinePlayers()) {
            UserManager.addUser(player);
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (User user: UserManager.users) {
                    if (!user.get().isOnline()) {
                        UserManager.users.remove(user);
                    }
                }
                if (!chatMessages.isEmpty()) {
                    a ++;
                }
                sendAll("§7----------------------");

                for (int i = 0; i < 64; i++) {
                    sendAll("\n");

                }
                if (chatMessages.size() > 12 || (a > 40 && !chatMessages.isEmpty())) {
                    chatMessages.remove(0);
                    a = 0;

                }
                for (User user: UserManager.users) {


                    if (user.currentMessage != null) {
                        if (user.currentMessage.time < System.currentTimeMillis() ) {
                            user.currentMessage = null;
                        } else {
                            if (user.currentMessage.empty) {
                                sendAll(String.format("%s is typing...", user.getName()));

                            } else {
                                sendAll(user.currentMessage.message);

                            }

                        }
                    }



                }


                sendAll("§7----------------------");
                for (User user: UserManager.users) {
                    for (WeirdMessage msg: user.selfMsg) {
                        if (System.currentTimeMillis() > msg.time) {
                            user.selfMsg.remove(msg);
                            continue;
                        }
                        user.get().sendMessage(msg.message);

                    }
                }
            }
        },1l,1l);
    }

    public void sendAll(String a) {
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.sendMessage(a);
        }
    }
    @Override
    public void onDisable() {}
}
