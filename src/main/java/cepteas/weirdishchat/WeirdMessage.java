package cepteas.weirdishchat;

public class WeirdMessage {
    public String message;
    public  double time;
    public boolean empty = false;
    public WeirdMessage(double time, String message) {
        this.time = time;
        this.message = message + "§r";
        if (message.length() >=256) {
            message.substring(0,message.length());

        }

    }

    public static WeirdMessage get(String message) {
        return new WeirdMessage(System.currentTimeMillis()+6000,message);
    }

    public  void set(String s) {

        time = System.currentTimeMillis()+10000;
        message = s + "§r";
    }
}
