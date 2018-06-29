package com.caru.polls.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * LoginRequest
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Data
public class LoginRequest {
	@NotBlank
	private String usernameOrEmail;

	@NotBlank
	private String password;
}
