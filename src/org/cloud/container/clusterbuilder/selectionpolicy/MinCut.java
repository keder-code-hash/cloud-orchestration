package org.cloud.container.clusterbuilder.selectionpolicy;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.cloud.container.core.Container;
import java.util.LinkedList;
import java.util.Queue;

public class MinCut {  
	public static ArrayList<ArrayList<Integer>> cutEdgeSet= new ArrayList<>();
	
	private static boolean bfs(int[][] rGraph, int s, int t, int[] parent) { 
		boolean[] visited = new boolean[rGraph.length];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visited[s] = true;
		parent[s] = -1;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < rGraph.length; i++) {
				if (rGraph[v][i] > 0 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					parent[i] = v;
				}
			}
		}
		return (visited[t] == true);
	}
 
	private static void dfs(int[][] rGraph, int s, boolean[] visited) {
	visited[s] = true;
	for (int i = 0; i < rGraph.length; i++) {
		if (rGraph[s][i] > 0 && !visited[i]) {
			dfs(rGraph, i, visited);
			}
		}
	}
 
	private static void minCut(int[][] graph, int s, int t) {
		int u,v; 
		int[][] rGraph = new int[graph.length][graph.length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
			rGraph[i][j] = graph[i][j];
			}
		}
		int[] parent = new int[graph.length];
		while (bfs(rGraph, s, t, parent)) {
			int pathFlow = Integer.MAX_VALUE;        
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min(pathFlow, rGraph[u][v]);
			}
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] = rGraph[u][v] - pathFlow;
				rGraph[v][u] = rGraph[v][u] + pathFlow;
			}
		}
		boolean[] isVisited = new boolean[graph.length];    
		dfs(rGraph, s, isVisited);
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
					ArrayList<Integer> tempArrayList = new ArrayList<>();
					tempArrayList.add(i);
					tempArrayList.add(j);
					cutEdgeSet.add(tempArrayList);
//					System.out.println(i + " - " + j);
				}
			}
		}
	}

	public static int generateRandomNumber(int lowerBound, int upperBound) {
		return ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
	}
	
	public static int[][] generateAdjacencyMatrixArrayList (ArrayList<Container> containers ){
		int length = containers.size();
		int[][] adjacencyMatrix = new int[length][length];
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(j%2==0 && adjacencyMatrix[i][j]!=-1) {
					int randomWeight = generateRandomNumber(5, 30);
					adjacencyMatrix[i][j]=randomWeight;
					adjacencyMatrix[j][i]=-1;
				}
			}
		}
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(adjacencyMatrix[i][j]<0) {
					adjacencyMatrix[i][j]=0;
				}
			}
		}
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(i==length-1) {
					adjacencyMatrix[i][j]=0;
				}
			}
		}
		return adjacencyMatrix;
	}
	
	public static void generateCut(ArrayList<Container> containers) {
		int length = containers.size();
		int[][] adjacencyMatrix = generateAdjacencyMatrixArrayList(containers);
		minCut(adjacencyMatrix, 0, length-2);
		for(int i=0;i<cutEdgeSet.size();i++) {
			System.out.println(cutEdgeSet.get(i).get(0)+" "+cutEdgeSet.get(i).get(1));
			adjacencyMatrix[cutEdgeSet.get(i).get(0)][cutEdgeSet.get(i).get(1)]=-1;
		}
		for(int i=0;i<adjacencyMatrix.length;i++) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Source Node : "+i);
			for(int j=0;j<adjacencyMatrix.length;j++) {
				if(adjacencyMatrix[i][j]!=0 && adjacencyMatrix[i][j]!=-1) {
					System.out.print("->"+j);
				}
			}
			System.out.println();
		}
	}
	
}
