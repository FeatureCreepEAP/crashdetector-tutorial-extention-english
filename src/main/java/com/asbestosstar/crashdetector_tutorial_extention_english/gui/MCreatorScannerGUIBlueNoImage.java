package com.asbestosstar.crashdetector_tutorial_extention_english.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.asbestosstar.crashdetector.config.ConfigColor;
import com.asbestosstar.crashdetector.config.ElementoConfig;
import com.asbestosstar.crashdetector.gui.CrashDetectorGUI;
import com.asbestosstar.crashdetector.gui.tipos.TipoGUI;
import com.asbestosstar.crashdetector.gui.tipos.mcreator.EscanerMCreatorGUI;

/**
 * Alternate MCreator scanner GUI implementation.
 *
 * This is a blue, image-free variant.
 *
 * The abstract parent class, EscanerMCreatorGUI, owns the scanner
 * functionality:
 *
 * - scan button logic - SwingWorker execution - result cleanup -
 * CrashDetectorGUI integration - TipoGUI integration
 *
 * This concrete class owns only the appearance and layout:
 *
 * - colors - fonts - panels - borders - spacing
 *
 * No image is used in this variant.
 */
public class MCreatorScannerGUIBlueNoImage extends EscanerMCreatorGUI {

	private static final long serialVersionUID = 1L;

	public static final String ID = "blue_no_image";

	// =====================================================
	// Configurable colors
	// =====================================================

	public ConfigColor windowBackgroundColor;
	public ConfigColor topPanelColor;
	public ConfigColor cardBackgroundColor;
	public ConfigColor resultBackgroundColor;

	public ConfigColor titleTextColor;
	public ConfigColor descriptionTextColor;
	public ConfigColor resultTextColor;
	public ConfigColor statusTextColor;

	public ConfigColor buttonBackgroundColor;
	public ConfigColor buttonTextColor;
	public ConfigColor borderColor;

	// =====================================================
	// Components owned by this visual implementation
	// =====================================================

	public JLabel titleLabel;
	public JLabel descriptionLabel;

	public JPanel topPanel;
	public JPanel descriptionCard;
	public JPanel resultCard;
	public JPanel buttonPanel;

	public MCreatorScannerGUIBlueNoImage() {
		windowBackgroundColor = ConfigColor.de("theme.mcreator.blue_no_image.color.window_background",
				new Color(230, 238, 252));

		topPanelColor = ConfigColor.de("theme.mcreator.blue_no_image.color.top_panel", new Color(55, 95, 165));

		cardBackgroundColor = ConfigColor.de("theme.mcreator.blue_no_image.color.card_background",
				new Color(245, 248, 255));

		resultBackgroundColor = ConfigColor.de("theme.mcreator.blue_no_image.color.result_background", Color.WHITE);

		titleTextColor = ConfigColor.de("theme.mcreator.blue_no_image.color.title_text", Color.WHITE);

		descriptionTextColor = ConfigColor.de("theme.mcreator.blue_no_image.color.description_text",
				new Color(35, 45, 65));

		resultTextColor = ConfigColor.de("theme.mcreator.blue_no_image.color.result_text", new Color(25, 35, 50));

		statusTextColor = ConfigColor.de("theme.mcreator.blue_no_image.color.status_text", new Color(70, 90, 130));

		buttonBackgroundColor = ConfigColor.de("theme.mcreator.blue_no_image.color.button_background",
				new Color(65, 120, 210));

		buttonTextColor = ConfigColor.de("theme.mcreator.blue_no_image.color.button_text", Color.WHITE);

		borderColor = ConfigColor.de("theme.mcreator.blue_no_image.color.border", new Color(170, 195, 235));
	}

	@Override
	public String id() {
		return ID;
	}

	@Override
	public TipoGUI<EscanerMCreatorGUI> tipo() {
		return TipoGUI.ESCANER_MCREATOR;
	}

	@Override
	public String tituloVentanaNoLocalizado() {
		return "MCreator Scanner - Blue No Image";
	}

	@Override
	public Font fuenteDescripcion() {
		return new Font("Segoe UI", Font.PLAIN, 14);
	}

