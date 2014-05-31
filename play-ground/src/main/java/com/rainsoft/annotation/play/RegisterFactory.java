package com.rainsoft.annotation.play;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

public class RegisterFactory {
	public final static HelloRecorder instanceOfHello() {
		HelloRecorder h = (HelloRecorder) Enhancer.create(HelloRecorder.class, new RecorderMethodInterceptor());
		return h;
	}

	public final static IHello proxyInstanceOfHello() {
		IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[] { IHello.class },
				new RecorderMethodInvocationHanlder(new HelloRecorder()));
		return hello;
	}
}
