package com.altimetrik.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecodedVinResult implements Serializable {
	
	private static final long serialVersionUID = 8933421425311365633L;

	@JsonProperty("Value")
    private String value;
    
    @JsonProperty("ValueId")
    private String valueId;
    
    @JsonProperty("Variable")
    private String variable;
    
    @JsonProperty("VariableId")
    private Long variableId;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Long getVariableId() {
		return variableId;
	}

	public void setVariableId(Long variableId) {
		this.variableId = variableId;
	}

	@Override
	public String toString() {
		return "DecodedVinResult [value=" + value + ", valueId=" + valueId + ", variable=" + variable + ", variableId="
				+ variableId + "]";
	}

}
