package org.camunda.bpm.getstarted.loanapproval

/**
 * Simple container for loan request response, contains only id of started loan request process
 */
data class LoanRequest(val id: String? = "")