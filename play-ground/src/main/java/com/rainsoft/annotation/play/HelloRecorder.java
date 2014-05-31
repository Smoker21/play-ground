package com.rainsoft.annotation.play;

public class HelloRecorder implements IHello{
	
	@Recorder
	public String sayHelloTo(String name) {
		return "Hi! " + name; 
	}
	
	public final static void main(String... args) {
		IHello h = RegisterFactory.proxyInstanceOfHello();
		h.sayHelloTo("Lance");
		HelloRecorder hello = RegisterFactory.instanceOfHello(); 
		hello.sayHelloTo("Lance");
	}
}
