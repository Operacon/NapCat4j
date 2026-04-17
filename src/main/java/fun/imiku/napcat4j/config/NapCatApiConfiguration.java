package fun.imiku.napcat4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * NapCat API 相关配置。
 */
@Configuration
public class NapCatApiConfiguration {

    @Bean
    public NapCatApiProperties napcatApiProperties(Environment environment) {
        String url = environment.getProperty("napcat4j.api.url");
        String accessToken = environment.getProperty("napcat4j.api.access-token");
        return new NapCatApiProperties(url, accessToken);
    }

    @Bean("napcatApiHttpClient")
    public HttpClient napcatApiHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
}
