package games.moegirl.sinocraft.sinocore.data.gen.forge;

import games.moegirl.sinocraft.sinocore.data.gen.IDataGenContext;
import games.moegirl.sinocraft.sinocore.data.gen.delegate.BlockModelProviderDelegateBase;
import games.moegirl.sinocraft.sinocore.data.gen.forge.impl.ForgeBlockModelProviderDelegateImpl;

public class AbstractBlockModelProviderImpl {
    public static BlockModelProviderDelegateBase createDelegate(IDataGenContext context) {
        if (context instanceof ForgeDataGenContextImpl impl) {
            return new ForgeBlockModelProviderDelegateImpl(impl);
        }
        throw new ClassCastException("Can't cast " + context + " to ForgeDataGenContextImpl at Forge Platform. " +
                "Use SinoCorePlatform#buildDataGeneratorContext to create this context. " +
                "Don't use context implemented yourself, because it contains different information in different platform");
    }
}
