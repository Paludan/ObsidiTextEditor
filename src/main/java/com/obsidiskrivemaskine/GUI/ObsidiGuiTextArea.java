package com.obsidiskrivemaskine.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Lee on 08-04-2016.
 */

@SideOnly(Side.CLIENT)
public class ObsidiGuiTextArea extends Gui
{
    private FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;

    public ObsidiGuiTextArea()
    {
        super();
    }

    /* this is simply used to create an abstraction layer */
    public void drawSplitLines (String str, int x, int y, int wrapWidth, int color)
    {
        renderer.drawSplitString(str, x, y, wrapWidth, color);
    }
}
