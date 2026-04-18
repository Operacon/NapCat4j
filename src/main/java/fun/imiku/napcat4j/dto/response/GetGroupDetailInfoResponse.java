package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import fun.imiku.napcat4j.dto.schema.OB11Group;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取群详细信息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetGroupDetailInfoResponse extends BaseResponse<OB11Group> {
}
