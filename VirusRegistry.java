package com.virus.removal.registry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgopi
 *
 */
public class VirusRegistry {

	private static VirusRegistry virusReg = null;

	/* HKEY_LOCAL_MACHINE */
	private final static String HKLM_SHELLFOLDERS = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders";
	private final static String HKLM_USERSHELLFOLDERS = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\User Shell Folders";
	private final static String HKLM_RUN = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Run";
	private final static String HKLM_RUNONCE = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\RunOnce";
	private final static String HKLM_RUNSERVICES = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\RunServices";
	private final static String HKLM_RUNSERVICESONCE = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\RunServicesOnce";
//	private final static String HKLM_WINLOGON_SHELL = "HKLM\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon\\Shell";
//	private final static String HKLM_INSTALLEDCOMPONENTS_KEYNAME = "HKLM\\Software\\Microsoft\\Active Setup\\Installed Components\\KeyName";
//	private final static String HKLM_EXEFILE_COMMAND = "HKLM\\Software\\CLASSES\\exefile\\shell\\open\\command";
//	private final static String HKLM_COMFILE_COMMAND = "HKLM\\Software\\CLASSES\\comfile\\shell\\open\\command";
//	private final static String HKLM_BATFILE_COMMAND = "HKLM\\Software\\CLASSES\\batfile\\shell\\open\\command";
//	private final static String HKLM_HTAFILE_COMMAND = "HKLM\\Software\\CLASSES\\htafile\\shell\\open\\command";
//	private final static String HKLM_PIFFILE_COMMAND = "HKLM\\Software\\CLASSES\\piffile\\shell\\open\\command";
//	private final static String HKLM_POLICIES_RUN = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\Run";
//	private final static String HKLM_WINDOWS_NT = "HKLM\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Windows";
//	private final static String HKLM_APPINIT_DLLS = "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Windows\\AppInit_DLLs";
//	private final static String HKLM_SHARED_TASK_SCHEDULER = "HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\SharedTaskScheduler";
//	private final static String HKLM_RUNONCEEX = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\RunOnceEx";
//	private final static String HKLM_BROWSER_HELPER_OBJECTS = "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Browser Helper Objects";
//	private final static String HKLM_SHELLSCRAP = "HKLM\\Software\\CLASSES\\ShellScrap";
//	private final static String HKLM_LYCOS = "HKLM\\Software\\Lycos\\Sidesearch";
//	private final static String HKLM_WOW6432NODE = "HKLM\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall";
//	private final static String HKLM_PNPSVC_SERVICES_DLL = "HKLM\\SYSTEM\\CurrentControlSet\\Services\\pnpsvc\\Parameters\\ServiceDll";
//	private final static String HKLM_SERVICENAME_SERVICES_DLL = "HKLM\\SYSTEM\\CurrentControlSet\\Services\\servicename\\Parameters\\ServiceDll";
//	private final static String HKLM_SVC= "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Svchost";
//	private final static String HKLM_INTEXPLORE= "HKLM\\SOFTWARE\\Clients\\StartMenu\\Internet\\INTEXPLORE.pif\\ToP";
	
	/* HKEY_CURRENT_USER */
	private final static String HKCU_SHELLFOLDERS = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders";
	private final static String HKCU_USERSHELLFOLDERS = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\User Shell Folders";
	private final static String HKCU_RUN = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run";
	private final static String HKCU_RUNONCE = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\RunOnce";
	private final static String HKCU_RUNSERVICES = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\RunServices";
	private final static String HKCU_RUNSERVICESONCE = "HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\RunServicesOnce";
//	private final static String HKCU_AGENT_APPS = "HKCU\\Software\\Mirabilis\\ICQ\\Agent\\Apps";
//	private final static String HKCU_APPS_TEST = "HKEY_CURRENT_USER\\Software\\Mirabilis\\ICQ\\Agent\\Apps\\test";
//	private final static String HKCU_WINLOGON_SHELL = "HKCU\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon\\Shell";
//	private final static String HKCU_POLICIES_RUN = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\Run";
//	private final static String HKCU_WINDOWS_NT = "HKCU\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Windows";
//	private final static String HKCU_ICQ = "HKCU\\Software\\Mirabilis\\ICQ\\Agent\\Apps"; 
//	private final static String HKCU_ARPCACHE = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\App Management\\ARPCache";
//	private final static String HKCU_PAV = "HKCU\\Software\\PAV";	
	
