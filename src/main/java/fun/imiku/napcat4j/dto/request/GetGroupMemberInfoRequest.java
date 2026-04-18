package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 获取群成员信息请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGroupMemberInfoRequest {

    /**
     * 群号
     */
    @NonNull
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * QQ 号
     */
    @NonNull
    @JsonProperty("user_id")
    private Long userId;

    /**
     * 是否不使用缓存
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("no_cache")
    private Boolean noCache;
}
