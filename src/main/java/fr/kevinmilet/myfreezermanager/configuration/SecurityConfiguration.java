package fr.kevinmilet.myfreezermanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.kevinmilet.myfreezermanager.jwt.JwtFilter;

/**
 * @author k.milet
 */
@EnableWebSecurity
public class SecurityConfiguration {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(RestAuthenticationEntryPoint restAuthenticationEntryPoint, JwtFilter jwtFilter) {
	this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
	this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
	http.csrf().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		.antMatchers("/").permitAll().antMatchers("/static/**/").permitAll().antMatchers("/creer_utilisateur")
		.permitAll().antMatchers("/authenticate").permitAll().antMatchers("/isConnected").permitAll()
		.antMatchers("/v3/api-docs/**").permitAll().antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/swagger-ui/**").permitAll().antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/webjars/**").permitAll().anyRequest().authenticated();
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

}
