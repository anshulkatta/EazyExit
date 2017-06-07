package com.helper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.bean.Node;

/**
 * Utility class 
 * 
 * @author Anshul
 *
 */
@Component
public class Util {

	@Autowired
	private DBUtil dbutil;

	public static final String MESSAGE_ON = "ON";
	public static final String MESSAGE_OFF = "OFF";

	/**Persists Nodes in Database
	 * 
	 * @param listofNodes
	 */
	public final void persistNodes(List<Node> listofNodes) {

		for (Node n : listofNodes) {
			dbutil.insertNodeData(n);
		}

	}
    /**
     * Finds all DB Nodes without any criteria
     * 
     * @return nodesMap 
     */
	public Map<String, Node> findDbNodes() {
		return dbutil.findNodes();

	}

}
