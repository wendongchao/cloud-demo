package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul拦截过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 返回字符串，代表过滤器类型：pre,routing,post,error
     * pre:请求在路由之前调用，作用：拦截未经允许的请求和校验信息
     * routing:在路由请求时调用，作用：访问服务提供者，可以添加参数，并校验和转换返回数据类型
     * post:在routing和error过滤器之后调用，作用：主要返回给服务消费方信息
     * error:处理请求时发生错误调用
     * @return
     */
    @Override
    public String filterType() {
        // 登录校验，肯定是在前置拦截
        return "pre";
    }

    /**
     * 通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回一个boolean值，判断该过滤器是否需要执行，true执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 返回true，代表过滤器生效。
        return true;
    }

    /**
     * 过滤器的具体业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 登录校验逻辑
        // 1. 获取zuul提供的请求上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        // 2. 从上下文中获取request对象
        HttpServletRequest req = ctx.getRequest();
        // 3. 从请求中获取token
        String token = req.getParameter("access-token");
        // 4. 判断
        if(token==null||"".equals(token.trim())){
            // 没有token，登录失败，拦截
            ctx.setSendZuulResponse(false);
            // 返回401状态码，也可以考虑重定向到登录页
            ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        // 校验通过，可以考虑把用户信息放入上下文，继续向后执行
        return null;
    }
}
