package org.kafka.streams.processor.api;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.streams.processor.AbstractProcessor;


public class NumberValidateProcessor extends AbstractProcessor<String, String> {

	@Override
	public void process(String key, String value) {
		System.out.println("Number validation processor:: validating the value=" + value);
		
		if (NumberUtils.isCreatable(value)) {
			context().forward(key, value);
		} else {
			System.out.println("Number validation processor:: Invalid number=" + value);
		}
		context().commit();
	}

}
