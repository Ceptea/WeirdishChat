package cepteas.weirdishchat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class PluginsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        User user = User.getUser((Player) commandSender);
        user.sendToUser(String.format("Plugins (%s):", Bukkit.getPluginManager().getPlugins().length));

        ArrayList<String> pl = new ArrayList<>();
        for (Plugin plugin: Bukkit.getPluginManager().getPlugins()) {
            pl.add("§a" + plugin.getName());
        }
        user.sendToUser(String.join("§f,§a ",pl));

        return true;
    }
}
