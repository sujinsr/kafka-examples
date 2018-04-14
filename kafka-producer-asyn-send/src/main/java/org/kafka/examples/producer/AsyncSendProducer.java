package org.kafka.examples.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;

public class AsyncSendProducer 
{
    public static void main( String[] args )
    {
    	String key 		= "key-three";
    	String value 	= "value-three";
    	String topic	= "example-topic";
    	
    	Properties props = new Properties();
    	props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
    	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	
    	Producer<String, String> producer = new KafkaProducer<>(props);
    	
    	ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
    	
    	
    	try {
			producer.send(producerRecord, new ProducerCallback());
			System.out.println("Message send to the broker Asynchronously.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to send the message.");
		} finally {
			producer.close();
		}
    	
    	System.out.println("AsyncSendProducer finished.");
    }
}
