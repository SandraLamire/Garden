package fr.eni.garden.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/").permitAll();

            auth.requestMatchers(HttpMethod.GET, "/garden").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/garden/*").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/garden/*").permitAll();
            // ou GardenController :
            // GET/POST     /add    +      /{idGarden}/edit     + REST  "/api/garden"

            auth.requestMatchers(HttpMethod.GET, "/plant").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/plant/*").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/plant/*").permitAll();
            // OU plantController :
            //  GET/POST     /{idPlant}/delete      +  /{idPlant}/edit


            // squareController :
            //  GET/POST     "/garden/{idGarden}/square/{idSquare}/planting" + /add  + "/{idPlanting}/delete" + /{idPlanting}/edit


            // plantingController :
            //  GET/POST     "/garden/{idGarden}/square" + /add    +  /{idSquare}/delete  +   "/{idSquare}/edit"


            auth.requestMatchers("/css/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .requestMatchers("/javascript/*").permitAll()
                    .requestMatchers("/error").permitAll();
//                    .anyRequest().authenticated();
        });

        http.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/").permitAll();
            form.failureUrl("/login-error");

            form.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request,
                                                    HttpServletResponse response, Authentication authentication)
                        throws IOException, ServletException {
                    if (authentication != null) {
                        MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
                        request.getSession().setAttribute("currentUser", userDetails.getUser());
                    }
                    super.onAuthenticationSuccess(request, response, authentication);
                }

            });
        });

        http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll());

        return http.build();

    }

}
