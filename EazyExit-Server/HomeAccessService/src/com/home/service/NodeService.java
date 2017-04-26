package com.home.service;

import java.util.List;
import java.util.Map;

import com.user.bean.Node;

public interface NodeService {
	
	List<Node> findAllNodes();
	
	String pingNode(String content);
	
	Map<String,Node> discoverNodes();
	
	boolean broadCastMessage(String message);
	
	

}
