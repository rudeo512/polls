package com.caru.polls.core.poll;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.caru.polls.core.choice.Choice;
import com.caru.polls.core.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Poll
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Entity
@Table(name = "polls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 140)
	private String question;

	@OneToMany(
		mappedBy = "poll",
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER,
		orphanRemoval = true
	)
	@Size(min = 2, max = 6)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30)
	private List<Choice> choices = new ArrayList<>();

	@NotNull
	private Instant expirationDateTime;

	public void addChoice(Choice choice) {
		choices.add(choice);
		choice.setPoll(this);
	}

	public void removeChoice(Choice choice) {
		choices.remove(choice);
		choice.setPoll(null);
	}
}
