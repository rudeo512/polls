package com.caru.polls.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ApiResponse
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Data
@AllArgsConstructor
public class ApiResponse {
	private Boolean success;
	private String message;
}
