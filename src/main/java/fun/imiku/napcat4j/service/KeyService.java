package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 密钥服务
 */
@Service
public class KeyService {

    private final ApiPostService apiPostService;

    public KeyService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
