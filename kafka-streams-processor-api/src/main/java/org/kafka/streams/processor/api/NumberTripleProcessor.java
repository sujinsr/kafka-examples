package org.kafka.streams.processor.api;

import org.apache.kafka.streams.processor.AbstractProcessor;

public class NumberTripleProcessor extends AbstractProcessor<String, String> {

	public void process(String key, String value) {
		System.out.println("NumberTriple processor:: triple the value=" + value);
		int v = Integer.valueOf(value);
		v = v * 3;
		context().forward(key, String.valueOf(v));
		context().commit();
	}

}
