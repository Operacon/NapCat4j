package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送群打卡响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendGroupSignResponse extends BaseResponse<Void> {
}
