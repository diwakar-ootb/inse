package io.ootb.schoolinfrontend.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Config JWT.
 * Only one property 'shuaicj.security.jwt.secret' is mandatory.
 *
 * @author shuaicj 2018/07/27
 */
@Getter
@ToString
public class JwtConfig {

    @Value("${shuaicj.security.jwt.url:/authenticate}")
    private String url;

    @Value("${shuaicj.security.jwt.header:Authorization}")
    private String header;

    @Value("${shuaicj.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${shuaicj.security.jwt.expiration:#{24*60*60*1000}}")
    private int expiration; // default 24 hours

    @Value("${shuaicj.security.jwt.secret}")
    private String secret;
}
