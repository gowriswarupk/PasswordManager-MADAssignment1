package org.setu.passwordmanager.console.models

interface PasswordManagerStore {
    fun findAll(): List<PasswordManagerModel>
    fun findOne(id: Long): PasswordManagerModel?
    fun create(passwordmanager: PasswordManagerModel)
    fun update(passwordmanager: PasswordManagerModel)
    fun delete(passwordmanager: PasswordManagerModel)
}
