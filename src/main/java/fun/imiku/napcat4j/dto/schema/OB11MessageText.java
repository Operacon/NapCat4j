package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 纯文本消息段
 */
@Data
public class OB11MessageText implements OB11MessageData {

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
    private TextData data;

    @Data
    public static class TextData {
        /**
         * 纯文本内容
         */
        @NonNull
        @JsonProperty("text")
        private String text;
    }
}
