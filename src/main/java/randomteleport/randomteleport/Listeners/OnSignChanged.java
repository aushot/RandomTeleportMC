package randomteleport.randomteleport.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import randomteleport.randomteleport.Config.ConfigFile;
import randomteleport.randomteleport.Utils.ChatUtils;

public class OnSignChanged implements Listener {

    public void forceSetBlank(SignChangeEvent event, int y) {
        for (int i = y; i < event.getLines().length; i++) {
            event.setLine(i, "");
        }
    }
    @EventHandler
    public void onSignChange(SignChangeEvent event){
        if(event.getPlayer().hasPermission("randomteleport.admin") || event.getPlayer().isOp()){

            //check if the line 1: randomtp
            //check if the line 2: (is not blank) ex: 200
            if(!(event.getLine(0).isEmpty()) && event.getLine(0).equalsIgnoreCase("randomtp")) {

                //check if line 2 is not empty
                if (!(event.getLine(1).isEmpty())) {
                    try {
                        int distance = Integer.parseInt(event.getLine(1));
                        ChatUtils chatUtils = new ChatUtils();
                        ConfigFile configFile = new ConfigFile();

                        event.setLine(0, chatUtils.utilsChat(configFile.getLine1()));
                        event.setLine(1, chatUtils.utilsChat(configFile.getLine2()).replaceAll("%distance", String.valueOf(distance)));

                        //force to sett all lines remaining in blank
                        forceSetBlank(event, 2);
                    } catch (NumberFormatException e){
                        Player player = event.getPlayer();
                        player.sendMessage("Invalid number format");
                    }
                } else {
                    ChatUtils chatUtils = new ChatUtils();
                    ConfigFile configFile = new ConfigFile();

                    event.setLine(0, chatUtils.utilsChat(configFile.getLine1()));
                    forceSetBlank(event, 1);
                }
            }
        }
    }
}
