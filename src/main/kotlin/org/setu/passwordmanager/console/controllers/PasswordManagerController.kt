package org.setu.passwordmanager.console.controllers

import mu.KotlinLogging
import org.setu.passwordmanager.console.models.PasswordManagerJSONStore
import org.setu.passwordmanager.console.models.PasswordManagerMemStore
import org.setu.passwordmanager.console.models.PasswordManagerModel
import org.setu.passwordmanager.console.views.PasswordManagerView

class PasswordManagerController {

    val passwordmanagers = PasswordManagerJSONStore()

    val passwordManagerView = PasswordManagerView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Password Manager App" }
        println("PasswordManager Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Password Manager App" }
    }

    fun menu() :Int { return passwordManagerView.menu() }

    fun add(){
        var aPasswordManager = PasswordManagerModel()

        if (passwordManagerView.addPasswordManagerData(aPasswordManager))
            passwordmanagers.create(aPasswordManager)
        else
            logger.info("Password Not Added")
    }

    fun list() {
        passwordManagerView.listPasswordManagers(passwordmanagers)
    }

    fun update() {

        passwordManagerView.listPasswordManagers(passwordmanagers)
        var searchId = passwordManagerView.getId()
        val aPasswordManager = search(searchId)

        if(aPasswordManager != null) {
            if(passwordManagerView.updatePasswordManagerData(aPasswordManager)) {
                passwordmanagers.update(aPasswordManager)
                passwordManagerView.showPasswordManager(aPasswordManager)
                logger.info("Password Updated : [ $aPasswordManager ]")
            }
            else
                logger.info("Password Not Updated")
        }
        else
            println("Password Not Updated...")
    }

    fun search() {
        val aPasswordManager = search(passwordManagerView.getId())!!
        passwordManagerView.showPasswordManager(aPasswordManager)
    }

    fun delete() {
        passwordManagerView.listPasswordManagers(passwordmanagers)
        var searchId = passwordManagerView.getId()
        val aPasswordManager = search(searchId)

        if(aPasswordManager != null) {
            passwordmanagers.delete(aPasswordManager)
            println("Password Deleted...")
            passwordManagerView.listPasswordManagers(passwordmanagers)
        }
        else
            println("Password Not Deleted...")
    }

    fun search(id: Long) : PasswordManagerModel? {
        var foundPasswordManager = passwordmanagers.findOne(id)
        return foundPasswordManager
    }

    fun dummyData() {
        passwordmanagers.create(PasswordManagerModel(title = "New York New York", description = "So Good They Named It Twice"))
        passwordmanagers.create(PasswordManagerModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        passwordmanagers.create(PasswordManagerModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}