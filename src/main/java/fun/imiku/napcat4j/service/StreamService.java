package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 流式操作服务
 */
@Service
@SuppressWarnings("unused")
public class StreamService {

    private final ApiPostService apiPostService;

    public StreamService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
