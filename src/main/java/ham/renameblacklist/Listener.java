package ham.renameblacklist;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listener implements org.bukkit.event.Listener {

    RenameBlacklist instance = RenameBlacklist.getInstance();

    @EventHandler
    public void colorAnvil(PrepareAnvilEvent evt){
        String str = evt.getInventory().getRenameText();
        System.out.println("Has Perm: " + evt.getView().getPlayer().hasPermission("renameblacklist.coloredanvils"));
        if (!instance.blacklist.contains(str) && evt.getView().getPlayer().hasPermission("renameblacklist.coloredanvils")) {
            String stv = ChatColor.translateAlternateColorCodes('&', str);
            System.out.println(stv);
            try {
                ItemStack result = evt.getResult();

                ItemMeta meta = result.getItemMeta();
                meta.setDisplayName(stv);

                result.setItemMeta(meta);
                evt.setResult(result);
            } catch (NullPointerException e) {

            } catch (Exception e) {
                System.out.println("woah something went wrong");
                e.printStackTrace();
            }
        }
    }
}
