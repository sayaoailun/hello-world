package com.dc.js;

import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJS {

	public static void info() {
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory factory : factories) {
			System.out.printf(
					"Name: %s%n" + "Version: %s%n" + "Language name: %s%n" + "Language version: %s%n"
							+ "Extensions: %s%n" + "Mime types: %s%n" + "Names: %s%n",
					factory.getEngineName(), factory.getEngineVersion(), factory.getLanguageName(),
					factory.getLanguageVersion(), factory.getExtensions(), factory.getMimeTypes(), factory.getNames());
		}
	}

	public static void exec() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			Double hour = (Double) engine.eval("var date = new Date();" + "date.getHours();");
			String msg;
			if (hour < 10) {
				msg = "上午好";
			} else if (hour < 16) {
				msg = "下午好";
			} else if (hour < 20) {
				msg = "晚上好";
			} else {
				msg = "晚安";
			}
			System.out.printf("小时%s: %s%n", hour, msg);
		} catch (ScriptException e) {
			System.err.println(e);
		}
	}

	public static void exec1() {
		ScriptEngineManager manager = new ScriptEngineManager();
		// 建立javascript脚本引擎
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			// 将变量name和变量值abcdefg传给javascript脚本
			engine.put("name", "abcdefg");
			// 开始执行脚本
			engine.eval("var output ='' ;" + "for (i = 0; i <= name.length; i++) {"
					+ " output = name.charAt(i) + output" + "}");
			// 得到output变量的值
			String name = (String) engine.get("output");
			System.out.printf("被翻转后的字符串：%s", name);
		} catch (ScriptException e) {
			System.err.println(e);
		}
	}

	public static void compile() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		engine.put("counter", 0); // 向javascript传递一个参数
		// 判断这个脚本引擎是否支持编译功能
		if (engine instanceof Compilable) {
			Compilable compEngine = (Compilable) engine;
			try {
				// 进行编译
				CompiledScript script = compEngine.compile(
						"function count() { " + " counter = counter +1; " + " return counter; " + "}; count();");
				System.out.printf("Counter: %s%n", script.eval());
				System.out.printf("Counter: %s%n", script.eval());
				System.out.printf("Counter: %s%n", script.eval());
			} catch (ScriptException e) {
				System.err.println(e);
			}
		} else {
			System.err.println("这个脚本引擎不支持编译!");
		}
	}

	public static void invoke() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String name = "abcdefg";
		if (engine instanceof Invocable) {
			try {
				engine.eval("function reverse(name) {" + " var output =' ';" + " for (i = 0; i <= name.length; i++) {"
						+ " output = name.charAt(i) + output" + " } return output;}");
				Invocable invokeEngine = (Invocable) engine;
				Object o = invokeEngine.invokeFunction("reverse", name);
				System.out.printf("翻转后的字符串：%s", o);
			} catch (NoSuchMethodException e) {
				System.err.println(e);
			} catch (ScriptException e) {
				System.err.println(e);
			}
		} else {
			System.err.println("这个脚本引擎不支持动态调用");
		}
	}

	public static void run() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			engine.eval("function run() {print('异步调用');}");
			Invocable invokeEngine = (Invocable) engine;
			Runnable runner = invokeEngine.getInterface(Runnable.class);
			Thread t = new Thread(runner);
			t.start();
			t.join();
		} catch (InterruptedException e) {
			System.err.println(e);
		} catch (ScriptException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		// TestJS.info();
		// TestJS.exec();
		// TestJS.exec1();
		// TestJS.compile();
		// TestJS.invoke();
		TestJS.run();
	}

}
