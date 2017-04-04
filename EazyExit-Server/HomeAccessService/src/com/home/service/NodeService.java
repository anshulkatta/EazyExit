package com.home.service;

import java.util.List;

import com.user.bean.Node;

public interface NodeService {
	
	List<Node> findAllNodes();
	
	String pingNode();

}
