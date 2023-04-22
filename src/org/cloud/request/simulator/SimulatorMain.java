package org.cloud.request.simulator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cloud.container.core.Container;
import org.cloud.container.core.ContainerEdge;
import org.cloud.host.core.ClusterBuilder;
import org.cloud.host.core.ContainerCluster;
import org.cloud.host.core.Host;
import org.cloud.request.core.Request;

public class SimulatorMain {
	private static final int REQUEST_NUMBER=200;
	private static final int MAX_NUM_OF_REQ_PER_CONTAINER=10;
	
	public static int genRandomNumber(int Min,int Max) {
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
	
	static ArrayList<Container> choppedRequestIntoContainer(ArrayList<Request> list, final int L) {
		int id=0;
		int userId=0;
		long size = 100L;
		double mips=(double)genRandomNumber(0, 10);
		int ram = genRandomNumber(1024, 4096);
		int numberOfRequests = L;
		long bandwidth=(long)genRandomNumber(1024, 40096);
	    ArrayList<Container> parts = new ArrayList<Container>();
	    final int N = list.size();
	    for (int i = 0; i < N; i += L) {
			LocalDateTime generated_at=LocalDateTime.now();
	    	List<Request> requestPooList=list.subList(i, Math.min(N, i + L)) ;
	    	Container ctnContainer=new Container(id, userId, size, mips, ram, numberOfRequests, bandwidth, generated_at);
	    	for(Request req:requestPooList) {
		    	ctnContainer.addRequest(req);
	    	}
	        parts.add(ctnContainer);
	        id++;
	        userId++;
	    }
	    return parts;
	}
	
	public static void main(String[] args) {
		
		// GENERATING REQUEST WITH RANDOM PARAMETER WITHOUT CONSIDERING THE TIMEFRAME
		ArrayList<Request> requestArrayList=new ArrayList<>();
		for(int i=0;i<REQUEST_NUMBER;i++) {
			int reqId = i;
			int req_size = genRandomNumber(10, 1000);
			int req_ram = genRandomNumber(10, 1000);
			LocalDateTime time = LocalDateTime.now();
			requestArrayList.add(new Request(reqId, req_size, req_ram, time));
		}
		
		// ALLOCATING REQUEST INTO THE CONTAINER BASED ON THE CAPACITY OF THE CONTAINER
		ArrayList<Container>containerPooL= new ArrayList<>();
		containerPooL=choppedRequestIntoContainer(requestArrayList, MAX_NUM_OF_REQ_PER_CONTAINER);
		
		// MAKING GRAPH OUT OF ALL CONTAINERS 
		ClusterBuilder clusterListBuilder = new ClusterBuilder(containerPooL);
		ArrayList<Map<Integer, LinkedList<ContainerEdge>>> clusteredContainers=clusterListBuilder.getClusteredContainerList();
		
		// ALLOCATING THE CLUSTER TO THE HOST
		int hostId=0;
		for(Map<Integer, LinkedList<ContainerEdge>> clusteredContainer:clusteredContainers) {
			LocalDateTime hostScheduleDateTime = LocalDateTime.now();
			Host host =new Host(hostId, hostScheduleDateTime, clusteredContainer);
			host.printHostDetails();
		}

		
		
	}
}
