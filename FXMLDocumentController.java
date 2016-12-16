/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.removal.javafxapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.apache.commons.lang3.StringUtils;

import com.virus.removal.cache.VirusCache;
import com.virus.removal.constants.VirusScanStatus;
import com.virus.removal.handler.VirusREMHandler;
import com.virus.removal.pojo.VirusScanHistory;
import com.virus.removal.registry.RegistryKeyManager;
import com.virus.removal.registry.VirusRegistry;

/**
 *
 * @author cgopi
 */
public class FXMLDocumentController implements Initializable {

	private VirusREMHandler handler = null;

	private Task<Void> workerThread;

	/* Images */
	@FXML
	private ImageView imageview;
	@FXML
	private ImageView imageview1;
	
	/* Buttons */
	@FXML
	private Button b1;
	@FXML
	private Button b2;
	@FXML
	private Button b3;
	@FXML
	private Button b4;
	@FXML
	private Button scan;
	@FXML
	private Button b5;
	@FXML
	private Button b6;
	@FXML
	private TitledPane b7;
	@FXML
	private Button w;
	@FXML
	private Button e;
	@FXML
	private Button r;
	@FXML
	private Button t;
	@FXML
	private Button y;
	@FXML
	private Button q;
	@FXML
	private Button max;
	@FXML
	private Button min;
	@FXML
	private Button u;
	@FXML
	private Button i;
	@FXML
	private Button clearHistory;
	
	/* Labels */
	@FXML
	private Label c1;
	@FXML
	private Label c2;
	@FXML
	private Label c3;
	@FXML
	private Label c4;
	
	/* Scan */
	@FXML
	private Label scan1;
	@FXML
	private Label scan2;
	@FXML
	private Label scan3;
	@FXML
	private Label scan4;
	@FXML
	private Label scan5;
	@FXML
	private Label scan6;
	
	@FXML
	private Text protect1;
	@FXML
	private Text protect2;
	@FXML
	private Text protect3;
	@FXML
	private Text protect4;
	@FXML
	private Text protect5;
	@FXML
	private Text protect6;
	
	/* Dashboard */
	@FXML
	private Label statusValInDash;
	@FXML
	private Label lastScanValInDash;
	@FXML
	private Label buildValInDash;
	
	@FXML
	private Text statusTextInDash;
	@FXML
	private Text lastScanTextInDash;
	@FXML
	private Text buildTextInDash;
	
	/* Pane */
	@FXML
	private Pane panel;
	@FXML
	private Pane top;
	@FXML
	private Pane about;
	@FXML
	private Pane dash;
	@FXML
	private Pane scann;
	@FXML
	private Pane his;
	@FXML
	private Pane update;
	@FXML
	private Pane general;
	@FXML
	private Pane unprotected;
	@FXML
	private Pane top1;
	@FXML
	private Pane isProtected;
	@FXML
	private Pane top11;

	/* Anchor Pane */
	@FXML
	private AnchorPane dropdown;

	/* TextArea */
	@FXML
	private TextArea txt;
	@FXML
	private TextArea textArea;
	@FXML
	private TextArea textForScanInProgress;
	@FXML
	private TextArea textAreaForHistory;
	@FXML
	private TextArea textAreaForGeneralSettings;
	@FXML
	private TextArea textAreaForUpdate;
	
	private TextArea textAreaForHistoryFromFile = null;
	
	/* Text */
	@FXML
	private Text textForThreatsFound;
	@FXML
	private Text textForThreatsRemoved;
	@FXML
	private Text timerTextForThreats;
	@FXML
	private Text textForScanning;
	@FXML
	private Text virusScanning;
	@FXML
	private Text textForLastScanInHistory;
	@FXML
	private Text textForDemo;
	
	/* Dashboard */
	@FXML
	private Text d1;
	@FXML
	private Text d2;
	@FXML
	private Text d3;
	
	/* Scan */
	@FXML
	private Text s1;
	@FXML
	private Text s2;
	@FXML
	private Text s3;
	@FXML
	private Text s4;
	@FXML
	private Text s5;
	@FXML
	private Text s6;
	
	/* General Settings */
	@FXML
	private Text g1;
	@FXML
	private Text g2;
	@FXML
	private Text g3;
	@FXML
	private Text g4;

	/* Update */
	@FXML
	private Text updateFound;
	@FXML
	private Text demoInUpdate;
	@FXML
	private Button updateNow;
	@FXML
	private Button installUpdates;
	@FXML
	private Button ignore;
	
	/* About */
	@FXML
	private Text a1;
	@FXML
	private Text a2;
	@FXML
	private Text a3;
	@FXML
	private Text a4;
	
	/* Menu Button */
	@FXML
	private MenuButton drop;
	
	/* Progress Bar */
	@FXML
	private ProgressBar progressBar;
	
	/* Close Button */
	@FXML
	public Button closeButton;

	private VirusRegistry virusRegistry;
	private VirusCache virusCache;
	private RegistryKeyManager rkm;

	private List<String> virusSearchList = new ArrayList<String>();
	private Set<String> viruses = new HashSet<String>();
	private List<VirusScanHistory> virusScanHistoryList = new ArrayList<VirusScanHistory>();

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");
	
	private String timestamp = null;
	private String virusScanHistoryFile = "virusScanHistory.txt";
	private String languageFile = "saveLanguage.txt";
	private static final String REGISTRY_KEY_MANEGER_EMPTY = "Registry key manager is empty";
	
	private static final String HISTORY_UNAVAILABLE = "History unavailable, please perform a scan.";
	private static final String HISTORY_UNAVAILABLE_IN_SPANISH = "Historial no disponible. Por favor, realice un escaneo.";
	private static final String HISTORY_UNAVAILABLE_IN_FRENCH = "Historique indisponible, Veuillez effectuer un scan.";
	private static final String HISTORY_UNAVAILABLE_IN_PORTUGUESE = "Hist�rico indispon�vel, por-favor fa�a uma an�lise.";
	
	private static final String SETTINGS_UNAVAILABLE = "No settings are available in demo version.";
	private static final String SETTINGS_UNAVAILABLE_IN_SPANISH = "Los ajustes no est�n disponibles en la versi�n de demostraci�n.";
	private static final String SETTINGS_UNAVAILABLE_IN_FRENCH = "Aucun r�glage sont disponibles en version d�mo.";
	private static final String SETTINGS_UNAVAILABLE_IN_PORTUGUESE = "Nenhuma configura��o est�o dispon�veis em vers�o demo.";

	private static final String UPDATE_UNAVAILABLE = "No updates are available in demo version.";
	private static final String UPDATE_UNAVAILABLE_IN_SPANISH = "No hay actualizaciones disponibles en la versi�n de demostraci�n.";
	private static final String UPDATE_UNAVAILABLE_IN_FRENCH = "Aucune mise � jour sont disponibles en version d�mo.";
	private static final String UPDATE_UNAVAILABLE_IN_PORTUGUESE = "N�o h� atualiza��es dispon�veis na vers�o demo.";
	
	private static final String POTENTIALLY_UNPROTECTED = "Potentially Unprotected";
	
	private int progressCount = 1;
	private int virusScanHistoryCount = 0;
	private int noOfThreats;
	private int removedThreats;
	
	private boolean isScanCancelled = false;
	private boolean isPageProtected = false;
	private boolean isHistoryDataAppended = false;
	
	File file;
	FileWriter fw;
	BufferedWriter bw;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(final URL url, final ResourceBundle rb) {

		dash.setVisible(false);
		scann.setVisible(false);
		about.setVisible(false);
		his.setVisible(false);
		update.setVisible(false);
		general.setVisible(false);
		isProtected.setVisible(false);
		unprotected.setVisible(true);
		
		handler = VirusREMHandler.getInstance();
		virusRegistry = VirusRegistry.getInstance();
		rkm = RegistryKeyManager.getInstance();
		virusCache = VirusCache.instance;
		
		textAreaForHistoryFromFile = readVirusScanHistoryFromFile();
		
		if (StringUtils.isBlank(textAreaForHistoryFromFile.getText())) {
			if (StringUtils.isNotBlank(textAreaForHistory.getText())) {
				textAreaForHistory.clear();
			}
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
			textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
			textAreaForHistory.appendText(HISTORY_UNAVAILABLE);
			clearHistory.setDisable(true);
		}
		
		final String languageName = readLastSavedLanguageFromFile();
		
		if (StringUtils.isNotBlank(languageName)) {
			if (StringUtils.equals(languageName, "English")) {
				this.ee(null);
			} else if (StringUtils.equals(languageName, "French")) {
				this.ff(null);
			} else if (StringUtils.equals(languageName, "Spanish")) {
				this.ss(null);
			} else if (StringUtils.equals(languageName, "Portuguese")) {
				this.pp(null);
			}
		} else {
			drop.setText("English");
			this.ee(null);
		}

	}

