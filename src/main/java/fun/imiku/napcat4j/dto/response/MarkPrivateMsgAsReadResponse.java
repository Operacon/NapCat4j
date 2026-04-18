package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标记私聊已读响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkPrivateMsgAsReadResponse extends BaseResponse<Void> {
}
