package com.rainsoft.annotation.play;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloRecorder implements IHello {
	private final static Logger logger = LoggerFactory.getLogger(HelloRecorder.class);

	@Recorder
	public String sayHelloTo(String name) {
		return "Hi! " + name;
	}

	@Recorder
	public String sayHi() {
		return "Hi! Have a nice day.";
	}

	@Recorder
	public String testError() {
		throw new RuntimeException("Errors For test");
	}

	public final static void main(String... args) {
		IHello h = RegisterFactory.proxyInstanceOfHello();
		System.out.println("Result:" + h.sayHelloTo("Lance"));
		System.out.println("Result:" + h.sayHi());
		try {
			System.out.println("Result:" + h.testError());
		} catch (Throwable t) {

		}
		HelloRecorder hello = RegisterFactory.instanceOfHello();
		System.out.println("Result:" + hello.sayHelloTo("Lance"));
		System.out.println("Result:" + hello.sayHi());
		System.out.println("Result:" + hello.testError());
	}

}
