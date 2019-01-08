package org.camunda.bpm.getstarted.loanapproval

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class LogVariables : JavaDelegate {

    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        val variables = execution.variables
        variables.keys.iterator().forEach{ key -> println("key $key, value ${variables.get(key)}") }
    }

}