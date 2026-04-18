package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 艾特消息段
 */
@Data
public class OB11MessageAt implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private AtData data;

    @Data
    public static class AtData {
        /**
         * QQ号或all
         */
        @NonNull
        @JsonProperty("qq")
        private String qq;

        /**
         * 显示名称
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("name")
        private String name;
    }
}
