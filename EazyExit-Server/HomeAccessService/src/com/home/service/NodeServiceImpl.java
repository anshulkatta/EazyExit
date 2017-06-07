package com.home.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.NodeDiscovery;
import com.node.NodePing;
import com.user.bean.Node;

/**
 * Service for providing Interface with Nodes
 * 
 * @author Anshul
 *
 */
@Service("getnodeService")
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodePing nodeping;

	@Autowired
	private NodeDiscovery discover;

	/**
	 * discovers nodes by sending message to Topic - [discovery]
	 * 
	 * @return nodesMap
	 */
	@Override
	public Map<String, Node> discoverNodes() {
		return discover.findNodes();
	}

	/**
	 * broadCasts message on Topic myHome
	 * 
	 * @param String
	 */
	@Override
	public boolean broadCastMessage(String message) {
		nodeping.ping(message);
		return false;
	}

}
