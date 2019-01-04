# camunda BPM - Getting Started with camunda BPM and Spring Boot

## seed

Seed implementation of this repository is taken from https://docs.camunda.org/get-started/spring-boot/

## changes

Notable changes

- Got this from master and imported to Idea
- Corrected some warnings which maven 4 was giving (I guess not important ones, but still)
- Changed admin user to demo/demo in resources/application.yaml
- Commented startup process to WebappExampleProcessApplication (functionality not changed)

## notes

loanApproval process in recources/loanApproval.bpmn is unchanged

I guess it's ok that resources/META-INF/processes.xml is empty since process is started at WebappExampleProcessApplication using exact name of process

     runtimeService.startProcessInstanceByKey("loanApproval");

## see process

there's just one process and you can look inside it
- get camunda modeler https://camunda.com/products/modeler/
- install it
- open resources/loanApproval.bpmn

It's not very fancy, but enough for now

## run and test

You can then build the application with mvn clean install and then run it with java -jar command.

See logs to find out if engine is deployed and see it running at localhost:8080 with demo/demo