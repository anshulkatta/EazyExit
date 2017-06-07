package com.user.bean;

import java.io.Serializable;

public class Node  implements Serializable{

	
	/**  Dont touch this
	 *   @Long value only
	 */
	private static final long serialVersionUID = -89756413215632L;

	private String uniqueId;

	private String nodename;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Node(){}
	
	public Node(String uniqueId , String nodename) {
		this.uniqueId = uniqueId;
		this.nodename= nodename;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Override
	public boolean equals(Object obj) {
		Node n = (Node) obj;
		if(n.getUniqueId().equals(this.uniqueId)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	

}
