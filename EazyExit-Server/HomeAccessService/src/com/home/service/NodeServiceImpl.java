package com.home.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NodePing;
import com.user.bean.Node;

@Service("getnodeService")
public class NodeServiceImpl implements NodeService {

	private static List<Node> nodes;
	@Autowired
	private NodePing nodeping;

	static {
		nodes = populateDummyUsers();
	}

	private static List<Node> populateDummyUsers() {
		nodes = new ArrayList<Node>(); 
		nodes.add(new Node(1L, "Node 1"));
		return nodes;
	}

	@Override
	public List<Node> findAllNodes() {
		nodes.get(0).setStatus(pingNode());
		return nodes;
	}
	@Override
	public String pingNode() {
		return nodeping.ping();
	}

}
