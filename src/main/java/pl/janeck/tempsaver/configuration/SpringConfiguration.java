package pl.janeck.tempsaver.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * ... comment class...
 *
 * @author JKN janeck@protonmail.com
 * @since 02 January 2021 @ 18:59
 */

@EnableScheduling
@Configuration
public class SpringConfiguration {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
