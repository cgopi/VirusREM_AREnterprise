package com.virus.removal.constants;

/**
 * @author cgopi
 *
 */
public enum VirusScanStatus {
	
	VIRUS_SCAN_STARTED("Virus scan is Started"),
	VIRUS_SCAN_STOPPED("Virus scan is stopped"),
	VIRUS_SCAN_COMPLETED("Virus scan is completed"),
	VIRUS_JOB_COMPLETED("Job is completed");
	
	private String virusScanStatus;
	
	/**
	 * @param virusScanStatus
	 */
	private VirusScanStatus(final String virusScanStatus) {
		this.virusScanStatus = virusScanStatus;
	}

	/**
	 * @return
	 */
	public String getVirusScanStatus() {
		return virusScanStatus;
	}

}
