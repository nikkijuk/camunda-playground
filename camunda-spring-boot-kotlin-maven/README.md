# camunda BPM - Getting Started with camunda BPM and Spring Boot

## seed

Seed implementation of this repository is taken from https://docs.camunda.org/get-started/spring-boot/

additional kotlin part was taken partially from https://github.com/holunda-io/camunda-springboot-kotlin

But I needed also some tips from here to get it running https://www.baeldung.com/spring-boot-kotlin

## changes

Notable changes

- Changed admin user to demo/demo in resources/application.yaml
- WebappExampleProcessApplication.java converted to Application.tk
- pom.xml modified heavily - not certain if all changes are relevant

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

You can then build the application with mvn clean install and then run it with mvn spring-boot:run command.

See logs to find out if engine is deployed and see it running at localhost:8080 with demo/demo

impotant to look for: there should be 1 process definition deployed