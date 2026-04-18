package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 获取群详细信息（扩展）响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetGroupInfoExResponse extends BaseResponse<Map<String, Object>> {
}
