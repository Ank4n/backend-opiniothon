package com.backend.opiniothon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.DemoEntity;

@RestController
@RequestMapping("/")
public class Demo {

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public DemoEntity demoApp(@RequestParam(value = "name", defaultValue = "Demo") String name) {
		return new DemoEntity(name);

	}
}
