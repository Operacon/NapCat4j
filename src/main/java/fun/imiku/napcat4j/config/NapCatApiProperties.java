package fun.imiku.napcat4j.config;

import org.springframework.util.StringUtils;

/**
 * NapCat HTTP API 配置。
 */
public record NapCatApiProperties(String baseUrl, String accessToken) {

    public NapCatApiProperties(String baseUrl, String accessToken) {
        if (!StringUtils.hasText(baseUrl)) {
            throw new IllegalArgumentException("缺少必填配置：napcat4j.api.url");
        }
        this.baseUrl = normalizeBaseUrl(baseUrl);
        this.accessToken = StringUtils.hasText(accessToken) ? accessToken.trim() : null;
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
