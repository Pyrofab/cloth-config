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

package me.shedaniel.clothconfig2.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ClothRequiresRestartScreen extends ConfirmScreen {
    public ClothRequiresRestartScreen(Screen parent) {
        super(t -> {
            if (t)
                MinecraftClient.getInstance().scheduleStop();
            else
                MinecraftClient.getInstance().openScreen(parent);
        }, new TranslatableText("text.cloth-config.restart_required"), new TranslatableText("text.cloth-config.restart_required_sub"), new TranslatableText("text.cloth-config.exit_minecraft"), new TranslatableText("text.cloth-config.ignore_restart"));
    }
}
