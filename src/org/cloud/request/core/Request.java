package org.cloud.request.core;
import java.time.LocalDateTime;

public class Request {
	private int id;
	
	// size in byte
	private int req_size;
	private int req_ram;

	private LocalDateTime scheduledAt;
	private LocalDateTime generated_at;
	
	public Request(int id, int req_size, int req_ram, LocalDateTime generated_at) {
		this.setId(id);
		this.setGenerated_at(generated_at);
		this.setReq_ram(req_ram);
		this.setReq_size(req_size);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReq_size() {
		return req_size;
	}
	public void setReq_size(int req_size) {
		this.req_size = req_size;
	}
	public int getReq_ram() {
		return req_ram;
	}
	public void setReq_ram(int req_ram) {
		this.req_ram = req_ram;
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
}
