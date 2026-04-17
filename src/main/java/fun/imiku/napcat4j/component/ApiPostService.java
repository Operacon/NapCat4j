package fun.imiku.napcat4j.component;

import fun.imiku.napcat4j.config.NapCatApiProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 内部 HTTP 服务
 */
@Service
public class ApiPostService {

    private static final String EMPTY_JSON_BODY = "{}";

    private final HttpClient httpClient;
    private final String[] defaultHeaders;

    private final String baseUrl;
    private final Map<NapCatApiPath, URI> uriCache;

    public ApiPostService(
            @Qualifier("napcatApiHttpClient") HttpClient httpClient,
            NapCatApiProperties properties
    ) {
        this.httpClient = httpClient;
        this.defaultHeaders = buildDefaultHeaders(properties);
        this.baseUrl = properties.baseUrl();
        this.uriCache = new HashMap<>();
        for (NapCatApiPath apiPath : NapCatApiPath.values()) {
            uriCache.put(apiPath, URI.create(baseUrl + apiPath));
        }
    }

    /**
     * 发送通用 JSON POST 请求
     */
    public HttpResponse<String> postJson(NapCatApiPath path, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(Objects.requireNonNull(
                        uriCache.computeIfAbsent(path, p -> URI.create(baseUrl + p)),
                        "uri 不能为空"))
                .headers(defaultHeaders)
                .POST(HttpRequest.BodyPublishers.ofString(body == null ? EMPTY_JSON_BODY : body))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String[] buildDefaultHeaders(NapCatApiProperties properties) {
        if (!properties.hasAccessToken()) {
            return new String[]{HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE};
        }
        return new String[]{
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
                HttpHeaders.AUTHORIZATION, properties.accessToken()
        };
    }
}
