package com.caru.polls.api.controller.poll;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caru.polls.api.request.poll.PollRequest;
import com.caru.polls.api.request.vote.VoteRequest;
import com.caru.polls.api.response.ApiResponse;
import com.caru.polls.api.response.PagedResponse;
import com.caru.polls.api.response.poll.PollResponse;
import com.caru.polls.core.poll.Poll;
import com.caru.polls.core.poll.PollRepository;
import com.caru.polls.core.poll.PollService;
import com.caru.polls.core.user.UserRepository;
import com.caru.polls.core.vote.VoteRepository;
import com.caru.polls.security.CurrentUser;
import com.caru.polls.security.UserPrincipal;
import com.caru.polls.util.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PollController
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@RestController
@RequestMapping("/api/polls")
@Slf4j
@AllArgsConstructor
public class PollController {
	private PollRepository pollRepository;
	private VoteRepository voteRepository;
	private UserRepository userRepository;
	private PollService pollService;

	@GetMapping
	public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return pollService.getAllPolls(currentUser, page, size);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
		Poll poll = pollService.createPoll(pollRequest);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{pollId}")
			.buildAndExpand(poll.getId()).toUri();

		return ResponseEntity.created(location)
			.body(new ApiResponse(true, "Poll Created Successfully"));
	}

	@GetMapping("/{pollId}")
	public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
		@PathVariable Long pollId) {
		return pollService.getPollById(pollId, currentUser);
	}

	@PostMapping("/{pollId}/votes")
	@PreAuthorize("hasRole('USER')")
	public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
		@PathVariable Long pollId,
		@Valid @RequestBody VoteRequest voteRequest) {
		return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
	}
}
