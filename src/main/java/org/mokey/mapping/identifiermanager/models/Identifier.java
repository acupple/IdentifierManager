package org.mokey.mapping.identifiermanager.models;


public class Identifier {	
	private String uuid;
	private String ip3;
	private String ip; 
	private String ua;
	private String os;
	private int osvs;
	private String browser;
	private String lng;
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIp3() {
		return ip3;
	}

	public void setIp3(String ip3) {
		this.ip3 = ip3;
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
