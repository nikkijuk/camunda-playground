package com.jukkanikki.loanapproval

import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Rest api for requesting loan
 */
@RestController
class LoanController {

    @Autowired
    private val runtimeService: RuntimeService? = null // get handle to camunda bpm engine

    // example calls
    // z.b. localhost:8080/requestLoan?income=1000&loan=100 (debts defaults to zero)
    // z.b. localhost:8080/requestLoan?income=1000&debts=123&loan=100

    @GetMapping("/requestLoan")
    fun greeting(
            @RequestParam(value = "income", defaultValue = "0") income: Long,
            @RequestParam(value = "debts", defaultValue = "0") debts: Long,
            @RequestParam(value = "loan", defaultValue = "0") loan: Long
    ) =

    // variables that need to be set for process to run
    // monthlyIncome
    // previousDebt
    // requestedLoan


    // NOTE TO MYSELF: ONELINERS SHOULD BE LEFT TO STANDUP-COMEDIANS ..
        LoanRequest (id = runtimeService?.createProcessInstanceByKey("loanApproval")?.setVariables(hashMapOf("monthlyIncome" to income, "previousDebt" to debts, "requestedLoan" to loan) as Map<String, Any>?)?.execute()?.processInstanceId) // ask camunda bpm to start named process with key, return id of process

}