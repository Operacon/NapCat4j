package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * QQ表情消息段
 */
@Data
public class OB11MessageFace implements OB11MessageData {

    /**
     * 消息段类型
     */
    @NonNull
    @JsonProperty("type")
    private String type;

    /**
     * 消息段数据
     */
    @NonNull
    @JsonProperty("data")
    private FaceData data;

    @Data
    public static class FaceData {
        /**
         * 表情 ID
         */
        @NonNull
        @JsonProperty("id")
        private String id;

        /**
         * 结果 ID
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("resultId")
        private String resultId;

        /**
         * 连击数
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("chainCount")
        private Long chainCount;
    }
}
