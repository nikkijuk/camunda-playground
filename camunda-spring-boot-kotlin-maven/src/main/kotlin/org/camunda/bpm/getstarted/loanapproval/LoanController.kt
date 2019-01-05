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

    @GetMapping("/requestLoan")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        LoanRequest (id = runtimeService?.startProcessInstanceByKey("loanApproval")?.processInstanceId) // ask camunda bpm to start named process with key, return id of process

}