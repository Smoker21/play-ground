package com.rainsoft.annotation.play;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RecorderMethodInvocationHanlder implements InvocationHandler {
	
	Object proxied; 
	
	public RecorderMethodInvocationHanlder(Object o) {
		this.proxied = o; 
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// get method for invoke
		Method m = proxied.getClass().getMethod(method.getName(),method.getParameterTypes());
		if (m.isAnnotationPresent(Recorder.class)) {
			System.out.println("Annotation Recorder founded");
			System.out.println("{Class:" + proxy.getClass().getCanonicalName() + ", Object:" + proxy);
			System.out.println("Argument list");
			for (Object o : args) {
				System.out.println("   arg:" + o);
			}
		}
		Object ret = null; 
		try {
			ret = m.invoke(proxied, args);
		} catch (Throwable t) {
			System.out.println("Error at invoking proxy object");
			t.printStackTrace();
		}
		return ret;
	}
		
}
