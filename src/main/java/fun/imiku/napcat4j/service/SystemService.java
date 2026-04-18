package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 系统操作服务
 */
@Service
@SuppressWarnings("unused")
public class SystemService {

    private final ApiPostService apiPostService;

    public SystemService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
