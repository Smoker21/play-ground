package com.rainsoft.annotation.play;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A Test Recorder 
 * @author Lance
 *
 */
@Target(value={METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Recorder {
	
}
