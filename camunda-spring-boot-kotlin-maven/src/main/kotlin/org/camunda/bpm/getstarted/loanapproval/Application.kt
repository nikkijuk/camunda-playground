package org.camunda.bpm.getstarted.loanapproval

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
@EnableProcessApplication // this is needed to enable process engine to start
class Application {

    @Autowired
    private val runtimeService: RuntimeService? = null // get handle to camunda bpm engine

    @EventListener
    private fun processPostDeploy(event: PostDeployEvent) {
        // runtimeService?.startProcessInstanceByKey("loanApproval") // ask camunda bpm to start named process with key
        runtimeService?.createProcessInstanceByKey("loanApproval")?.setVariables(hashMapOf("monthlyIncome" to 0L, "previousDebt" to 0L, "requestedLoan" to 0L) as Map<String, Any>?)?.execute()
    }
}

fun main(args: Array<String>) = runApplication<Application>(*args).let { Unit }
