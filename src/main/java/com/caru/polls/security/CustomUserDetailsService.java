package com.caru.polls.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caru.polls.core.user.User;
import com.caru.polls.core.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService
 *
 * @author kyungdae.cho
 * @version 1.0.0
 * @since 2018. 06. 29.
 */

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// Let people login with either username or email
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
			.orElseThrow(() ->
				new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
			);

		log.info("user1 == {}", user);
		return UserPrincipal.create(user);
	}

	// This method is used by JwtAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new UsernameNotFoundException("User not found with id : " + id)
		);

		log.info("user2 == {}", user);
		return UserPrincipal.create(user);
	}
}
