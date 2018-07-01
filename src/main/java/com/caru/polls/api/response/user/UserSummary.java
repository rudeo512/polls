package com.caru.polls.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserSummary
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {
	private Long id;
	private String username;
	private String name;
}
