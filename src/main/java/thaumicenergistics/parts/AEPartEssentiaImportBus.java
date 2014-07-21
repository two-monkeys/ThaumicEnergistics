package thaumicenergistics.parts;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.registries.AEPartsEnum;
import thaumicenergistics.texture.BlockTextureManager;
import appeng.api.parts.IPartCollsionHelper;
import appeng.api.parts.IPartRenderHelper;
import appeng.api.util.AEColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AEPartEssentiaImportBus extends AEPartEssentiaIO
{

	public AEPartEssentiaImportBus()
	{
		super( AEPartsEnum.EssentiaImportBus );
	}

	@Override
	public boolean aspectTransferAllowed( Aspect aspect )
	{
		boolean wasAllNull = true;

		for( int i = 0; i < this.filterAspects.length; i++ )
		{
			if ( this.filterAspects[i] != null )
			{
				wasAllNull = false;

				if ( this.filterAspects[i] == aspect )
				{
					return true;
				}
			}
		}

		return wasAllNull;
	}

	@Override
	public int cableConnectionRenderTo()
	{
		return 5;
	}

	@Override
	public boolean doWork( int transferAmount )
	{
		if ( this.facingContainer != null )
		{
			return this.injectEssentaToNetwork( transferAmount );
		}

		return false;
	}

	@Override
	public void getBoxes( IPartCollsionHelper helper )
	{
		// Face + Large chamber
		helper.addBox( 4.0F, 4.0F, 14.0F, 12.0F, 12.0F, 16.0F );
		
		// Small chamber
		helper.addBox( 5.0F, 5.0F, 12.0F, 11.0F, 11.0F, 14.0F );
		
		// Lights
		helper.addBox( 6.0D, 6.0D, 11.0D, 10.0D, 10.0D, 12.0D );
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventory( IPartRenderHelper helper, RenderBlocks renderer )
	{
		// Get the tessellator
		Tessellator ts = Tessellator.instance;

		// Get the side texture
		IIcon busSideTexture = BlockTextureManager.BUS_SIDE.getTexture();
		
		helper.setTexture( busSideTexture, busSideTexture, busSideTexture, BlockTextureManager.ESSENTIA_IMPORT_BUS.getTexture(), busSideTexture,
			busSideTexture );

		// Face
		helper.setBounds( 4.0F, 4.0F, 15.0F, 12.0F, 12.0F, 16.0F );
		helper.renderInventoryBox( renderer );
		
		// Set the texture to the chamber
		helper.setTexture( BlockTextureManager.ESSENTIA_IMPORT_BUS.getTextures()[2] );
		
		// Large chamber
		helper.setBounds( 4.0F, 4.0F, 14.0F, 12.0F, 12.0F, 15.0F );
		helper.renderInventoryBox( renderer );
		
		// Small chamber
		helper.setBounds( 5.0F, 5.0F, 13.0F, 11.0F, 11.0F, 14.0F );
		helper.renderInventoryBox( renderer );
		
		// Set the texture back to the side texture
		helper.setTexture( busSideTexture );
		
		// Small chamber back wall
		helper.setBounds( 5.0F, 5.0F, 12.0F, 11.0F, 11.0F, 13.0F );
		helper.renderInventoryBox( renderer );
		
		// Face overlay
		helper.setBounds( 4.0F, 4.0F, 15.0F, 12.0F, 12.0F, 16.0F );
		helper.setInvColor( AEPartBase.inventoryOverlayColor);
		ts.setBrightness( 15728880 );
		helper.renderInventoryFace( BlockTextureManager.ESSENTIA_IMPORT_BUS.getTextures()[1], ForgeDirection.SOUTH, renderer );
		
		// Lights
		helper.setBounds( 6.0F, 6.0F, 11.0F, 10.0F, 10.0F, 12.0F );
		this.renderInventoryBusLights( helper, renderer );
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderStatic( int x, int y, int z, IPartRenderHelper helper, RenderBlocks renderer )
	{
		Tessellator ts = Tessellator.instance;

		IIcon busSideTexture = BlockTextureManager.BUS_SIDE.getTexture();
		helper.setTexture( busSideTexture, busSideTexture, busSideTexture, BlockTextureManager.ESSENTIA_IMPORT_BUS.getTextures()[0], busSideTexture, busSideTexture );
		
		// Face
		helper.setBounds( 4.0F, 4.0F, 15.0F, 12.0F, 12.0F, 16.0F );
		helper.renderBlock( x, y, z, renderer );

		if( this.host.getColor() != AEColor.Transparent )
		{
			ts.setColorOpaque_I( this.host.getColor().blackVariant );
		}
		else
		{
			ts.setColorOpaque_I( AEPartBase.inventoryOverlayColor );
		}
		if ( this.isActive() )
		{
			ts.setBrightness( 0xF000F0 );
		}
		// Face overlay
		helper.renderFace( x, y, z, BlockTextureManager.ESSENTIA_IMPORT_BUS.getTextures()[1], ForgeDirection.SOUTH, renderer );

		// Set the pass to alpha
		helper.renderForPass( 1 );
		
		// Set the texture to the chamber
		helper.setTexture( BlockTextureManager.ESSENTIA_IMPORT_BUS.getTextures()[2] );
		
		// Large chamber
		helper.setBounds( 4.0F, 4.0F, 14.0F, 12.0F, 12.0F, 15.0F );
		helper.renderBlock( x, y, z, renderer );
		
		// Small chamber
		helper.setBounds( 5.0F, 5.0F, 13.0F, 11.0F, 11.0F, 14.0F );
		helper.renderBlock( x, y, z, renderer );
		
		// Set the pass back to opaque
		helper.renderForPass( 0 );
		
		// Set the texture back to the side texture
		helper.setTexture( busSideTexture );
		
		// Small chamber back wall
		helper.setBounds( 5.0F, 5.0F, 12.0F, 11.0F, 11.0F, 13.0F );
		helper.renderBlock( x, y, z, renderer );

		// Lights
		helper.setBounds( 6.0F, 6.0F, 11.0F, 10.0F, 10.0F, 12.0F );
		this.renderStaticBusLights( x, y, z, helper, renderer );
	}

}
