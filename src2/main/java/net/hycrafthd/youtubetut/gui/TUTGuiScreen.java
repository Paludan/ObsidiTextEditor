package net.hycrafthd.youtubetut.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.youtubetut.TUTMain;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

public class TUTGuiScreen extends GuiScreen {
	
	private ResourceLocation tutguiback = new ResourceLocation(TUTMain.MODID + ":textures/gui/tutgui.png");
	
	private String hallowelt;
	
	private int closebutton;
	
	private int actionbutton;
	
	private GuiTextField textbox;
	
	private int textboxid;
	
	/*public TUTGuiScreen() {
		
	}*/
	
	@Override
	public void initGui() {
		hallowelt = "Hallo Welt!";
		
		closebutton = 0;
		
		actionbutton = 1;
		
		textboxid = 0;
		
		textbox = new GuiTextField(textboxid, fontRendererObj, width / 2 - 32, 130, 64, 20);
		
		textbox.setFocused(true);
		
		Keyboard.enableRepeatEvents(true);
		
		this.buttonList.add(new GuiButton(closebutton, width - 20, height - height, 20, 20, "x"));
		
		this.buttonList.add(new GuiButton(actionbutton, width / 2 - 32, 100, 64, 20, "Tryk på mig dit svin!"));
		
		super.initGui();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		textbox.textboxKeyTyped(typedChar, keyCode);
		
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 0:
			this.mc.thePlayer.closeScreen();
			break;
		case 1:
			hallowelt = textbox.getText();
		default:
			break;
		}
		super.actionPerformed(button);
	}
	
	@Override
	public void updateScreen() {
		
		textbox.updateCursorCounter();
		
		super.updateScreen();
	}
	
	int c = 0;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		this.drawDefaultBackground();
		
		mc.getTextureManager().bindTexture(tutguiback);
		
		this.drawTexturedModalRect(this.width / 2 - 128, this.height / 2 - 128, 0, 0, 256, 256);
		
		textbox.drawTextBox();
		
		this.fontRendererObj.drawString(hallowelt, width / 2 - 27, 50, 0xFF8000);
		
		this.fontRendererObj.drawString(String.valueOf(c), width / 2 - 100, 70, 0x0000FF);
		
		c++;
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
