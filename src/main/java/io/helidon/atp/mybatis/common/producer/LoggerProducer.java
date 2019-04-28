package io.helidon.atp.mybatis.common.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dependent
public class LoggerProducer {

	@Inject
	InjectionPoint point;

	@Produces
	public Logger producer() {
		String className = point.getMember().getDeclaringClass().getName();
		Logger logger = LoggerFactory.getLogger(className);
		return logger;
	}
}
