package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 个人操作服务
 */
@Service
@SuppressWarnings("unused")
public class PersonalService {

    private final ApiPostService apiPostService;

    public PersonalService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
