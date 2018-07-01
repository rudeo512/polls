package com.caru.polls.api.request.poll;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ChoiceRequest
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
public class ChoiceRequest {
	@NotBlank
	@Size(max = 40)
	private String text;
}
