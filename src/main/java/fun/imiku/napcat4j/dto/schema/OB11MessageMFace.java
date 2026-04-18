package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 商城表情消息段
 */
@Data
public class OB11MessageMFace implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private MFaceData data;

    @Data
    public static class MFaceData {
        @NonNull
        @JsonProperty("emoji_package_id")
        private Long emojiPackageId;

        @NonNull
        @JsonProperty("emoji_id")
        private String emojiId;

        @NonNull
        @JsonProperty("key")
        private String key;

        @NonNull
        @JsonProperty("summary")
        private String summary;
    }
}
