package com.rainsoft.annotation.play;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RecorderMethodInterceptor implements MethodInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecorderMethodInterceptor.class);

	public Object intercept(Object o, Method m, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		if (m.isAnnotationPresent(Recorder.class)) {
			LOGGER.info("{Class:{}, Object:{}}",o.getClass().getCanonicalName(),o);
			LOGGER.info("{Class: " + o.getClass().getCanonicalName() + ", Object:" + o + "}");			
			LOGGER.info("Args list");
			for (Object arg : args) {
				LOGGER.info(""+arg);
			}
		}
		Object ret = null;
		try {
			ret = proxy.invokeSuper(o, args);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		LOGGER.info(""+ret);
		return ret;
	}

}
