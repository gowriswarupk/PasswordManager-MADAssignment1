package org.setu.passwordmanager.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.setu.passwordmanager.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "passwordmanagersavefile.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PasswordManagerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PasswordManagerJSONStore : PasswordManagerStore {

    var passwordmanagers = mutableListOf<PasswordManagerModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PasswordManagerModel> {
        return passwordmanagers
    }

    override fun findOne(id: Long) : PasswordManagerModel? {
        var foundPasswordManager: PasswordManagerModel? = passwordmanagers.find { p -> p.id == id }
        return foundPasswordManager
    }

    fun findCat(category: String) : PasswordManagerModel? {
        var foundPasswordManager: PasswordManagerModel? = passwordmanagers.find { p -> p.category == category }
        return foundPasswordManager
    }

    override fun create(passwordmanager: PasswordManagerModel) {
        passwordmanager.id = generateRandomId()
        passwordmanagers.add(passwordmanager)
        serialize()
    }

    override fun update(passwordmanager: PasswordManagerModel) {
        var foundPasswordManager = findOne(passwordmanager.id!!)
        if (foundPasswordManager != null) {
            foundPasswordManager.category = passwordmanager.category
            foundPasswordManager.name = passwordmanager.name
            foundPasswordManager.username = passwordmanager.username
            foundPasswordManager.password = passwordmanager.password
            foundPasswordManager.notes= passwordmanager.notes
        }
        serialize()
    }

    override fun delete(passwordmanager: PasswordManagerModel) {
        passwordmanagers.remove(passwordmanager)
        serialize()
    }

    internal fun logAll() {
        passwordmanagers.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(passwordmanagers, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        passwordmanagers = Gson().fromJson(jsonString, listType)
    }
}