package com.altimetrik.demo.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ApiError implements Serializable {

	private static final long serialVersionUID = 8399353839326650118L;
	
	private String status;
    private String title;
    private String error;
    private List<String> details;
    private LocalDateTime timestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return "ApiError [status=" + status + ", title=" + title + ", error=" + error + ", details=" + details
				+ ", timestamp=" + timestamp + "]";
	}

}
