package com.obsidiskrivemaskine.Proxy;

import com.obsidiskrivemaskine.GUI.CustomCow;
import com.obsidiskrivemaskine.ObsidiSkriveMaskineMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Lee on 06-04-2016.
 */
public class ObsidiClientProxy extends ObsidiServerProxy
{
    public void registerRenderThings() {

    }

    public void registerModels()
    {

        //CustomCow.RegisterEntity();
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(ObsidiSkriveMaskineMod.obsidiblock), 0, new ModelResourceLocation(ObsidiSkriveMaskineMod.MODID + ":KurtSkriveMaskineBlock", "inventory"));
    }
}