	/**
	 * @param event
	 */
	@FXML
	private void b1(final ActionEvent event) {
		
		final String languageName = readLastSavedLanguageFromFile();
		
		drop.setText(languageName);
		
		if (isPageProtected) {
			
			/* Display the protected screen. */
			showTheProtectedPage();
		} else {

			c2.setStyle("-fx-background-color:#DF4444");
			b1.setStyle("-fx-background-color:#373E48");

			c1.setStyle("-fx-background-color:transparent");
			b2.setStyle("-fx-background-color:transparent");

			c3.setStyle("-fx-background-color:transparent");
			b3.setStyle("-fx-background-color:transparent");

			c4.setStyle("-fx-background-color:transparent");
			b4.setStyle("-fx-background-color:transparent");

			b5.setStyle("-fx-background-color:transparent");
			b6.setStyle("-fx-background-color:transparent");

			dash.setVisible(false);
			dash.setManaged(false);

			scann.setVisible(false);
			scann.setManaged(false);

			isProtected.setVisible(false);
			isProtected.setManaged(false);

			unprotected.setVisible(true);
			unprotected.setManaged(true);

			about.setVisible(false);
			about.setManaged(false);

			his.setVisible(false);
			his.setManaged(false);

			update.setVisible(false);
			update.setManaged(false);

			general.setVisible(false);
			general.setManaged(false);
		}
		
	}

