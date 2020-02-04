package de.TntTastisch.BlackNerux.api;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemAPI {

  private ItemStack stack;
  
  public ItemAPI(Material material)
  {
    stack = new ItemStack(material);
  }
  
  public ItemAPI(Material material, int data) {
    stack = new ItemStack(material, 1, (short)data);
  }
  
  public ItemAPI setAmount(int amount) {
    stack.setAmount(amount);
    return this;
  }
  
  public ItemAPI setDisplayname(String name) {
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(name);
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI setUnbreakable(boolean unbreakable) {
    ItemMeta meta = stack.getItemMeta();
    meta.spigot().setUnbreakable(unbreakable);
    return this;
  }
  
  public ItemAPI setMaterial(Material material) {
    stack.setType(material);
    return this;
  }
  
  public ItemAPI setLore(List<String> list) {
    ItemMeta meta = stack.getItemMeta();
    meta.setLore(list);
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI addLore(String lore) {
    ItemMeta meta = stack.getItemMeta();
    if (!meta.hasLore()) {
      List<String> newLore = new java.util.ArrayList();
      newLore.add(lore);
      meta.setLore(newLore);
    } else {
      List<String> itemLore = meta.getLore();
      itemLore.add(lore);
      meta.setLore(itemLore);
    }
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI addEnchantment(Enchantment enchantment, int level) {
    stack.addEnchantment(enchantment, level);
    return this;
  }
  
  public ItemAPI addUnsafeEnchantment(Enchantment enchantment, int level) {
    stack.addUnsafeEnchantment(enchantment, level);
    return this;
  }
  
  public ItemAPI setColor(Color color) {
    LeatherArmorMeta meta = (LeatherArmorMeta)stack.getItemMeta();
    meta.setColor(color);
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI hideEnchantment() {
    ItemMeta meta = stack.getItemMeta();
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI hideAttributes() {
    ItemMeta meta = stack.getItemMeta();
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI hidePlaceOn() {
    ItemMeta meta = stack.getItemMeta();
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_PLACED_ON });
    stack.setItemMeta(meta);
    return this;
  }
  
  public ItemAPI setGlowing() {
    ItemMeta meta = stack.getItemMeta();
    meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    meta.addEnchant(Enchantment.DURABILITY, 1, false);
    stack.setItemMeta(meta);
    return this;
  }
  
  public static ItemStack SkullBuilder(String name, String owner) {
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
    SkullMeta meta = (SkullMeta)skull.getItemMeta();
    meta.setDisplayName(name);
    meta.setOwner(owner);
    skull.setItemMeta(meta);
    return skull;
  }

  public static ItemStack SkullBuilder(String name, String owner, List<String> lore) {
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
    SkullMeta meta = (SkullMeta)skull.getItemMeta();
    meta.setDisplayName(name);
    meta.setOwner(owner);
    meta.setLore(lore);
    skull.setItemMeta(meta);

    return skull;
  }
  
  public ItemStack PotionBuilder(String name, int amount, int potionid)
  {
    ItemStack stack = new ItemStack(Material.POTION, amount, (short)potionid);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(name);
    stack.setItemMeta(meta);
    
    return stack;
  }
  
  public ItemStack PotionBuilder(String name, int amount, int potionid, String... lore)
  {
    ItemStack stack = new ItemStack(Material.POTION, amount, (short)potionid);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(name);
    if (lore != null) {
      meta.setLore(Arrays.asList(lore));
    }
    stack.setItemMeta(meta);
    
    return stack;
  }
  
  public ItemStack create() {
    return stack;
  }
  
  public ItemMeta getMeta() {
    return stack.getItemMeta();
  }

}
