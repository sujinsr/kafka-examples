package org.kafka.streams.processor.api;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

public class NumberStreamTransform {

	public static void main(String []args) {
		//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFOl");

		StringSerializer stringSerializer = new StringSerializer();
		StringDeserializer stringDeserializer = new StringDeserializer();

		Topology topology = new Topology();
		topology.addSource("SOURCE", stringDeserializer, stringDeserializer, "in-number")
		.addProcessor("PROCESS-VALIDATE", NumberValidateProcessor::new, "SOURCE")
		.addProcessor("PROCESS-DOUBLE", NumberDoubleProcessor::new, "PROCESS-VALIDATE")
		.addProcessor("PROCESS-TRIPLE", NumberTripleProcessor::new, "PROCESS-VALIDATE")
		.addSink("SINK-DOUBLE", "out-double-number", stringSerializer, stringSerializer, "PROCESS-DOUBLE")
		.addSink("SINK-TRIPLE", "out-triple-number", stringSerializer, stringSerializer, "PROCESS-TRIPLE");

		StreamsConfig config = new StreamsConfig(getProperties());
		KafkaStreams stream = new KafkaStreams(topology, config);
		stream.start();
	}

	private static Properties getProperties() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testing-streams-processor-api");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
		return props;
	}
}
