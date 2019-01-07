package org.camunda.bpm.getstarted.loanapproval

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate


class ToUppercase : JavaDelegate {

    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        val input = execution.getVariable("input") as String
        execution.setVariable("input", input.toUpperCase())
    }

}

class ResolveCreditRating : JavaDelegate {

    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        val name = execution.getVariable("name") as String
        val rating = "4"
        execution.setVariable("rating", rating)
    }

}