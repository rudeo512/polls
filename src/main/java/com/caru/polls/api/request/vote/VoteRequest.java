package com.caru.polls.api.request.vote;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * VoteRequest
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
public class VoteRequest {
	@NotNull
	private Long choiceId;
}
