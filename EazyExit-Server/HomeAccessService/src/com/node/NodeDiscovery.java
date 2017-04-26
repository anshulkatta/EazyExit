package com.node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helper.MqTTTUtil;
import com.user.bean.Node;

@Component
public class NodeDiscovery {
	
	@Autowired
	MqTTTUtil mqttUtil;
	
	private Map<String,Node> nodesMap = new HashMap<String,Node>();
	
	public Map<String,Node> findNodes() {
		
		mqttUtil.sendMessage("identify");
		
		try {
			Thread.sleep(1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> discoverList = mqttUtil.getMessageRecieved();
		Iterator<String> itertor = discoverList.iterator();
		
		while(itertor.hasNext()) {
			String ip = itertor.next();
			if(!nodesMap.containsKey(ip)) {
				nodesMap.put(ip, new Node(ip, "New Node"));
				itertor.remove();
			}
		}
		
		return nodesMap;
		
	}
	
	
	

}
