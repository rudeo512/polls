package com.caru.polls.api.response.jwt;

import lombok.Data;

/**
 * JwtAuthenticationResponse
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */
@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
