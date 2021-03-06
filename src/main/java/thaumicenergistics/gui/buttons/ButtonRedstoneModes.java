package thaumicenergistics.gui.buttons;

import java.util.List;
import net.minecraft.util.StatCollector;
import thaumicenergistics.texture.AEStateIconsEnum;
import appeng.api.config.RedstoneMode;

public class ButtonRedstoneModes
	extends AbstractAEButton
{
	/**
	 * Localization header string for tooltips
	 */
	private static final String TOOLTIP_LOC_HEADER = "gui.tooltips.appliedenergistics2.";

	/**
	 * Mode to represent
	 */
	private RedstoneMode redstoneMode;

	/**
	 * True when this button is attached to a level emitter
	 */
	private boolean emitter = false;

	/**
	 * Creates the button.
	 * 
	 * @param ID
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param mode
	 */
	public ButtonRedstoneModes( final int ID, final int xPos, final int yPos, final int width, final int height, final RedstoneMode mode )
	{
		this( ID, xPos, yPos, width, height, mode, false );
	}

	/**
	 * Creates the button with argument for if this is an emitter.
	 * 
	 * @param ID
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param mode
	 * @param emitter
	 */
	public ButtonRedstoneModes( final int ID, final int xPos, final int yPos, final int width, final int height, final RedstoneMode mode,
								final boolean emitter )
	{
		// Call super
		super( ID, xPos, yPos, width, height, null );

		// Set the if we are attached to an emitter
		this.emitter = emitter;

		// Set the redstone mode
		this.setRedstoneMode( mode );
	}

	@Override
	public void getTooltip( final List<String> tooltip )
	{
		// Get the explanation based on mode
		String explanation = "";
		switch ( this.redstoneMode )
		{
			case HIGH_SIGNAL:
				explanation = StatCollector.translateToLocal( this.emitter ? TOOLTIP_LOC_HEADER + "EmitLevelAbove" : TOOLTIP_LOC_HEADER +
								"ActiveWithSignal" );
				break;

			case IGNORE:
				explanation = StatCollector.translateToLocal( TOOLTIP_LOC_HEADER + "AlwaysActive" );
				break;

			case LOW_SIGNAL:
				explanation = StatCollector.translateToLocal( this.emitter ? TOOLTIP_LOC_HEADER + "EmitLevelsBelow" : TOOLTIP_LOC_HEADER +
								"ActiveWithoutSignal" );
				break;

			case SIGNAL_PULSE:
				explanation = StatCollector.translateToLocal( TOOLTIP_LOC_HEADER + "ActiveOnPulse" );
				break;

		}

		// Add info
		this.addAboutToTooltip( tooltip, StatCollector.translateToLocal( TOOLTIP_LOC_HEADER + "RedstoneMode" ), explanation );

	}

	/**
	 * Sets the redstone mode this button represents.
	 * 
	 * @param mode
	 */
	public void setRedstoneMode( final RedstoneMode mode )
	{
		// Set the mode
		this.redstoneMode = mode;

		// Set the icon
		switch ( this.redstoneMode )
		{
			case HIGH_SIGNAL:
				this.icon = AEStateIconsEnum.REDSTONE_HIGH;
				break;

			case IGNORE:
				this.icon = AEStateIconsEnum.REDSTONE_IGNORE;
				break;

			case LOW_SIGNAL:
				this.icon = AEStateIconsEnum.REDSTONE_LOW;
				break;

			case SIGNAL_PULSE:
				this.icon = AEStateIconsEnum.REDSTONE_PULSE;
				break;
		}
	}
}
