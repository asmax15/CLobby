package me.asmax.clobby.util

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

class ItemBuilder @JvmOverloads constructor(material: Material?, subID: Short = 0.toShort()) {
    private val item: ItemStack
    private val itemMeta: ItemMeta

    init {
        item = ItemStack(material!!, 1, subID)
        itemMeta = item.itemMeta
    }

    fun setName(name: String?): ItemBuilder {
        itemMeta.setDisplayName(name)
        return this
    }

    fun setLore(vararg lore: String?): ItemBuilder {
        itemMeta.lore = Arrays.asList(*lore)
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        item.amount = amount
        return this
    }

    fun addUnsafeEnchantment(enchantment: Enchantment?, level: Int?): ItemBuilder {
        itemMeta.addEnchant(enchantment!!, level!!, true)
        return this
    }

    fun hideEnchantments(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        return this
    }

    fun hideAttributes(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        return this
    }

    fun hidePlaceOn(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON)
        return this
    }

    fun hideUnbreakable(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        return this
    }

    fun hideDestroys(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS)
        return this
    }

    fun hideDye(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE)
        return this
    }

    fun build(): ItemStack {
        item.itemMeta = itemMeta
        return item
    }
}