	/* HKEY_CLASSES_ROOT */
	private final static String HKCR_EXEFILE_COMMAND = "HKCR\\exefile\\shell\\open\\command";
	private final static String HKCR_COMFILE_COMMAND = "HKCR\\comfile\\shell\\open\\command";
	private final static String HKCR_BATFILE_COMMAND = "HKCR\\batfile\\shell\\open\\command";
	private final static String HKCR_HTAFILE_COMMAND = "HKCR\\htafile\\shell\\open\\command";
	private final static String HKCR_PIFFILE_COMMAND = "HKCR\\piffile\\shell\\open\\command";	
	private final static String HKCR_TXTFILE_COMMAND = "HKCR\\txtfile\\shell\\open\\command";
//	private final static String HKCR_EXEFILE_RUNAS_COMMAND = "HKCR\\exefile\\shell\\runas\\command";
//	private final static String HKCR_CHECK_ASSOC = "HKCR\\WindowFiles\\Check_Associations";

	private List<String> virusSearchList = new ArrayList<String>();

	/**
	 * @return
	 */
	public static VirusRegistry getInstance() {
		if(virusReg == null) {
			virusReg = new VirusRegistry();
		}
		return virusReg;
	}

	/**
	 * @return
	 */
	public List<String> getVirusSearchList() {
		virusSearchList.add(HKCU_RUN);
		virusSearchList.add(HKCU_RUNONCE);
		virusSearchList.add(HKCU_RUNSERVICES);
		virusSearchList.add(HKLM_RUN);
		virusSearchList.add(HKLM_RUNONCE);
		virusSearchList.add(HKLM_RUNSERVICES);
		virusSearchList.add(HKLM_RUNSERVICESONCE);
		virusSearchList.add(HKCU_SHELLFOLDERS);
		virusSearchList.add(HKCU_USERSHELLFOLDERS);
		virusSearchList.add(HKLM_SHELLFOLDERS);
		virusSearchList.add(HKLM_USERSHELLFOLDERS);
//		virusSearchList.add(HKLM_WINLOGON_SHELL);
//		virusSearchList.add(HKLM_INSTALLEDCOMPONENTS_KEYNAME);
		virusSearchList.add(HKCR_COMFILE_COMMAND);
		virusSearchList.add(HKCR_EXEFILE_COMMAND);
		virusSearchList.add(HKCR_HTAFILE_COMMAND);
		virusSearchList.add(HKCR_BATFILE_COMMAND);		
		virusSearchList.add(HKCR_PIFFILE_COMMAND);
//		virusSearchList.add(HKLM_COMFILE_COMMAND);
//		virusSearchList.add(HKLM_EXEFILE_COMMAND);
//		virusSearchList.add(HKLM_HTAFILE_COMMAND);
//		virusSearchList.add(HKLM_BATFILE_COMMAND);
//		virusSearchList.add(HKLM_PIFFILE_COMMAND);
		virusSearchList.add(HKCU_RUNSERVICESONCE);
//		virusSearchList.add(HKCU_AGENT_APPS);
//		virusSearchList.add(HKCU_APPS_TEST);
//		virusSearchList.add(HKCU_WINLOGON_SHELL);
//		virusSearchList.add(HKCU_POLICIES_RUN);
//		virusSearchList.add(HKCU_WINDOWS_NT);
//		virusSearchList.add(HKCU_ICQ);
//		virusSearchList.add(HKCU_ARPCACHE);
//		virusSearchList.add(HKCU_PAV);
//		virusSearchList.add(HKLM_BROWSER_HELPER_OBJECTS);
//		virusSearchList.add(HKLM_SHELLSCRAP);
//		virusSearchList.add(HKLM_WOW6432NODE);
//		virusSearchList.add(HKLM_WINDOWS_NT);
//		virusSearchList.add(HKLM_APPINIT_DLLS);
//		virusSearchList.add(HKLM_RUNONCEEX);
//		virusSearchList.add(HKLM_SHARED_TASK_SCHEDULER);
//		virusSearchList.add(HKLM_POLICIES_RUN);
//		virusSearchList.add(HKLM_PNPSVC_SERVICES_DLL);
//		virusSearchList.add(HKLM_SVC);
//		virusSearchList.add(HKLM_SERVICENAME_SERVICES_DLL);
//		virusSearchList.add(HKLM_INTEXPLORE);
//		virusSearchList.add(HKLM_LYCOS);
		virusSearchList.add(HKCR_TXTFILE_COMMAND);
//		virusSearchList.add(HKCR_EXEFILE_RUNAS_COMMAND);
//		virusSearchList.add(HKCR_CHECK_ASSOC);
		return virusSearchList;
	}

}