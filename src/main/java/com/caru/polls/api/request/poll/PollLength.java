package com.caru.polls.api.request.poll;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * PollLength
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
public class PollLength {
	@NotNull
	@Max(7)
	private Integer days;

	@NotNull
	@Max(23)
	private Integer hours;
}
