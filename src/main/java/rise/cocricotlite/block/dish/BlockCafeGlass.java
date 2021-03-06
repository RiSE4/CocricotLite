package rise.cocricotlite.block.dish;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import rise.cocricotlite.Tabs;
import rise.cocricotlite.block.BaseBlock;
import rise.cocricotlite.item.CommonItemBlock;
import rise.cocricotlite.util.AABBList;
import rise.cocricotlite.util.Helper;
import rise.cocricotlite.util.type.dish.EnumCafeGlass;
import rise.cocricotlite.util.type.PropertyList;

public class BlockCafeGlass extends BaseBlock {

    public BlockCafeGlass()
    {
        super("cafe_glass", Material.GLASS, Tabs.TAB_DISH, SoundType.GLASS, 0.5F, 1F);
        this.setBoundingBox(AABBList.AABB_CUBE_SMALL);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PropertyList.CAFE_GLASS_TYPE, EnumCafeGlass.LATTE));
        this.register(new CommonItemBlock(this, itemStack -> EnumCafeGlass.byMetadata(itemStack.getMetadata()).getName()));
    }

    @Override
    public void registerModels()
    {
        Helper.forItemModels(this, "dish", EnumCafeGlass.class, EnumCafeGlass.values().length);
    }

    public int damageDropped(IBlockState state)
    {
        return state.getValue(PropertyList.CAFE_GLASS_TYPE).getMetadata();
    }

    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        Helper.forCreativeTab(this, list, EnumCafeGlass.values().length);

    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(PropertyList.CAFE_GLASS_TYPE, EnumCafeGlass.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(PropertyList.CAFE_GLASS_TYPE).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PropertyList.CAFE_GLASS_TYPE);
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