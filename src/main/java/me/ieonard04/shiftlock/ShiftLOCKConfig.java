package me.ieonard04.shiftlock;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ShiftLOCKConfig implements ModMenuApi {
    public static boolean OR = false;

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Set up all builders
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("config.shiftlock.title"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // Set up a dummy category
            ConfigCategory category = builder.getOrCreateCategory(Text.translatable("dummy"));

            category.addEntry(
                    entryBuilder.startBooleanToggle(Text.translatable("config.shiftlock.OR"), OR)
                            .setDefaultValue(false)
                            .setTooltip(Text.translatable("config.shiftlock.OR.tooltip"))
                            .setSaveConsumer(newValue -> OR = newValue)
                            .build()
            );

            return builder.build();
        };
    }
}
