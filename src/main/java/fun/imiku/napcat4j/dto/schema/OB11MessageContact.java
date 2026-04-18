package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 联系人消息段
 */
@Data
public class OB11MessageContact implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private ContactData data;

    @Data
    public static class ContactData {
        /**
         * 联系人类型
         */
        @NonNull
        @JsonProperty("type")
        private String type;

        /**
         * 联系人ID
         */
        @NonNull
        @JsonProperty("id")
        private String id;
    }
}
