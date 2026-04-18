package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * QQ闪传消息段
 */
@Data
public class OB11MessageFlashTransfer implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private FlashTransferData data;

    @Data
    public static class FlashTransferData {
        /**
         * 文件集ID
         */
        @NonNull
        @JsonProperty("fileSetId")
        private String fileSetId;
    }
}
