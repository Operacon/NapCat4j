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
public class GroupGrayTipNoticeEvent extends NoticeEvent {
    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * 业务 id
     */
    @JsonProperty("busi_id")
    private String busiId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("raw_info")
    private Object rawInfo;
}
