package com.altimetrik.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodedVin implements Serializable {
    
	private static final long serialVersionUID = -7327637705384309123L;

	@JsonProperty("Count")
    private Long count;
    
    @JsonProperty("Message")
    private String message;
    
    @JsonProperty("SearchCriteria")
    private String searchCriteria;
    
    @JsonProperty("Results")
    private List<DecodedVinResult> results;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public List<DecodedVinResult> getResults() {
		return results;
	}

	public void setResults(List<DecodedVinResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "DecodedVin [count=" + count + ", message=" + message + ", searchCriteria=" + searchCriteria
				+ ", results=" + results + "]";
	}

}
