package net.kgomc.ocular.paper;

import net.kgomc.ocular.api.IOcularApi;
import net.kgomc.ocular.paper.migrations.V1_Initial;
import net.kgomc.zelda.builder.Zelda;
import net.kgomc.zelda.database.module.DatabaseModule;
import net.kgomc.zelda.paper.PaperPluginAdapter;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public class OcularPlugin extends JavaPlugin {

    private final OcularApi api;

    public OcularPlugin() {
        this.api = new OcularApi(this);
    }

    @Override
    public void onEnable() {
        Zelda.builder()
                .centralConfig(Path.of(getDataFolder().getPath(), "../", "Zelda").normalize())
                .withDatabase()
                .withUI()
                .withBus()
                .withConfiguration(getDataPath())
                .initialize(new PaperPluginAdapter(this));

        this.registerAndRunDatabaseMigration();

        this.registerApi();
    }

    private void registerApi(){
        getServer().getServicesManager().register(
                IOcularApi.class,
                api,
                this,
                ServicePriority.High
        );
        getLogger().info("Ocular API enabled");
    }

    private void registerAndRunDatabaseMigration(){
        getLogger().info("Running database migrations");
        DatabaseModule databaseModule = Zelda.modules().find(DatabaseModule.class).orElseThrow();
        databaseModule.migrations()
                .register(new V1_Initial())
                .run();
    }

    @Override
    public void onDisable() {

        // Disable this last
        Zelda.shutdown();
    }
}
