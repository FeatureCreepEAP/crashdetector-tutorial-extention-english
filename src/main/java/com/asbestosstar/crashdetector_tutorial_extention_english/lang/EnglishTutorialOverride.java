package com.asbestosstar.crashdetector_tutorial_extention_english.lang;

import com.asbestosstar.crashdetector.Config;
import com.asbestosstar.crashdetector.idioma.Ingles;

/**
 * Example English language override.
 *
 * This class extends CrashDetector's normal English language class and
 * overrides only a few methods.
 *
 * Because the language code is still "en", registering this class replaces the
 * normal English language entry in CrashDetector's language registry.
 *
 * This is useful when an extension wants to customize specific wording without
 * copying the entire English language file.
 */
public class EnglishTutorialOverride extends Ingles {

	private final Config config = Config.obtenerInstancia();

	@Override
	public String nombre_del_idioma() {
		/*
		 * This is the visible name shown in language dropdowns.
		 *
		 * The code is still "en", so this is not a new language. It is an override of
		 * English.
		 */
		return "English - Tutorial Override";
	}

	@Override
	public String texto_de_gui() {
		return "<span style='color:#" + config.obtenerColorInfo() + "'>"
				+ "This is CrashDetector with the tutorial extension language override enabled. "
				+ "If the game closed normally, you can ignore this window." + "</span>";
	}

	@Override
	public String infoDeVerificaciones() {
		return "<b style='color:#" + config.obtenerColorInfo() + "'>"
				+ "Tutorial English override: CrashDetector found these verification results. "
				+ "Start with the first result because it is usually closest to the real cause. "
				+ "Later results may be cascade errors caused by the first problem." + "</b>";
	}

	@Override
	public String noResultados() {
		return "<b style='color:#" + config.obtenerColorAdvertencia() + "'>"
				+ "Tutorial override: no CrashDetector results were found." + "</b>";
	}

	@Override
	public String botonDeCompartirInforme() {
		return "Share Tutorial Report";
	}

	@Override
	public String descripcionEscanerMCreator() {
		return "Tutorial override: this scanner checks the mod list for signs of MCreator-generated mods. "
				+ "This is only a wording change supplied by the extension.";
	}

	@Override
	public String escanear() {
		return "Tutorial Scan";
	}

	@Override
	public String config() {
		return "Tutorial Config";
	}
}