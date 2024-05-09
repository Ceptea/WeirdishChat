package cepteas.weirdishchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ChatCommandCompletions implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player event) {
            User user = User.getUser((Player) commandSender);
            if (user.currentMessage == null) {
                user.say(String.join(" ",strings));
            }
            for (WeirdMessage msg: WeirdishChat.chatMessages) {
                if (msg.equals(user.currentMessage)) {
                    WeirdMessage lastMessage = user.currentMessage;
                    user.currentMessage.set(user.getName() + ": "+ String.join(" ",strings));
                    user.currentMessage.empty = String.join(" ",strings).isBlank();
                }
            }
//            WeirdishChat.chatMessages.add(a);

        }

        return null;
    }
}
