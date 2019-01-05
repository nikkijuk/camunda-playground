# camunda BPM - Getting Started with camunda BPM and Spring Boot

## seed

Seed implementation of this repository is taken from https://docs.camunda.org/get-started/spring-boot/

additional kotlin part was taken partially from https://github.com/holunda-io/camunda-springboot-kotlin

But I needed also some tips from here to get it running https://www.baeldung.com/spring-boot-kotlin

## changes

Notable changes

- Changed admin user to demo/demo in resources/application.yaml
- WebappExampleProcessApplication.java converted to Application.tk
- LoanController added to support get request to /requestLoan
- LoanRequest added to serialize result of process initialization (returns id)
- pom.xml modified heavily - not certain if all changes are relevant
- important: repackage didn't find main class - result: app can't be started with java -jar

## notes

loanApproval process in recources/loanApproval.bpmn is unchanged

I guess it's ok that resources/META-INF/processes.xml is empty since process is started at WebappExampleProcessApplication using exact name of process

     runtimeService?.startProcessInstanceByKey("loanApproval");

Additional question mark is needed since kotlin can't be sure that Spring finds instance at runtime - so, reference might be null

## see process

there's just one process and you can look inside it
- get camunda modeler https://camunda.com/products/modeler/
- install it
- open resources/loanApproval.bpmn

It's not very fancy, but enough for now

## run and test

You can then build the application with mvn clean install and then run it with mvn spring-boot:run command.

note: spring-boot:run is ok at development time, but not at production, so here might be some problems waiting

See logs to find out if engine is deployed and see it running at localhost:8080 with demo/demo

Try also to send requests to  localhost:8080/requestLoan, result should be something like id: "6be0e2e5-1140-11e9-95f7-9cb6d0f48aa2"

impotant to look for:
- there should be 1 process definition deployed, and at the very start one process instance started (one human task waiting)
- there should be one process instance, i.e. one human task, for each get request to /requestLoan