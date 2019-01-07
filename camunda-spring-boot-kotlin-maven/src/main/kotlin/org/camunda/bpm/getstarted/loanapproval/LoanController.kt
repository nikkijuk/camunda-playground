package org.camunda.bpm.getstarted.loanapproval

import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoanController {

    @Autowired
    private val runtimeService: RuntimeService? = null // get handle to camunda bpm engine

    // monthlyIncome
    // previousDebt
    // requestedLoan
    @GetMapping("/requestLoan")
    fun greeting(
            @RequestParam(value = "income", defaultValue = "0") income: String,
            @RequestParam(value = "debts", defaultValue = "0") debts: String,
            @RequestParam(value = "loan", defaultValue = "0") loan: String
    ) =
        //    LoanRequest (id = runtimeService?.startProcessInstanceByKey("loanApproval")?.processInstanceId) // ask camunda bpm to start named process with key, return id of process

        // NOTE TO MYSELF: ONELINERS SHOULD BE LEFT TO STANDUP-COMEDIANS ..
        LoanRequest (id = runtimeService?.createProcessInstanceByKey("loanApproval")?.setVariables(hashMapOf("monthlyIncome" to income, "previousDebt" to debts, "requestedLoan" to loan) as Map<String, Any>?)?.execute()?.processInstanceId) // ask camunda bpm to start named process with key, return id of process

}