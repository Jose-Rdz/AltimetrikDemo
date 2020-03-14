package com.altimetrik.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.demo.dto.DecodedVin;
import com.altimetrik.demo.service.DecodeVinService;
import com.altimetrik.demo.util.DecodeVinException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class DecodeVinController {
	
	@Autowired
	private DecodeVinService decodeVinService;
	
	@ApiOperation(value = "Decode Vin", notes = "This method decodes a VIN value")
    @GetMapping(value="api/vin/decode/{vin}", produces = MediaType.APPLICATION_JSON_VALUE, params = "format=json")
    public ResponseEntity<DecodedVin> decodeVin(
            @ApiParam(name = "vin", value = "The car Vehicle Identification Number (VIN)", example = "WAUFFAFM3CA000000", required = true) 
            @PathVariable("vin") String vin) throws DecodeVinException {
		DecodedVin decodedVin = decodeVinService.decodeVin(vin);
		
		// is there any error detected?
		long errorCount = decodedVin.getResults().stream()
			.filter(r -> {
				String variable = r.getVariable();
				String value = r.getValue();
				return variable.contains("Error Code") && !value.contains("0");
			}).count();
		
		if (errorCount > 0) { // decoded vin contains error, should respond 400
			return new ResponseEntity<>(decodedVin, HttpStatus.BAD_REQUEST);
		}
		
		// decoded valid vin, should respond 201
		return new ResponseEntity<>(decodedVin, HttpStatus.CREATED);
    }
	
	
	
}
