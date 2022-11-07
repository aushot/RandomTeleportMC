package randomteleport.randomteleport;

import org.bukkit.plugin.java.JavaPlugin;
import randomteleport.randomteleport.Listeners.OnSignChanged;
import randomteleport.randomteleport.Listeners.TeleportSignEvent;
import randomteleport.randomteleport.Metrics.Metrics;

public final class Randomteleport extends JavaPlugin {

    @Override
    public void onEnable() {

        int pluginId = 16830; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        getServer().getPluginManager().registerEvents(new OnSignChanged(), this);
        getServer().getPluginManager().registerEvents(new TeleportSignEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
