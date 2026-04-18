package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import org.springframework.stereotype.Service;

/**
 * 文件相关服务
 */
@Service
public class FileService {

    private final ApiPostService apiPostService;

    public FileService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }
}
