package com.mikuac.shiro.dto.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 群头衔变更通知事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class GroupTitleNoticeEvent extends NoticeEvent {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("title")
    private String title;
}
