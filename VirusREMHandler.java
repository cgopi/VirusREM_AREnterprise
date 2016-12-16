package com.virus.removal.handler;

//import java.io.BufferedInputStream;
import com.virus.removal.constants.VirusScanStatus;
import com.virus.removal.registry.RegistryKeyManager;

/**
 * @author cgopi
 *
 */
public class VirusREMHandler {

	//	private VirusRegistry virusRegistry = null;
	//	private VirusCache virusCache = null;
	private RegistryKeyManager rkm = null;

	//	private List<String> virusSearchList = new ArrayList<String>();
	//	private Map<String, String> virusDetectedMap = null;
	//	private Set<String> viruses = new HashSet<String>();	
	
	private VirusScanStatus virusScanStatus = null;
	
	private static VirusREMHandler virusREMHandler = null;
	//	private static final String REGISTRY_KEY_MANEGER_EMPTY = "Registry key manager is empty";

	//	private static final String DIRECTORY_OF = "Directory of";
	//	private static final String FILE_NOT_FOUND = "File Not Found";
	//	private static final String DELETED_FILE = "Deleted file";
	
	//	/**
	//	 * 
	//	 */
	//	public VirusREMHandler() {
	//		virusRegistry = VirusRegistry.getInstance();
	//		virusCache = VirusCache.instance;
	//		rkm = RegistryKeyManager.getInstance();
	//	}
	
	/**
	 * @return
	 */
	public static VirusREMHandler getInstance() {
		if(virusREMHandler == null) {
			virusREMHandler = new VirusREMHandler();
		}
		return virusREMHandler;
	}

//	/**
//	 * Start scanning for viruses
//	 * 
//	 * @param progressBar
//	 * @param textArea
//	 * @param textForThreats
//	 * @param virusScanning
//	 * @param workerThread
//	 * @return
//	 */
//	public Map<String,String> startScanning(final ProgressBar progressBar, 
//											final TextArea textArea, 
//											final Text textForThreats,
//											final Text virusScanning,
//											final Task<Void> workerThread) {
//		int index = 1;
//		boolean isNextVirus = false;
//		virusDetectedMap = new HashMap<String, String>();
//		final List<String> detectedViruses = new ArrayList<String>();
//		
//		textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
//		
//		if (textArea.getText() != null && !textArea.getText().isEmpty()) {
//			textArea.clear();
//		}
//		
//		if (virusScanning != null && !virusScanning.getText().isEmpty()) {
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					virusScanning.setText("Scanning...");
//				}
//			});
//		}
//
//		progressBar.setTooltip(new Tooltip("Virus scan in progress"));
//
//		if (textForThreats != null && textForThreats.getText() != null && !textForThreats.getText().isEmpty()) {
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					textForThreats.setText("");
//				}
//			});
//		}
//		
//		System.out.println("Scan in progress..!!");
//		System.out.println();
//		
//		if(virusRegistry != null) {
//			virusSearchList.clear();
//			virusSearchList = virusRegistry.getVirusSearchList();
//		}
//		if(virusCache != null) {
//			viruses = virusCache.getAllViruses();
//		}
//		if(rkm == null) {
//			rkm = RegistryKeyManager.getInstance();
//		}
//		
//		/* Wait for sometime */
//		try {
//			Thread.sleep(5000);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		javafx.application.Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				textArea.appendText("Currently Scanning:");
//				textArea.appendText("\n");
//				textArea.appendText("----------------------");
//				textArea.appendText("\n");
//			}
//		});
//		
//		for (final String virus : viruses) {
//			if(rkm == null) {
//				virusDetectedMap.put(VirusScanStatus.VIRUS_SCAN_STOPPED.toString(), REGISTRY_KEY_MANEGER_EMPTY);
//				break;
//			}
//			
//			if (isNextVirus) {
//				javafx.application.Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						textArea.appendText("\n\n");
//					}
//				});
//			}
//
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					textArea.appendText(virus);
//					textArea.appendText("\n");
//				}
//			});
//			for (final String virusSearchUsingReg : virusSearchList) {
//				javafx.application.Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						textArea.appendText("\n");
//						textArea.appendText(virusSearchUsingReg);
//					}
//				});
//				try {
//					if(virus != null && !virus.isEmpty() && virusSearchUsingReg != null && !virusSearchUsingReg.isEmpty()) {
//						if(rkm != null) {
//							rkm.query(virusSearchUsingReg, virus);
//							if(rkm.getKey() != null && !rkm.getKey().isEmpty()) {
//								javafx.application.Platform.runLater(new Runnable() {
//									@Override
//									public void run() {
//										textArea.appendText("\n");
//									}
//								});
//								detectedViruses.add(virusSearchUsingReg+"\\"+virus);
//								System.out.println("Found the virus: "+virusSearchUsingReg+"\\"+virus);
//								virusDetectedMap.put(String.valueOf(index++)+virusSearchUsingReg, virus);
//							}
//						} else {
//							virusDetectedMap.put(VirusScanStatus.VIRUS_SCAN_STOPPED.toString(), REGISTRY_KEY_MANEGER_EMPTY);
//							break;
//						}
//					} 
//				} catch (Exception e) {
//					System.out.println("Virus scanning is abruptly terminated");
//					virusDetectedMap.put(VirusScanStatus.VIRUS_SCAN_ABRUPTLY_STOPPED.toString(), e.getMessage());
//					return virusDetectedMap;
//				}
//				isNextVirus = true;
//			}
//		}
//		
//		if (workerThread != null && workerThread.isCancelled()) {
//			virusScanStatus = VirusScanStatus.VIRUS_SCAN_STOPPED;
//		} else {
//			virusScanStatus = VirusScanStatus.VIRUS_SCAN_COMPLETED;
//		}
//		System.out.println("Virus scanning is completed");
//		
//		javafx.application.Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				virusScanning.setText("Scanned");
//			}
//		});
//		
//		if(virusDetectedMap != null 
//				&& virusDetectedMap.size() > 0 
//				&& !virusDetectedMap.containsKey(VirusScanStatus.VIRUS_SCAN_STOPPED.toString())
//				&& !virusDetectedMap.containsKey(VirusScanStatus.VIRUS_SCAN_ABRUPTLY_STOPPED.toString())) {
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					textForThreats.setText("Found Threats: "+virusDetectedMap.size());
//				}
//			});
//
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					textArea.appendText("\n\n");
//					textArea.appendText("Found the below virus(s):");
//					textArea.appendText("\n");
//					textArea.appendText("-----------------------------");
//					textArea.appendText("\n");
//				}
//			});
//			
//			for (final String detectedVirus : detectedViruses) {
//				javafx.application.Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						textArea.appendText(detectedVirus);
//						textArea.appendText("\n");
//					}
//				});
//			}
//			
//			/* Wait for sometime */
//			try {
//				Thread.sleep(5000);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		} else {
//
//			javafx.application.Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					textForThreats.setText("Found Threats: 0");
//					textArea.appendText("\n\n");
//					textArea.appendText("No threats detected");
//				}
//			});
//			
//			/* Wait for sometime */
//			try {
//				Thread.sleep(1500);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//		
//		return virusDetectedMap;
//	}

