package org.setu.passwordmanager.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PasswordManagerMemStore : PasswordManagerStore {

    val passwordmanagers = ArrayList<PasswordManagerModel>()

    override fun findAll(): List<PasswordManagerModel> {
        return passwordmanagers
    }

    override fun findOne(id: Long) : PasswordManagerModel? {
        var foundPasswordManager: PasswordManagerModel? = passwordmanagers.find { p -> p.id == id }
        return foundPasswordManager
    }

    override fun create(passwordmanager: PasswordManagerModel) {
        passwordmanager.id = getId()
        passwordmanagers.add(passwordmanager)
        logAll()
    }

    override fun update(passwordmanager: PasswordManagerModel) {
        var foundPasswordManager = findOne(passwordmanager.id!!)
        if (foundPasswordManager != null) {
            foundPasswordManager.category = passwordmanager.category
            foundPasswordManager.name = passwordmanager.name
            foundPasswordManager.username = passwordmanager.username
            foundPasswordManager.password = passwordmanager.password
            foundPasswordManager.notes = passwordmanager.notes
        }
    }
    override fun delete(passwordmanager: PasswordManagerModel) {
        passwordmanagers.remove(passwordmanager)
    }

    internal fun logAll() {
        passwordmanagers.forEach { logger.info("${it}") }
    }
}
