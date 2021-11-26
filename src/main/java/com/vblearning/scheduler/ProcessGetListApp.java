package com.vblearning.scheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.vblearning.scheduler")
@EnableScheduling
public class ProcessGetListApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProcessGetListApp.class);
		// SpringApplication.run(VbMyspringBasicApplication.class, args);
		Scheduler scheduler = ac.getBean(Scheduler.class);
		scheduler.fixedRateSch();
		// ac.close();
	}

}
