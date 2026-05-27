package com.asbestosstar.crashdetector_tutorial_extention_english.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.asbestosstar.crashdetector.CrashDetectorLogger;
import com.asbestosstar.crashdetector.gui.CrashDetectorGUI;
import com.asbestosstar.crashdetector.gui.elementos.BotonDeBarraLateralDerecha;
import com.asbestosstar.crashdetector.gui.tipos.TipoGUI;

/**
 * Abstract base class for the extension's example sidebar GUI.
 *
 * This class owns the shared functionality:
 *
 * - JFrame setup - common Swing fields - init() - base window structure - image
 * loading from resources - fallback image generation
 *
 * Concrete subclasses should own the appearance:
 *
 * - colors - fonts - borders - spacing - ConfigColor values
 *
 * This follows the normal CrashDetector GUI pattern:
 *
 * - abstract base class = functionality - concrete implementation class =
 * appearance
 */
public abstract class ExampleSidebarGUI extends JFrame implements CrashDetectorGUI, BotonDeBarraLateralDerecha {

	private static final long serialVersionUID = 1L;

	/**
	 * Registry of available visual implementations for this GUI type.
	 *
	 * TipoGUI uses this map to retrieve the configured/default GUI implementation.
	 */
	public static final Map<String, Supplier<ExampleSidebarGUI>> GUIS = new HashMap<String, Supplier<ExampleSidebarGUI>>();

	/**
	 * Custom TipoGUI owned by this extension.
	 *
	 * This lets the extension add a sidebar GUI without modifying CrashDetector's
	 * core TipoGUI.java file.
	 */
	public static final TipoGUI<ExampleSidebarGUI> TIPO = new TipoGUI<ExampleSidebarGUI>() {

		@Override
		public String id() {
			/*
			 * Stable type ID.
			 *
			 * CrashDetector may use this for config keys such as:
			 *
			 * guitipo_example_sidebar
			 */
			return "example_sidebar";
		}

		@Override
		public String etiquetaDelBoton() {
			/*
			 * Text shown on the sidebar button.
			 *
			 * This extension uses hard-coded English text.
			 */
			return "Example GUI";
		}

		@Override
		public javax.swing.Icon icon() {
			/*
			 * Some sidebar styles may use TipoGUI.icon().
			 *
			 * The current text-button sidebar may not show it, but returning an icon keeps
			 * this type complete.
			 */
			return loadIconFromResources(24, 24);
		}

		@Override
		public void registrarGUI(String id, Supplier<ExampleSidebarGUI> gui) {
			ExampleSidebarGUI.GUIS.put(id, gui);
		}

		@Override
		public Map<String, Supplier<ExampleSidebarGUI>> obtenerGUIs() {
			return ExampleSidebarGUI.GUIS;
		}
	};

	// Shared Swing components used by all visual implementations.

	public JPanel mainPanel;
	public JPanel topPanel;
	public JPanel centerPanel;
	public JPanel bottomPanel;

	public JLabel titleLabel;
	public JLabel imageLabel;
	public JTextArea textArea;

	public ExampleSidebarGUI() {
		super();
	}

	@Override
	public TipoGUI<ExampleSidebarGUI> tipo() {
		return TIPO;
	}

	@Override
	public void init() {
		/*
		 * init() is called when the user clicks the sidebar button.
		 */
		setTitle(windowTitle());
		setSize(520, 360);
		setMinimumSize(new Dimension(460, 300));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		buildBaseStructure();
		applyAppearance();

		setVisible(true);
	}

	/**
	 * Builds the common window structure.
	 *
	 * This method does not apply final colors, fonts, or borders. Visual styling
	 * belongs in the concrete implementation.
	 */
	public void buildBaseStructure() {
		getContentPane().removeAll();
		setLayout(new BorderLayout());

		mainPanel = new JPanel(new BorderLayout(10, 10));

		topPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel(new BorderLayout());

		titleLabel = new JLabel(mainTitle(), SwingConstants.CENTER);

		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setVerticalAlignment(SwingConstants.CENTER);
		imageLabel.setIcon(decorativeIcon());

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(mainText());

		topPanel.add(titleLabel, BorderLayout.CENTER);
		centerPanel.add(imageLabel, BorderLayout.CENTER);
		bottomPanel.add(textArea, BorderLayout.CENTER);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		add(mainPanel, BorderLayout.CENTER);

		revalidate();
		repaint();
	}

	@Override
	public void recargarApariencia() {
		applyAppearance();
	}

	/**
	 * Concrete visual implementations apply fonts, colors, borders, and spacing
	 * here.
	 */
	public abstract void applyAppearance();

	/**
	 * Window title.
	 */
	public String windowTitle() {
		return "CrashDetector Extension Example GUI";
	}

	/**
	 * Main title shown inside the window.
	 */
	public String mainTitle() {
		return "Example CrashDetector Extension GUI";
	}

	/**
	 * Main explanatory text shown in the bottom area.
	 */
	public String mainText() {
		return "This is an example GUI registered by a CrashDetector extension.\n\n"
				+ "The GUI is split into an abstract base class for functionality "
				+ "and a concrete implementation class for appearance.\n\n"
				+ "The concrete class uses ConfigColor values so the colors can be edited "
				+ "through CrashDetector's normal configuration system.";
	}

	/**
	 * Large decorative image shown in the center.
	 */
	public ImageIcon decorativeIcon() {
		return loadIconFromResources(140, 140);
	}

	/**
	 * Loads an image from the extension JAR.
	 *
	 * Expected source path:
	 *
	 * src/main/resources/assets/crashdetector_tutorial_extention_english/example_sidebar.png
	 *
	 * Runtime resource path inside the JAR:
	 *
	 * /assets/crashdetector_tutorial_extention_english/example_sidebar.png
	 */
	public static ImageIcon loadIconFromResources(int width, int height) {
		try {
			java.net.URL url = ExampleSidebarGUI.class
					.getResource("/assets/crashdetector_tutorial_extention_english/example_sidebar.png");

			if (url != null) {
				Image image = ImageIO.read(url);
				Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				return new ImageIcon(scaled);
			}
		} catch (Exception e) {
			CrashDetectorLogger.logException(e);
		}

		return createFallbackIcon(width, height);
	}

	/**
	 * Generates a fallback icon if the extension resource image is missing.
	 *
	 * This keeps the example usable even before the user adds the PNG file.
	 */
	public static ImageIcon createFallbackIcon(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();

		try {
			g.setColor(new java.awt.Color(50, 90, 160));
			g.fillRoundRect(0, 0, width - 1, height - 1, 18, 18);

			g.setColor(new java.awt.Color(255, 255, 255));
			g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, Math.max(12, width / 4)));
			g.drawString("CD", Math.max(8, width / 5), Math.max(22, height / 2));

			g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, Math.max(9, width / 12)));
			g.drawString("EXT", Math.max(8, width / 5), Math.max(36, height / 2 + height / 5));
		} finally {
			g.dispose();
		}

		return new ImageIcon(image);
	}
}