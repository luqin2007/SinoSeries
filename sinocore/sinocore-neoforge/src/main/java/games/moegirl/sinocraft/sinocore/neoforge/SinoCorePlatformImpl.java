package games.moegirl.sinocraft.sinocore.neoforge;

import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.nio.file.Path;

public class SinoCorePlatformImpl {

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }
}
