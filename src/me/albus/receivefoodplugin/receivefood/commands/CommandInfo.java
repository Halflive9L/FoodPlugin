package me.albus.receivefoodplugin.receivefood.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by lande on 15/08/2017.
 */
public class CommandInfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            player.sendMessage("Welcome to our test plugin!");
        }
        return true;
    }
}
