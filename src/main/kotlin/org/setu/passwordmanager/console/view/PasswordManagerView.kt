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
        println(" 6. Find using Name") //in progress
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
        print("Enter a Category (email, finance, education, etc) : ")
        passwordmanager.category = readLine()!!
        print("Enter the name for the service (Google, Moodle, etc): ")
        passwordmanager.name = readLine()!!
        print("Enter your username: ")
        passwordmanager.username = readLine()!!
        print("Enter the password: ")
        passwordmanager.password = readLine()!!
        print("Enter additional notes (Leave Blank if not required): ")
        passwordmanager.notes = readLine()!!

        return passwordmanager.category.isNotEmpty() && passwordmanager.name.isNotEmpty() && passwordmanager.username.isNotEmpty() && passwordmanager.password.isNotEmpty()
    }

    fun updatePasswordManagerData(passwordmanager : PasswordManagerModel) : Boolean {

        val tempCategory: String?
        val tempName: String?
        val tempUsername: String?
        val tempPassword: String?
        val tempNotes: String?

        if (passwordmanager != null) {
            print("Enter a new Category for [ " + passwordmanager.category + " ] : ")
            tempCategory = readLine()!!
            print("Enter a new Source Name for [ " + passwordmanager.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new User Name for [ " + passwordmanager.username + " ] : ")
            tempUsername = readLine()!!
            print("Enter a new Password for [ " + passwordmanager.password + " ] : ")
            tempPassword = readLine()!!
            print("Enter any Notes for [ " + passwordmanager.notes + " ] (Leave Blank if not required): ")
            tempNotes = readLine()!!

            if (!tempCategory.isNullOrEmpty() && !tempName.isNullOrEmpty() && !tempUsername.isNullOrEmpty() && !tempPassword.isNullOrEmpty()) {
                passwordmanager.category = tempCategory
                passwordmanager.name = tempName
                passwordmanager.username = tempUsername
                passwordmanager.password = tempPassword
                passwordmanager.notes = tempNotes
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

    fun getCategory() : String {
        var strId : String? // String to hold user input
        var searchCat : String // Long to hold converted id
        print("Enter the website / name to find faster : ")
        strId = readLine()!!
        searchCat = if (strId != null && !strId.isEmpty())
            strId
        else
            (-9).toString()
        return searchCat
    }
}