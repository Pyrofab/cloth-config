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

package me.shedaniel.clothconfig2.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicNewSmoothScrollingEntryListWidget<E> {
    
    public DynamicElementListWidget(MinecraftClient client, int width, int height, int top, int bottom, Identifier backgroundLocation) {
        super(client, width, height, top, bottom, backgroundLocation);
    }
    
    public boolean changeFocus(boolean boolean_1) {
        boolean boolean_2 = super.changeFocus(boolean_1);
        if (boolean_2)
            this.ensureVisible(this.getFocused());
        return boolean_2;
    }
    
    protected boolean isSelected(int int_1) {
        return false;
    }
    
    @Environment(EnvType.CLIENT)
    public abstract static class ElementEntry<E extends ElementEntry<E>> extends Entry<E> implements ParentElement {
        private Element focused;
        private boolean dragging;
        
        public ElementEntry() {
        }
        
        public boolean isDragging() {
            return this.dragging;
        }
        
        public void setDragging(boolean boolean_1) {
            this.dragging = boolean_1;
        }
        
        public Element getFocused() {
            return this.focused;
        }
        
        public void setFocused(Element element_1) {
            this.focused = element_1;
        }
    }
}

