package com.virus.removal.pojo;

import java.io.Serializable;

/**
 * @author cgopi
 *
 */
public class VirusScanHistory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7053954007252118069L;

	private String scanDate;
	
	private String scanStatus;
	
	private String pcName;
	
	private int threatsFound;
	
	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(final String scanDate) {
		this.scanDate = scanDate;
	}

	public String getScanStatus() {
		return scanStatus;
	}

	public void setScanStatus(final String scanStatus) {
		this.scanStatus = scanStatus;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(final String pcName) {
		this.pcName = pcName;
	}

	public int getThreatsFound() {
		return threatsFound;
	}

	public void setThreatsFound(final int threatsFound) {
		this.threatsFound = threatsFound;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((scanDate == null) ? 0 : scanDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VirusScanHistory other = (VirusScanHistory) obj;
		if (scanDate == null) {
			if (other.scanDate != null)
				return false;
		} else if (!scanDate.equals(other.scanDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "scanDate=" + scanDate + "  scanStatus="
				+ scanStatus + "  pcName=" + pcName + "  threatsFound="
				+ threatsFound;
	}
	
}