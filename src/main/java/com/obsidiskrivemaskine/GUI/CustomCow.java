package com.obsidiskrivemaskine.GUI;

import com.obsidiskrivemaskine.ObsidiSkriveMaskineMod;
import com.obsidiskrivemaskine.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CustomCow extends EntityAnimal
{
    public CustomCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.3F);
        ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
    }

    public static void RegisterEntity(){
        EntityRegistry.registerModEntity(CustomCow.class, "CustomCow", 255, ObsidiSkriveMaskineMod.INSTANCE, 64, 20, true);
        EntityRegistry.registerEgg(CustomCow.class, Utils.ColorFromRGB(137, 91, 0), Utils.ColorFromRGB(88, 62, 0));
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
    }

    @Override
    public void applyEntityCollision(Entity entityIn)
    {
    }

    public void breakBlock(BlockPos blockpos, Block blockToBreak){
        blockToBreak.breakBlock(worldObj, blockpos, blockToBreak.getDefaultState());
    }

    @Override
    public boolean canBePushed(){
        return false;
    }
    /* Makes the entity immovable.
    @Override
    public void moveEntity(double x, double y, double z){

    }*/

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.cow.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected Item getDropItem()
    {
        return Items.leather;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {

    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.bucket && !player.capabilities.isCreativeMode && !this.isChild())
        {
            if (itemstack.stackSize-- == 1)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.milk_bucket));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
            }

            return true;
        }
        else
        {
            return super.interact(player);
        }
    }

    public CustomCow createChild(EntityAgeable ageable)
    {
        return new CustomCow(this.worldObj);
    }

    public float getEyeHeight()
    {
        return this.height;
    }
}