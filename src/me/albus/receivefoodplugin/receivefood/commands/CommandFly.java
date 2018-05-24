package me.albus.receivefoodplugin.receivefood.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Created by lande on 15/08/2017.
 */
public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            PermissionUser user = PermissionsEx.getUser(player);
            if(user.inGroup("Admin")){
                toggleFlight(player);
            } else {
                player.sendMessage("You are not allowed to use this!");
            }
        }
        return true;
    }

    private void toggleFlight(Player player) {
        if (player.getAllowFlight()) {
            executeFly(player, false, "disabled");
        } else {
            executeFly(player, true, "enabled");
        }
    }

    private void executeFly(Player player, boolean b, String statusMessage) {
        player.setAllowFlight(b);
        player.sendMessage(String.format("Flying %s for %s.", statusMessage, player.getDisplayName()));
    }

}
