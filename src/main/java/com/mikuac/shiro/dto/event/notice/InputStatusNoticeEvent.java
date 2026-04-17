package com.mikuac.shiro.dto.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 输入状态事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class InputStatusNoticeEvent extends NoticeEvent {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("event_type")
    private Long eventType;

    @JsonProperty("status_text")
    private String statusText;
}
