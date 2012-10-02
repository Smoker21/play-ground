package com.rainsoft.play;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;

/**
 * This is a simple MVEL test class. 
 * 
 */
public class App {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		String express = "age > 40";
		Map<String, Number> vars = new HashMap<String, Number>();
		vars.put("age", 60);
		MVEL.eval(express, vars);
		System.out.println(MVEL.eval(express, vars));
		Object o = MVEL.compileExpression(express);
		Type[] types = o.getClass().getInterfaces();
		for (int i = 0; i < types.length; i++) {
			System.out.println(types[i]);
		}
		ExecutableStatement stmt = (ExecutableStatement) o;
		HashMap a = new HashMap();
		String script = "var colors = {'red', 'green', 'blue'}; "
				+ "var a = 'Who cares'; " + " foreach (c : colors) { "
				+ "     System.out.println(c + '!'); " + "}";
		// In this case variable colors and a will store in hashMap a

		System.out.println(MVEL.eval(script, a));
		// In this case MVEL return a HashMap s
		Object s = MVEL.eval(
				"{\"Company\":'Rainty',\"What\":'Soft',\"name\":'Test'}", a);
		System.out.println(s);
		Object result = MVEL.eval("age > 40;", new HashMap() {
			{
				put("age", 43);
			}
		});
		System.out.println(result);
	}
	
	static class Person {
		
	}
}
