package org.cloud.container.clusterbuilder.selectionpolicy;

import java.util.ArrayList;
import java.util.Comparator;

import org.cloud.container.core.Container;

public class FCFSSorter implements Comparator<Container>{
	public ArrayList<Container> availableContainersList = new ArrayList<Container>();

	@Override
	public int compare(Container ct1,Container ct2) {
		return ct1.getGenerated_at().compareTo(ct2.getGenerated_at());
	}
}
