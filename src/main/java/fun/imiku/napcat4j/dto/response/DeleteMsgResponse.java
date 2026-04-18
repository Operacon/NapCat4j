package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 撤回消息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteMsgResponse extends BaseResponse<Void> {
}
