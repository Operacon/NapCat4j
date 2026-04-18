package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 位置消息段
 */
@Data
public class OB11MessageLocation implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private LocationData data;

    @Data
    public static class LocationData {
        /**
         * 纬度（string|number）
         */
        @NonNull
        @JsonProperty("lat")
        private Object lat;

        /**
         * 经度（string|number）
         */
        @NonNull
        @JsonProperty("lon")
        private Object lon;

        /**
         * 标题
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("title")
        private String title;

        /**
         * 内容
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("content")
        private String content;
    }
}
