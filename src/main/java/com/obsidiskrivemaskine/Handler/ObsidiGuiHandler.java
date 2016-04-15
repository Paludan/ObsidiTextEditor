package com.obsidiskrivemaskine.Handler;

import com.obsidiskrivemaskine.GUI.ObsidiGUIScreen;
import com.obsidiskrivemaskine.ObsidiSkriveMaskineMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Lee on 06-04-2016.
 */
public class ObsidiGuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == ObsidiSkriveMaskineMod.kurtguiid) {
            return new ObsidiGUIScreen();
        }

        return null;
    }

}
