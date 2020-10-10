package com.freddy.kulachat.core.aop;

import com.freddy.kulachat.utils.JsonMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author FreddyChen
 * @name Controller层日志打印
 * @date 2020/09/23 15:19
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.freddy.kulachat.core.controller..*.*(..))")
    public void controllerLog() {}

    @Before("controllerLog()")
    public void doBefore(JoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        logger.info("========================================== Start ==========================================");
        logger.info("Request:");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求头
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String header = headers.nextElement();
            logger.info("Header         : {}--->{}", header, request.getHeader(header));
        }
        // 打印请求入参
        logger.info("Request Args   : {}", JsonMapper.toJSONString(point.getArgs()));
        logger.info("\n");
    }

//    @After("controllerLog()")
//    public void doAfter() throws Throwable {
//    }

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info("Response:");
        // 打印出参
        logger.info("Response Args  : {}", JsonMapper.toJSONString(result));
        // 执行耗时
        logger.info("\n");
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        logger.info("========================================== End ==========================================");
        return result;
    }
}
