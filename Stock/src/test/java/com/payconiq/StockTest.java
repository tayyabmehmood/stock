package com.payconiq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.pojo.RequestPayload;
import com.payconiq.pojo.StockDetailResponse;
import com.payconiq.pojo.StockResponse;

class StockTest {

	@LocalServerPort
	int randomServerPort = 8082;

	private RestTemplate restTemplate;
	private String url;

	@BeforeEach
	void setUp() {
		restTemplate = new RestTemplate();
		url = "http://localhost:" + randomServerPort + "/api/stocks";
	}


    @Test
    void testGetAll() throws Exception {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());        
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = responseEntity.getBody().toString();
        StockDetailResponse stockValue = null;
        try {
        	stockValue = mapper.readValue(jsonResponse, StockDetailResponse.class);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        assertEquals("success", stockValue.getStatus());
    }
    
    @Test
    void testGetAllPaginated() throws Exception {
        ResponseEntity responseEntity = restTemplate.getForEntity(url+"?pageNumber=1&pageSize=2", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());        
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = responseEntity.getBody().toString();
        StockDetailResponse stockValue = null;
        try {
        	stockValue = mapper.readValue(jsonResponse, StockDetailResponse.class);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        assertEquals("success", stockValue.getStatus());
    }

    @Test
    void testGet() throws Exception {
        ResponseEntity responseEntity = restTemplate.getForEntity(url+"/1", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());        
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = responseEntity.getBody().toString();
        StockDetailResponse stockValue = null;
        try {
        	stockValue = mapper.readValue(jsonResponse, StockDetailResponse.class);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        assertTrue(stockValue.getStatus().equals("success") || stockValue.getStatus().equals("no stock found."));
    }
    
    @Test
    void testDelete() throws Exception {
    	ResponseEntity responseEntity = restTemplate.exchange(url+"/2", HttpMethod.DELETE, new HttpEntity<String>(""),
                StockResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

	@Test
    void testPatch() throws Exception {
    	try {			
			HttpEntity<Double> request = new HttpEntity<>(new Double(81));
			RestTemplate restTemplate = new RestTemplate();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setConnectTimeout(3000);
			requestFactory.setReadTimeout(3000);
			restTemplate.setRequestFactory(requestFactory);
			ResponseEntity<StockDetailResponse> responseEntity = restTemplate.exchange(url+"/1", HttpMethod.PATCH, request , StockDetailResponse.class);
			assertEquals("success", responseEntity.getBody().getStatus());

    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	}

	@Test
	void testPost() throws Exception {
		try {
			RequestPayload payload = new RequestPayload();
			payload.setName("Basketball");
			payload.setCurrentPrice(65);
			
			HttpEntity<RequestPayload> request = new HttpEntity<>(payload);
			StockDetailResponse response = restTemplate.postForObject(url, request, StockDetailResponse.class);
			assertEquals("success", response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    	
    	

}
