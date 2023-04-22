package org.cloud.host.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import org.cloud.container.core.Container;
import org.cloud.container.core.ContainerEdge;

public class Host {
	private int id;
	
	// million instruction per second
	private int NUMBER_OF_CONTAINERS=8;

	// time metrics
	private LocalDateTime scheduledAt;
	private LocalDateTime generated_at;
	
	private Map<Integer, LinkedList<ContainerEdge>> containerCluseterMap;
	
	public Host(int id, LocalDateTime generated_at,Map<Integer, LinkedList<ContainerEdge>> containerClusterMap) {
		this.setId(id);
		this.setGenerated_at(generated_at);
		this.containerCluseterMap=containerClusterMap;
		this.NUMBER_OF_CONTAINERS=containerClusterMap.size();
	}
	
	public void printHostDetails() {
		System.out.println("------------------------------------------------------------------------------------");
		System.err.println("Total Number of container in Host : "+NUMBER_OF_CONTAINERS+"\n");
		for(Integer key:containerCluseterMap.keySet()) {
			LinkedList<ContainerEdge> adjNodEdges = containerCluseterMap.get(key);
			System.out.println("Key Node : "+key+": ");
			for(ContainerEdge containerEdge:adjNodEdges) {
				System.out.println(" -> | Node Id : "+containerEdge.getDestContainer().getId()+" Network Transfer Rate: "+containerEdge.getNetwrokTransferRate()+" |");
			}
			System.out.println("\n");
		}
		System.out.println("------------------------------------------------------------------------------------");
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getGenerated_at() {
		return generated_at;
	}
	public void setGenerated_at(LocalDateTime generated_at) {
		this.generated_at = generated_at;
	}
	public int getNumberOfContainers() {
		return NUMBER_OF_CONTAINERS;
	}
	public LocalDateTime getScheduledAt() {
		return scheduledAt;
	}
	public void setScheduledAt(LocalDateTime scheduledAt) {
		this.scheduledAt = scheduledAt;
	}
}
