package org.mokey.mapping.identifiermanager.models;

import java.util.UUID;

import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class Identifier {
	
	private static UserAgentStringParser parser = null;
	
	private String uuid;
	
	private String ip; 
	private String ua;
	private String os;
	private int osvs;
	private String browser;
	private String lng;
	
	public Identifier(){}
	
	public Identifier(String ip, String ua, String lng){
		
		this.uuid = UUID.randomUUID().toString();
		
		this.ip = ip;
		this.ua = ua;
		ReadableUserAgent agent = parser.parse(this.ua);
		OperatingSystem ops = agent.getOperatingSystem();
		this.os = ops.getFamilyName();
		String[] tokens = ops.getName().split(" ");
		if(tokens != null && tokens.length > 1){
			this.osvs = Integer.parseInt(tokens[1]);
		}
		
		this.browser = agent.getFamily().getName().toLowerCase();
		
		tokens = lng.split(",");
		if(tokens != null)
			this.lng = tokens[0];
	}
	
	static{
		parser = UADetectorServiceFactory.getResourceModuleParser();
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setOsvs(int osvs) {
		this.osvs = osvs;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getIp() {
		return ip;
	}

	public String getUa() {
		return ua;
	}

	public String getOs() {
		return os;
	}

	public int getOsvs() {
		return osvs;
	}

	public String getBrowser() {
		return browser;
	}

	public String getLng() {
		return lng;
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UUID: " + this.uuid + "\r\n")
			.append("IP: " + this.ip + "\r\n")
			.append("UA: " + this.ua + "\r\n")
			.append("OS: " + this.os + "\r\n")
			.append("OS Verson: " + this.osvs + "\r\n")
			.append("Browser: " + this.browser + "\r\n")
			.append("Language: " + this.lng + "\r\n");
		return sb.toString();
	}
}
