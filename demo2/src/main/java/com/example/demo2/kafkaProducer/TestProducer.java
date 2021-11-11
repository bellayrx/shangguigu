package com.example.demo2.kafkaProducer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class TestProducer {
    public static void main(String[] args) {
        //创建配置对象
        Properties props = new Properties();
        //kafka集群
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "43.135.31.142:9092");
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //应答机制
        //props.put("acks",1);



        //创建生产者
        Producer<String,String> producer = new KafkaProducer<String, String>(props);//key -value的方式
        //准备数据
        String topic  = "first";
        String value = "hello,kafka";

        ProducerRecord record = new ProducerRecord(topic,value);

        //回调方法
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                recordMetadata.partition();
            }
        })
        //生产（发送）数据
        for (int i = 0; i < 50; i++) {
            producer.send(record);
        }
        producer.close();

    }
}
