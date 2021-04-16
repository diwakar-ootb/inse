package io.ootb.schoolinfrontend.security;

import java.util.List;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config beans.
 *
 * @author Diwakar Kuruba 17/04/2021
 */
@Configuration
public class SecurityBeanConfig {

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}

	@Bean
	public JwtUsernamePasswordAuthFilter jwtUsernamePasswordAuthFilter(JwtConfig config,
			@Qualifier("schoolINRealm") SchoolINRealm realm) {
		return new JwtUsernamePasswordAuthFilter(config, realm);
	}

	@Bean
	public JwtTokenAuthFilter jwtTokenAuthFilter(JwtConfig config) {
		return new JwtTokenAuthFilter(config);
	}

	@Bean("jwtTokenRealm")
	public Realm jwtTokenRealm() {
		return new JwtTokenRealm();
	}

	@Bean
	public DefaultWebSecurityManager webSecurityManager(List<Realm> realms) {
		return new DefaultWebSecurityManager(realms);
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition(JwtConfig config) {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

		// The following two lines indicate that:
		// 1. we need no session, because statelessness is preferred in REST world.
		// 2. use 'jwtUsernamePasswordAuth' to authenticate login request.
		// 3. use 'jwtTokenAuth' to authenticate all other requests.
		// 3. and leave authorization things to annotations in rest controllers.
		chainDefinition.addPathDefinition(config.getUrl(), "noSessionCreation, jwtUsernamePasswordAuth");
		chainDefinition.addPathDefinition("/**", "noSessionCreation, jwtTokenAuth");

		return chainDefinition;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
			JwtUsernamePasswordAuthFilter jwtUsernamePasswordAuthFilter, JwtTokenAuthFilter jwtTokenAuthFilter,
			ShiroFilterChainDefinition filterChainDefinition) {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		filterFactoryBean.setSecurityManager(securityManager);
		filterFactoryBean.getFilters().put("jwtUsernamePasswordAuth", jwtUsernamePasswordAuthFilter);
		filterFactoryBean.getFilters().put("jwtTokenAuth", jwtTokenAuthFilter);
		filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition.getFilterChainMap());
		return filterFactoryBean;
	}
}
