package com.dc.springboot;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("demo")
public class DemoController {

	@Resource(name = "demoService")
	private DemoService service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	String hello() {
		return service.hello();
	}

}
