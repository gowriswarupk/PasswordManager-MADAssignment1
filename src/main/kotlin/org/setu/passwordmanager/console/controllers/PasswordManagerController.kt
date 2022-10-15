package org.setu.passwordmanager.console.controllers

import jdk.jfr.Category
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
        println("PasswordManager Kotlin App Version 1.2")
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
                6 -> filter()
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

    fun search(id: Long) : PasswordManagerModel? {
        var foundPasswordManager = passwordmanagers.findOne(id)
        return foundPasswordManager
    }

    fun catsearch(category: String) : PasswordManagerModel? {
        var foundPasswordManager = passwordmanagers.findCat(category)
        return foundPasswordManager
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

    fun filter(){
        val aPasswordManager = catsearch(passwordManagerView.getCategory())!!
        passwordManagerView.showPasswordManager(aPasswordManager)
    }



    fun dummyData() {
        passwordmanagers.create(PasswordManagerModel(category = "email", name = "gmail.com", username = "gowriswarupk@gmail.com", password= "123456@mail!", notes = "security codes: 002020102  10102302310  102430042034" ))
        passwordmanagers.create(PasswordManagerModel(category = "shopping", name = "ebay.com", username = "kgs@gmail.com", password= "ebayPassw0rd!", notes = "Purchase history updated?"))
        passwordmanagers.create(PasswordManagerModel(category = "email", name = "gmail.com", username = "20087165@mail.wit.ie", password= "mailpassword@@", notes = "security codes: 087983475345  3454353459  8235928759"))
    }
}