	@Override
	public Font fuenteResultados() {
		return new Font("Consolas", Font.PLAIN, 13);
	}

	@Override
	public Font fuenteBoton() {
		return new Font("Segoe UI", Font.BOLD, 16);
	}

	@Override
	public String textoEstadoCargando() {
		return "Scanning MCreator mods...";
	}

	@Override
	public String textoEstadoCompletado() {
		return "Scan complete";
	}

	@Override
	public String textoEstadoError() {
		return "Scan failed";
	}

	@Override
	public void construirEstructuraBase() {
		setMinimumSize(new Dimension(720, 455));

		/*
		 * Main content panel.
		 *
		 * This replaces the image-based layout used by the Rosemi implementation.
		 */
		panelContenido = new JPanel(new BorderLayout(8, 8));
		panelContenido.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		add(panelContenido, BorderLayout.CENTER);

		// =====================================================
		// Top title panel
		// =====================================================

		titleLabel = new JLabel("MCreator Mod Scanner", JLabel.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		titleLabel.setOpaque(false);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		topPanel = new JPanel(new BorderLayout());
		topPanel.add(titleLabel, BorderLayout.CENTER);

		// =====================================================
		// Description card
		// =====================================================

		descriptionLabel = new JLabel("<html>" + "<div style='text-align:center; width:620px;'>"
				+ "This example scans the current mod list for signs of MCreator-generated mods. "
				+ "It is an alternate blue implementation of the same abstract MCreator scanner GUI, "
				+ "but it intentionally uses no decorative image." + "</div>" + "</html>", JLabel.CENTER);

		descriptionLabel.setFont(fuenteDescripcion());
		descriptionLabel.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));

		descriptionCard = new JPanel(new BorderLayout());
		descriptionCard.add(descriptionLabel, BorderLayout.CENTER);

		// =====================================================
		// Scan button
		// =====================================================

