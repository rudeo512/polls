package com.caru.polls.api.response.poll;

import java.time.Instant;
import java.util.List;

import com.caru.polls.api.response.choice.ChoiceResponse;
import com.caru.polls.api.response.user.UserSummary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PollResponse
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollResponse {
	private Long id;
	private String question;
	private List<ChoiceResponse> choices;
	private UserSummary createdBy;
	private Instant creationDateTime;
	private Instant expirationDateTime;
	private Boolean isExpired;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long selectedChoice;
	private Long totalVotes;
}
