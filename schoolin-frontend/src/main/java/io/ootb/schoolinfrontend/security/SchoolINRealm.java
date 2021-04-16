package io.ootb.schoolinfrontend.security;

import java.util.Optional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.ootb.schoolinfrontend.domain.dao.UserDAO;
import io.ootb.schoolinfrontend.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchoolINRealm extends AuthorizingRealm {

    @Autowired
    private UserDAO userDAO;

    public SchoolINRealm() {
        super();
        this.setAuthorizationCachingEnabled(false);
    }
    
    AuthorizationInfo getAuthorizationInfo(Subject subject) {
        return doGetAuthorizationInfo(subject.getPrincipals());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.debug("Getting authorization information for principles " + principals);

        //User u = (User) getAvailablePrincipal(principals);
        //Optional<User> pu = userDAO.findByUsername(u.getId());
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();

        return authInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("Getting authorization information for authentication token " + authenticationToken);
        if (authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            Optional<User> user = userDAO.findByUsername(token.getUsername());
            if (user.isPresent()) {
                if (user.get().isEnabled()) {
                    String pwd = new String(token.getPassword());
                    Boolean passwordsMatch = BCrypt.checkpw(pwd, user.get().getPassword());
                    if (passwordsMatch) {
                        return new SimpleAuthenticationInfo(user.get(), token.getPassword(), this.getName());
                    } else {
                        throw new AuthenticationException();
                    }
                } else {
                    throw new DisabledAccountException();
                }
            } else {
                throw new AuthenticationException();
            }
        } else {
            throw new AuthenticationException();
        }

    }

    @Override
    public String getName() {
        return "schoolINRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return (authenticationToken instanceof UsernamePasswordToken);
    }

}