package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 账号相关服务
 */
@Service
@SuppressWarnings("unused")
public class AccountService {

    private final ApiPostService apiPostService;

    public AccountService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
