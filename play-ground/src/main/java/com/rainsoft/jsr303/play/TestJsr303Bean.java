package com.rainsoft.jsr303.play;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

/**
 * Simple test for JSR 303 with Apache BVal 
 * @author Lance
 *
 */
public class TestJsr303Bean {

	@NotNull(message = "幹嘛啦")
	private String name;

	@NotNull(message = "test success")
	public String getName() {
		return name;
	}

	public TestJsr303Bean(@NotNull(message = "你在幹嘛") String name) {
		this.name = name;
	}

	public static final void main(String... args) {
		TestJsr303Bean b = new TestJsr303Bean(null);
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<TestJsr303Bean>> s = vf.getValidator()
				.validate(b);
		for (ConstraintViolation<TestJsr303Bean> constraintViolation : s) {
			System.out.println(constraintViolation.getMessage());
		}
	}
}
