package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication // this annotation enables camunda addon to start process engine
public class WebappExampleProcessApplication {

	@Autowired
	private RuntimeService runtimeService; // get handle to camunda bpm engine

	public static void main(String... args) {
		SpringApplication.run(WebappExampleProcessApplication.class, args);
	}

	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		runtimeService.startProcessInstanceByKey("loanApproval"); // ask camunda bpm to start named process with key
	}
}