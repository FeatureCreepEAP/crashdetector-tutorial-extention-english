package com.asbestosstar.crashdetector_tutorial_extention_english;

import net.neoforged.fml.common.Mod;

//We do not do anything here, this is just to show that it can be in the same file as a neoforge / mcforge / fabric / featurecreep mod
@Mod("crashdetector_tutorial_extention_english")
public class TutorialExtentionInitForNF {

	public TutorialExtentionInitForNF() {
		System.out.println("TutorialExtentionInitForNF loaded ok in JPMS");// SysOut generally shows up ok in a dev
																			// launcher like TLauncher. I would NOT test
																			// this on MultiMC or Prism Launcher though
																			// because they handle JPMS differently and
																			// are not as strict as other launchers
	}

}
