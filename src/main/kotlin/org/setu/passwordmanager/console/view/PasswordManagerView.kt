package org.setu.passwordmanager.console.views

import org.setu.passwordmanager.console.models.PasswordManagerJSONStore
import org.setu.passwordmanager.console.models.PasswordManagerMemStore
import org.setu.passwordmanager.console.models.PasswordManagerModel

class PasswordManagerView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Password")
        println(" 2. Update Password")
        println(" 3. List All Passwords")
        println(" 4. Search Passwords")
        println(" 5. Delete Password")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listPasswordManagers(passwordmanagers : PasswordManagerJSONStore) {
        println("List All Passwords")
        println()
        passwordmanagers.logAll()
        println()
    }

    fun showPasswordManager(passwordmanager : PasswordManagerModel) {
        if(passwordmanager != null)
            println("Password Details [ $passwordmanager ]")
        else
            println("Password Not Found...")
    }

    fun addPasswordManagerData(passwordmanager : PasswordManagerModel) : Boolean {

        println()
        print("Enter a Title : ")
        passwordmanager.title = readLine()!!
        print("Enter a Description : ")
        passwordmanager.description = readLine()!!

        return passwordmanager.title.isNotEmpty() && passwordmanager.description.isNotEmpty()
    }

    fun updatePasswordManagerData(passwordmanager : PasswordManagerModel) : Boolean {

        val tempTitle: String?
        val tempDescription: String?

        if (passwordmanager != null) {
            print("Enter a new Title for [ " + passwordmanager.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + passwordmanager.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                passwordmanager.title = tempTitle
                passwordmanager.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}