	/**
	 * @param event
	 */
	@FXML
	private void b2(final ActionEvent event) {

		if (isPageProtected) {
			c1.setStyle("-fx-background-color:#3FC639");
		} else {
			c1.setStyle("-fx-background-color:#DF4444");
		}
		b2.setStyle("-fx-background-color:#373E48");

		c2.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		unprotected.setVisible(false);
		unprotected.setManaged(false);

		scann.setManaged(false);
		scann.setVisible(false);

		isProtected.setVisible(false);
		isProtected.setManaged(false);

		dash.setVisible(true);
		dash.setManaged(true);

		about.setVisible(false);
		about.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		javafx.application.Platform.runLater(new Runnable() {
			
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				buildValInDash.setText("01.01.193");
				
				if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STOPPED) {
					if (drop.getText().equals("English")) {
						statusValInDash.setText(POTENTIALLY_UNPROTECTED);
					} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
						statusValInDash.setText("Potencialmente Desprotegido");
					} else if (drop.getText().equals("French")) {
						statusValInDash.setText("Potentiellement non prot�g�");
					}
					lastScanValInDash.setText(timestamp);
				}
			}
		});

	}

	/**
	 * @param event
	 */
	@FXML
	private void b3(final ActionEvent event) {

		if (isPageProtected) {
			c3.setStyle("-fx-background-color:#3FC639");
		} else {
			c3.setStyle("-fx-background-color:#DF4444");
		}
		b3.setStyle("-fx-background-color:#373E48");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		his.setVisible(true);
		his.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);
		
		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);
		
		javafx.application.Platform.runLater(new Runnable() {
			
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()L
			 */
			@Override
			public void run() {

				textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
				
				if (!isHistoryDataAppended) {
					if (StringUtils.isNotBlank(textAreaForHistoryFromFile.getText())) {
						System.out.println(textAreaForHistoryFromFile.getText());
						textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 11));
						textAreaForHistory.appendText(textAreaForHistoryFromFile.getText());
						clearHistory.setDisable(false);
						isHistoryDataAppended = true;
					} 
				}
				
			}
		});
		
	}

	/**
	 * @param event
	 */
	@FXML
	private void b4(final ActionEvent event) {

		System.out.println(drop.getText());
		
		c4.setStyle("-fx-background-color: transparent");
		b4.setStyle("-fx-background-color:#373E48");

		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		about.setVisible(true);
		about.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);
		
		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);

	}

	int xOffset;
	int yOffset;

	/**
	 * @param event
	 */
	@FXML
	private void mouseDrag(final MouseEvent event) {
		JavaFXApplication.primaryStage.setX(event.getScreenX() + xOffset);
		JavaFXApplication.primaryStage.setY(event.getScreenY() + yOffset);
	}

	/**
	 * @param event
	 */
	@FXML
	private void mousePress(final MouseEvent event) {
		xOffset = (int) (JavaFXApplication.primaryStage.getX() - event.getScreenX());
		yOffset = (int) (JavaFXApplication.primaryStage.getY() - event.getScreenY());
	}

	/**
	 * @param event
	 */
	@FXML
	private void scanMouseExited(final MouseEvent event) {

	}

	/**
	 * @param event
	 */
	@FXML
	private void scanMouseEntered(final MouseEvent event) {

	}

	/**
	 * @param event
	 */
	@FXML
	private void close(final MouseEvent event) {
		if (StringUtils.isNotBlank(textAreaForHistory.getText())) {
			writeVirusScanHistoryIntoFile(textAreaForHistory);
		}
		saveLanguageIntoFile(drop.getText());
		//writeVirusScanHistoryIntoFileInsideCode(textAreaForHistory);
		System.exit(5);
	}

	/**
	 * @param event
	 */
	@FXML
	private void minimizee(final MouseEvent event) {
		JavaFXApplication.primaryStage.setIconified(true);
	}

	/**
	 * @param event
	 */
	@FXML
	private void b5(final ActionEvent event) {

		b5.setStyle("-fx-background-color:#373E48");
		b6.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		general.setVisible(true);
		general.setManaged(true);
		
		isProtected.setVisible(false);
		isProtected.setManaged(false);

		scann.setManaged(false);
		scann.setVisible(false);
		
		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);
		
		if (StringUtils.isBlank(textAreaForGeneralSettings.getText())) {
			javafx.application.Platform.runLater(new Runnable() {

				@Override
				public void run() {
					textAreaForGeneralSettings.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
					if (StringUtils.equals(drop.getText(), "English")) {
						textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE);
					} else if (StringUtils.equals(drop.getText(), "Spanish")) {
						textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_SPANISH);
					} else if (StringUtils.equals(drop.getText(), "French")) {
						textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_FRENCH);
					} else if (StringUtils.equals(drop.getText(), "Portuguese")) {
						textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_PORTUGUESE);
					}
					textAreaForGeneralSettings.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
					textAreaForGeneralSettings.setStyle("-fx-text-fill: #00AEEF;");
				}

			});
		}
		
	}
	
	/**
	 * @param event
	 */
	@FXML
	private void b6(final ActionEvent event) {

		b6.setStyle("-fx-background-color:#373E48");
		b5.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		update.setVisible(true);
		update.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);
		
		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);

		if (StringUtils.isBlank(textAreaForUpdate.getText())) {
			javafx.application.Platform.runLater(new Runnable() {

				@Override
				public void run() {
					textAreaForUpdate.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
					if (drop.getText().equals("English")) {
						textAreaForUpdate.appendText(UPDATE_UNAVAILABLE);
					} else if (drop.getText().equals("Spanish")) {
						textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_SPANISH);
					} else if (drop.getText().equals("French")) {
						textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_FRENCH);
					} else if (drop.getText().equals("Portuguese")) {
						textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_PORTUGUESE);
					}
					textAreaForUpdate.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
					textAreaForUpdate.setStyle("-fx-text-fill: #00AEEF;");
				}

			});
		}
		
	}

	/**
	 * @param event
	 */
	@FXML
	private void scanningPressed(final ActionEvent event) {

		/* Set the status of virus scanning */
		handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STARTED);

		/* Clear the text */
		if (textForScanInProgress != null && textForScanInProgress.getText() != null) {
			textForScanInProgress.clear();
			javafx.application.Platform.runLater(new Runnable() {
			
				/* (non-Javadoc)
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {
					/* Set the text */
					if (StringUtils.equals(drop.getText(), "English")) {
						textForScanInProgress.appendText("VirusREM has began scanning your PC. Please allow up to 5 minutes per 500GB."); 					
					} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
						textForScanInProgress.appendText("VirusREM de empezar el escaneo de su PC. Por favor, espere hasta 5 minutos por 500 GB.");
					} else if (drop.getText().equals("French")) {
						textForScanInProgress.appendText("VirusREM a commenc� la num�risation de votre PC. S'il vous pla�t permettre jusqu'� 5 minutes par 500Go.");
					} else if( drop.getText().equals("Portuguese")) {
						textForScanInProgress.appendText("VirusREM j� come�ou a varredura de seu PC. Por favor, aguarde at� 5 minutos por 500GB.");
					} 					
				}
			});
		}

		/* Set the style */
		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:#00AEEF");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:#373E48");

		general.setVisible(false);
		general.setManaged(false);

		scann.setVisible(true);
		scann.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);

		/* Create the task to start scan operation */
		workerThread = createWorker();

		progressBar.progressProperty().unbind();
		progressBar.progressProperty().bind(workerThread.progressProperty());

		/* Start the thread */
		Thread thread = new Thread(workerThread);
		thread.setDaemon(true);
		thread.start();
		
	}
	
	/**
	 * @param event
	 */
	@FXML
	private void scanNowPressed(final ActionEvent event) {
		
		/* Set the style */
		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:#00AEEF");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:#373E48");

		general.setVisible(false);
		general.setManaged(false);

		scann.setVisible(true);
		scann.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);
		
		if (workerThread == null 
				|| VirusScanStatus.VIRUS_JOB_COMPLETED == handler.getVirusScanStatus()
				|| VirusScanStatus.VIRUS_SCAN_STOPPED == handler.getVirusScanStatus()) {
			
			/* Create the task to start scan operation */
			workerThread = createWorker();

			progressBar.progressProperty().unbind();
			progressBar.progressProperty().bind(workerThread.progressProperty());

			/* Set the status of virus scanning */
			handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STARTED);
			
			/* Clear the text */
			if (textForScanInProgress != null && textForScanInProgress.getText() != null) {
				textForScanInProgress.clear();
				javafx.application.Platform.runLater(new Runnable() {
				
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						/* Set the text */
						if (StringUtils.equals(drop.getText(), "English")) {
							textForScanInProgress.appendText("VirusREM has began scanning your PC. Please allow up to 5 minutes per 500GB."); 					
						} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
							textForScanInProgress.appendText("VirusREM de empezar el escaneo de su PC. Por favor, espere hasta 5 minutos por 500 GB.");
						} else if (drop.getText().equals("French")) {
							textForScanInProgress.appendText("VirusREM a commenc� la num�risation de votre PC. S'il vous pla�t permettre jusqu'� 5 minutes par 500Go.");
						} else if( drop.getText().equals("Portuguese")) {
							textForScanInProgress.appendText("VirusREM j� come�ou a varredura de seu PC. Por favor, aguarde at� 5 minutos por 500GB.");
						}
					}
				});
			}
			
			/* Start the thread */
			Thread thread = new Thread(workerThread);
			thread.setDaemon(true);
			thread.start();
		}

	}

	/**
	 * @return
	 */
	private Task<Void> createWorker() {
		return new Task<Void>() {
			
			/* (non-Javadoc)
			 * @see javafx.concurrent.Task#call()
			 */
			@Override
			protected Void call() throws Exception {

				/* Start the scanning of viruses. */
				//				virusDetectedMap = handler.startScanning(progressBar, 
				//														 textArea,
				//														 textForThreatsFound,
				//														 virusScanning,
				//														 workerThread);

				javafx.application.Platform.runLater(new Runnable() {
					
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						/* Set the status to Scanning in Dashboard */
						if (statusValInDash.getStyle() != null) {
							statusValInDash.setStyle("");
						}
						statusValInDash.setText(POTENTIALLY_UNPROTECTED);
					}
				});
				
				/* Start Scanning */
				int index = 1;
				boolean isNextVirus = false;
				isPageProtected = false;

				final Calendar c = Calendar.getInstance();
				timestamp = simpleDateFormat.format(c.getTime());
				
				if (isScanCancelled) {
					isScanCancelled = false;
				}

				final Map<String, String> virusDetectedMap = new HashMap<String, String>();
				final List<String> detectedViruses = new ArrayList<String>();
				
				/* Cancel the Scan */
				q.setOnMouseClicked(new EventHandler<Event>() {

					/**
					 * @param me
					 */
					@Override
					public void handle(Event me) {
						isScanCancelled = true;
					}
				});

				if (isScanCancelled) {
					handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
					setVirusScanHistoryIfScanIsStopped(handler);
					return null;
				}
				
				javafx.application.Platform.runLater(new Runnable() {
			
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						
						if (textArea != null) {
							textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
							if (textArea.getText() != null && !textArea.getText().isEmpty()) {
								textArea.clear();
							}
						}
						
						if (StringUtils.equals(drop.getText(), "English")) {
							virusScanning.setText("Scanning...");
							timerTextForThreats.setText("Virus Timer: 15 seconds");
						} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
							virusScanning.setText("Exploraci�n...");
							timerTextForThreats.setText("Timer Virus: 15 segundos");
						} else if (drop.getText().equals("French")) {
							virusScanning.setText("Balayage...");
							timerTextForThreats.setText("Virus Timer: 15 secondes");
						} else if( drop.getText().equals("Portuguese")) {
							virusScanning.setText("Digitaliza��o...");
							timerTextForThreats.setText("Temporizador V�rus: 15 segundos");
						}
						
						progressBar.setTooltip(new Tooltip("Virus scan in progress"));

						if (textForThreatsFound != null && textForThreatsFound.getText() != null && !textForThreatsFound.getText().isEmpty()) {
							textForThreatsFound.setText("");
						}
						if (textForThreatsRemoved != null && textForThreatsRemoved.getText() != null && !textForThreatsRemoved.getText().isEmpty()) {
							textForThreatsRemoved.setText("");
						}
						
						if (isScanCancelled) {
							isScanCancelled = false;
						}
					}
				});
				
				System.out.println("Scan in progress..!!");
				System.out.println();

				/* Cancel the Scan */
				q.setOnMouseClicked(new EventHandler<Event>() {

					/**
					 * @param me
					 */
					@Override
					public void handle(Event me) {
						isScanCancelled = true;
					}
				});

				if (isScanCancelled) {
					handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
					setVirusScanHistoryIfScanIsStopped(handler);
					return null;
				}
				
				if(virusRegistry != null) {
					virusSearchList.clear();
					virusSearchList = virusRegistry.getVirusSearchList();
				}
				if(virusCache != null) {
					viruses = virusCache.getAllViruses();
				}
				if(rkm == null) {
					rkm = RegistryKeyManager.getInstance();
				}

				/* Wait for sometime */
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				javafx.application.Platform.runLater(new Runnable() {
					
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						textArea.appendText("Currently Scanning:");
						textArea.appendText("\n");
						textArea.appendText("----------------------");
						textArea.appendText("\n");
						
						/* Cancel the Scan */
						q.setOnMouseClicked(new EventHandler<Event>() {
							
							/**
							 * @param me
							 */
							@Override
							public void handle(Event me) {
								isScanCancelled = true;
							}
						});
					}
				});

				if (isScanCancelled) {
					handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
					setVirusScanHistoryIfScanIsStopped(handler);
					return null;
				}
				
				for (final String virus : viruses) {
					
					/* Cancel the Scan */
					q.setOnMouseClicked(new EventHandler<Event>() {

						/**
						 * @param me
						 */
						@Override
						public void handle(Event me) {
							isScanCancelled = true;
						}
					});
					
					if (isScanCancelled) {
						handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
						setVirusScanHistoryIfScanIsStopped(handler);
						return null;
					}
					
					if(rkm == null) {
						System.out.println("Scan is stopped due to: "+REGISTRY_KEY_MANEGER_EMPTY);
						handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
						return null;
					}

					if (isNextVirus) {
						javafx.application.Platform.runLater(new Runnable() {
					
							/* (non-Javadoc)
							 * @see java.lang.Runnable#run()
							 */
							@Override
							public void run() {
								textArea.appendText("\n\n");
							}
						});
					}

					javafx.application.Platform.runLater(new Runnable() {
						
						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							textArea.appendText(virus);
							textArea.appendText("\n");
						}
					});

					for (final String virusSearchUsingReg : virusSearchList) {
						
						/* Cancel the Scan */
						q.setOnMouseClicked(new EventHandler<Event>() {

							/**
							 * @param me
							 */
							@Override
							public void handle(Event me) {
								isScanCancelled = true;
							}
						});
						
						if (isScanCancelled) {
							handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
							setVirusScanHistoryIfScanIsStopped(handler);
							return null;
						}
						
						javafx.application.Platform.runLater(new Runnable() {
						
							/* (non-Javadoc)
							 * @see java.lang.Runnable#run()
							 */
							@Override
							public void run() {
								textArea.appendText("\n");
								textArea.appendText(virusSearchUsingReg);
							}
						});
					
						try {
							if(virus != null && !virus.isEmpty() && virusSearchUsingReg != null && !virusSearchUsingReg.isEmpty()) {
								if(rkm != null) {
									rkm.query(virusSearchUsingReg, virus);
									if(rkm.getKey() != null && !rkm.getKey().isEmpty()) {
						
										javafx.application.Platform.runLater(new Runnable() {
											
											/* (non-Javadoc)
											 * @see java.lang.Runnable#run()
											 */
											@Override
											public void run() {
												textArea.appendText("\n");
											}
										});
										
										detectedViruses.add(virusSearchUsingReg+"\\"+virus);
										System.out.println("Found the virus: "+virusSearchUsingReg+"\\"+virus);
										virusDetectedMap.put(String.valueOf(index++)+virusSearchUsingReg, virus);
									}
								} else {
									System.out.println("Scan is stopped due to: "+REGISTRY_KEY_MANEGER_EMPTY);
									handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
									return null;
								}
							} 
						} catch (final Exception e) {
							System.out.println("Failed to scan due to: "+e.getMessage());
							handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
							return null;
						}
						isNextVirus = true;
					}

				}

				System.out.println("Virus scanning is completed");
				handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_COMPLETED);

				javafx.application.Platform.runLater(new Runnable() {
					
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						virusScanning.setText("");
						timerTextForThreats.setText("");
					}
				});

				/* Cancel the Scan */
				q.setOnMouseClicked(new EventHandler<Event>() {

					/**
					 * @param me
					 */
					@Override
					public void handle(final Event me) {
						isScanCancelled = true;
					}
				});
				
				if (isScanCancelled) {
					handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
					setVirusScanHistoryIfScanIsStopped(handler);
					return null;
				}

				noOfThreats = virusDetectedMap.size();
				
				if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_COMPLETED
												 && virusDetectedMap != null
												 && virusDetectedMap.size() > 0)  {

					javafx.application.Platform.runLater(new Runnable() {
					
						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							if (StringUtils.equals(drop.getText(), "English")) {
								textForThreatsFound.setText("Found Threats: "+noOfThreats);
							} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
								textForThreatsFound.setText("Las amenazas que se encuentran: "+noOfThreats);
							} else if (drop.getText().equals("French")) {
								textForThreatsFound.setText("Menaces trouv�s: "+noOfThreats);
							} else if( drop.getText().equals("Portuguese")) {
								textForThreatsFound.setText("Amea�as encontradas: "+noOfThreats);
							}
							textArea.appendText("\n\n");
							textArea.appendText("Found one or more viruses:");
							textArea.appendText("\n");
							textArea.appendText("--------------------------------");
							textArea.appendText("\n");
							for (final String detectedVirus : detectedViruses) {
								textArea.appendText(detectedVirus);
								textArea.appendText("\n");
							}
						}
					});

					/* Wait for sometime */
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					
					javafx.application.Platform.runLater(new Runnable() {
						
						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							if (StringUtils.equals(drop.getText(), "English")) {
								textForThreatsFound.setText("Found Threats: "+noOfThreats);
							} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
								textForThreatsFound.setText("Las amenazas que se encuentran: "+noOfThreats);
							} else if (drop.getText().equals("French")) {
								textForThreatsFound.setText("Menaces trouv�s: "+noOfThreats);
							} else if( drop.getText().equals("Portuguese")) {
								textForThreatsFound.setText("Amea�as encontradas: "+noOfThreats);
							}
							textArea.appendText("\n\n");
							textArea.appendText("No viruses are found");
						}
					});
					
					/* Update the progress */
					updateProgress(1, 1);
					
					VirusScanHistory virusScanHistory = new VirusScanHistory();
					virusScanHistory.setScanDate(timestamp);
					virusScanHistory.setScanStatus("SUCCESSFUL");
					virusScanHistory.setPcName(System.getenv("COMPUTERNAME"));
					virusScanHistory.setThreatsFound(0);

					virusScanHistoryList.add(virusScanHistory);

					/* Wait for sometime */
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}

				if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_COMPLETED && noOfThreats > 0) {

					/* Delete the viruses found in scanning. */
					//					final boolean virusesDeleted = handler.deleteViruses(progressBar, 
					//																		 textArea,
					//																		 textForThreatsFound,
					//																		 virusScanning);

					/* Start killing viruses */

					javafx.application.Platform.runLater(new Runnable() {
						
						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							textArea.appendText("\n\n");
							textArea.appendText("Killing:");
							textArea.appendText("\n");
							textArea.appendText("--------");
							textArea.appendText("\n");
						}
					});

					/* Cancel the Scan */
					q.setOnMouseClicked(new EventHandler<Event>() {

						/**
						 * @param me
						 */
						@Override
						public void handle(Event me) {
							isScanCancelled = true;
						}
					});
					
					if (isScanCancelled) {
						handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
						setVirusScanHistoryIfScanIsStopped(handler);
						return null;
					}

					System.out.println("\n");

					removedThreats = 0;
					final Map<String, String> tempVirusDetectedMap = new HashMap<String, String>(virusDetectedMap);
					for (final Map.Entry<String, String> virusEntry : tempVirusDetectedMap.entrySet()) {
						try {
							if (rkm != null) {
								rkm.deleteKey(virusEntry.getKey().substring(1), virusEntry.getValue());
								javafx.application.Platform.runLater(new Runnable() {
						
									/* (non-Javadoc)
									 * @see java.lang.Runnable#run()
									 */
									@Override
									public void run() {
										textArea.appendText("Removed the virus: "+virusEntry.getKey().substring(1)+"\\"+virusEntry.getValue());
										virusScanning.setText("");
										timerTextForThreats.setText("");
									}
								});
								removedThreats++;
								System.out.println("Removed the virus: "+virusEntry.getKey().substring(1)+"\\"+virusEntry.getValue());	
								virusDetectedMap.remove(virusEntry.getKey());
							} else {
								System.out.println("Scan is stopped due to: "+REGISTRY_KEY_MANEGER_EMPTY);
								handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);
								return null;
							}
						} catch (Exception e) {
							System.out.println(e.getLocalizedMessage());
						}

						if (progressCount == tempVirusDetectedMap.size()) {
							updateProgress(1, 1);
						}
						
						progressCount++;
					}
					
					if (StringUtils.equals(drop.getText(), "English")) {
						textForThreatsRemoved.setText("Removed Threats: "+removedThreats);
					} else if (StringUtils.equals(drop.getText(), "Spanish")) { 
						textForThreatsFound.setText("Amenazas eliminados: "+noOfThreats);
					} else if (drop.getText().equals("French")) {
						textForThreatsFound.setText("Menaces supprim�es: "+noOfThreats);
					} else if( drop.getText().equals("Portuguese")) {
						textForThreatsFound.setText("Amea�as removidos: "+noOfThreats);
					}
					
					/* Update the progress */
					updateProgress(1, 1);

					/* Wait for sometime */
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					System.out.println();
					System.out.println("Virus removal is completed");
					
					if (tempVirusDetectedMap != null) {
						tempVirusDetectedMap.clear();
					}
					
					javafx.application.Platform.runLater(new Runnable() {

						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							if (textForScanInProgress.getText() != null) {
								textForScanInProgress.clear();
							}
							textForScanInProgress.appendText("Virus scanning is completed");
						}
					});
				
					if (virusDetectedMap.size() == 0) {
						
						/* Display the protected screen. */
						showTheProtectedPage();
						isPageProtected = true;
						
						javafx.application.Platform.runLater(new Runnable() {
							
							/* (non-Javadoc)
							 * @see java.lang.Runnable#run()
							 */
							@Override
							public void run() {
								/* Set the status */
								statusValInDash.setStyle("-fx-text-fill: green;");
								
								if (drop.getText().equals("English")) {
									statusValInDash.setText("Potentially Protected");
								} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
									statusValInDash.setText("Potencialmente Protegido");
								} else if (drop.getText().equals("French")) {
									statusValInDash.setText("Potentiellement Prot�g�");
								}
								
								VirusScanHistory virusScanHistory = new VirusScanHistory();
								virusScanHistory.setScanDate(timestamp);
								virusScanHistory.setScanStatus("SUCCESSFUL");
								virusScanHistory.setPcName(System.getenv("COMPUTERNAME"));
								virusScanHistory.setThreatsFound(noOfThreats);

								virusScanHistoryList.add(virusScanHistory);
							}
						});
					} else {
						c2.setStyle("-fx-background-color:#DF4444");
						b1.setStyle("-fx-background-color:#373E48");
						c1.setStyle("-fx-background-color:transparent");
						b2.setStyle("-fx-background-color:transparent");
						c3.setStyle("-fx-background-color:transparent");
						b3.setStyle("-fx-background-color:transparent");
						c4.setStyle("-fx-background-color:transparent");
						b4.setStyle("-fx-background-color:transparent");
						b5.setStyle("-fx-background-color:transparent");
						b6.setStyle("-fx-background-color:transparent");

						unprotected.setVisible(true);
						unprotected.setManaged(true);

						isProtected.setVisible(false);
						isProtected.setManaged(false);

						general.setVisible(false);
						general.setManaged(false);

						scann.setVisible(false);
						scann.setManaged(false);

						dash.setVisible(false);
						dash.setManaged(false);

						his.setVisible(false);
						his.setManaged(false);

						about.setVisible(false);
						about.setManaged(false);

						update.setVisible(false);
						update.setManaged(false);
						
						javafx.application.Platform.runLater(new Runnable() {
							
							/* (non-Javadoc)
							 * @see java.lang.Runnable#run()
							 */
							@Override
							public void run() {
								/* Set the text */
								statusValInDash.setStyle("-fx-text-fill: red;");
								
								if (drop.getText().equals("English")) {
									statusValInDash.setText(POTENTIALLY_UNPROTECTED);
								} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
									statusValInDash.setText("Potencialmente Desprotegido");
								} else if (drop.getText().equals("French")) {
									statusValInDash.setText("Potentiellement non prot�g�");
								}

								VirusScanHistory virusScanHistory = new VirusScanHistory();
								virusScanHistory.setScanDate(timestamp);
								virusScanHistory.setScanStatus("FAILED");
								virusScanHistory.setPcName(System.getenv("COMPUTERNAME"));
								virusScanHistory.setThreatsFound(noOfThreats);

								virusScanHistoryList.add(virusScanHistory);
							}
						});
					}
                    if (virusDetectedMap != null) {
                        virusDetectedMap.clear();
                    }
				} else if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_COMPLETED) {

					javafx.application.Platform.runLater(new Runnable() {
						
						/* (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						@Override
						public void run() {
							if (textForScanInProgress.getText() != null) {
								textForScanInProgress.clear();
							}
							textForScanInProgress.appendText("Virus scanning is completed");
							statusValInDash.setStyle("-fx-text-fill: green;");
							if (drop.getText().equals("English")) {
								statusValInDash.setText("Potentially Protected");
							} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
								statusValInDash.setText("Potencialmente Protegido");
							} else if (drop.getText().equals("French")) {
								statusValInDash.setText("Potentiellement Prot�g�");
							}
						}
					});

					showTheProtectedPage();
					isPageProtected = true;
					
					System.out.println("No viruses are found in scanning");
				}

				handler.setVirusScanStatus(VirusScanStatus.VIRUS_JOB_COMPLETED);
				
				
				javafx.application.Platform.runLater(new Runnable() {
					
					/* (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {

						/* Set the values like last-scan, build in Dashboard based on the virus scan results. */
						if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_JOB_COMPLETED) {
							
							lastScanValInDash.setText(timestamp);
							if (StringUtils.equals(drop.getText(), "English")) {
								textForLastScanInHistory.setText("Last Scan: "+timestamp);
							} else if (StringUtils.equals(drop.getText(), "Spanish")) {
								textForLastScanInHistory.setText("�ltimo escaneo: "+timestamp);
							} else if (StringUtils.equals(drop.getText(), "French")) {
								textForLastScanInHistory.setText("Dernier scan: "+timestamp);
							} else if (StringUtils.equals(drop.getText(), "Portuguese")) {
								textForLastScanInHistory.setText("�ltima an�lise: "+timestamp);
							} 

						} else if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STOPPED) {

							statusValInDash.setStyle("-fx-text-fill: red;");

							if (drop.getText().equals("English")) {
								statusValInDash.setText(POTENTIALLY_UNPROTECTED);
							} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
								statusValInDash.setText("Potencialmente Desprotegido");
							} else if (drop.getText().equals("French")) {
								statusValInDash.setText("Potentiellement non prot�g�");
							}

							lastScanValInDash.setText(timestamp);

						}
					}
					
				});
				
				/* Add the scan results to history. */
				processHistoryData();
				
				return null;
			}
		};
	}

	/**
	 * @param event
	 */
	@FXML
	private void notProtectedPressed(final ActionEvent event) {

		handler.setVirusScanStatus(VirusScanStatus.VIRUS_SCAN_STOPPED);

		if (workerThread != null && workerThread.isRunning()) {
			workerThread.cancel();
		}

		if (textArea != null && textArea.getText() != null && !textArea.getText().isEmpty()) {
			textArea.clear();
		}

		javafx.application.Platform.runLater(new Runnable() {
			
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				if (textForScanInProgress.getText() != null) {
					textForScanInProgress.clear();
				}
				textForScanInProgress.appendText("Virus scanning is stopped");
				statusValInDash.setStyle("-fx-text-fill: red;");

				if (drop.getText().equals("English")) {
					statusValInDash.setText(POTENTIALLY_UNPROTECTED);
				} else if (drop.getText().equals("Spanish") || drop.getText().equals("Portuguese")) {
					statusValInDash.setText("Potencialmente Desprotegido");
				} else if (drop.getText().equals("French")) {
					statusValInDash.setText("Potentiellement non prot�g�");
				}

			}
		});
		
		handler.stopScanning();

		c2.setStyle("-fx-background-color:#DF4444");
		b1.setStyle("-fx-background-color:#373E48");

		c1.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		unprotected.setVisible(true);
		unprotected.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

	}

	/**
	 * @param event
	 */
