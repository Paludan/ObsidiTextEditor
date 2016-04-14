package com.obsidiskrivemaskine.GUI;

import com.obsidiskrivemaskine.ObsidiSkriveMaskineMod;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Lee on 06-04-2016.
 */
public class ObidiGUIScreen extends GuiScreen
{
    private ResourceLocation skrivemaskinegui = new ResourceLocation(ObsidiSkriveMaskineMod.MODID + ":Textures/GUI/obsidiskrivemaskinegui.png");

    private StringBuilder text = new StringBuilder();
    private int cursorLocation = 0;
    private char cursor = '_'; // cursor symbol
    private int saveButton;
    File obsidiFile;
    FileWriter obsidiFileWriter;
    private ObisidiGuiTextArea textbox = new ObisidiGuiTextArea();

    @Override
    public void initGui() {

        saveButton = 0;
        this.buttonList.add(new GuiButton(saveButton, this.width / 2, this.height / 2 + 200, 100, 20, "Save and Exit"));

        text.append('_');
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id) {
            case 0:

                try
                {
                    /* Closes screen and saves editor text to "test.oc" in the run folder */
                    mc.thePlayer.closeScreen();
                    text.deleteCharAt(cursorLocation);
                    obsidiFile = new File ("test.oc");
                    obsidiFileWriter = new FileWriter(obsidiFile);
                    obsidiFileWriter.write(text.toString());
                    obsidiFileWriter.flush();
                    obsidiFileWriter.close();

                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        mc.getTextureManager().bindTexture(skrivemaskinegui);

        this.drawTexturedModalRect(this.width / 2 - 128, this.height / 2 - 128, 0, 0, 256, 256);


        /* this is where the editor reads keys and writes to the screen */
        if (org.lwjgl.input.Keyboard.getEventKeyState()) {
             //
            if (isAllowedDeletion())
            {
                text.deleteCharAt(cursorLocation);
                cursorLocation--;
                text.deleteCharAt(cursorLocation);
                text.insert(cursorLocation, cursor); // moving cursor
            } else if (isAllowedNewLine()) {
                text.deleteCharAt(cursorLocation);
                text.insert(cursorLocation,'\n');
                cursorLocation++;
                text.insert(cursorLocation, cursor); // moving cursor
            } else if (isAllowedLeftArrowNavigation()){
                text.deleteCharAt(cursorLocation);
                cursorLocation--;
                text.insert(cursorLocation, cursor); // moving cursor

            } else if (isAllowedRightArrowNavigation()) {
                text.deleteCharAt(cursorLocation);
                cursorLocation++;
                text.insert(cursorLocation, cursor); // moving cursor
            } else {
                if (isAllowedCharacters())
                {
                    System.out.println(org.lwjgl.input.Keyboard.getEventKey());

                    text.deleteCharAt(cursorLocation);
                    text.insert(cursorLocation, org.lwjgl.input.Keyboard.getEventCharacter());
                    cursorLocation++;
                    text.insert(cursorLocation, cursor); // moving cursor
                }
            }

            org.lwjgl.input.Keyboard.destroy();
        } else if (!org.lwjgl.input.Keyboard.isCreated()) {
            try {
                org.lwjgl.input.Keyboard.create();
            } catch (Exception e)
            {}
        }

        /* writes the text string to the screen */
        textbox.drawSplitLines(text.toString(), this.width / 2 - 128, this.height / 2 -128, 256 , 0xFF8000);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /* returns true if pressed button is allowed for "writing" */
    private boolean isAllowedCharacters ()
    {
        if ( (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_BACK)
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_LEFT )
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_RIGHT)
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_UP)
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_DOWN)
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_RSHIFT)
                && (org.lwjgl.input.Keyboard.getEventKey() != org.lwjgl.input.Keyboard.KEY_LSHIFT))
            return true;
        else
            return false;
    }

    /* returns true if the users right arrow navigation is allowed */
    private boolean isAllowedRightArrowNavigation ()
    {
        if (org.lwjgl.input.Keyboard.getEventKey() == org.lwjgl.input.Keyboard.KEY_RIGHT && cursorLocation < text.length() - 1)
            return true;
        else
            return false;
    }

    /* returns true if the users left arrow navigation is allowed */
    private boolean isAllowedLeftArrowNavigation ()
    {
        if (org.lwjgl.input.Keyboard.getEventKey() == org.lwjgl.input.Keyboard.KEY_LEFT && cursorLocation < text.length() - 1)
            return true;
        else
            return false;
    }

    /* returns true if the key press is the return key */
    private boolean isAllowedNewLine ()
    {
        if (org.lwjgl.input.Keyboard.getEventKey() == org.lwjgl.input.Keyboard.KEY_RETURN)
            return true;
        else
            return false;
    }

    /* returns true if deletion is allowed */
    private boolean isAllowedDeletion()
    {
        if ( (org.lwjgl.input.Keyboard.getEventKey() == org.lwjgl.input.Keyboard.KEY_BACK) && (text.length() > 1) && (cursorLocation > 0) )
            return true;
        else
            return false;
    }
}
