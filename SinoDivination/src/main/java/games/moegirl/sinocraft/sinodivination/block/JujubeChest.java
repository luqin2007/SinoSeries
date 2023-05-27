package games.moegirl.sinocraft.sinodivination.block;

import games.moegirl.sinocraft.sinocore.block.BaseChestBlock;
import games.moegirl.sinocraft.sinocore.tree.Tree;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.BlockHitResult;

public class JujubeChest extends BaseChestBlock {

    public JujubeChest(Tree tree, Properties properties) {
        super(properties, tree);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (noSignal(state, level, pos)) {
            return InteractionResult.CONSUME;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    private boolean noSignal(BlockState pState, Level pLevel, BlockPos pPos) {
        if (pLevel.hasNeighborSignal(pPos)) {
            return false;
        }
        ChestType value = pState.getValue(TYPE);
        Direction direction = pState.getValue(FACING);
        if (value == ChestType.LEFT) {
            return !pLevel.hasNeighborSignal(pPos.relative(direction.getClockWise()));
        } else if (value == ChestType.RIGHT) {
            return !pLevel.hasNeighborSignal(pPos.relative(direction.getCounterClockWise()));
        }
        return true;
    }
}
