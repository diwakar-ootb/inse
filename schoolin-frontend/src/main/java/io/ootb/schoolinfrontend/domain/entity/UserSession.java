package io.ootb.schoolinfrontend.domain.entity;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserSession {
	
	public User getUser() {
        try {
            return (User) SecurityUtils.getSubject().getPrincipal();
        } catch (UnavailableSecurityManagerException e) {
            log.debug("Exception caught while getting user in Skye Session: ",e);
            return null;
        }
    }
}
