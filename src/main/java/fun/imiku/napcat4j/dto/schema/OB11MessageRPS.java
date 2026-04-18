package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 猜拳消息段
 */
@Data
public class OB11MessageRPS implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private RpsData data;

    @Data
    public static class RpsData {
        /**
         * 猜拳结果（number|string）
         */
        @NonNull
        @JsonProperty("result")
        private Object result;
    }
}
