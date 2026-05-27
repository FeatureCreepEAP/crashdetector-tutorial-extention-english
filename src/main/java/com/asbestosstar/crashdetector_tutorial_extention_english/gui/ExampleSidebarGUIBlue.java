package com.asbestosstar.crashdetector_tutorial_extention_english.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import com.asbestosstar.crashdetector.config.ConfigColor;
import com.asbestosstar.crashdetector.config.ElementoConfig;
import com.asbestosstar.crashdetector_tutorial_extention_english.gui.ExampleSidebarGUI;

/**
 * Concrete visual implementation of the example sidebar GUI.
 *
 * The abstract parent class owns the functionality. This class owns the
 * appearance.
 *
 * All colors are ConfigColor values, following the normal CrashDetector GUI
 * style pattern.
 */
public class ExampleSidebarGUIBlue extends ExampleSidebarGUI {

	private static final long serialVersionUID = 1L;

	public static final String ID = "blue_example";

	// Configurable colors.

	public ConfigColor windowBackgroundColor;
	public ConfigColor topPanelColor;
	public ConfigColor centerPanelColor;
	public ConfigColor bottomPanelColor;

	public ConfigColor titleTextColor;
	public ConfigColor mainTextColor;

	public ConfigColor textAreaBackgroundColor;
	public ConfigColor textAreaBorderColor;

	public ExampleSidebarGUIBlue() {
		windowBackgroundColor = ConfigColor.de("theme.example_sidebar.blue.color.window_background",
				new Color(232, 240, 255));

		topPanelColor = ConfigColor.de("theme.example_sidebar.blue.color.top_panel", new Color(60, 105, 180));

		centerPanelColor = ConfigColor.de("theme.example_sidebar.blue.color.center_panel", new Color(245, 248, 255));

		bottomPanelColor = ConfigColor.de("theme.example_sidebar.blue.color.bottom_panel", new Color(232, 240, 255));

		titleTextColor = ConfigColor.de("theme.example_sidebar.blue.color.title_text", Color.WHITE);

		mainTextColor = ConfigColor.de("theme.example_sidebar.blue.color.main_text", new Color(35, 42, 55));

		textAreaBackgroundColor = ConfigColor.de("theme.example_sidebar.blue.color.text_area_background", Color.WHITE);

		textAreaBorderColor = ConfigColor.de("theme.example_sidebar.blue.color.text_area_border",
				new Color(190, 205, 235));
	}

	@Override
	public String id() {
		return ID;
	}

	@Override
	public String windowTitle() {
		return "CrashDetector Extension Example - Blue";
	}

	@Override
	public String mainTitle() {
		return "Extension GUI Example";
	}

	@Override
	public void applyAppearance() {
		/*
		 * Read the current configured colors.
		 *
		 * If the user edits these colors through CrashDetector's config system and the
		 * GUI reloads its appearance, ConfigColor.obtener() returns the updated values.
		 */
		Color windowBackground = windowBackgroundColor.obtener();
		Color top = topPanelColor.obtener();
		Color center = centerPanelColor.obtener();
		Color bottom = bottomPanelColor.obtener();
		Color titleText = titleTextColor.obtener();
		Color mainText = mainTextColor.obtener();
		Color textAreaBackground = textAreaBackgroundColor.obtener();
		Color textAreaBorder = textAreaBorderColor.obtener();

		getContentPane().setBackground(windowBackground);
		setBackground(windowBackground);

		if (mainPanel != null) {
			mainPanel.setBackground(windowBackground);
			mainPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		}

		if (topPanel != null) {
			topPanel.setBackground(top);
			topPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		}

		if (centerPanel != null) {
			centerPanel.setBackground(center);
			centerPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
		}

		if (bottomPanel != null) {
			bottomPanel.setBackground(bottom);
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		}

		if (titleLabel != null) {
			titleLabel.setForeground(titleText);
			titleLabel.setBackground(top);
			titleLabel.setOpaque(true);
			titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		}

		if (imageLabel != null) {
			imageLabel.setBackground(center);
			imageLabel.setOpaque(true);
			imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		if (textArea != null) {
			textArea.setForeground(mainText);
			textArea.setBackground(textAreaBackground);
			textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			textArea.setCaretColor(mainText);
			textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(textAreaBorder),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		}
	}

	@Override
	public List<ElementoConfig> obtenerElementosConfigs() {
		List<ElementoConfig> elements = new ArrayList<ElementoConfig>();

		/*
		 * Display names are hard-coded English because this is an extension example.
		 *
		 * No MonitorDePID.idioma is used.
		 */
		windowBackgroundColor.establecerNombreParaMostrar(() -> "Example GUI window background");
		topPanelColor.establecerNombreParaMostrar(() -> "Example GUI top panel");
		centerPanelColor.establecerNombreParaMostrar(() -> "Example GUI image panel");
		bottomPanelColor.establecerNombreParaMostrar(() -> "Example GUI text panel");
		titleTextColor.establecerNombreParaMostrar(() -> "Example GUI title text");
		mainTextColor.establecerNombreParaMostrar(() -> "Example GUI main text");
		textAreaBackgroundColor.establecerNombreParaMostrar(() -> "Example GUI text area background");
		textAreaBorderColor.establecerNombreParaMostrar(() -> "Example GUI text area border");

		elements.add(windowBackgroundColor);
		elements.add(topPanelColor);
		elements.add(centerPanelColor);
		elements.add(bottomPanelColor);
		elements.add(titleTextColor);
		elements.add(mainTextColor);
		elements.add(textAreaBackgroundColor);
		elements.add(textAreaBorderColor);

		return elements;
	}
}