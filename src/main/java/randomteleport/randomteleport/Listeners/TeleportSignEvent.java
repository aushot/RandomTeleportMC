package randomteleport.randomteleport.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import randomteleport.randomteleport.Config.ConfigFile;
import randomteleport.randomteleport.Utils.ChatUtils;

import java.util.Random;

public class TeleportSignEvent implements Listener {

    public Location newRandomLocation(Player player, int distance){
        World world = player.getWorld();
        Location playerlocation = player.getLocation();
        Random random = new Random();

        int random_X = random.nextInt(distance);
        int random_Z = random.nextInt(distance);
        int random_Y = world.getHighestBlockYAt(random_X, random_Z);

        return new Location(world, playerlocation.getX()+random_X, random_Y, playerlocation.getZ()+random_Z);
    }
    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock() != null) {
                if (event.getClickedBlock().getState() instanceof Sign) {
                    Sign sign = (Sign) event.getClickedBlock().getState();
                    ChatUtils chatUtils = new ChatUtils();
                    ConfigFile configFile = new ConfigFile();

                    //if sign has the same line of config (line1)
                    if (sign.getLine(0).equalsIgnoreCase(chatUtils.utilsChat(configFile.getLine1()))) {
                        if (event.getPlayer().hasPermission("randomteleport.user")) {
                            Player player = event.getPlayer();
                            Location newlocation;

                            //if distance is specified
                            if (!sign.getLine(1).isEmpty()) {
                                try {
                                    int distance = Integer.parseInt(ChatColor.stripColor(sign.getLine(1)));
                                    newlocation = newRandomLocation(player, distance);
                                } catch (NumberFormatException e) {
                                    player.sendMessage("Invalid format of the sign");
                                    return;
                                }
                            } else {
                                //check if all lines are blank
                                for (int i = 1; i < sign.getLines().length; i++) {
                                    if (!sign.getLine(i).isEmpty()) {
                                        return;
                                    }
                                }
                                newlocation = newRandomLocation(player, configFile.getDefaultdistance());
                            }
                            player.teleport(newlocation);
                            player.sendMessage(chatUtils.utilsChat(configFile.getMessage()));
                        }
                    }
                }
            }
        }
    }
}
