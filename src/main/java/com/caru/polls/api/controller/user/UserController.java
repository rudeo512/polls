package com.caru.polls.api.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caru.polls.api.response.PagedResponse;
import com.caru.polls.api.response.poll.PollResponse;
import com.caru.polls.api.response.user.UserIdentityAvailability;
import com.caru.polls.api.response.user.UserProfile;
import com.caru.polls.api.response.user.UserSummary;
import com.caru.polls.core.poll.PollRepository;
import com.caru.polls.core.poll.PollService;
import com.caru.polls.core.user.User;
import com.caru.polls.core.user.UserRepository;
import com.caru.polls.core.vote.VoteRepository;
import com.caru.polls.exception.ResourceNotFoundException;
import com.caru.polls.security.CurrentUser;
import com.caru.polls.security.UserPrincipal;
import com.caru.polls.util.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserController
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 07. 01.
 */

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class UserController {
	private UserRepository userRepository;
	private PollRepository pollRepository;
	private VoteRepository voteRepository;
	private PollService pollService;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
		return userSummary;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		long pollCount = pollRepository.countByCreatedBy(user.getId());
		long voteCount = voteRepository.countByUserId(user.getId());

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

		return userProfile;
	}

	@GetMapping("/users/{username}/polls")
	public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
		@CurrentUser UserPrincipal currentUser,
		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return pollService.getPollsCreatedBy(username, currentUser, page, size);
	}

	@GetMapping("/users/{username}/votes")
	public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
		@CurrentUser UserPrincipal currentUser,
		@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
		@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return pollService.getPollsVotedBy(username, currentUser, page, size);
	}
}
