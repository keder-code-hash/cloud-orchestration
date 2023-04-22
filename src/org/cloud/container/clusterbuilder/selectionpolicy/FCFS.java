package org.cloud.container.clusterbuilder.selectionpolicy;

import java.util.ArrayList;
import java.util.Collections;

import org.cloud.container.core.Container;


/**
 * @author keder
 * 
 * Policy : First Come First Serve
 *
 */

public class FCFS {
	public static ArrayList<Container> getContainerList(ArrayList<Container> availableContainersList){
		Collections.sort(availableContainersList,new FCFSSorter());
		return availableContainersList;
	}
}
