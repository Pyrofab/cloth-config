/*
 * This file is part of Cloth Config.
 * Copyright (C) 2020 shedaniel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class TextDescriptionBuilder extends FieldBuilder<Text, TextListEntry> {
    
    private int color = -1;
    @Nullable private Supplier<Optional<Text[]>> tooltipSupplier = null;
    private final Text value;
    
    public TextDescriptionBuilder(Text resetButtonKey, Text fieldNameKey, Text value) {
        super(resetButtonKey, fieldNameKey);
        this.value = value;
    }
    
    @Override
    public void requireRestart(boolean requireRestart) {
        throw new UnsupportedOperationException();
    }
    
    public TextDescriptionBuilder setTooltipSupplier(Supplier<Optional<Text[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }
    
    public TextDescriptionBuilder setTooltip(Optional<Text[]> tooltip) {
        this.tooltipSupplier = () -> tooltip;
        return this;
    }
    
    public TextDescriptionBuilder setTooltip(Text... tooltip) {
        this.tooltipSupplier = () -> Optional.ofNullable(tooltip);
        return this;
    }
    
    public TextDescriptionBuilder setColor(int color) {
        this.color = color;
        return this;
    }
    
    @NotNull
    @Override
    public TextListEntry build() {
        return new TextListEntry(getFieldNameKey(), value, color, tooltipSupplier);
    }
    
}
