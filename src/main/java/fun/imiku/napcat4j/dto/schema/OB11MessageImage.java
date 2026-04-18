package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 图片消息段
 */
@Data
public class OB11MessageImage implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private ImageData data;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ImageData extends FileBaseData {
        /**
         * 图片摘要
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("summary")
        private String summary;

        /**
         * 图片子类型
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("sub_type")
        private Long subType;
    }
}
