package fun.imiku.napcat4j.component;

import com.mikuac.shiro.common.utils.JsonUtils;
import fun.imiku.napcat4j.config.NapCatApiProperties;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

/**
 * 内部 HTTP 服务
 */
@Slf4j
@Service
public class ApiPostService {

    private static final String EMPTY_JSON_BODY = "{}";
    private static final Executor VIRTUAL_THREAD_EXECUTOR = Thread::startVirtualThread;

    private final HttpClient httpClient;
    private final String[] defaultHeaders;
    private static final ObjectMapper objectMapper = JsonUtils.getObjectMapper();

    private final String baseUrl;
    private final Map<NapCatApiPath, URI> uriCache;

    public ApiPostService(
            NapCatApiProperties properties
    ) {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        this.defaultHeaders = buildDefaultHeaders(properties);
        this.baseUrl = properties.getUrl();
        this.uriCache = new HashMap<>();
        for (NapCatApiPath apiPath : NapCatApiPath.values()) {
            uriCache.put(apiPath, URI.create(baseUrl + apiPath));
        }
    }

    /**
     * 发送通用 JSON POST 请求
     */
    public HttpResponse<String> postJson(NapCatApiPath path, Object body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(Objects.requireNonNull(
                        uriCache.computeIfAbsent(path, p -> URI.create(baseUrl + p)),
                        "uri 不能为空"))
                .headers(defaultHeaders)
                .POST(HttpRequest.BodyPublishers.ofString(serializeBody(body)))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * 响应体反序列化
     */
    public static <T extends BaseResponse<?>> T parseResponse(HttpResponse<String> resp, Class<T> clazz) {
        try {
            T r = objectMapper.readValue(resp.body(), clazz);
            if (r.getRetcode() != 0) {
                log.error("API 调用异常，状态: {} ; message: {} ; wording: {}", r.getRetcode(), r.getMessage(), r.getWording());
                throw new RuntimeException();
            }
            return r;
        } catch (Exception e) {
            throw new RuntimeException("API 调用将 " + resp.body() + " 反序列化为 " + clazz.getName() + " 失败", e);
        }
    }

    /**
     * 基于虚拟线程异步调用
     */
    public static <T> CompletableFuture<T> supplyAsyncWithCatch(ThrowingSupplier<T> supplier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (IOException | InterruptedException e) {
                throw new CompletionException(e);
            }
        }, VIRTUAL_THREAD_EXECUTOR);
    }

    private String serializeBody(Object body) throws IOException {
        if (body == null) {
            return EMPTY_JSON_BODY;
        }
        if (body instanceof String json) {
            return json;
        }
        try {
            return objectMapper.writeValueAsString(body);
        } catch (JacksonException e) {
            throw new IOException("JSON serialize failed", e);
        }
    }

    private static String[] buildDefaultHeaders(NapCatApiProperties properties) {
        if (!properties.hasAccessToken()) {
            return new String[]{HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE};
        }
        return new String[]{
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE,
                HttpHeaders.AUTHORIZATION, "Bearer " + properties.getAccessToken()
        };
    }

    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws IOException, InterruptedException;
    }
}
