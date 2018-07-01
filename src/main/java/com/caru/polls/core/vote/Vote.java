package com.caru.polls.core.vote;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.caru.polls.core.audit.DateAudit;
import com.caru.polls.core.choice.Choice;
import com.caru.polls.core.poll.Poll;
import com.caru.polls.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vote
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Entity
@Table(name = "votes", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"poll_id",
		"user_id"
	})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "poll_id", nullable = false)
	private Poll poll;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "choice_id", nullable = false)
	private Choice choice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
