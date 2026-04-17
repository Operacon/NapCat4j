package com.mikuac.shiro.dto.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 个人资料点赞通知事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ProfileLikeNoticeEvent extends NoticeEvent {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("operator_id")
    private Long operatorId;

    @JsonProperty("operator_nick")
    private String operatorNick;

    @JsonProperty("times")
    private Long times;

    @JsonProperty("time")
    private Long time;
}
