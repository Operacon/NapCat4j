package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群组禁言响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetGroupBanResponse extends BaseResponse<Void> {
}
