package com.precipicegames.zeryl.replenish;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author Zeryl
 */
public class ReplenishBlockListener extends BlockListener {

    private final Replenish plugin;

    public ReplenishBlockListener(Replenish instance) {
        plugin = instance;
    }

    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack inHand = player.getItemInHand();
        int amt = 0;
        short damage = 0;

        if (inHand.getAmount() == 1) {
            PlayerInventory inventory = player.getInventory();            
            
            HashMap<Integer, ? extends ItemStack> invItems = player.getInventory().all(inHand.getTypeId());
            
            for(Map.Entry<Integer, ? extends ItemStack> entry : invItems.entrySet()) {
                int index = entry.getKey();
                
                if(inventory.getHeldItemSlot() == index) {
                    continue;
                }
                else {
                    ItemStack item = entry.getValue();
                    damage = item.getDurability();
                    amt = item.getAmount();
                    inventory.clear(index);
                    break;
                }
            }
            
            ItemStack item = new ItemStack(inHand.getType(), amt, damage);
            player.setItemInHand(item);
            player.updateInventory();
        }
    }
}
