package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * OneBot 11 群信息
 */
@Data
public class OB11Group {

    /**
     * 是否全员禁言
     */
    @NonNull
    @JsonProperty("group_all_shut")
    private Integer groupAllShut;

    /**
     * 群备注
     */
    @NonNull
    @JsonProperty("group_remark")
    private String groupRemark;

    /**
     * 群号
     */
    @NonNull
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * 群名称
     */
    @NonNull
    @JsonProperty("group_name")
    private String groupName;

    /**
     * 成员人数
     */
    @Nullable
    @JsonProperty("member_count")
    private Integer memberCount;

    /**
     * 最大成员人数
     */
    @Nullable
    @JsonProperty("max_member_count")
    private Integer maxMemberCount;
}
