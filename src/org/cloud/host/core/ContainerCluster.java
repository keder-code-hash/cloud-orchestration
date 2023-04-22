package org.cloud.host.core;
 
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cloud.container.core.Container;
import org.cloud.container.core.ContainerEdge;
import org.cloud.container.core.NetworkTransferMetrics; 


/**
 * @author keder
 *	Responsible for making clustered container based on their newtWorkTransfer rate
 *	Input : Set of All Containers
 *	Output : Generated List of cluster of container
 *	Events : 
 */

public class ContainerCluster {
	private static final int maxContainerNumber = 8;
	public List<Container> availableContainersList = new ArrayList<Container>(); 
	private ArrayList<Map<Integer, LinkedList<ContainerEdge>>> clusterContainerList=new ArrayList<>();
	
	public ArrayList<Map<Integer, LinkedList<ContainerEdge>>> getClusterContainerList() {
		return clusterContainerList;
	}


	public ContainerCluster(ArrayList<Container> containers) { 
		availableContainersList=containers;
	}
	
	
	// CAN BE REDUCED TO BINARY SEARCH AS THEY ARE SORTED BY ID IN THIS CASE ONLY
	public Container getContainerById(int id) {
		for(Container container : this.availableContainersList) {
			if(container.getId()==id) {
				return container;
			}
		}
		return null;
	}
	
	public void constructCluseter() {
//		System.out.println("size: "+availableContainersList.size());
		// select group of maxContainerNumber and make a graph out of then and allocate to list
		for (int i = 0; i < availableContainersList.size(); i += maxContainerNumber) {
			
			// THIS REQUESTS WILL BE IN NUMBER OF maxContainerNumber OF EACH
	    	List<Container> containerPool=availableContainersList.subList(i, Math.min(availableContainersList.size(), i + maxContainerNumber)) ; 
	    	
//	    	System.out.println("container Pool size : "+containerPool.size());
	    	// MAKE A GRPAH IN FORM OF LINKED LIST.
	    	// Ref : https://www.lavivienpost.net/weighted-graph-as-adjacency-list/ 

    		Map<Integer, LinkedList<ContainerEdge>> containersAdjacencyMap = new HashMap<>() ;
	    	for(Container container:containerPool) {
	    		for(NetworkTransferMetrics containerEdge : container.networkTransferRate) {
	    			if(containerEdge.containerId<Math.min(availableContainersList.size(), i + maxContainerNumber)
	    					&& containerEdge.containerId>i) {
	    				containersAdjacencyMap.putIfAbsent(Integer.valueOf(container.getId()), new LinkedList<>());
	    				containersAdjacencyMap.putIfAbsent(Integer.valueOf(containerEdge.containerId), new LinkedList<>());
	    				Container destContainer=this.getContainerById(containerEdge.containerId);
	    				ContainerEdge edge=new ContainerEdge(containerEdge.transferRate, container, destContainer);
	    				containersAdjacencyMap.get(container.getId()).add(edge);
	    				// AS THIS IS NOT A DIRECTED ONE
//	    				ContainerEdge reversedEdge=new ContainerEdge(containerEdge.transferRate, destContainer, container);
//	    				containersAdjacencyMap.get(container.getId()).add(reversedEdge);
	    			}
	    		}
	    	}
    		clusterContainerList.add(containersAdjacencyMap);
	    }		
	}
	
}
