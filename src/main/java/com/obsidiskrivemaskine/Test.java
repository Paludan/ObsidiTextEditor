package com.obsidiskrivemaskine;

import com.obsidiskrivemaskine.GUI.CustomCow;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by esben on 15/04/16.
 */
public class Test {
    static World world;
    static EntityPlayer player;
    public static CustomCow cow;

    //This method is called when the block is rightclicked to initialize the world and player.
    public static void hack(World worldIn, EntityPlayer playerIn){
        world = worldIn;
        player = playerIn;
    }

    public void Talk(String msg){
        player.addChatMessage(new ChatComponentText("\247f" + msg));
    }

    public static void move(String direction, double dest, double speed){
        if (direction.equalsIgnoreCase("east"))
            cow.getNavigator().tryMoveToXYZ(cow.posX + dest, cow.posY, cow.posZ, speed);
        else if(direction.equalsIgnoreCase("west"))
            cow.getNavigator().tryMoveToXYZ(cow.posX - dest, cow.posY, cow.posZ, speed);
        else if(direction.equalsIgnoreCase("north"))
            cow.getNavigator().tryMoveToXYZ(cow.posX, cow.posY, cow.posZ - dest, speed);
        else if(direction.equalsIgnoreCase("south"))
            cow.getNavigator().tryMoveToXYZ(cow.posX, cow.posY, cow.posZ + dest, speed);
    }

    public static void dig(String direction) {
        if (direction.equalsIgnoreCase("down"))
            world.destroyBlock(new BlockPos(cow.posX, cow.posY - 1, cow.posZ), false);
        else if (direction.equalsIgnoreCase("up"))
            world.destroyBlock(new BlockPos(cow.posX, cow.posY + 1, cow.posZ), false);
        else if (direction.equalsIgnoreCase("east"))
            world.destroyBlock(new BlockPos(cow.posX + 1, cow.posY, cow.posZ), false);
        else if (direction.equalsIgnoreCase("west"))
            world.destroyBlock(new BlockPos(cow.posX - 1, cow.posY, cow.posZ), false);
        else if (direction.equalsIgnoreCase("north"))
            world.destroyBlock(new BlockPos(cow.posX, cow.posY, cow.posZ - 1), false);
        else if (direction.equalsIgnoreCase("south"))
            world.destroyBlock(new BlockPos(cow.posX, cow.posY, cow.posZ + 1), false);
    }

    public static void createEntity(){
        cow = new CustomCow(world);
    }

    public static void createEntityIfNotPresent(){
        if(cow == null) {
            createEntity();
            spawnEntity();
        }
    }

    public static void spawnEntity(){
        cow.setLocationAndAngles(((double)(int)player.posX) + 5.5, (double)(int)player.posY, ((double)(int)player.posZ) + 0.5, player.rotationYaw, 0.0F);
        for(int i = 0; i < 15; i++)
            cow.spawnExplosionParticle();
        world.spawnEntityInWorld(cow);
    }

    public void spawnChicken(){
        EntityChicken entitychicken = new EntityChicken(world);
        entitychicken.setGrowingAge(-24000);
        entitychicken.setLocationAndAngles(player.posX - 5, player.posY, player.posZ, player.rotationYaw, 0.0F);
        world.spawnEntityInWorld(entitychicken);
    }
}
