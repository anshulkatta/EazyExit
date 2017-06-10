package com.home.service;

import java.util.Map;

import com.user.bean.Node;

public interface NodeService {
	
	public Map<String,Node> discoverNodes();
	
	boolean broadCastMessage(String message);
	
	

}
