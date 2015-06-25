package app.common;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.datanucleus.util.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.appspot.cloudserviceapi.security.spring.GaeGrantedAuthority;
import com.appspot.cloudserviceapi.security.spring.GaeUserDetails;

import app.model.Movie;

public class SecurityUtils {

	public static boolean isAuthenticated(HttpServletRequest request) {
		boolean retVal = false;

		//TODO buggy - check disabled!
		retVal = true;
		
//		String uid = (String) request.getSession().getAttribute(Constants.UNIVERSAL_ID);
//		if(!StringUtils.isEmpty(uid)) {
//			retVal = true;
//		}
		
        return retVal;
	}
	
	public static boolean isOwner(Movie item, String oid) {
		boolean retVal = false;
		
		String owner = oid;	//AppUtils.getOwnerPart(oid);
		if(oid != null && owner != null && owner.equals(item.getOid())) {
			retVal = true;
		} else 
		//handles migrated entities too
		if(oid != null && owner != null && "undefined".equals(item.getOid())) {
			retVal = true;
		}

		return retVal;
	}

	/** Loggedin User's ID */
	public static String getLoggedInUser() {
		GaeUserDetails user = null;
	    String name = "anonymous";
		
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			try {
				name = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				System.out.println("ServiceRegistryStart.java 1 Not anonymous login, trying the authenticated credential ...");
				user = (GaeUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				name = user.getUsername(); //get logged in username
			}
		}
		
		return name;
	}

	public static String getLoggedInUserRole() {
		String retVal = "";
		
		Collection<GaeGrantedAuthority> authorities = null;
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			authorities = (Collection<GaeGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		}
		try {
			SimpleGrantedAuthority auth = null;
			
			if(authorities != null) {
				Iterator it = authorities.iterator();
				while(it.hasNext()) {
					auth = (SimpleGrantedAuthority) it.next();
					retVal += "_" + auth.toString();
				}
			} else {
				retVal = "No Role Assigned";
			}
		} catch (Exception e) {
			//TODO should not need to depend on exception to perform authentication check!!!
			//System.out.println("ServiceRegistryStart.java 2 Not anonymous login, trying the authenticated credential ...");
			GaeGrantedAuthority auth = null;
			
			if(authorities != null) {
				Iterator it = authorities.iterator();
				while(it.hasNext()) {
					auth = (GaeGrantedAuthority) it.next();
					retVal += "_" + auth.getRole().toString();
				}
			} else {
				retVal = "No Role Assigned";
			}
		}

		return retVal;
	}
	
	/**
	 * targetSecurityToken is the string/text to compare against the role's string acquired by the user.
	 *
	 * @param targetSecurityToken
	 * @return
	 */
	public static boolean isAdmin(String targetSecurityToken) {
		boolean retVal = false;

		if(!StringUtils.isEmpty(targetSecurityToken) && !StringUtils.isEmpty(getLoggedInUserRole()) && getLoggedInUserRole().indexOf(targetSecurityToken) > -1) {
			retVal = true;
		}
		
        return retVal;
	}

}