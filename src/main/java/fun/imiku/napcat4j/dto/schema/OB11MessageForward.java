package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 合并转发消息段
 */
@Data
public class OB11MessageForward implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private ForwardData data;

    @Data
    public static class ForwardData {
        /**
         * 合并转发ID
         */
        @NonNull
        @JsonProperty("id")
        private String id;

        /**
         * 消息内容（OB11Message[]）
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("content")
        private List<OB11MessageData> content;
    }
}
