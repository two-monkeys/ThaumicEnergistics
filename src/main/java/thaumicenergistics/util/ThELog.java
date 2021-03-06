package thaumicenergistics.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThELog
{
	public static final Logger log = LogManager.getLogger( "Thaumic Energistics" );

	/**
	 * Displays the section header.
	 */
	public static long beginSection( final String section )
	{
		log.info( String.format( "Starting (%s)", section ) );
		return System.currentTimeMillis();
	}

	/**
	 * Displays the section footer.
	 */
	public static void endSection( final String section, final long sectionStartTime )
	{
		log.info( String.format( "Finished (%s in %dms)", section, ( System.currentTimeMillis() - sectionStartTime ) ) );
	}

	/**
	 * Logs basic info.
	 * 
	 * @param format
	 * @param data
	 */
	public static void info( final String format, final Object ... data )
	{
		log.info( String.format( format, data ) );
	}

	/**
	 * Logs an error.
	 * 
	 * @param format
	 * @param data
	 */
	public static void severe( final String format, final Object ... data )
	{
		log.error( String.format( format, data ) );
	}

	/**
	 * Logs a warning.
	 * 
	 * @param format
	 * @param data
	 */
	public static void warning( final String format, final Object ... data )
	{
		log.warn( String.format( format, data ) );
	}

}
