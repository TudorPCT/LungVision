package com.lungvision.lungdiseasexrayclassificationbe.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("${cloudinary.cloud_url}")
    String cloudUrl;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(cloudUrl);
    }
}
