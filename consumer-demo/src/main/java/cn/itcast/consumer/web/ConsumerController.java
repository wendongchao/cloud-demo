package cn.itcast.consumer.web;

import cn.itcast.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    //服务发现，可以根据服务名发现服务
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id){
        //根据服务ID获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出IP和端口
        ServiceInstance instance = instances.get(0);
        String ipAndPort = instance.getHost()+":"+instance.getPort();
        //String url = "http://localhost:8081/user/"+id;
        String url = "http://"+ipAndPort+"/user/"+id;
        User user = restTemplate.getForObject(url,User.class);
        return user;
    }
}
