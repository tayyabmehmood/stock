package com.payconiq.pojo;

import lombok.Data;

@Data
public class ClientRequestPayload {
	private Long clientId;
	private RequestPayload[] requestPayLoad;

}
