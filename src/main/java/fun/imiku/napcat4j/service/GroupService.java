package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 群组服务
 */
@Service
public class GroupService {

    private final ApiPostService apiPostService;

    public GroupService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
