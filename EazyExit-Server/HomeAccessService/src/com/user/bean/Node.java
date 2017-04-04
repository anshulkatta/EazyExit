package com.user.bean;

public class Node {

	private long id;

	private String nodename;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Node(){}
	
	public Node(long id , String nodename) {
		this.id = id;
		this.nodename= nodename;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