/*	@FXML
	private void protectedPressed(final ActionEvent event) {

		b5.setStyle("-fx-background-color:transparent");

		b6.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:#3FC639");
		b2.setStyle("-fx-background-color:#373E48");

		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		isProtected.setVisible(true);
		isProtected.setManaged(true);

		unprotected.setVisible(false);
		unprotected.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

	} */
	
	/**
	 * @param event
	 */
	@FXML
	private void clearHistory(final ActionEvent event) {

		c3.setStyle("-fx-background-color:#DF4444");
		b3.setStyle("-fx-background-color:#373E48");

		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:transparent");

		c2.setStyle("-fx-background-color:transparent");
		b2.setStyle("-fx-background-color:transparent");

		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");

		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");

		his.setVisible(true);
		his.setManaged(true);

		isProtected.setVisible(false);
		isProtected.setManaged(false);

		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		unprotected.setVisible(false);
		unprotected.setManaged(false);
		
		javafx.application.Platform.runLater(new Runnable() {
		
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				textAreaForHistory.clear();
				clearHistory.setDisable(true);
				
				textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");

				if (StringUtils.equals(drop.getText(), "English")) {
					textAreaForHistory.appendText(HISTORY_UNAVAILABLE);
					textForLastScanInHistory.setText("Last Scan: Please perform a scan.");
				} else if (StringUtils.equals(drop.getText(), "Spanish")) {
					textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_SPANISH);
					textForLastScanInHistory.setText("�ltimo escaneo: Por favor, haga un escaneo.");
				} else if (StringUtils.equals(drop.getText(), "French")) {
					textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_FRENCH);
					textForLastScanInHistory.setText("Dernier scan: Veuillez effectuer un scan.");
				} else if (StringUtils.equals(drop.getText(), "Portuguese")) {
					textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_PORTUGUESE);
					textForLastScanInHistory.setText("�ltima an�lise: Por-favor fa�a uma an�lise.");
				} 
				
				textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
				
				virusScanHistoryCount = 0;
			}
		});
		
		clearHistory.setTooltip(new Tooltip("Clear the history"));
		
	}

	/**
	 * @param event
	 */
	@FXML
	private void ee(final ActionEvent event) {
		drop.setText("English");
		
		/* Dashboard */
		b2.setText("   Dashboard                   ");
		b2.setTooltip(new Tooltip("Dashboard"));
		
		b2.setLayoutX(1.0);
		b2.setLayoutY(109.0);
		
		d1.setText("Dashboard");
		d2.setText("Version: Adware & Toolbar Removal Tool");
		d3.setText("Demo");
		
		statusTextInDash.setText("Status:");
		lastScanTextInDash.setText("Last Scan:");
		buildTextInDash.setText("Build:");
		
		statusValInDash.setText(POTENTIALLY_UNPROTECTED);
		lastScanValInDash.setText("Please perform a scan");
		buildValInDash.setText("01.01.193");
		
		scan.setText("Scan Now");
		scan.setTooltip(new Tooltip("Scan Now"));
		
		/* Scan */
		b1.setText("   Scan                             ");
		b1.setTooltip(new Tooltip("Scan"));

		b1.setLayoutX(1.0);
		b1.setLayoutY(167.0);
		
		/* Unprotected after scan is completed */
		s1.setText("Potentially Unprotected!");
		s2.setText("Your computer may be at risk, please perform a scan.");
		s3.setText("Virus Scan");
		
		s4.setText("Adware Cleaner");
		s5.setText("Toolbar Cleaner");
		s6.setText("Demo");
		
		scan1.setText("Not up to date");
		scan2.setText("Not checked");
		scan3.setText("Not checked");
		
		/* Protected after scan is completed */
		protect1.setText("Potentially Protected!");
		protect2.setText("Your computer has been scanned and protected.");
		protect3.setText("Virus Scan");
		
		protect4.setText("Adware Cleaner");
		protect5.setText("Toolbar Cleaner");
		protect6.setText("Demo");
		
		scan4.setText("Up to date");
		scan5.setText("Checked");
		scan6.setText("Checked");
		
		u.setText("Start Scanning");
		u.setTooltip(new Tooltip("Start Scanning"));
		
		q.setText("Cancel Scan");
		
		/* History */
		b3.setText(" History                        ");
		b3.setTooltip(new Tooltip("History"));
		
		b3.setLayoutX(1.0);
		b3.setLayoutY(228.0);
		
		textForLastScanInHistory.setText("Last Scan: Please perform a scan.");
		textForDemo.setText("Demo");
		
		clearHistory.setText("Clear history");
		clearHistory.setTooltip(new Tooltip("Clear history"));
		
		if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
				&& (StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_SPANISH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_FRENCH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_PORTUGUESE))) {
			textAreaForHistory.clear();
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
			textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
			textAreaForHistory.appendText(HISTORY_UNAVAILABLE);
			clearHistory.setDisable(true);
		}

		/* General Settings */
		b5.setText("General Settings      ");
		b5.setTooltip(new Tooltip("General Settings"));
		
		b5.setLayoutX(6.0);
		b5.setLayoutY(27.0);
		
		g1.setText("General Settings:");
		g2.setText("Version: Adware & Toolbar Removal Tool");
		g3.setText("Build: 01.01.193");
		g4.setText("Demo");

		w.setText("Apply");
		e.setText("Cancel");
		
		if (StringUtils.isNotBlank(textAreaForGeneralSettings.getText())) {
			textAreaForGeneralSettings.clear();
		}
		textAreaForGeneralSettings.setStyle("-fx-text-fill: #00AEEF;");
		textAreaForGeneralSettings.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForGeneralSettings.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE);
		
		/* Update */
		b6.setText("Update                   ");
		b6.setTooltip(new Tooltip("Update"));
		
		b6.setLayoutX(3.0);
		b6.setLayoutY(82.0);
		
		updateFound.setText("Update(s) Found:");
		updateNow.setText("Update Now");
		installUpdates.setText("Install Updates");
		ignore.setText("Ignore");
		demoInUpdate.setText("Demo");
		
		textAreaForUpdate.clear();
		textAreaForUpdate.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForUpdate.appendText(UPDATE_UNAVAILABLE);
		textAreaForUpdate.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForUpdate.setStyle("-fx-text-fill: #00AEEF;");
		
		/* About */
		b4.setText("  About                        ");
		b4.setTooltip(new Tooltip("About"));
		
		b4.setLayoutX(4.0);
		b4.setLayoutY(136.0);
		
		a1.setText("About");
		a2.setText("Version: Adware & Toolbar Removal Tool");
		a3.setText("Device ID: Demo Version");
		a4.setText("Demo");
		
		t.setText("Help");
		y.setText("Register");
		
		/* Advanced Settings */
		b7.setText("Advanced Settings");
		//b7.setTooltip(new Tooltip("Advanced Settings"));
				
		b7.setLayoutX(1.0);
		b7.setLayoutY(292.0);

		txt.setText("An important aspect of technology is its accessibility and moreover assurance that things will not go wrong, as everything which has to do with the technology industry and the ecosystem it has built, has grown phenomenally, so has the need for better security, more reliable interfaces, and optimal performance. Keeping just that in mind at VirusREM we�ve come up with the perfect product which caters to both the mass market of consumers and the niche requirements of corporations.");
	}

	/**
	 * @param event
	 */
	@FXML
	private void ss(final ActionEvent event) {
		drop.setText("Spanish");
		
		/* Dashboard */
		b2.setText(" Panel de Control        ");
		b2.setTooltip(new Tooltip("Panel de Control"));
		
		b2.setLayoutX(1.0);
		b2.setLayoutY(109.0);

		d1.setText("Panel de Control");
		d2.setText("Versi�n: Adware & Herramienta para remover Barra de Herramientas");
		d3.setText("Demo");

		statusTextInDash.setText("Estatus:");
		lastScanTextInDash.setText("�ltimo escaneo:");
		buildTextInDash.setText("Armado:");

		statusValInDash.setText("Potencialmente Desprotegido");
		lastScanValInDash.setText("Por favor, haga un escaneo");
		buildValInDash.setText("01.01.193");
		
		scan.setText("Escanear Ahora");
		scan.setTooltip(new Tooltip("Escanear Ahora"));
		
		/* Scan */
		b1.setText(" Escaneo                      ");
		b1.setTooltip(new Tooltip("Escaneo"));
		
		b1.setLayoutX(1.0);
		b1.setLayoutY(167.0);
		
		/* Unprotected after scan is completed */
		s1.setText("Potencialmente Desprotegido!");
		s2.setText("Tu computadora puede estar en riesgo. Por favor, haz un escaneo.");
		s3.setText("Escaneo de virus");
		
		s4.setText("Limpiador de Adware");
		s5.setText("Limpiador de Barra de Herramientas");
		s6.setText("Demo");
		
		scan1.setText("No actualizado");
		scan2.setText("Sin revisar");
		scan3.setText("Sin revisar");
		
		/* Protected after scan is completed */
		protect1.setText("Potencialmente Protegido!");
		protect2.setText("El ordenador ha sido escaneada y protegido.");
		protect3.setText("Escaneo de virus");
		
		protect4.setText("Limpiador de Adware");
		protect5.setText("Limpiador de Barra de Herramientas");
		protect6.setText("Demo");
		
		scan4.setText("A hoy");
		scan5.setText("Comprobado");
		scan6.setText("Comprobado");
		
		u.setText("Comenzando escaneo");
		u.setTooltip(new Tooltip("Comenzando escaneo"));
		
		q.setText("Cancelar Escaneo");
		
		/* History */
		b3.setText("   Historial                        ");
		b3.setTooltip(new Tooltip("Historial"));
		
		b3.setLayoutX(1.0);
		b3.setLayoutY(228.0);
		
		textForLastScanInHistory.setText("�ltimo escaneo: Por favor, haga un escaneo.");
		textForDemo.setText("Demo");
		
		clearHistory.setText("Limpiar historial");
		clearHistory.setTooltip(new Tooltip("Limpiar historial"));
		
		if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
				&& (StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_FRENCH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_PORTUGUESE))) {
			textAreaForHistory.clear();
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
			textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
			textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_SPANISH);
			clearHistory.setDisable(true);
		}

		/* General Settings */
		b5.setText("  Configuraciones Generales   ");
		b5.setTooltip(new Tooltip("Configuraciones Generales"));
		
		b5.setLayoutX(6.0);
		b5.setLayoutY(27.0);
		
		g1.setText("Configuraciones Generales:");
		g2.setText("Versi�n: Adware & Herramienta para remover Barra de Herramientas");
		g3.setText("Armado: 01.01.193");
		g4.setText("Demo");

		w.setText("Aplicar");
		e.setText("Cancelar");
		
		if (StringUtils.isNotBlank(textAreaForGeneralSettings.getText())) {
			textAreaForGeneralSettings.clear();
		}
		textAreaForGeneralSettings.setStyle("-fx-text-fill: #00AEEF;");
		textAreaForGeneralSettings.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForGeneralSettings.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_SPANISH);
		
		/* Update */
		b6.setText("   Actualizar                   ");
		b6.setTooltip(new Tooltip("Actualizar"));
		
		b6.setLayoutX(3.0);
		b6.setLayoutY(82.0);
		
		updateFound.setText("Actualizaciones Encontradas:");
		updateNow.setText("Actualice Ahora");
		ignore.setText("Ignorar");
		demoInUpdate.setText("Demo");
		
		textAreaForUpdate.clear();
		textAreaForUpdate.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_SPANISH);
		textAreaForUpdate.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForUpdate.setStyle("-fx-text-fill: #00AEEF;");
		
		/* About */
		b4.setText("  Acerca                        ");
		b4.setTooltip(new Tooltip("Acerca"));
		
		b4.setLayoutX(4.0);
		b4.setLayoutY(136.0);
		
		a1.setText("Acerca");
		a2.setText("Versi�n: Adware & Herramienta para remover Barra de");
		a3.setText("ID del dispositivo: Demo Versi�n");
		a4.setText("Demo");
		
		t.setText("Ayuda");
		y.setText("Registrar");
		
		/* Advanced Settings */
		b7.setText("Configuraciones Avanzadas     ");
		//b7.setTooltip(new Tooltip("Configuraciones Avanzadas"));
		
		b7.setLayoutX(1.0);
		b7.setLayoutY(292.0);

		txt.setText("Un aspecto importante de la tecnolog�a es su accesibilidad y garant�a, adem�s, que las cosas no van mal, como todo lo que tiene que ver con la industria de la tecnolog�a y el ecosistema que ha construido, ha crecido enormemente, por lo que tiene la necesidad de mejorar la seguridad, las interfaces m�s fiables y un rendimiento �ptimo. Mantener s�lo eso en mente en VirusREM que hemos llegado con el producto perfecto, que atiende a las necesidades del mercado de masas de los consumidores y las necesidades de las empresas de nicho.");
	}

	/**
	 * @param event
	 */
	@FXML
	private void ff(final ActionEvent event) {
		drop.setText("French");

		/* Dashboard */
//		b2.setText("   Tableau de bord                   ");
		b2.setText("Tableau de bord");
		b2.setTooltip(new Tooltip("Tableau de bord"));
		
		b2.setLayoutX(-12.0);
		b2.setLayoutY(109.0);
		
		d1.setText("Tableau de bord");
		d2.setText("Version: Outil de suppression de barre d�outils et Adwares (logiciels publicitaires)");
		d3.setText("D�mo");

		statusTextInDash.setText("Statut:");
		lastScanTextInDash.setText("Dernier scan:");
		buildTextInDash.setText("Construire:");
		
		statusValInDash.setText("Potentiellement non prot�g�");
		lastScanValInDash.setText("Veuillez effectuer un scan");
		buildValInDash.setText("01.01.193");
		
		scan.setText("Scanner maintenant");
		scan.setTooltip(new Tooltip("Scanner maintenant"));
		
		/* Scan */
		b1.setText(" Scan                             ");
		b1.setTooltip(new Tooltip("Scan"));
		
		b1.setLayoutX(1.0);
		b1.setLayoutY(167.0);

		
		/* Unprotected after scan is completed */
		s1.setText("Potentiellement Non prot�g�!");
		s2.setText("Votre ordinateur peut �tre en risque, veuillez effectuer un scan.");
		s3.setText("Scan de virus");
		
		s4.setText("Nettoyeur des Adwares");
		s5.setText("Nettoyeur de barre d�outils");
		s6.setText("D�mo");
		
		scan1.setText("n'est pas � jour");
		scan2.setText("Non v�rifi�");
		scan3.setText("Non v�rifi�");
		
		/* Protected after scan is completed */
		protect1.setText("Potentiellement Protected!");
		protect2.setText("Votre ordinateur a �t� num�ris� et prot�g�.");
		protect3.setText("Scan de virus");
		
		protect4.setText("Nettoyeur des Adwares");
		protect5.setText("Nettoyeur de barre d�outils");
		protect6.setText("D�mo");
		
		scan4.setText("� jour");
		scan5.setText("V�rifi�");
		scan6.setText("V�rifi�");
		
		u.setText("commencer le scan");
		u.setTooltip(new Tooltip("commencer le scan"));

		q.setText("Annuler Scan");
		
		/* History */
		//b3.setText("   Historique                        ");
		b3.setText("Historique");
		b3.setTooltip(new Tooltip("Historique"));
		
		b3.setLayoutX(-30.0);
		b3.setLayoutY(228.0);

		textForLastScanInHistory.setText("Dernier scan: Veuillez effectuer un scan.");
		textForDemo.setText("D�mo");
		
		clearHistory.setText("Supprimer l�historique");
		clearHistory.setTooltip(new Tooltip("Supprimer l�historique"));
		
		if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
				&& (StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_SPANISH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_PORTUGUESE))) {
			textAreaForHistory.clear();
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
			textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
			textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_FRENCH);
			clearHistory.setDisable(true);
		}
		
		/* General Settings */
		b5.setText("  Param�tres g�n�raux      ");
		b5.setTooltip(new Tooltip("Param�tres g�n�raux"));
		
		b5.setLayoutX(6.0);
		b5.setLayoutY(27.0);

		g1.setText("Param�tres g�n�raux:");
		g2.setText("Version: Outil de suppression de barre d�outils et Adwares");
		g3.setText("Construire: 01.01.193");
		g4.setText("D�mo");

		w.setText("Appliquer");
		e.setText("Annuler");

		if (StringUtils.isNotBlank(textAreaForGeneralSettings.getText())) {
			textAreaForGeneralSettings.clear();
		}
		textAreaForGeneralSettings.setStyle("-fx-text-fill: #00AEEF;");
		textAreaForGeneralSettings.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForGeneralSettings.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_FRENCH);
		
		/* Update */
