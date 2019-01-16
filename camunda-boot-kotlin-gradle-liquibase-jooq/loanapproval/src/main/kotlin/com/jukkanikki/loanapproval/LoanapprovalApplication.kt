package com.jukkanikki.loanapproval

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.context.event.EventListener

/**
 * Main class for spring boot application
 *
 * note: process engine enabled using @EnableProcessApplication annotation
 */
@SpringBootApplication
@EnableProcessApplication // this is needed to enable process engine to start
class LoanapprovalApplication {

	@Autowired
	private val runtimeService: RuntimeService? = null // get handle to camunda bpm engine

	@EventListener
	private fun processPostDeploy(event: PostDeployEvent) {
		// starts process with defaults (all zero)
		// this is just to test manually
		runtimeService?.createProcessInstanceByKey("loanApproval")?.setVariables(hashMapOf("monthlyIncome" to 0L, "previousDebt" to 0L, "requestedLoan" to 0L) as Map<String, Any>?)?.execute()
	}
}

// main for running spring boot
fun main(args: Array<String>) = runApplication<LoanapprovalApplication>(*args).let { Unit }

//fun main(args: Array<String>) {
//	runApplication<LoanapprovalApplication>(*args)
//}

