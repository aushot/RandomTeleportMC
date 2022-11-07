package randomteleport.randomteleport.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import randomteleport.randomteleport.Utils.ChatUtils;

public class OnSignChanged implements Listener {
    @EventHandler
    public void onSignChange(SignChangeEvent event){
        if(event.getPlayer().hasPermission("randomteleport.admin") || event.getPlayer().isOp()){
            if(event.getLine(0).equalsIgnoreCase("randomtp")){
                ChatUtils chatUtils = new ChatUtils();

                event.setLine(0, chatUtils.utilsChat("&4[RandomTP]"));
            }
        }
    }
}
