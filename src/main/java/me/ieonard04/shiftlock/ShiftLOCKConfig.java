package me.ieonard04.shiftlock;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ShiftLOCKConfig implements ModMenuApi {
    public static boolean OR = false;
    private static File file;
    private static final Logger LOGGER = LogManager.getLogger();

    // Checks if the config file has the correct path
    private static void prepareConfigFile(){
        if(file != null) return;
        file = new File(FabricLoader.getInstance().getConfigDir().toFile(), ShiftLOCK.MOD_ID + ".txt");
    }

    public static void load(){
        prepareConfigFile();

        try(FileReader reader = new FileReader(file)){
            if(!file.exists()) {
                save();
            }

            // System.out.println(reader.read());
            OR = reader.read() == '1';

        } catch (IOException e) {
            LOGGER.error("Config file failed to load; Reverting to default values");
            e.printStackTrace();
        }
    }

    // Tries to save the config file
    public static void save(){
        prepareConfigFile();

        LOGGER.info("Saving config file");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(OR ? "1" : "0"); // Stores a 1 for true, 0 for false
        } catch (IOException e) {
            LOGGER.error("Failed to save config file");
            e.printStackTrace();
        }
    }
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

            builder.setSavingRunnable(ShiftLOCKConfig::save);
            return builder.build();
        };
    }
}
