package me.albus.receivefoodplugin.receivefood.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pressurePlateListener implements Listener {
    private boolean inEatGui = false;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType() == Material.STONE_PLATE && e.getClickedBlock().getState().isPlaced()) {
                Inventory inv = createInventory(player);
                createGlassPanesSurronding(inv);
                inv.addItem(createInventoryFood(Material.APPLE, ChatColor.GOLD + "-[Breakfast]-", ChatColor.BLUE + "Always nice; to have a; breakfast"));
                inv.addItem(createInventoryFood(Material.GRILLED_PORK, "Dinner", "Always nice; to have; dinner;"));
                inv.addItem(createInventoryFood(Material.COOKED_BEEF, "Dinner2", "Always nice; to have; dinner;"));
                inv.addItem(createInventoryFood(Material.BREAD, "Lunch", "Always nice; to have; lunch;"));
                inv.addItem(createInventoryFood(Material.CAKE, "Dessert", "Always nice; to have; Dessert;"));
                createGlassPanesSurronding(inv);
                player.openInventory(inv);
                inEatGui = true;
            }
        }
    }

    private ItemStack createInventoryFood(Material material, String displayName, String itemLore) {
        ItemStack stack = new ItemStack(material, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(createLore(itemLore));
        stack.setItemMeta(meta);
        return stack;
    }

    private ArrayList<String> createLore(String itemLore) {
        ArrayList<String> lore = new ArrayList<String>();
        Collections.addAll(lore, itemLore.split(";"));
        return lore;
    }

    private void createGlassPanesSurronding(Inventory inv) {
        for (int i = 0; i < 11; i++) {
            ItemStack stackPane;
            if (i % 2 == 0) {
                stackPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PURPLE.getData());
            } else {
                stackPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData());
            }
            ItemMeta metaPane = stackPane.getItemMeta();
            metaPane.setDisplayName(" ");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Choose an option!");
            metaPane.setLore(lore);
            stackPane.setItemMeta(metaPane);
            inv.setItem(inv.firstEmpty(), stackPane);

        }
    }

    private Inventory createInventory(Player player) {
        Inventory inv = Bukkit.createInventory(player, 3 * 9);
        inv.setMaxStackSize(1);
        return inv;
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        if (inEatGui) {
            inEatGui = false;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!e.getCurrentItem().getItemMeta().hasDisplayName() || e.getCurrentItem() == null) {
            e.setCancelled(true);
        }
        if (inEatGui && e.getCurrentItem().getItemMeta().hasDisplayName()) {
            if (e.getCurrentItem().getType() == Material.APPLE) {
                addFood(player, new ItemStack(Material.APPLE, 10));
            } else if (e.getCurrentItem().getType() == Material.GRILLED_PORK) {
                addFood(player, new ItemStack(Material.GRILLED_PORK, 10));
            } else if (e.getCurrentItem().getType() == Material.COOKED_BEEF) {
                addFood(player, new ItemStack(Material.COOKED_BEEF, 10));
            } else if (e.getCurrentItem().getType() == Material.BREAD) {
                addFood(player, new ItemStack(Material.BREAD, 10));
            } else if (e.getCurrentItem().getType() == Material.CAKE) {
                addFood(player, new ItemStack(Material.CAKE, 1));
            }
            e.setCancelled(true);
        }
    }

    private void addFood(Player player, ItemStack itemStack) {
        player.getInventory().addItem(itemStack);
        player.closeInventory();
    }
}