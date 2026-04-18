package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * JSON消息段
 */
@Data
public class OB11MessageJson implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private JsonData data;

    @Data
    public static class JsonData {
        /**
         * JSON数据（string|object）
         */
        @NonNull
        @JsonProperty("data")
        private Object data;

        /**
         * 配置
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("config")
        private ConfigData config;
    }

    @Data
    public static class ConfigData {
        /**
         * token
         */
        @NonNull
        @JsonProperty("token")
        private String token;
    }
}
