package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 发送戳一戳请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupPokeRequest {

    /**
     * 群号
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * 用户 QQ，本字段似乎是无意义的，任意传入
     */
    @NonNull
    @JsonProperty("user_id")
    private Long userId = 1L;

    /**
     * 目标 QQ
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("target_id")
    private Long targetId;
}
