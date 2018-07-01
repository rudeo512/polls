package com.caru.polls.api.response.choice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChoiceResponse
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponse {
	private long id;
	private String text;
	private long voteCount;
}
