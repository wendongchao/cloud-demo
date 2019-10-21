package cn.itcast.consumer.client;

import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 使用feign进行熔断配置，返回信息类，需要实现熔断配置接口UserClient，
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public User queryUserById(Long id) {
        User user = new User();
        user.setName("未查询到用户信息！");
        return user;
    }
}
