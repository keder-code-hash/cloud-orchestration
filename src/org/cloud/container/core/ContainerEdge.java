package org.cloud.container.core;

public class ContainerEdge {
	private int netwrokTransferRate;
	public int getNetwrokTransferRate() {
		return netwrokTransferRate;
	}

	public Container getSourceContainer() {
		return sourceContainer;
	}

	public Container getDestContainer() {
		return destContainer;
	}

	private Container sourceContainer;
	private Container destContainer;
	
	public ContainerEdge(int netwrokTransferRate, Container sourceContainer, Container destContainer) {
		this.netwrokTransferRate = netwrokTransferRate;
		this.sourceContainer = sourceContainer;
		this.destContainer = destContainer;
	}
}
