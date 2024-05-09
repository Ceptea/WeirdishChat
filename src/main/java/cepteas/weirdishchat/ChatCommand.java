package cepteas.weirdishchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            WeirdishChat.chatMessages.add(new WeirdMessage(System.currentTimeMillis(), String.format("Console: %s", String.join(" ",strings))));
            return true;
        }
        User.getUser((Player) commandSender).say(String.join(" ",strings));
        return true;
    }
}