	/**
	 *  Stop scanning for viruses 
	 */
	public void stopScanning() {
		if(rkm != null) {
			Process queryProc = rkm.getQueryProc();
			if (queryProc != null) {
				queryProc.destroy();
			}
			rkm = null;
		}
		virusScanStatus = VirusScanStatus.VIRUS_SCAN_STOPPED;
		System.out.println("Virus scanning is stopped");
		System.out.println();
	}

	/**
	 * Delete the viruses 
	 * 
	 * @param progressBar
	 * @param textArea
	 * @param textForThreats
	 * @param virusScanning
	 * @return
	 */
//	public boolean deleteViruses(final ProgressBar progressBar, 
//								 final TextArea textArea, 
//								 final Text textForThreats,
//								 final Text virusScanning) {
//		boolean virusesDeleted = false;
//		
//		javafx.application.Platform.runLater(new Runnable() {
//			@Override
//			public void run() {
//				textArea.appendText("\n\n");
//				textArea.appendText("Killing:");
//				textArea.appendText("\n");
//				textArea.appendText("--------");
//				textArea.appendText("\n");
//			}
//		});
//		if(getVirusScanStatus() == VirusScanStatus.VIRUS_SCAN_STOPPED) {
//			return virusesDeleted;
//		}
//		if(virusDetectedMap != null 
//				&& virusDetectedMap.size() > 0 
//				&& !virusDetectedMap.containsKey(VirusScanStatus.VIRUS_SCAN_STOPPED.toString())
//				&& !virusDetectedMap.containsKey(VirusScanStatus.VIRUS_SCAN_ABRUPTLY_STOPPED.toString())) {
//			System.out.println("\n");
//			for(final Map.Entry<String, String> virusEntry : virusDetectedMap.entrySet()) {
//				try {
//					if (rkm != null) {
//						rkm.deleteKey(virusEntry.getKey().substring(1), virusEntry.getValue());
//						javafx.application.Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
//								textArea.appendText("Removed the virus: "+virusEntry.getKey().substring(1)+"\\"+virusEntry.getValue());
//								virusScanning.setText("Scanned & Killed");
//							}
//						});
//						System.out.println("Removed the virus: "+virusEntry.getKey().substring(1)+"\\"+virusEntry.getValue());
//						virusesDeleted = true;
//					} else {
//						virusDetectedMap.put(VirusScanStatus.VIRUS_SCAN_STOPPED.toString(), REGISTRY_KEY_MANEGER_EMPTY);
//						break;
//					}
//				} catch (Exception e) {
//					System.out.println(e.getLocalizedMessage());
//				}
//			}
//		} 
//		
//		/* Wait for sometime */
//		try {
//			Thread.sleep(1500);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println();
//		System.out.println("Virus removal is completed");
//		if (virusDetectedMap != null) {
//			virusDetectedMap.clear();
//		}
//		return virusesDeleted;
//	}

	/* Help information on VirusREM */
	public void help() {
		//TODO
	}

	/**
	 * @return
	 */
	public VirusScanStatus getVirusScanStatus() {
		return virusScanStatus;
	}

	/**
	 * @param virusScanStatus
	 */
	public void setVirusScanStatus(final VirusScanStatus virusScanStatus) {
		this.virusScanStatus = virusScanStatus;
	}
	
	/**
	 * @param command
	 * @return
	 */
//	private String executeCommand(final String command) {
//
//		String output = null;
//		Process p;
//		try {
//			p = Runtime.getRuntime().exec(command);
//			p.waitFor();
//			Thread.sleep(10000);
//			//BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			final BufferedInputStream inputStream = new BufferedInputStream(p.getInputStream());
//			
//			byte[] byteArray = new byte[1024];
//
////			String line = "";			
////			while ((line = reader.readLine())!= null) {
////				output.append(line);
////			}
//            Thread.sleep(15000);
//			while (inputStream.available() > 0) {
//                int length = inputStream.read(byteArray, 0, 1024);
//                if (length < 0)
//                    break;
//                output = new String(byteArray, 0, length);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return output;
//
//	}

	/**
	 * @param commandOutput
	 * @return
	 */
//	private String parseTheOutputAndFetchFilePath(final String commandOutput) {
//		return commandOutput.split(DIRECTORY_OF+" ")[1].split("\n")[0].trim();
//	}

}