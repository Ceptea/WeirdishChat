package cepteas.weirdishchat;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UserManager {
    public static ConcurrentLinkedQueue<User> users = new ConcurrentLinkedQueue<>();
    public static void addUser(Player player) {
        users.add(new User(player));
    }
}
