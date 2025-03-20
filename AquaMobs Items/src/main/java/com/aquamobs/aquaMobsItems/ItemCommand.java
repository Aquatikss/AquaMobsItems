package com.aquamobs.aquaMobsItems;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;

public class ItemCommand implements CommandExecutor {

    MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            ItemStack item;
            if (args.length >= 1) {
                switch (args[0]) {
                    case "get":
                        item = (ItemStack) (ConfigUtil.getConfig().get("items." + args[1]));
                        p.getInventory().addItem(item);
                        p.sendMessage(miniMessage.deserialize("<#11EE11>Successfully been given " + args[1] + "!"));
                        break;
                    case "set":
                        item = p.getItemInHand().asOne();
                        ConfigUtil.getConfig().set("items." + args[1], item);
                        try {
                            ConfigUtil.getConfig().save(ConfigUtil.file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        p.sendMessage(miniMessage.deserialize("<#11EE11>Successfully set " + args[1] + " to " + item + "!"));
                        break;
                    case "delete":
                        ConfigUtil.getConfig().set("items." + args[1], null);
                        p.sendMessage(miniMessage.deserialize("<#11EE11>Successfully deleted " + args[1] + "!"));
                        break;
                    default:
                        p.sendMessage(miniMessage.deserialize("<#EE1111>Please use get/set/delete for the first argument!"));
                        return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
