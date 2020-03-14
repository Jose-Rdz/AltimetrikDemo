package com.altimetrik.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.altimetrik.demo.controller.DecodeVinController;
import com.altimetrik.demo.dto.DecodedVin;
import com.altimetrik.demo.service.DecodeVinService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DecodeVinController.class)
public class DecodeVinControllerTest {
	
	private static final String CONTROLLER_ENDPOINT_URL = "/api/vin/decode/{vin}";
	
	private ObjectMapper objectMapper;
	
	@Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DecodeVinService decodeVinService;
    
    @BeforeEach
    public void setUp() {
    	objectMapper = new ObjectMapper();
    }

    @Test
    public void whenDecodeValidVin_expect_HttpCreated_and_JsonOutput() throws Exception {
    	String validVin = "WAUFFAFM3CA000000";
    	
    	DecodedVin expectedDecodedVin = new DecodedVin();
    	expectedDecodedVin.setCount(1L);
    	expectedDecodedVin.setResults(new ArrayList<>());
    	
        when(decodeVinService.decodeVin(validVin))
        	.thenReturn(expectedDecodedVin);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(CONTROLLER_ENDPOINT_URL, validVin)
                .param("format", "json")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andReturn();
        
        String jsonOutput = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedDecodedVin);
        
        assertThat(jsonOutput)
        	.isEqualTo(expectedJson);
    }
    
    @Test
    public void when_DecodeNotValidVin_expect_HttpBadRequest_and_JsonOutput()
            throws Exception {
    	String invalidVin = "JH4TB2H26CC000000"; // can be partially invalid
    	
    	DecodedVin expectedDecodedVin = new DecodedVin();
    	expectedDecodedVin.setCount(1L);
    	expectedDecodedVin.setResults(new ArrayList<>());
    	
        when(decodeVinService.decodeVin(invalidVin))
        	.thenReturn(expectedDecodedVin);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(CONTROLLER_ENDPOINT_URL, invalidVin)
                .param("format", "json")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        String jsonOutput = mvcResult.getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(expectedDecodedVin);
        
        assertThat(jsonOutput)
        	.isEqualTo(expectedJson);
    }
    
}
