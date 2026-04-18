package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * ID音乐消息段
 */
@Data
public class OB11MessageIdMusic implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private IdMusicData data;

    @Data
    public static class IdMusicData {
        /**
         * 音乐平台类型
         */
        @NonNull
        @JsonProperty("type")
        private String type;

        /**
         * 音乐ID（number|string）
         */
        @NonNull
        @JsonProperty("id")
        private Object id;
    }
}
