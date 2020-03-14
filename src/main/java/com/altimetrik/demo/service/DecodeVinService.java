package com.altimetrik.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.demo.dto.DecodedVin;
import com.altimetrik.demo.dto.DecodedVinResult;
import com.altimetrik.demo.util.DecodeVinException;

//TODO upgrade to implement interface
@Service
public class DecodeVinService {

	private static final Logger logger = LoggerFactory.getLogger(DecodeVinService.class);
	private static final String DECODE_VIN_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinExtended/{vin}";
	
	private RestTemplate restTemplate = new RestTemplateBuilder()
            //.setConnectTimeout(Duration.ofSeconds(10)) not required for this test
            //.setReadTimeout(Duration.ofSeconds(10))
            .build();
	
	public DecodedVin decodeVin(String vin) {
		ResponseEntity<DecodedVin> response = 
                restTemplate.exchange(DECODE_VIN_URL + "?format=json", HttpMethod.GET, null, DecodedVin.class, vin);
		
		HttpStatus statusCode = response.getStatusCode();
		
		// detect if rest service is presenting errors
		if (statusCode.is5xxServerError() || !statusCode.is2xxSuccessful()) {
			logger.error("REST endpoint for {} is not working properly", DECODE_VIN_URL);
			throw new DecodeVinException("Service unnavailable");
		}
		
		// get only available characteristics
		DecodedVin decodedVin = response.getBody();
		
		List<DecodedVinResult> results = decodedVin.getResults();
		
		List<DecodedVinResult> onlyAvailableData = results.stream().filter(r -> {
			String value = r.getValue();
			return value != null && value.length() > 0;
		}).collect(Collectors.toList());
		
		decodedVin.setResults(onlyAvailableData);
		
		return decodedVin;
	}
}
