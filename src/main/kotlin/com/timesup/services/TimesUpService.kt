package com.timesup.services

import com.timesup.dto.AddNewNameResponse
import com.timesup.dto.ValidateMot
import com.timesup.utils.NameList
import org.springframework.stereotype.Service

@Service
class TimesUpService() {

    fun addNewName(newName: String): AddNewNameResponse {
        NameList.addName(newName)
        println("New name added : $newName")
        return AddNewNameResponse("OK")
    }

    fun shuffle(): AddNewNameResponse {
        NameList.shuffle()
        println("Shuffled list : ${NameList.nameList}")
        return AddNewNameResponse("OK")
    }

    fun startNewRound(): AddNewNameResponse{
        return AddNewNameResponse(NameList.next())
    }

    fun found(valueFounded: String): String {
        println("Founded value : $valueFounded")
        return NameList.next()
    }

    fun validermots(values: List<ValidateMot>): String {
        val motOk = values.filter { it.ischeked }.map { it.nom }
        val motKo = values.filter { !it.ischeked }.map { it.nom }
        println("Mots tourves : $motOk")
        println("Mots non tourves : $motKo")
        motKo.forEach{NameList.pass(it)}

        return "OK"
    }

    fun chronoEnded(valueToPass: String): String {
        NameList.pass(valueToPass)
        return "OK"
    }
}