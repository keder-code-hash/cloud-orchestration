package org.cloud.host.core;
import java.util.*;
import java.lang.Math;

import org.cloud.container.clusterbuilder.selectionpolicy.FCFS;
import org.cloud.container.core.Container;
import org.cloud.container.core.ContainerEdge;

/**
 * @author keder
 *	Responsible for making clustered container based on their newtWorkTransfer rate
 *	Input : Set of All Containers
 *	Output : set of All clustered Container which will eventually gets deployed into separate host.
 *	Events : Fetching all set of containers. Set their Network transfer rate based on each set of request. Based on containers arrival time and network transfer rate we have to form a cluster of container
 */
public class ClusterBuilder {
	public ArrayList<Container> availableContainersList;
	public ArrayList<Container> policyFilteredAvailableContainersList;
	public int cotainerNumber;
	
	private static int minNetworkTransferRate = 0;
	private static int maxNetworkTransferRate = 100;
	

	public ClusterBuilder(ArrayList<Container> availableContainersList) {
		this.availableContainersList = availableContainersList;
		this.setNetwrokTransferRate();
		this.getPolicyFilteredContainers();
	}
	
	/**
	 * Set the Network Transfer Rate for each of the container
	 * */
	public void setNetwrokTransferRate() {
		int totalContainer = this.availableContainersList.size();
		
		for(int i=0;i<totalContainer;i++) {
			for(int j=0;j<totalContainer;j++) {
				int netwrokTransferRate = (int)(Math.random()*(maxNetworkTransferRate-minNetworkTransferRate+1)+minNetworkTransferRate);
				if(i==j) {
					netwrokTransferRate=0;
				}
				Container sourceContainer = this.availableContainersList.get(i);
				Container destinationContainer = this.availableContainersList.get(j);
				sourceContainer.addnetworkTransferRate(netwrokTransferRate, destinationContainer.getId());
			}
		}
	}
	
	
	/**
	 * Get the policy filtered Container List.
	 * Can be changed based upon the container selection policy. It can FCFS or Random.
	 * */
	public void getPolicyFilteredContainers() {
		this.policyFilteredAvailableContainersList=FCFS.getContainerList(this.availableContainersList);
	}
	
	/**
	 * Get the clustered Container List.
	 * */
	public ArrayList<Map<Integer, LinkedList<ContainerEdge>>> getClusteredContainerList() {
		ContainerCluster clusteredContainer = new ContainerCluster(this.availableContainersList);
		clusteredContainer.constructCluseter();
		ArrayList<Map<Integer, LinkedList<ContainerEdge>>> clusterContainerList = clusteredContainer.getClusterContainerList();
		return clusterContainerList;
	}
	
	
}
