package com.example.demo2.kafkaProducer;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        FutureTask<Integer> ft1 = new FutureTask<Integer>(new MyCallable());
        FutureTask<Integer> ft2 = new FutureTask<Integer>(new MyCallable100());
        FutureTask<Integer> ft3 = new FutureTask<Integer>(new MyCallable1000());

        Thread thread = new Thread(ft1);
        Thread thread1 = new Thread(ft2);
        Thread thread2 = new Thread(ft3);

        thread.start();
        thread1.start();
        thread2.start();
        System.out.println(ft1.get()+ft2.get()+ft3.get());//get线程阻塞

    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for (int i = 0; i < 10; i++) {
            sum +=i;
        }
        return sum;
    }
}
class MyCallable100 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for (int i = 0; i < 100; i++) {
            sum +=i;
        }
        return sum;
    }
}
class MyCallable1000 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for (int i = 0; i < 1000; i++) {
            sum +=i;
        }
        return sum;
    }
}