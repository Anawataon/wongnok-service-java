package com.ttb.wongnok.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

// CORS (Cross-Origin Resource Sharing)
// บอก Spring ว่า class นี้คือ Configuration Class
// จะถูกโหลดตอน startup เหมือนเป็น Java-based config (แทน application.properties)
@Configuration
public class WebConfig {

    // บอก Spring ว่า method นี้จะสร้าง Bean เข้า Spring Context
    @Bean
    // WebMvcConfigurer คือ interface ที่ใช้กำหนดการตั้งค่าเกี่ยวกับ Web MVC
    // เช่น CORS, Interceptors, ViewResolvers, etc.
    // ในที่นี้เราจะใช้สำหรับกำหนด CORS policy
    // CORS คือการอนุญาตให้ client (เช่น React, Angular)
    // สามารถเรียก API จาก domain อื่นได้
    // เช่น ถ้า frontend อยู่ที่ http://localhost:3000
    // และ backend API อยู่ที่ http://localhost:8080
    // เราต้องกำหนด CORS policy ให้ frontend สามารถเรียก API ได้
    // โดยปกติ Spring Boot จะไม่อนุญาตให้เรียก API จาก domain อื่น
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override // Override method สำหรับกำหนด CORS rule
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                // addMapping ระบุ path ที่จะใช้ CORS rule นี้
                // allowedOrigins อนุญาตให้ frontend ที่มาจาก http://localhost:3000 เข้าถึง
                // backend ได้
                // allowedMethods ระบุว่าอนุญาตให้ใช้ HTTP methods อะไรบ้าง
                // allowedHeaders อนุญาตให้ใช้ headers อะไรบ้าง
                // allowCredentials ระบุว่าอนุญาตให้ส่ง cookies หรือ HTTP authentication
                // credentials ได้ ถ้าใช้ token หรือ session cookie ต้องเปิดอันนี้เสมอ
                // หมายเหตุ: ถ้าเปิด allowCredentials(true) แล้ว allowedOrigins("*") จะผิด
                // ต้องระบุ origin แบบเจาะจงเท่านั้น
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
