package com.jeremy.board.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Before("execution(* com.jeremy.board.springboot.web.*.*(..))")
    public void logPrint(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Cookie cookie = new Cookie("view", null);
        cookie.setComment("분석");
        cookie.setMaxAge(60*60*24*365);


        logger.info("{},{}", request.getRequestURI(), request.getCookies()[0]);
    }
}
