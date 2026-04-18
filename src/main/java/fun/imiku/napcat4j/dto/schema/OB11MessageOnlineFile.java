package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 在线文件消息段
 */
@Data
public class OB11MessageOnlineFile implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private OnlineFileData data;

    @Data
    public static class OnlineFileData {
        /**
         * 消息ID
         */
        @NonNull
        @JsonProperty("msgId")
        private String msgId;

        /**
         * 元素ID
         */
        @NonNull
        @JsonProperty("elementId")
        private String elementId;

        /**
         * 文件名
         */
        @NonNull
        @JsonProperty("fileName")
        private String fileName;

        /**
         * 文件大小
         */
        @NonNull
        @JsonProperty("fileSize")
        private String fileSize;

        /**
         * 是否为目录
         */
        @NonNull
        @JsonProperty("isDir")
        private Boolean isDir;
    }
}
