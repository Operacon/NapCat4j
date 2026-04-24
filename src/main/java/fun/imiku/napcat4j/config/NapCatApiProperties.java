package fun.imiku.napcat4j.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * NapCat HTTP API 配置。
 */
@Data
@Component
@ConfigurationProperties(prefix = "napcat4j.api")
public class NapCatApiProperties {
    /**
     * NapCat API http 服务器地址，必填
     * <p>
     * 例如 "127.0.0.1:7002"
     */
    private String url;

    /**
     * NapCat API http 服务器 token，可留空
     * <p>
     * 例如 "t0K3n"
     */
    private String accessToken;

    public void setUrl(String url) {
        this.url = normalizeBaseUrl(url);
    }

    public void setAccessToken(String token) {
        accessToken = StringUtils.hasText(token) ? token.trim() : null;
    }

    public boolean hasAccessToken() {
        return StringUtils.hasText(accessToken);
    }

    private static String normalizeBaseUrl(String rawUrl) {
        String normalized = rawUrl.trim();
        if (!hasScheme(normalized)) {
            normalized = "http://" + normalized;
        }
        if (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    private static boolean hasScheme(String url) {
        return url.contains("://");
    }
}
