
package com.virus.removal.registry;
import java.io.*;

/**
 * Registry key manager to add/delete/search key signature(s) from/to windows registry.
 *
 * @author cgopi
 * 
 */
public class RegistryKeyManager {

	private static RegistryKeyManager regKey = null;

	private final String TYPES[] = { "SZ", "BINARY", "DWORD", "QWORD", 
			"DWORD_LITTLE_ENDIAN", "QWORD_LITTLE_ENDIAN", "DWORD_BIG_ENDIAN", 
			"EXPAND_SZ", "LINK", "MULTI_SZ", "NONE", "RESOURCE_LIST" };

	private String type = "", value = "", key = "";
	
	private Process queryProc = null;
	private Process deleteProc = null;

	/**
	 * @return
	 */
	public static RegistryKeyManager getInstance() {
		if(regKey == null) {
			regKey = new RegistryKeyManager();
		}
		return regKey;
	}

	/**
	 * @param loc
	 * @param k
	 * @throws Exception
	 */
	public void query(final String loc, final String k) throws Exception {
		if(key != null && key.length() > 0) {
			key = "";
			type = "";
			value = "";
		}
		queryProc = Runtime.getRuntime().exec("reg QUERY \"" + loc + "\" /v \"" + k + "\"");

		BufferedReader in = new BufferedReader( new InputStreamReader( queryProc.getInputStream() ) );
		String out = "";

		while ((out = in.readLine()) != null) {
			if (out.matches("(.*)\\s+REG_(.*)")) { break; }
		}
		in.close();

		if(out != null && !out.isEmpty()) {
			String str[] = out.split(" ");
			int b = 0;
			for (int a=0; a < str.length; a++) {
				if ( str[a].matches("\\S+") ) {
					switch (b) {
					case 0: key = str[a]; break;
					case 1: type = str[a]; break;
					case 2: value = str[a]; break;
					}
					b++;
				}
			}
		}

	}

	/**
	 * @return
	 */
	public String getKey() { return key; }

	/**
	 * @return
	 */
	public String getType() { return type; }

	/**
	 * @return
	 */
	public String getValue() { return value; }

	/**
	 * @param loc
	 * @param name
	 * @param dType
	 * @param value
	 * @return
	 * @throws Exception
	 */
	protected boolean add(final String loc, final String name, final String dType, final String value) throws Exception {
		boolean comp = false, valid = false;

		for (int a = 0; a < TYPES.length; a++) {
			if (dType.equalsIgnoreCase("REG_" + TYPES[a])) { valid = true; break; }
		}

		if ( valid ) {
			Process p = Runtime.getRuntime().exec("reg ADD \"" + loc + "\" /v \"" + name + "\" /t \"" + dType + "\" /d \"" + value + "\"");

			BufferedReader in = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
			String out = "";

			while ( (out = in.readLine() ) != null ) {
				if (out.equalsIgnoreCase("The operation is completed successfully.")) { comp = true; }
			}
			in.close();
		}

		return comp;
	}

	/**
	 * @param loc
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean deleteKey(final String loc, final String key) throws Exception {
		boolean comp = false;
		deleteProc = Runtime.getRuntime().exec("reg delete \"" + loc + "\" /v \"" + key + "\" /f");
		InputStream is = deleteProc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equalsIgnoreCase("The operation is completed successfully.")) { 
				comp = true;
			}
		}
		br.close();
		return comp;
	}

	/**
	 * @return
	 */
	public Process getQueryProc() {
		return queryProc;
	}

	/**
	 * @param queryProc
	 */
	public void setQueryProc(final Process queryProc) {
		this.queryProc = queryProc;
	}

	/**
	 * @return
	 */
	public Process getDeleteProc() {
		return deleteProc;
	}

	/**
	 * @param deleteProc
	 */
	public void setDeleteProc(final Process deleteProc) {
		this.deleteProc = deleteProc;
	}

}