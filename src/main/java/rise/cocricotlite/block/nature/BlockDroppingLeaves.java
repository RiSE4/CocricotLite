package rise.cocricotlite.block.nature;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import rise.cocricotlite.Tabs;
import rise.cocricotlite.block.BaseBlock;
import rise.cocricotlite.item.CommonItemBlock;
import rise.cocricotlite.util.AABBList;
import rise.cocricotlite.util.Helper;
import rise.cocricotlite.util.type.EnumDroopingLeaves;
import rise.cocricotlite.util.type.PropertyList;

public class BlockDroppingLeaves extends BaseBlock {

    public BlockDroppingLeaves()
    {
        super("drooping_leaves", Material.GRASS, Tabs.TAB_NATURE, SoundType.GROUND, 0.5F, 1F);
        this.setCollisionBox(AABBList.AABB_NONE);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PropertyList.DROOPING_LEAVES_TYPE, EnumDroopingLeaves.GREEN));
        this.register(new CommonItemBlock(this, itemStack -> EnumDroopingLeaves.byMetadata(itemStack.getMetadata()).getName()));
    }

    @Override
    public void registerModels()
    {
        Helper.forItemModels(this, 2, "nature", new String[]{"drooping_leaves_green", "drooping_leaves_light_green", "drooping_leaves_dark_green"});
    }

    public int damageDropped(IBlockState state)
    {
        return state.getValue(PropertyList.DROOPING_LEAVES_TYPE).getMetadata();
    }

    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        Item item = Item.getItemFromBlock(this);

        for (int meta = 0; meta < EnumDroopingLeaves.values().length; ++meta)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(PropertyList.DROOPING_LEAVES_TYPE, EnumDroopingLeaves.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(PropertyList.DROOPING_LEAVES_TYPE).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PropertyList.DROOPING_LEAVES_TYPE);
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}