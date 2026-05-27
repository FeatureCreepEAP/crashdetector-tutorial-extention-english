package com.asbestosstar.crashdetector_tutorial_extention_english;

import com.asbestosstar.crashdetector.Consola;
import com.asbestosstar.crashdetector.analizador.QuickFix;
import com.asbestosstar.crashdetector.analizador.VerificacionDeStackTrace.TraceInfo;
import com.asbestosstar.crashdetector.analizador.Verificaciones;
import com.asbestosstar.crashdetector.gui.tipos.docs.Documento;

/**
 * Example CrashDetector extension verification.
 *
 * This verification detects the log message:
 *
 * Missing sound for event
 *
 * That message usually means Minecraft or a mod tried to play/register a sound
 * event, but the sound event or sound resource was missing, broken, renamed, or
 * incorrectly referenced.
 *
 * This class is intentionally hard-coded in English because extension examples
 * should not need to modify CrashDetector's internal Idioma system.
 */
public class MissingSoundForEventVerification implements Verificaciones {

	/**
	 * Global pre-check flag.
	 *
	 * CrashDetector calls verificar(consola) before checking every line.
	 *
	 * The purpose of this flag is performance: - First, scan the whole log once
	 * using a cheap contains() check. - If the phrase is not present anywhere,
	 * verificarPorLinea() exits instantly. - If the phrase is present, then
	 * verificarPorLinea() does the precise line match and records the exact line
	 * number.
	 */
	private boolean posibleMissingSoundForEvent = false;

	/**
	 * True when this verification has found the problem.
	 *
	 * CrashDetector later calls activado() to decide whether this verification
	 * should be shown in the final analysis result.
	 */
	private boolean activado = false;

	/**
	 * Link/anchor to the exact log line.
	 *
	 * consola.agregarErrorALectador(num, this) creates a clickable link in the
	 * CrashDetector log reader. The returned string is appended to the message.
	 */
	private String enlace = "";

	@Override
	public void verificar(Consola consola) {
		/*
		 * This is the fast global scan.
		 *
		 * consola.contenido_verificar is the full text that CrashDetector prepared for
		 * analysis. We only check whether the important phrase exists anywhere.
		 *
		 * Do not do expensive parsing here. This method can run for many verifications.
		 */
		if (consola.contenido_verificar != null && consola.contenido_verificar.contains("Missing sound for event")) {
			posibleMissingSoundForEvent = true;
		}
	}

	@Override
	public void verificarPorLinea(Consola consola, String linea, int num) {
		/*
		 * If the global pre-check did not find the phrase, there is no reason to
		 * inspect each line. This avoids wasting time on logs that obviously do not
		 * contain this issue.
		 */
		if (!posibleMissingSoundForEvent) {
			return;
		}

		/*
		 * Precise line-level detection.
		 *
		 * This catches the exact line where Minecraft printed:
		 *
		 * Missing sound for event
		 *
		 * The line may contain prefixes, timestamps, logger names, or extra details, so
		 * contains() is better than equals().
		 */
		if (linea != null && linea.contains("Missing sound for event")) {
			this.enlace = consola.agregarErrorALectador(num, this);
			this.activado = true;
		}
	}

	@Override
	public Verificaciones nueva() {
		/*
		 * CrashDetector stores one verification object in the global registry, then
		 * asks each verification to create a fresh copy for analysis.
		 *
		 * This is important because fields like activado and enlace are per-analysis
		 * state and should not be shared between different runs.
		 */
		return new MissingSoundForEventVerification();
	}

	@Override
	public boolean activado() {
		return activado;
	}

	@Override
	public float prioridad() {
		/*
		 * Higher/lower priority depends on how your UI sorts findings.
		 *
		 * This is a warning-style issue, not usually a fatal crash by itself, so it
		 * should normally be lower priority than hard crashes like missing classes,
		 * broken bytecode, bad mixins, etc.
		 */
		return 450;
	}

	@Override
	public String mensaje() {
		/*
		 * Hard-coded extension message.
		 *
		 * No MonitorDePID.idioma is used here because this extension owns its own text.
		 */
		return "Minecraft reported a missing sound event. "
				+ "A mod, resource pack, or data pack may be referencing a sound that does not exist, "
				+ "has the wrong name, or is missing from sounds.json. "
				+ "Check the nearby log line for the exact sound event ID. " + this.enlace;
	}

	@Override
	public String nombre() {
		return "Missing sound for event";
	}

	@Override
	public QuickFix solucion() {
		/*
		 * No automatic repair is provided.
		 *
		 * A real quick fix would need to know which mod/resource pack owns the missing
		 * sound and whether the correct fix is removing a pack, updating a mod, or
		 * fixing sounds.json.
		 */
		return QuickFix.NINGUN;
	}

	@Override
	public boolean ocupaTrazo(TraceInfo trazo) {
		/*
		 * This message is usually a normal log warning, not a Java stack trace.
		 *
		 * Returning false tells CrashDetector this verification does not need to claim
		 * a stack trace.
		 */
		return false;
	}

	@Override
	public String id() {
		/*
		 * Stable ID used by CrashDetector for configs, deny lists, and internal
		 * tracking.
		 *
		 * Keep this stable after release.
		 */
		return "missing_sound_for_event";
	}

	@Override
	public Documento docs() {
		/*
		 * No custom documentation page for this tutorial example.
		 */
		return Documento.NINGUN;
	}

	@Override
	public String enlaceACodigo() {
		/*
		 * For an extension, point this to the extension repository, not the main
		 * CrashDetector repository.
		 */
		return "https://github.com/FeatureCreepEAP/crashdetector-tutorial-extention-english/blob/main/src/main/java/com/asbestosstar/crashdetector_tutorial_extention_english/"
				+ this.getClass().getSimpleName() + ".java";
	}
}