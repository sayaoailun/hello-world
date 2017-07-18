package com.dc.springboot;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoService {

	@Resource(name = "demoDAO")
	private DemoDAO dao;

	public String hello() {
		return dao.hello();
	}

}
