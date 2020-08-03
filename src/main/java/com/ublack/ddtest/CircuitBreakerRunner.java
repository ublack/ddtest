package com.ublack.ddtest;

import java.util.concurrent.Callable;

public class CircuitBreakerRunner {

    Object run(CircuitBreaker ck, Callable<Object> callable){
        if (ck.runAllowed()) {
            System.out.println("no  fallback");
            try {
                Object sucObj = callable.call();
                ck.success();
                return sucObj;
            } catch (Exception e) {
           //     e.printStackTrace();  // 输出异常日志
                ck.fail(); // 异常计数
                throw  new RuntimeException(e); // 出现异常
            }
        }else {
            System.out.println("run fallback");
            return ck.fallback(); // 熔断回调
        }

    }
}

