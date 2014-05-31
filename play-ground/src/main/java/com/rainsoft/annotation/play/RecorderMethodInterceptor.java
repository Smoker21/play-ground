package com.rainsoft.annotation.play;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RecorderMethodInterceptor implements MethodInterceptor {

	public Object intercept(Object o, Method m, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		if (m.isAnnotationPresent(Recorder.class)) {
			System.out.println("{Class: " + o.getClass().getCanonicalName() + ", Object:" + o + "}");
			System.out.println("Args list");
			for (Object arg : args) {				
				System.out.println(arg);
			}
		}
		Object ret = null;
		try {
			ret = proxy.invokeSuper(o, args);
		} catch (Throwable t) {
			t.printStackTrace(); 		
		}
		System.out.println(ret);
		return ret;
	}

}
