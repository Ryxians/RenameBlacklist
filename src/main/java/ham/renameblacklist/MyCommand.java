package ham.renameblacklist;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Locale;

public class MyCommand implements CommandExecutor {
    RenameBlacklist plugin = RenameBlacklist.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null || item.getType() == Material.AIR) {
                plugin.fail(sender, "You cannot rename air.");
            } else {
                String str = "";
                String name = "";
                if (args.length == 0) {
                    str = item.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
                    name = null;
                } else {
                    for (String string : args) {
                        str += string;
                        str += " ";
                    }
                    str = str.trim();
                    if (!plugin.blacklist.contains(str.toLowerCase())) {
                        str = ChatColor.translateAlternateColorCodes('&', str);
                    }
                    name = str;
                }


                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);

                plugin.success(sender, "Item successfully renamed to: " + ChatColor.RESET + str);
            }
        } else {
            plugin.fail(sender, "You must be a player to run this command.");
        }

        return true;
    }
}
