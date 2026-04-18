package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * Markdown消息段
 */
@Data
public class OB11MessageMarkdown implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private MarkdownData data;

    @Data
    public static class MarkdownData {
        /**
         * Markdown内容
         */
        @NonNull
        @JsonProperty("content")
        private String content;
    }
}
