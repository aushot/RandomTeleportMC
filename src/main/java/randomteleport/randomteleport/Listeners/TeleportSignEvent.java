package randomteleport.randomteleport.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import randomteleport.randomteleport.Utils.ChatUtils;

import java.util.Random;

public class TeleportSignEvent implements Listener {
    @EventHandler
    public void onSignClick(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.OAK_SIGN){
                Sign sign = (Sign) event.getClickedBlock().getState();
                ChatUtils chatUtils = new ChatUtils();

                if(sign.getLine(0).equalsIgnoreCase(chatUtils.utilsChat("&4[RandomTP]"))){
                    Player player = event.getPlayer();
                    World world = player.getWorld();
                    Location playerLocation = player.getLocation();

                    Random random = new Random();
                    int random_X = random.nextInt(200);
                    int random_Z = random.nextInt(200);
                    int random_Y = world.getHighestBlockYAt(random_X, random_Z);

                    Location newLocation = new Location(world, playerLocation.getX()+random_X, random_Y, playerLocation.getZ()+random_Z);

                    player.teleport(newLocation);
                }

            }
        }
    }
}
