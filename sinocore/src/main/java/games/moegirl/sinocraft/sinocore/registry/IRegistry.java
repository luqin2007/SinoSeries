package games.moegirl.sinocraft.sinocore.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.function.Supplier;

/**
 * 用于注册元素的注册表类型
 *
 * @param <T> 元素类型
 */
public interface IRegistry<T> {

    /**
     * 获取当前 Mod Id
     *
     * @return Mod id
     */
    String getModId();

    /**
     * 向 MC 注册该注册表修改
     */
    void register();

    /**
     * 向该注册表注册内容
     *
     * @param name 注册名，实际为 modid:name
     * @return 对象引用
     */
    <R extends T> IRef<T, R> register(String name, Supplier<? extends R> supplier);

    /**
     * 创建或获取某个 Tag
     *
     * @param name Tag 名
     * @return TagKey
     */
    TagKey<T> createTag(ResourceLocation name);

    /**
     * 创建或获取某个 Tag
     *
     * @param name Tag 名，实际为 modid:name
     * @return TagKey
     */
    default TagKey<T> createTag(String name) {
        return createTag(new ResourceLocation(getModId(), name));
    }
}
