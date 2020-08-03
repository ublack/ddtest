package com.ublack.ddtest;

import java.util.concurrent.Callable;

public class CKTest {

    public static void main(String[] args) {
        CircuitBreaker ck = new CircuitBreaker(10, 3);
        ck.setDelayTime(3000);
        CircuitBreakerRunner ckRunner = new CircuitBreakerRunner();
        Callable<Object> callable = new Callable<Object>() {
            public Void call() throws Exception {
                if (Math.random() < 0.4) {
                    throw new Exception();
                }
                return null;
            }
        };
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                Object ret = ckRunner.run(ck, callable);
                System.out.println(ret);
            } catch (Exception ignored) {

            }
        }

    }
}
