package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 消息服务
 */
@Service
public class MessageService {

    private final ApiPostService apiPostService;

    public MessageService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
