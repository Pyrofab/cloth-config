package me.shedaniel.clothconfig2.gui.entries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.OrderedText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class TextListEntry extends TooltipListEntry<Object> {
    
    private int savedWidth = -1;
    private int color;
    private Text text;
    
    @ApiStatus.Internal
    @Deprecated
    public TextListEntry(Text fieldName, Text text) {
        this(fieldName, text, -1);
    }
    
    @ApiStatus.Internal
    @Deprecated
    public TextListEntry(Text fieldName, Text text, int color) {
        this(fieldName, text, color, null);
    }
    
    @ApiStatus.Internal
    @Deprecated
    public TextListEntry(Text fieldName, Text text, int color, Supplier<Optional<Text[]>> tooltipSupplier) {
        super(fieldName, tooltipSupplier);
        this.text = text;
        this.color = color;
    }
    
    @Override
    public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
        super.render(matrices, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
        this.savedWidth = entryWidth;
        int yy = y + 4;
        List<OrderedText> strings = MinecraftClient.getInstance().textRenderer.wrapLines(text, savedWidth);
        for (OrderedText string : strings) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, string, x, yy, color);
            yy += MinecraftClient.getInstance().textRenderer.fontHeight + 3;
        }
    }
    
    @Override
    public int getItemHeight() {
        if (savedWidth == -1)
            return 12;
        List<OrderedText> strings = MinecraftClient.getInstance().textRenderer.wrapLines(text, savedWidth);
        if (strings.isEmpty())
            return 0;
        return 15 + strings.size() * 12;
    }
    
    @Override
    public void save() {
        
    }
    
    @Override
    public Object getValue() {
        return null;
    }
    
    @Override
    public Optional<Object> getDefaultValue() {
        return Optional.empty();
    }
    
    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
    
}
