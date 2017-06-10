package com.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helper.MqTTTUtil;
import com.helper.Util;
import com.user.bean.Node;
/**
 * 
 * @author Anshul
 * 
 * NodeDiscovery to provide discovery service 
 * of Nodes
 *
 */
@Component
public class NodeDiscovery {
	
	@Autowired
	private MqTTTUtil mqttUtil;
	
	@Autowired
	private Util util;
	
	/** 
	 *  Maintains the Node data , consists of Key as Unique id against
	 *  Node object for that unique id
	 */
	private Map<String,Node> nodesMap = new HashMap<String,Node>();
	
	
	/**
	 * 
	 *  On Server start or restart 
	 *  Populates the Map with the existing Node data
	 */
	@PostConstruct
	void populateNodesMap() {
		nodesMap = util.findDbNodes();
	}
	
	/**
	 * Discover node on demand
	 * 
	 * @return Map<String,node> 
	 */
	public Map<String,Node> findNodes() {
		
		List<Node> nodeTobePersist = new ArrayList<Node>();
		
		mqttUtil.sendMessage("identify");
		
		try {
			Thread.sleep(1000);
			
			List<String> discoverList = mqttUtil.getMessageRecieved();
			Iterator<String> itertor = discoverList.iterator();
			
			while(itertor.hasNext()) {
				String uniqueId = itertor.next();
				if(!nodesMap.containsKey(uniqueId)) {
					Node n = new Node(uniqueId, "New Node");
					nodeTobePersist.add(n);
					nodesMap.put(uniqueId, n );
				}
			}
			/*
			 * clears temporary list for next discovery
			 */
			discoverList.clear();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Persists in DB only when there is any new Node discovered
		 */
		if(!nodeTobePersist.isEmpty())
		util.persistNodes(nodeTobePersist);
		
		return nodesMap;
	}
}
