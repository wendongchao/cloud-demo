package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用feign进行熔断配置，实现传入参数调用服务，
 * 使用feign需要以下几个元素：请求路径，请求方式，请求参数，返回结果类型
 */
@FeignClient(value = "user-service",fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("user/{id}")
    User queryUserById(@PathVariable("id") Long id);
}
