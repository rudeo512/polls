package com.caru.polls.core.poll;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PollRepository
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
	Optional<Poll> findById(Long pollId);

	Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

	long countByCreatedBy(Long userId);

	List<Poll> findByIdIn(List<Long> pollIds);

	List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
