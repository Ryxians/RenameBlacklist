package ham.renameblacklist;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RenameBlacklist extends JavaPlugin {

    FileConfiguration config;

    private static RenameBlacklist instance;

    public List<String> blacklist;

    public String ignored;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();

        ignored = "[" + config.getString("Ignored") + "]";

        blacklist = config.getStringList("Blacklist");

        blacklist.forEach(i -> blacklist.set(blacklist.indexOf(i), ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', i.toLowerCase().replaceAll(ignored, "")))));
        instance = this;

        getServer().getPluginManager().registerEvents(new Listener(), this);
        getCommand("itemname").setExecutor(new MyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RenameBlacklist getInstance() {
        return instance;
    }

    public static void success(CommandSender sender, String str) {
        sender.sendMessage(ChatColor.GREEN + "[HAM] " + ChatColor.GOLD + str);
    }

    public static void fail(CommandSender sender, String str) {
        sender.sendMessage(ChatColor.DARK_RED + "[HAM] " + ChatColor.RED + str);
    }
}
