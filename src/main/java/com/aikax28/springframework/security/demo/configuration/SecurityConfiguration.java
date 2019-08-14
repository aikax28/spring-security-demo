package com.aikax28.springframework.security.demo.configuration;

import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerHttpBasicAuthenticationConverter;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.MediaTypeServerWebExchangeMatcher;

/**
 *
 * @author aikax28
 * @see
 * https://docs.spring.io/spring-security/site/docs/current/reference/html/jc-webflux.html
 * @see http://nosix.hatenablog.com/entry/2018/07/30/143921
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(@Nonnull final ServerHttpSecurity http,
                                                            @Nonnull final ReactiveAuthenticationManager authenticationManager,
                                                            @Nonnull final ServerCodecConfigurer serverCodecConfigurer) {

        AuthenticationWebFilter authenticationWebFilter = authenticationWebFilter(authenticationManager);

        http.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().csrfTokenRepository(csrfTokenRepository());

        http.addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.HTTP_BASIC);

        return http.build();
    }

    @Bean
    public ServerCsrfTokenRepository csrfTokenRepository() {
        CookieServerCsrfTokenRepository csrfTokenRepository = new CookieServerCsrfTokenRepository();
        return csrfTokenRepository;
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    public AuthenticationWebFilter authenticationWebFilter(@Nonnull final ReactiveAuthenticationManager authenticationManager) {
        MediaTypeServerWebExchangeMatcher matcher = new MediaTypeServerWebExchangeMatcher(MediaType.ALL);
        matcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
//        ServerHttpSecurity.this.defaultEntryPoints.add(new DelegatingServerAuthenticationEntryPoint.DelegateEntry(restMatcher, this.entryPoint));
        AuthenticationWebFilter authenticationFilter = new AuthenticationWebFilter(authenticationManager);
//        authenticationFilter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(this.entryPoint));
        authenticationFilter.setAuthenticationConverter(new ServerHttpBasicAuthenticationConverter());
//        if (this.securityContextRepository != null) {
//            authenticationFilter.setSecurityContextRepository(this.securityContextRepository);
//        }
        return new AuthenticationWebFilter(authenticationManager);
    }
}
