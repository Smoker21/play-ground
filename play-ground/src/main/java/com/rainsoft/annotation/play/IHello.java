package com.rainsoft.annotation.play;

public interface IHello {
	@Recorder
	String sayHelloTo(String name);
	
	@Recorder
	String sayHi();
	
	@Recorder
	String testError();
}
