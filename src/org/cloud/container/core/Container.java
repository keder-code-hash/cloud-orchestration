package org.cloud.container.core;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.cloud.request.core.Request;


public class Container {
	private int id;
	private int userId;
	// size in byte
	private long size;
	
	// million instruction per second
	private double mips;
	private int ram;
	private int numberOfRequests;
	private long bandwidth;
	
	// time metrics
	private LocalDateTime scheduledAt;
	private LocalDateTime generated_at;
	
	// network transfer details
	public int averageNetworkTransferRate=0;
	public ArrayList<NetworkTransferMetrics> networkTransferRate = new ArrayList<NetworkTransferMetrics>();
	public ArrayList<NetworkTransferMetrics> getNetworkTransferRate() {
		return networkTransferRate;
	}

	public void addnetworkTransferRate(int rate,int cotainerId) {
		this.networkTransferRate.add(new NetworkTransferMetrics(cotainerId, rate));
	}
	
	// request pool
	public ArrayList<Request> requestPool= new ArrayList<Request>();
	public void addRequest(Request request) {
		this.requestPool.add(request);
	}
	
	public Container(int id, int userId, long size, double mips, int ram, int numberOfRequests, long bandwidth,LocalDateTime generated_at) {
		this.setBandwidth(bandwidth);
		this.setId(id);
		this.setUserId(userId);
		this.setMips(mips);
		this.setNumberOfRequests(numberOfRequests);
		this.setRam(ram);
		this.setSize(size);
		this.setGenerated_at(generated_at);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public double getMips() {
		return mips;
	}

	public void setMips(double mips) {
		this.mips = mips;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

	public long getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(long bandwidth) {
		this.bandwidth = bandwidth;
	}
	public LocalDateTime getScheduledAt() {
		return scheduledAt;
	}

	public void setScheduledAt(LocalDateTime scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	public LocalDateTime getGenerated_at() {
		return generated_at;
	}

	public void setGenerated_at(LocalDateTime generated_at) {
		this.generated_at = generated_at;
	}
	

	public int getAverageNetworkTransferRate() {
		return averageNetworkTransferRate;
	}

	public void setAverageNetworkTransferRate(int averageNetworkTransferRate) {
		this.averageNetworkTransferRate = averageNetworkTransferRate;
	}
	
}
