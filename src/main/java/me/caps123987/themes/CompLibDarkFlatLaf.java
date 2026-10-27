package me.caps123987.themes;

import com.formdev.flatlaf.FlatDarkLaf;

public class CompLibDarkFlatLaf
	extends FlatDarkLaf
{
	public static final String NAME = "CompLibDarkFlatLaf";

	public static boolean setup() {
		return setup( new CompLibDarkFlatLaf() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, CompLibDarkFlatLaf.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
