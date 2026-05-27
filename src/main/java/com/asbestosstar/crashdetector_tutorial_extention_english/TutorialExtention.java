package com.asbestosstar.crashdetector_tutorial_extention_english;

import com.asbestosstar.crashdetector.CrashDetectorLogger;
import com.asbestosstar.crashdetector.Extencion;
import com.asbestosstar.crashdetector.MonitorDePID;
import com.asbestosstar.crashdetector.analizador.Analizador;
import com.asbestosstar.crashdetector.gui.tipos.TipoGUI;
import com.asbestosstar.crashdetector.gui.tipos.principal.PrincipalGUI;
import com.asbestosstar.crashdetector_tutorial_extention_english.gui.ExampleSidebarGUI;
import com.asbestosstar.crashdetector_tutorial_extention_english.gui.ExampleSidebarGUIBlue;
import com.asbestosstar.crashdetector_tutorial_extention_english.gui.MCreatorScannerGUIBlueNoImage;
import com.asbestosstar.crashdetector_tutorial_extention_english.lang.EnglishTutorialOverride;
import com.asbestosstar.crashdetector_tutorial_extention_english.lang.PirateSpanishLanguage;

/**
 * Main CrashDetector extension entry point.
 *
 * This is the class named by the JAR manifest entry:
 *
 * ExtencionCrashDetector:
 * com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
 *
 * CrashDetector loads this class from the extension JAR, creates an instance,
 * and calls one of these methods depending on the process.
 */
public class TutorialExtention implements Extencion {

	@Override
	public void procesoDelApp() {
		/*
		 * This method runs inside the application/game process.
		 *
		 * This tutorial verification does not need to patch the game or transform
		 * classes, so we do not register anything here.
		 */
		CrashDetectorLogger.log("[TutorialExtention] Loaded in app process");
	}

	@Override
	public void procesoDeLaMonitorizacionDePID() {

		/*
		 * Register languages in the monitor process because the GUI and analyzer live
		 * here.
		 */
		registerLanguages();

		/*
		 * This method runs inside the CrashDetector monitor process.
		 *
		 * Verifications belong here because the monitor process is the process that
		 * reads logs and runs the analyzer after the game/app exits.
		 */
		CrashDetectorLogger.log("[TutorialExtention] Registering MissingSoundForEventVerification");

		/*
		 * Register the verification with CrashDetector.
		 *
		 * CrashDetector's built-in analyzer keeps a static set of verification objects.
		 * Adding our verification here makes it available to future Analizador
		 * instances.
		 *
		 * The object added here acts like a prototype. During analysis, CrashDetector
		 * calls nueva() to make a fresh instance with clean per-run state.
		 */
		Analizador.verificaciones.add(new MissingSoundForEventVerification());

		/*
		 * Register the concrete visual implementation.
		 *
		 * ExampleSidebarGUI is the abstract functional base. ExampleSidebarGUIBlue is
		 * the appearance implementation.
		 */
		ExampleSidebarGUI.TIPO.registrarGUI(ExampleSidebarGUIBlue.ID, ExampleSidebarGUIBlue::new);

		/*
		 * Registration into the global GUI type list.
		 *
		 * Useful if configuration screens enumerate known GUI types.
		 */
		TipoGUI.TIPOS_DE_GUI.add(ExampleSidebarGUI.TIPO);

		/*
		 * Register the sidebar button.
		 *
		 * The default implementation for this button is the blue implementation.
		 */
		PrincipalGUI.registrarBotonDeBarraLateralDerecha(ExampleSidebarGUI.TIPO, ExampleSidebarGUIBlue.ID,
				ExampleSidebarGUIBlue::new);

		/**
		 * 
		 * If you want to register an alternate version of one of the existing guis you
		 * can do that as well
		 * 
		 */
		TipoGUI.ESCANER_MCREATOR.registrarGUI(MCreatorScannerGUIBlueNoImage.ID, MCreatorScannerGUIBlueNoImage::new);

	}

	private void registerLanguages() {
		MonitorDePID.registrarIdioma(new EnglishTutorialOverride());
		MonitorDePID.registrarIdioma(new PirateSpanishLanguage());
		MonitorDePID.recalcularIdiomaDespuesDeExtensiones();
	}

}