//		b6.setText("   Mise � jour                   ");
		b6.setText("Mise � jour");
		b6.setTooltip(new Tooltip("Mise � jour"));
		
		b6.setLayoutX(-18.0);
		b6.setLayoutY(82.0);
		
		updateFound.setText("Mise � jour (s) trouv�e (s):");
		updateNow.setText("Mettre � jour maintenant");
		ignore.setText("Ignorer");
		demoInUpdate.setText("D�mo");
		
		textAreaForUpdate.clear();
		textAreaForUpdate.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_FRENCH);
		textAreaForUpdate.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForUpdate.setStyle("-fx-text-fill: #00AEEF;");
		
		/* About */
//		b4.setText("   � propos                        ");
		b4.setText("� propos");
		b4.setTooltip(new Tooltip("� propos"));
		
		b4.setLayoutX(-23.0);
		b4.setLayoutY(136.0);

		a1.setText("� propos");
		a2.setText("Version: Outil de suppression de barre d�outils et des Adwares");
		a3.setText("ID de l'appareil: D�mo Version");
		a4.setText("D�mo");
		
		t.setText("Aide");
		y.setText("S'inscrire");
		
		/* Advanced Settings */
		b7.setText("Param�tres avanc�s");
		//b7.setTooltip(new Tooltip("Param�tres avanc�s"));
		
		b7.setLayoutX(1.0);
		b7.setLayoutY(292.0);
		
		txt.setText("Un aspect important de la technologie est son accessibilit� et de l'assurance de plus que les choses ne vont pas aller mal, comme tout ce qui a � voir avec l'industrie de la technologie et de l'�cosyst�me, il a construit, a connu une croissance ph�nom�nale, a donc la n�cessit� d'une meilleure s�curit�, interfaces plus fiables , et des performances optimales. Garder tout cela � l'esprit � VirusREM nous avons mis au point le produit parfait qui r�pond � la fois le march� de masse des consommateurs et les exigences de niche des soci�t�s.");
	}

	/**
	 * @param event
	 */
	@FXML
	private void pp(final ActionEvent event) {
		drop.setText("Portuguese");
		
		/* Dashboard */
		b2.setText("Painel                   ");
		b2.setTooltip(new Tooltip("Painel"));
		
		b2.setLayoutX(-10.0);
		b2.setLayoutY(109.0);

		d1.setText("Painel");
		d2.setText("Vers�o: Adware & Ferramenta De Remo��o da Barra de Ferramentas");
		d3.setText("Demonstra��o");

		statusTextInDash.setText("Estado:");
		lastScanTextInDash.setText("�ltima an�lise:");
		buildTextInDash.setText("Construa:");
		
		statusValInDash.setText("Potencialmente Desprotegido");
		lastScanValInDash.setText("Por-favor fa�a uma an�lise");
		buildValInDash.setText("01.01.193");
		
		scan.setText("An�lise agora");
		scan.setTooltip(new Tooltip("An�lise agora"));
		
		/* Scan */
//		b1.setText("      An�lise                             ");
		b1.setText("An�lise");
		b1.setTooltip(new Tooltip("An�lise"));
		
		b1.setLayoutX(-38.0);
		b1.setLayoutY(167.0);
		
		/* Unprotected after scan is completed */
		s1.setText("Potencialmente Desprotegido!");
		s2.setText("O seu computador pode estar em risco, por-favor fa�a uma an�lise.");
		s3.setText("An�lise de v�rus");
		
		s4.setText("Limpador do Adware");
		s5.setText("Limpador da Barra de Ferramentas");
		s6.setText("Demonstra��o");
		
		scan1.setText("Desatualizado");
		scan2.setText("N�o verificado");
		scan3.setText("N�o verificado");
		
		/* Protected after scan is completed */
		protect1.setText("Potencialmente Protegido!");
		protect2.setText("O seu computador foi digitalizado e protegida.");
		protect3.setText("An�lise de v�rus");
		
		protect4.setText("Limpador do Adware");
		protect5.setText("Limpador da Barra de Ferramentas");
		protect6.setText("Demonstra��o");
		
		scan4.setText("Atualizado");
		scan5.setText("Verificado");
		scan6.setText("Verificado");

		u.setText("Come�ar a an�lise");
		u.setTooltip(new Tooltip("Come�ar a an�lise"));

		q.setText("Cancele An�lise");
		
		/* History */ 
		b3.setText("Hist�rico");
		b3.setTooltip(new Tooltip("Hist�rico"));
		
		b3.setLayoutX(-33.0);
		b3.setLayoutY(228.0);

		textForLastScanInHistory.setText("�ltima an�lise: Por-favor fa�a uma an�lise.");
		textForDemo.setText("Demonstra��o");
		
		clearHistory.setText("Hist�rico limpo");
		clearHistory.setTooltip(new Tooltip("Hist�rico limpo"));
		
		if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
				&& (StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_SPANISH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_FRENCH)
				||  StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE))) {
			textAreaForHistory.clear();
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
			textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
			textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_PORTUGUESE);
			clearHistory.setDisable(true);
		}

		/* General Settings */
		b5.setText("  Configura��es Gerais      ");
		b5.setTooltip(new Tooltip("Configura��es Gerais"));
		
		b5.setLayoutX(6.0);
		b5.setLayoutY(27.0);
		
		g1.setText("Configura��es Gerais:");
		g2.setText("Vers�o: Adware & Ferramenta De Remo��o da Barra de Ferramentas");
		g3.setText("Construa: 01.01.193");
		g4.setText("Demonstra��o");

		w.setText("Aplique");
		e.setText("Cancele");
		
		if (StringUtils.isNotBlank(textAreaForGeneralSettings.getText())) {
			textAreaForGeneralSettings.clear();
		}
		textAreaForGeneralSettings.setStyle("-fx-text-fill: #00AEEF;");
		textAreaForGeneralSettings.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForGeneralSettings.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForGeneralSettings.appendText(SETTINGS_UNAVAILABLE_IN_PORTUGUESE);

		/* Update */
		b6.setText("Actualiza��oe");
		b6.setTooltip(new Tooltip("Actualiza��oe"));
		 
		b6.setLayoutX(-10.0);
		b6.setLayoutY(82.0);
		updateFound.setText("Actualiza��o(�es) Encontrada(s):");
		updateNow.setText("Actualize agora");
		ignore.setText("Ignore");
		demoInUpdate.setText("Demonstra��o");
		
		textAreaForUpdate.clear();
		textAreaForUpdate.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
		textAreaForUpdate.appendText(UPDATE_UNAVAILABLE_IN_PORTUGUESE);
		textAreaForUpdate.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
		textAreaForUpdate.setStyle("-fx-text-fill: #00AEEF;");
		
		/* About */
		b4.setText(" Sobre                        ");
		b4.setTooltip(new Tooltip("Sobre"));
		
		b4.setLayoutX(4.0);
		b4.setLayoutY(136.0);
		
		a1.setText("Sobre");
		a2.setText("Vers�o: Adware & Ferramenta De Remo��o da Barra de Ferramentas");
		a3.setText("ID do aparelho: Demonstra��o Vers�o");
		a4.setText("Demonstra��o");
		
		t.setText("Ajuda");
		y.setText("Registe-se");
		
		/* Advanced Settings */
		b7.setText("Configura��es Avan�adas");
		//b7.setTooltip(new Tooltip("Configura��es Avan�adas"));
		
		b7.setLayoutX(1.0);
		b7.setLayoutY(292.0);
		
		txt.setText("Um aspecto importante da tecnologia � a sua acessibilidade e garantia, al�m disso, que as coisas n�o v�o dar errado, como tudo o que tem a ver com a ind�stria da tecnologia e do ecossistema que construiu, tem crescido fenomenalmente, tem assim a necessidade de uma melhor seguran�a, as interfaces mais fi�veis e um desempenho ideal. Mantendo apenas isso em mente o tempo VirusREM n�s viemos acima com o produto perfeito, que serve tanto para o mercado de massa de consumidores e os requisitos de nicho de empresas.");
	}

	/**
	 * @param handler
	 */
	private void setVirusScanHistoryIfScanIsStopped(final VirusREMHandler handler) {
		
		if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STOPPED) {
			
			if (StringUtils.equals(drop.getText(), "English")) {
				textForLastScanInHistory.setText("Last Scan: "+timestamp);
			} else if (StringUtils.equals(drop.getText(), "Spanish")) {
				textForLastScanInHistory.setText("�ltimo escaneo: "+timestamp);
			} else if (StringUtils.equals(drop.getText(), "French")) {
				textForLastScanInHistory.setText("Dernier scan: "+timestamp);
			} else if (StringUtils.equals(drop.getText(), "Portuguese")) {
				textForLastScanInHistory.setText("�ltima an�lise: "+timestamp);
			} 
			
			VirusScanHistory virusScanHistory = new VirusScanHistory();
			virusScanHistory.setScanDate(timestamp);
			virusScanHistory.setScanStatus("FAILED");
			virusScanHistory.setPcName(System.getenv("COMPUTERNAME"));
			virusScanHistory.setThreatsFound(0);

			virusScanHistoryList.add(virusScanHistory);
			
			/* Add the scan results to history. */
			processHistoryData();
		}
	}
	
	/**
	 * 
	 */
	private void showTheProtectedPage() {
		
		b5.setStyle("-fx-background-color:transparent");
		b6.setStyle("-fx-background-color:transparent");
		c4.setStyle("-fx-background-color:transparent");
		b4.setStyle("-fx-background-color:transparent");
		c2.setStyle("-fx-background-color:#3FC639");
		b2.setStyle("-fx-background-color:transparent");
		c3.setStyle("-fx-background-color:transparent");
		b3.setStyle("-fx-background-color:transparent");
		c1.setStyle("-fx-background-color:transparent");
		b1.setStyle("-fx-background-color:#373E48");

		isProtected.setVisible(true);
		isProtected.setManaged(true);

		unprotected.setVisible(false);
		unprotected.setManaged(false);

		general.setVisible(false);
		general.setManaged(false);

		scann.setVisible(false);
		scann.setManaged(false);

		dash.setVisible(false);
		dash.setManaged(false);

		his.setVisible(false);
		his.setManaged(false);

		about.setVisible(false);
		about.setManaged(false);

		update.setVisible(false);
		update.setManaged(false);

	}
	
	/**
	 * @param event
	 */
	@FXML
	public void handleCloseButtonAction(final ActionEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * @param event
	 */
	@FXML
	public void handleInstallUpdatesButtonAction(final ActionEvent event) {
		//TODO
	}
	
	/**
	 * @param event
	 */
	@FXML
	public void handleIgnoreButtonAction(final ActionEvent event) {
		//TODO
	}
	
	/**
	 * @param event
	 */
	@FXML
	public void handleHelpButtonAction(final ActionEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://virusrem.com/support"));
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (URISyntaxException use) {
			System.out.println(use.getMessage());
		}
	}

	/**
	 * @param event
	 */
	@FXML
	public void handleRegisterButtonAction(final ActionEvent event) {
		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://virusrem.com/register"));
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (URISyntaxException use) {
			System.out.println(use.getMessage());
		}
	}

	/**
	 * @param textAreaForHistory
	 */
	public void writeVirusScanHistoryIntoFile(final TextArea textAreaForHistory) {
		
		try {

			final String userWorkingDrive = System.getProperty("user.dir").split(":")[0];
			
			System.out.println(userWorkingDrive);
			
			final File file = new File(userWorkingDrive 
								  + ":" 
								  + File.separator 
								  + "VirusScanHistory" 
								  + File.separator 
								  + virusScanHistoryFile);
			
			file.getParentFile().mkdirs();
		    if (!file.exists())
		    {
		        file.createNewFile();
		    }
		    
			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);
			
			if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
					&& !StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE)
					&& !StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_SPANISH)
					&& !StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_FRENCH)
					&& !StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE_IN_PORTUGUESE)) {
				bw.write(textAreaForHistory.getText());
			} else {
				bw.write("");
			}
			
			bw.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param textAreaForHistory
	 */
	public void saveLanguageIntoFile(final String languageName) {
		try {

			final String userDirectory = System.getProperty("user.dir");
			final File file = new File(userDirectory
									 + File.separator
									 + languageFile);
			
			System.out.println(file.getAbsolutePath());

			if (!file.exists()) {
				file.createNewFile();
			} 

			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);

			if (StringUtils.isNotBlank(languageName)) {
				bw.write(languageName);
			} else {
				bw.write("");
			}
			
			bw.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param textAreaForHistory
	 */
/* public void writeVirusScanHistoryIntoFileInsideCode(final TextArea textAreaForHistory) {
		
		try {

			final File file = new File(virusScanHistoryFile);
			
			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);
			
			if (StringUtils.isNotBlank(textAreaForHistory.getText()) 
					&& !StringUtils.contains(textAreaForHistory.getText(), HISTORY_UNAVAILABLE)) {
				bw.write(textAreaForHistory.getText());
			} else {
				bw.write("");
			}
			
			bw.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	} */
	
	/**
	 * @return
	 */
	public TextArea readVirusScanHistoryFromFile() {

		BufferedReader br = null;
		final TextArea textArea = new TextArea();

		try {

			String currentLine;
			final String userWorkingDrive = System.getProperty("user.dir").split(":")[0];
			
			System.out.println(userWorkingDrive);
			
			final File file = new File(userWorkingDrive 
								  + ":" 
								  + File.separator 
								  + "VirusScanHistory" 
								  + File.separator 
								  + virusScanHistoryFile);
			
			System.out.println(file.getParentFile().getAbsolutePath());
			
			if (file.exists()) {
				br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				while ((currentLine = br.readLine()) != null) {
					if (!currentLine.isEmpty()) {
						if (StringUtils.contains(currentLine, "PC Name:")) {
							textArea.appendText(currentLine+"\n\n\n\n");
						} else {
							textArea.appendText(currentLine+"\n\n");
						}
					}
					System.out.println(currentLine);
				}
				System.out.println(textArea.getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return textArea;
	}
	
	/**
	 * @return
	 */
	public String readLastSavedLanguageFromFile() {

		BufferedReader br = null;
		final StringBuffer languageBuffer = new StringBuffer();

		try {

			String currentLine;

			final String userDirectory = System.getProperty("user.dir");
			final File file = new File(userDirectory
									 + File.separator
									 + languageFile);

			if (file.exists()) {
				br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				while ((currentLine = br.readLine()) != null) {
					if (!currentLine.isEmpty()) {
						languageBuffer.append(currentLine);
					}
					System.out.println(currentLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return languageBuffer.toString();
	}
	
	/**
	 * 
	 */
	private void processHistoryData() {
		
		if (handler.getVirusScanStatus() == VirusScanStatus.VIRUS_JOB_COMPLETED 
				|| handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STOPPED) {
			
			textAreaForHistory.setStyle("-fx-text-fill: #00AEEF;");
			
			/* Enable the button */
			if (clearHistory.isDisabled() 
					&& (textAreaForHistory.getText().contains(HISTORY_UNAVAILABLE)
							|| textAreaForHistory.getText().contains(HISTORY_UNAVAILABLE_IN_SPANISH)
							|| textAreaForHistory.getText().contains(HISTORY_UNAVAILABLE_IN_FRENCH)
							|| textAreaForHistory.getText().contains(HISTORY_UNAVAILABLE_IN_PORTUGUESE))
					&& virusScanHistoryList.size() > 0) {
				clearHistory.setDisable(false);
				textAreaForHistory.clear();
			}
			
			textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 11));
			
			for (final VirusScanHistory virusScanHistory : virusScanHistoryList) {
				
				/* Scanned Date */
				final Text scannedText = new Text("Scanned: "+virusScanHistory.getScanDate());
				scannedText.setLayoutX(50.0);
				scannedText.setLayoutY(150.0);

				if (virusScanHistoryCount != 0) {
					textAreaForHistory.appendText("\n\n\n\n");
				}
				
				textAreaForHistory.appendText(scannedText.getText());
				textAreaForHistory.appendText("                                                         ");
				
				/* Status */
				final String jobStatus = virusScanHistory.getScanStatus();
				final Text jobStatusText = new Text(jobStatus);

				final Text statusText = new Text("Status: "+jobStatusText.getText());
				statusText.setLayoutX(450.0);
				statusText.setLayoutY(150.0);

				textAreaForHistory.appendText(statusText.getText());
				
				textAreaForHistory.appendText("\n\n");
				
				/* PC Name */
				final Text pcNameText = new Text("PC Name: "+virusScanHistory.getPcName());
				pcNameText.setLayoutX(50.0);
				pcNameText.setLayoutY(175.0);

				textAreaForHistory.appendText(pcNameText.getText());
				textAreaForHistory.appendText("                                                                                  ");
				
				/* Threats Found */
				final Text threatsFoundText = new Text("Threats Found: "+virusScanHistory.getThreatsFound());
				threatsFoundText.setLayoutX(450.0);
				threatsFoundText.setLayoutY(175.0);
				
				textAreaForHistory.appendText(threatsFoundText.getText());
				virusScanHistoryCount++;
				
			}
		} else {
			
//			if (handler.getVirusScanStatus() == null || handler.getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STARTED) {
//
//				if (textAreaForHistory.getText() == null || textAreaForHistory.getText().isEmpty()) {
//					textAreaForHistory.appendText("\n\n\n\n\n\n\n\n\n\n\n                                                  ");
//
//					if (StringUtils.equals(drop.getText(), "English")) {
//						textAreaForHistory.appendText(HISTORY_UNAVAILABLE);
//						textForLastScanInHistory.setText("Last Scan: Please perform a scan.");
//					} else if (StringUtils.equals(drop.getText(), "Spanish")) {
//						textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_SPANISH);
//						textForLastScanInHistory.setText("�ltimo escaneo: Por favor, haga un escaneo.");
//					} else if (StringUtils.equals(drop.getText(), "French")) {
//						textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_FRENCH);
//						textForLastScanInHistory.setText("Dernier scan: Veuillez effectuer un scan.");
//					} else if (StringUtils.equals(drop.getText(), "Portuguese")) {
//						textAreaForHistory.appendText(HISTORY_UNAVAILABLE_IN_PORTUGUESE);
//						textForLastScanInHistory.setText("�ltima an�lise: Por-favor fa�a uma an�lise.");
//					} 
//					
//					textAreaForHistory.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));
//					
//					/* First time, button is disabled */
//					clearHistory.setDisable(true);
//					
//				}
//			}
		}

		virusScanHistoryList.clear();
	}
	
}