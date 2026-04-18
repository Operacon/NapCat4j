package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标记所有消息已读响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkAllAsReadResponse extends BaseResponse<Void> {
}
