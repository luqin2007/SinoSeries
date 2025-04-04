package games.moegirl.sinocraft.sinobrush.gui.screen;

import games.moegirl.sinocraft.sinobrush.SinoBrush;
import games.moegirl.sinocraft.sinobrush.client.FanRenderer;
import games.moegirl.sinocraft.sinobrush.network.Common2FanLines;
import games.moegirl.sinocraft.sinocore.gui.widgets.entry.TextureEntry;
import games.moegirl.sinocraft.sinocore.network.NetworkManager;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static games.moegirl.sinocraft.sinobrush.client.FanRenderer.MAX_DISPLAY_LINES;
import static games.moegirl.sinocraft.sinobrush.client.FanRenderer.TEXTURE;

public class FanScreen extends Screen {

    private int leftPos, topPos;
    private final int imageWidth, imageHeight;
    private final List<Component> lines;
    private int currentLine = -1;
    private long focusedTime = 0;
    private boolean isChanged = false;

    public FanScreen(List<Component> lines) {
        super(Component.literal("fan"));
        TextureEntry background = (TextureEntry) TEXTURE.get().getWidget("background");
        imageWidth = background.getWidth();
        imageHeight = background.getHeight();
        this.lines = new ArrayList<>(lines);
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        FanRenderer.renderInGui(guiGraphics, font, leftPos, topPos, lines, currentLine, focusedTime);
    }

    private void moveCursorLeft() {
        if (currentLine != -1) {
            currentLine = (currentLine + 1) % MAX_DISPLAY_LINES;
        }
    }

    private void moveCursorRight() {
        if (currentLine != -1) {
            currentLine = (MAX_DISPLAY_LINES + currentLine - 1) % MAX_DISPLAY_LINES;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= leftPos && mouseX < leftPos + imageWidth && mouseY >= topPos && mouseY < topPos + imageHeight) {
            if (currentLine == -1) {
                focusedTime = Util.getMillis();
                currentLine = 0;
            }
        } else {
            currentLine = -1;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            if (currentLine != -1) {
                currentLine = -1;
                return true;
            }
        } else if (keyCode == GLFW.GLFW_KEY_LEFT) {
            moveCursorLeft();
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            if (currentLine != MAX_DISPLAY_LINES - 1) {
                moveCursorLeft();
            }
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            moveCursorRight();
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_BACKSPACE && currentLine != -1) {
            Component component = getComponentAt(currentLine);
            String content = component.getString();
            if (!content.isEmpty()) {
                lines.set(currentLine, Component.literal(content.substring(0, content.length() - 1)));
                isChanged = true;
            } else if (currentLine > 0) {
                moveCursorRight();
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (currentLine >= 0) {
            if (codePoint == '\n') {
                if (currentLine != MAX_DISPLAY_LINES - 1) {
                    moveCursorLeft();
                }
            } else {
                Component component = getComponentAt(currentLine);
                lines.set(currentLine, Component.literal(component.getString() + codePoint));
                isChanged = true;
            }
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    private Component getComponentAt(int i) {
        for (int j = lines.size(); j <= i; j++) {
            lines.add(Component.empty());
        }
        return lines.get(i);
    }

    @Override
    public void onClose() {
        if (isChanged) {
            NetworkManager.sendToServer(new Common2FanLines(lines));
        }
        try {
            SinoBrush.CONFIGURATIONS.getClientConfigs().save();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        super.onClose();
    }
}
