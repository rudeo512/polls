package com.caru.polls.api.request.poll;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * PollRequest
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */
@Data
public class PollRequest {
	@NotBlank
	@Size(max = 140)
	private String question;

	@NotNull
	@Size(min = 2, max = 6)
	@Valid
	private List<ChoiceRequest> choices;

	@NotNull
	@Valid
	private PollLength pollLength;
}
