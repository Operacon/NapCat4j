package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标记消息已读响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkMsgAsReadResponse extends BaseResponse<Void> {
}
