package com.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.user.bean.Node;


/**
 * DB Utility class for access to Database using 
 * data source object
 * 
 * 
 * 
 * @author Anshul
 *
 */
@Component
public class DBUtil {
	
	
	@Autowired
	@Qualifier("dataSource")
	private  DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Inserts Node data into database , TABLE - [nodes]
	 * 	 
	 * */
	public  void insertNodeData(Node node){
		
		String query = "insert into nodes (node_id,node_name) values(?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query); 
			
			ps.setString(1, node.getUniqueId());
			ps.setString(2, node.getNodename());
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Finds Nodes by uniqueId in database , TABLE - [nodes]
	 * 
	 *  
	 * @param listOfUniqueId
	 * @return List<Node> listofNodes
	 */
	public  List<Node> findNodebyId(List<String> listOfUniqueId){
		
		StringBuilder query = new StringBuilder("select * from nodes where node_id in");
		Connection con = null;
		PreparedStatement ps = null;
		List<Node> listofNodes = new ArrayList<Node>();
		query = createQuery(query,listOfUniqueId);
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query.toString()); 
			for( int i = 1; i <= listOfUniqueId.size() ; i++ ){
				ps.setString(i, listOfUniqueId.get(i));
			}
			ps.execute();
			
			ResultSet rs = null;
			rs = ps.getResultSet();
			
			while ( rs.next() ) {
				Node n = new Node(rs.getString(1), rs.getString(2));
				listofNodes.add(n);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
 		 
 		return listofNodes;
	}

	private  StringBuilder createQuery(StringBuilder query, List<String> listOfUniqueId) {
		if(listOfUniqueId.size() == 1 ){
			query.append("( ? )");
		} else {
			query.append(" ( ");
			for (int i = 0 ;i < listOfUniqueId.size() ; i++){
				query.append("? ,");
			}
			query.deleteCharAt(query.lastIndexOf(","));
			query.append(" ) ");
		}
		
		return query;
	}
    
	/**
	 * finds all Database nodes , TABLE- [nodes]
	 * without any condition
	 * 
	 * SELECT * FROM NODES
	 * 
	 * @return nodesMap <String,Node> mapOfNodes
	 */
	public Map<String,Node> findNodes() {
		
		Map<String,Node> nodesMap = new HashMap<String, Node>();
		StringBuilder query = new StringBuilder("select * from nodes");
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query.toString());
			
			ps.execute();
			
			ResultSet rs = null;
			rs = ps.getResultSet();
			
			while ( rs.next()) {
				nodesMap.put(rs.getString(1),new Node(rs.getString(1),rs.getString(2)));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nodesMap;
		
	}
}