		botonEscanear = new JButton("Scan");
		botonEscanear.setFocusPainted(false);
		botonEscanear.setFont(fuenteBoton());
		botonEscanear.setPreferredSize(new Dimension(180, 42));
		botonEscanear.addActionListener(e -> iniciarEscaneo());

		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(8, 180, 8, 180));
		buttonPanel.add(botonEscanear, BorderLayout.CENTER);

		// =====================================================
		// Result area
		// =====================================================

		areaResultados = new JTextArea();
		areaResultados.setEditable(false);
		areaResultados.setFont(fuenteResultados());
		areaResultados.setLineWrap(true);
		areaResultados.setWrapStyleWord(true);
		areaResultados.setText("Press Scan to inspect the current mod list for MCreator-generated mods.\n\n"
				+ "This variant has no image and is intended as a clean blue example theme.");

		panelDesplazamiento = new JScrollPane(areaResultados);
		panelDesplazamiento.setBorder(BorderFactory.createEmptyBorder());

		resultCard = new JPanel(new BorderLayout());
		resultCard.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		resultCard.add(panelDesplazamiento, BorderLayout.CENTER);

		// =====================================================
		// Optional status label
		// =====================================================

		etiquetaEstado = new JLabel("Ready", JLabel.CENTER);
		etiquetaEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		etiquetaEstado.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		// =====================================================
		// Assemble full layout
		// =====================================================

		JPanel upperStack = new JPanel(new BorderLayout(8, 8));
		upperStack.add(topPanel, BorderLayout.NORTH);
		upperStack.add(descriptionCard, BorderLayout.CENTER);
		upperStack.add(buttonPanel, BorderLayout.SOUTH);

		panelContenido.add(upperStack, BorderLayout.NORTH);
		panelContenido.add(resultCard, BorderLayout.CENTER);
		panelContenido.add(etiquetaEstado, BorderLayout.SOUTH);
	}

	@Override
	public void aplicarApariencia() {
		Color windowBackground = windowBackgroundColor.obtener();
		Color top = topPanelColor.obtener();
		Color card = cardBackgroundColor.obtener();
		Color resultBackground = resultBackgroundColor.obtener();

		Color titleText = titleTextColor.obtener();
		Color descriptionText = descriptionTextColor.obtener();
		Color resultText = resultTextColor.obtener();
		Color statusText = statusTextColor.obtener();

		Color buttonBackground = buttonBackgroundColor.obtener();
		Color buttonText = buttonTextColor.obtener();
		Color border = borderColor.obtener();

		getContentPane().setBackground(windowBackground);
		setBackground(windowBackground);

		if (panelContenido != null) {
			panelContenido.setBackground(windowBackground);
		}

		if (topPanel != null) {
			topPanel.setBackground(top);
			topPanel.setBorder(new LineBorder(border, 1, true));
		}

		if (titleLabel != null) {
			titleLabel.setForeground(titleText);
			titleLabel.setBackground(top);
			titleLabel.setOpaque(false);
		}

		if (descriptionCard != null) {
			descriptionCard.setBackground(card);
			descriptionCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(border, 1, true),
					BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		}

		if (descriptionLabel != null) {
			descriptionLabel.setForeground(descriptionText);
			descriptionLabel.setBackground(card);
			descriptionLabel.setOpaque(false);
		}

		if (buttonPanel != null) {
			buttonPanel.setBackground(windowBackground);
		}

		if (botonEscanear != null) {
			botonEscanear.setFont(fuenteBoton());
			botonEscanear.setForeground(buttonText);
			botonEscanear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			botonEscanear.setFocusPainted(false);
			botonEscanear.setBorder(BorderFactory.createCompoundBorder(new LineBorder(border, 1, true),
					BorderFactory.createEmptyBorder(8, 14, 8, 14)));

			if (!CrashDetectorGUI.esMac()) {
				botonEscanear.setBackground(buttonBackground);
				botonEscanear.setOpaque(true);
				botonEscanear.setContentAreaFilled(true);
			}
		}

		if (resultCard != null) {
			resultCard.setBackground(card);
			resultCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(border, 1, true),
					BorderFactory.createEmptyBorder(8, 8, 8, 8)));
		}

		if (areaResultados != null) {
			areaResultados.setForeground(resultText);
			areaResultados.setBackground(resultBackground);
			areaResultados.setCaretColor(resultText);
			areaResultados.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}

		if (panelDesplazamiento != null) {
			panelDesplazamiento.setBackground(resultBackground);
			panelDesplazamiento.getViewport().setBackground(resultBackground);
			panelDesplazamiento.setBorder(new LineBorder(border, 1, true));
		}

		if (etiquetaEstado != null) {
			etiquetaEstado.setForeground(statusText);
			etiquetaEstado.setBackground(windowBackground);
			etiquetaEstado.setOpaque(true);
		}
	}

	@Override
	public List<ElementoConfig> obtenerElementosConfigs() {
		List<ElementoConfig> elements = new ArrayList<ElementoConfig>();

		windowBackgroundColor.establecerNombreParaMostrar(() -> "MCreator blue window background");
		topPanelColor.establecerNombreParaMostrar(() -> "MCreator blue top panel");
		cardBackgroundColor.establecerNombreParaMostrar(() -> "MCreator blue card background");
		resultBackgroundColor.establecerNombreParaMostrar(() -> "MCreator blue result background");

		titleTextColor.establecerNombreParaMostrar(() -> "MCreator blue title text");
		descriptionTextColor.establecerNombreParaMostrar(() -> "MCreator blue description text");
		resultTextColor.establecerNombreParaMostrar(() -> "MCreator blue result text");
		statusTextColor.establecerNombreParaMostrar(() -> "MCreator blue status text");

		buttonBackgroundColor.establecerNombreParaMostrar(() -> "MCreator blue button background");
		buttonTextColor.establecerNombreParaMostrar(() -> "MCreator blue button text");
		borderColor.establecerNombreParaMostrar(() -> "MCreator blue border");

		elements.add(windowBackgroundColor);
		elements.add(topPanelColor);
		elements.add(cardBackgroundColor);
		elements.add(resultBackgroundColor);

		elements.add(titleTextColor);
		elements.add(descriptionTextColor);
		elements.add(resultTextColor);
		elements.add(statusTextColor);

		elements.add(buttonBackgroundColor);
		elements.add(buttonTextColor);
		elements.add(borderColor);

		return elements;
	}
}