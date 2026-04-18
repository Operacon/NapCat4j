package com.mikuac.shiro.dto.event.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 和 MessageEmojiLikeNoticeEvent 相同，只是符合 NapCat 的命名
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupMsgEmojiLikeNoticeEvent extends MessageEmojiLikeNoticeEvent {
}
