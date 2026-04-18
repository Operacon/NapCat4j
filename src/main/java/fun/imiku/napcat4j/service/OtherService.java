package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 其他服务
 */
@Service
@SuppressWarnings("unused")
public class OtherService {

    private final ApiPostService apiPostService;

    public OtherService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
