package com.appspot.cloudserviceapi.security.spring;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class GaeDaoAuthenticationProvider extends DaoAuthenticationProvider {

//	private void runAuthenticationChecks(GaeUserDetails user) throws Exception {
//		if (!user.isAccountNonLocked()) {
//			throw new LockedException("User account is locked");
//		}
//
//		if (!user.isEnabled()) {
//			throw new Exception("User account is disabled");
//		}
//
//		if (!user.isAccountNonExpired()) {
//			throw new Exception("User account is expired");
//		}
//
//		if (!user.isCredentialsNonExpired()) {
//			throw new CredentialsExpiredException("User credentials have expired");
//		}
//	}

	// @Autowired
	UserDetailsDAO userDetailsDAO;

	public void setUserDetailsDAO(UserDetailsDAO userDetailsDAO) {
		this.userDetailsDAO = userDetailsDAO;
	}

	// @Autowired
	// @Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else an exception will be
			// thrown
			// reset the user_attempts
			userDetailsDAO.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			// invalid login, update to user_attempts
			userDetailsDAO.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			// this user is locked!
			String error = "";
//			UserAttempts userAttempts = userDetailsDAO.getUserAttempts(authentication.getName());
//
//			if (userAttempts != null) {
//				Date lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
//						+ "<br>Last Attempts : " + lastAttempts
				;
//			} else {
//				error = e.getMessage();
//			}

			throw new LockedException(error);
		}

	}

}