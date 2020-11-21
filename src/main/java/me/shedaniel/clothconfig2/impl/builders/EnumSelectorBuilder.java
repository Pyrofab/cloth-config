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

import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class EnumSelectorBuilder<T extends Enum<?>> extends FieldBuilder<T, EnumListEntry<T>> {
    
    private Consumer<T> saveConsumer = null;
    private Function<T, Optional<Text[]>> tooltipSupplier = e -> Optional.empty();
    private final T value;
    private final Class<T> clazz;
    private Function<Enum, Text> enumNameProvider = EnumListEntry.DEFAULT_NAME_PROVIDER;
    
    public EnumSelectorBuilder(Text resetButtonKey, Text fieldNameKey, Class<T> clazz, T value) {
        super(resetButtonKey, fieldNameKey);
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(value);
        this.value = value;
        this.clazz = clazz;
    }
    
    public EnumSelectorBuilder<T> setErrorSupplier(Function<T, Optional<Text>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }
    
    public EnumSelectorBuilder<T> requireRestart() {
        requireRestart(true);
        return this;
    }
    
    public EnumSelectorBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }
    
    public EnumSelectorBuilder<T> setDefaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
    
    public EnumSelectorBuilder<T> setDefaultValue(T defaultValue) {
        Objects.requireNonNull(defaultValue);
        this.defaultValue = () -> defaultValue;
        return this;
    }
    
    public EnumSelectorBuilder<T> setTooltipSupplier(Function<T, Optional<Text[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }
    
    public EnumSelectorBuilder<T> setTooltipSupplier(Supplier<Optional<Text[]>> tooltipSupplier) {
        this.tooltipSupplier = e -> tooltipSupplier.get();
        return this;
    }
    
    public EnumSelectorBuilder<T> setTooltip(Optional<Text[]> tooltip) {
        this.tooltipSupplier = e -> tooltip;
        return this;
    }
    
    public EnumSelectorBuilder<T> setTooltip(Text... tooltip) {
        this.tooltipSupplier = e -> Optional.ofNullable(tooltip);
        return this;
    }
    
    public EnumSelectorBuilder<T> setEnumNameProvider(Function<Enum, Text> enumNameProvider) {
        Objects.requireNonNull(enumNameProvider);
        this.enumNameProvider = enumNameProvider;
        return this;
    }
    
    @NotNull
    @Override
    public EnumListEntry<T> build() {
        EnumListEntry<T> entry = new EnumListEntry<>(getFieldNameKey(), clazz, value, getResetButtonKey(), defaultValue, saveConsumer, enumNameProvider, null, isRequireRestart());
        entry.setTooltipSupplier(() -> tooltipSupplier.apply(entry.getValue()));
        if (errorSupplier != null)
            entry.setErrorSupplier(() -> errorSupplier.apply(entry.getValue()));
        return entry;
    }
    
}
