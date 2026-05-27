package com.asbestosstar.crashdetector_tutorial_extention_english.lang;

import java.nio.file.Path;

import com.asbestosstar.crashdetector.Config;
import com.asbestosstar.crashdetector.Statics;
import com.asbestosstar.crashdetector.idioma.Espanol;

/**
 * Example "new language" for a CrashDetector extension.
 *
 * To avoid creating a full translation file, this class extends Espanol and
 * only overrides the minimum identity methods plus a few visible strings.
 *
 * Because the language code is "pirate", this becomes a new selectable language
 * instead of replacing Spanish.
 *
 * Most strings will still come from Espanol because this class only overrides a
 * small number of methods.
 */
public class PirateSpanishLanguage extends Espanol {

	private final Config config = Config.obtenerInstancia();

	@Override
	public String codigo() {
		/*
		 * This must be unique.
		 *
		 * If another language is registered with the same code, the latest registered
		 * language replaces the earlier one.
		 */
		return "pi";
	}

	@Override
	public String nombre_del_idioma_espanol_minusculas_ascii() {
		/*
		 * This is used as a secondary lookup name by the language dropdown helper.
		 *
		 * It should be lowercase ASCII without accents.
		 */
		return "pirata";
	}

	@Override
	public String nombre_del_idioma() {
		/*
		 * Visible name shown in the language dropdown.
		 */
		return "Pirate Spanish";
	}

	@Override
	public Path imagen_bandera() {
		/*
		 * Reuse an existing flag/image to keep the example simple.
		 *
		 * A real extension could copy its own flag into CrashDetector's image folder or
		 * provide its own UI resource strategy.
		 */
		return Statics.carpeta.resolve("imagenes").resolve("bandera_esperanto.png");
	}

	@Override
	public String texto_de_gui() {
		return "<span style='color:#" + config.obtenerColorInfo() + "'>"
				+ "Ahoy. This be CrashDetector. If the ship sailed fine, ignore this window." + "</span>";
	}

	@Override
	public String buscando_para_pid(long pid) {
		return "<span style='color:#" + config.obtenerColorInfo() + "'>" + "Looking for the process ship, PID: " + pid
				+ "</span>";
	}

	@Override
	public String pid_esta_muerto(long pid) {
		return "<span style='color:#" + config.obtenerColorAdvertencia() + "'>" + "The process ship has sunk. PID: "
				+ pid + "</span>";
	}

	@Override
	public String noResultados() {
		return "<b style='color:#" + config.obtenerColorAdvertencia() + "'>" + "No treasure found in the logs."
				+ "</b>";
	}

	@Override
	public String infoDeVerificaciones() {
		return "<b style='color:#" + config.obtenerColorInfo() + "'>"
				+ "Here be the CrashDetector findings. Start with the first one; "
				+ "the rest may just be waves caused by the first crash." + "</b>";
	}

	@Override
	public String botonDeCompartirInforme() {
		return "Share the Treasure Map";
	}

	@Override
	public String abrirCarpeta() {
		return "Open Cargo Hold";
	}

	@Override
	public String actualizar() {
		return "Refresh the Deck";
	}

	@Override
	public String anadirRegistro() {
		return "Add Log Scroll";
	}

	@Override
	public String config() {
		return "Captain's Settings";
	}

	@Override
	public String volver() {
		return "Sail Back";
	}

	@Override
	public String escanear() {
		return "Scan the Seas";
	}

	@Override
	public String descripcionEscanerMCreator() {
		return "This scanner searches the mod cargo for MCreator-made mods. "
				+ "Most are harmless, but some may bring rough seas.";
	}
}