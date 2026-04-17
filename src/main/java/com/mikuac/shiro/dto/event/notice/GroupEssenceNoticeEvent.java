package com.mikuac.shiro.dto.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 精华消息事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class GroupEssenceNoticeEvent extends NoticeEvent {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("sender_id")
    private Long senderId;

    @JsonProperty("operator_id")
    private Long operatorId;

    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * 操作类型:
     * delete
     * add
     */
    @JsonProperty("sub_type")
    private String subType;
}
