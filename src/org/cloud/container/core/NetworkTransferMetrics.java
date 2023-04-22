package org.cloud.container.core;

public class NetworkTransferMetrics {
	public int containerId;
	public int transferRate;
	
	public NetworkTransferMetrics(int containerId, int transferRate) {
		this.containerId = containerId;
		this.transferRate = transferRate;
	}
}
