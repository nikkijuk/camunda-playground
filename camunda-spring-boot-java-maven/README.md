# camunda BPM - Getting Started with camunda BPM and Spring Boot

## seed

Seed implementation of this repository is taken from the example Spring Boot application for the guide at [camunda.org](http://camunda.org/get-started/spring-boot.html).

## changes

Notable changes

- Got this from master
- Imported to Idea
- Corrected some warnings which maven 4 was givin
- Changed admin user to demo/demo in resources/application.yaml
- Commented startup process to WebappExampleProcessApplication

## notes

loanApproval process in recources/loanApproval.bpmn is unchanged

I guess it's ok that resources/META-INF/processes.xml is empty since process is started at WebappExampleProcessApplication using exact name of process

     runtimeService.startProcessInstanceByKey("loanApproval");

## process

there's just one process and you can look inside it
- get camunda modeler https://camunda.com/products/modeler/
- install it
- open resources/loanApproval.bpmn

It's not very fancy, but enough for now

## starting

You can then build the application with mvn clean install and then run it with java -jar command.

See logs to find out if engine is deployed and see it running at localhost:8080 with demo/demo

## examples

https://github.com/camunda/camunda-bpm-examples/tree/master/spring-boot-starter/example-web