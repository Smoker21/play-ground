package com.rainsoft.annotation.play;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecorderMethodInvocationHanlder implements InvocationHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecorderMethodInvocationHanlder.class);

	/**
	 * A reference to original object.
	 */
	private final Object original;

	/**
	 * 
	 * Provide a reference to original object
	 * 
	 * @param o
	 */
	public RecorderMethodInvocationHanlder(Object original) {
		this.original = original;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// get method from proxied one to invoke
		Method m = original.getClass().getMethod(method.getName(), method.getParameterTypes());
		if (m.isAnnotationPresent(Recorder.class)) {
			LOGGER.info("Annotation Recorder founded");
			LOGGER.info("Proxy Class:{}, Original Class:{}", proxy.getClass().getCanonicalName(), original.getClass()
					.getCanonicalName());

			if (m.getTypeParameters().length != 0) {
				LOGGER.info("Argument list");
				m.getTypeParameters();
				for (Object o : args) {
					LOGGER.info("arg:" + o);
				}
			}
		}
		Object ret = null;
		try {
			ret = m.invoke(original, args);
		} catch (Throwable t) {
			LOGGER.error("Error at invoking proxy object", t);
			throw t; 
		}
		return ret;
	}

}
