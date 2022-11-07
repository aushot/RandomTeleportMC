package randomteleport.randomteleport;

import org.bukkit.plugin.java.JavaPlugin;
import randomteleport.randomteleport.Listeners.OnSignChanged;
import randomteleport.randomteleport.Listeners.TeleportSignEvent;

public final class Randomteleport extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new OnSignChanged(), this);
        getServer().getPluginManager().registerEvents(new TeleportSignEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
