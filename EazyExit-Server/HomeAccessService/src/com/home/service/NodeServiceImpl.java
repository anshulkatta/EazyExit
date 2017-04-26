package com.home.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NodeDiscovery;
import com.node.NodePing;
import com.user.bean.Node;

@Service("getnodeService")
public class NodeServiceImpl implements NodeService {

	private static List<Node> nodes;

	@Autowired
	private NodePing nodeping;

	@Autowired
	private NodeDiscovery discover;

	static {
		nodes = populateDummyUsers();
	}

	private static List<Node> populateDummyUsers() {
		nodes = new ArrayList<Node>();
		nodes.add(new Node("", "Node 1"));
		return nodes;
	}

	@Override
	public List<Node> findAllNodes() {
		nodes.get(0).setStatus(pingNode("onn"));
		return nodes;
	}

	@Override
	public String pingNode(String content) {
		return nodeping.ping(content);
	}

	@Override
	public Map<String, Node> discoverNodes() {
		return discover.findNodes();
	}

	@Override
	public boolean broadCastMessage(String message) {
		nodeping.ping(message);
		return false;
	}

}
