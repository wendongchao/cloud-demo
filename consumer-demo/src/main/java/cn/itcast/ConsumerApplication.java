package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务的调用者，调用者调用服务提供者的服务
 * 调用者                      提供者
 * ConsumerApplication -----> UserApplication
 * EnableFeignClients:引入feign依赖，
 */
/*@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
*/

@EnableFeignClients // 开启Feign功能
@SpringCloudApplication
public class ConsumerApplication {
    /**
     * LoadBalanced作用：ribbon的负载均衡拦截，会拦截restTemplate请求，之后进行负载均衡
     * @return
     */
/*    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        // 这次我们使用了OkHttp客户端,只需要注入工厂即可
        return new RestTemplate();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
