package cepteas.weirdishchat;

import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
    WeirdMessage currentMessage;
    Player player;
    ConcurrentLinkedQueue<WeirdMessage> selfMsg = new ConcurrentLinkedQueue<>();
    public User(Player player) {
        this.player = player;
    }
    public Player get() {
        return player;
    }

    public static User getUser(Player player) {
        for (User user: UserManager.users) {
            if ( user.get().getName().equals(player.getName())) {
                return user;
            }
        }
        return null;
    }

    public void sendToUser(String message) {
        selfMsg.add(WeirdMessage.get(message));
    }


    public String getName() {
        String name = get().getName();
        if (name.equalsIgnoreCase("Ceptea")) {
            name = "§b[OWNER] Ceptea§f";
        }
        return name;
    }
    public void say(String message) {

        WeirdMessage a = new WeirdMessage(System.currentTimeMillis()+WeirdishChat.chatRemovalTime,String.format("%s: %s", getName(),message));
        a.empty = message.isBlank();

        currentMessage = a;

        WeirdishChat.chatMessages.add(a);
    }


}
