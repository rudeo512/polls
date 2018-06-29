package com.caru.polls.core.choice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChoiceVoteCount
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceVoteCount {
	private Long choiceId;
	private Long voteCount;
}
