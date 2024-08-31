package com.app.demo.command;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StatusCommand {
    private String uuid;
    private MultipartFile file;
}
