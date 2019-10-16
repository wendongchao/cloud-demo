package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务的调用者，调用者调用服务提供者的服务
 * 调用者                      提供者
 * ConsumerApplication -----> UserApplication
 */
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {
    @Bean
    public RestTemplate restTemplate() {
        // 这次我们使用了OkHttp客户端,只需要注入工厂即可
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
