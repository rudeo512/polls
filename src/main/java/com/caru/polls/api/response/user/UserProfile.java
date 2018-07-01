package com.caru.polls.api.response.user;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserProfile
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	private Long id;
	private String username;
	private String name;
	private Instant joinedAt;
	private Long pollCount;
	private Long voteCount;
}
