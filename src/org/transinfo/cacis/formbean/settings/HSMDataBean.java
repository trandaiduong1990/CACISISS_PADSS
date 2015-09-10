package org.transinfo.cacis.formbean.settings;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HSMDataBean implements Serializable {
	private String ip;
	private String port;
	private String maxConn;
	private String weight;
	private String oldIp;

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPort() {
		return port;
	}

	public void setMaxConn(String maxConn) {
		this.maxConn = maxConn;
	}

	public String getMaxConn() {
		return maxConn;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeight() {
		return weight;
	}

	public void setOldIp(String oldIp) {
		this.oldIp = oldIp;
	}

	public String getOldIp() {
		return oldIp;
	}
}
