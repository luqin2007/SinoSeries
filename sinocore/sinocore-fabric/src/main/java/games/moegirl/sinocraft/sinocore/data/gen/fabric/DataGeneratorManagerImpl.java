package games.moegirl.sinocraft.sinocore.data.gen.fabric;

import games.moegirl.sinocraft.sinocore.data.gen.IDataGenContext;
import games.moegirl.sinocraft.sinocore.data.gen.IDataGenerator;
import net.minecraft.data.DataProvider;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;
import java.util.function.Supplier;

public class DataGeneratorManagerImpl {
    public static IDataGenerator _createDataProvider(String modId) {
//        throw new NotImplementedException("Fabric data generator is not supported now!");
        return new IDataGenerator() {
            @Override
            public <T extends DataProvider> Supplier<T> put(Function<IDataGenContext, ? extends T> builder, boolean run) {
                return () -> null;
            }
        };
    }
}
