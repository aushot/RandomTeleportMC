package randomteleport.randomteleport.Config;

import randomteleport.randomteleport.Randomteleport;

public class ConfigFile {
    private int defaultdistance;
    private String line1, line2;
    private String message;

    public ConfigFile(){
        Randomteleport main = Randomteleport.getInstance();

        defaultdistance = main.getConfig().getInt("defaultdistance");
        line1 = main.getConfig().getString("line1");
        line2 = main.getConfig().getString("line2");
        message = main.getConfig().getString("message");
    }

    public int getDefaultdistance() {
        return defaultdistance;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getMessage() {
        return message;
    }
}
