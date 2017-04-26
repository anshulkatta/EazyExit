package com.user.bean;

public class Node {

	private String ip;

	private String nodename;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Node(){}
	
	public Node(String ip , String nodename) {
		this.ip = ip;
		this.nodename= nodename;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	

}
