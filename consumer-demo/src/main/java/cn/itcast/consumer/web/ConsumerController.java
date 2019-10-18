package cn.itcast.consumer.web;

import cn.itcast.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * DefaultProperties:hystrix类全局配置，适用这个类
 */
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    //引入ribbon用于负载均衡，使消费者可以访问多个服务提供者
 //   @Autowired
 //   private RibbonLoadBalancerClient client;

    //服务发现，可以根据服务名发现服务
    @Autowired
    private DiscoveryClient discoveryClient;

    //@GetMapping("/{id}")
   // public User queryById(@PathVariable("id") Long id){
        //1. String url = "http://localhost:8081/user/"+id;

        //2. 根据服务ID获取实例
        // List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出IP和端口
        //当服务有多个时，使用list集合不方便，这里引入rebbion
        //  ServiceInstance instance = instances.get(0);
        // String ipAndPort = instance.getHost()+":"+instance.getPort();
      //  String url = "http://"+ipAndPort+"/user/"+id;
        //3.
        /*ServiceInstance instance = client.choose("user-service");
        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
        System.out.printf("url="+url);*/


        //4. 根据ribbon在服务请求的时候会拦截请求并解析，这里的URL使下面这种格式
     //   String url = "http://user-service/user/"+id;
    //    User user = restTemplate.getForObject(url,User.class);
     //   return user;
   // }


    /**
     * 编写降级逻辑
     * 失败回归机制,该机制的方式有特定的的规则：fallbackMethod的value是一个方法，
     * 该方法与该注释注解的方法返回参数必须是一致
     * HystrixCommand:配合全局使用，可以自定义默认超时时长，格式如下
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    //@HystrixCommand(fallbackMethod = "queryByIdFallBack")
    //@HystrixCommand(commandProperties = {
          //  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    //})
    public String queryById(@PathVariable("id") Long id){
        String url = "http://user-service/user/"+id;
        String user = restTemplate.getForObject(url,String.class);
        return user;
    }
    public String defaultFallback(){
        return "服务太拥挤了";
    }
}
