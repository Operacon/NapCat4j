package com.mikuac.shiro.dto.event.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 和 PokeNoticeEvent 相同，只是符合 NapCat 的命名
 */
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class GroupPokeNoticeEvent extends PokeNoticeEvent {
}
