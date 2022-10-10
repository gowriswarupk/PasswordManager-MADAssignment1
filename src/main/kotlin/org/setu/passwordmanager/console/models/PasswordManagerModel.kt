package org.setu.passwordmanager.console.models

data class PasswordManagerModel(var id: Long = 0,
                                var category: String = "",
                                var name: String = "",
                                var username: String = "",
                                var password: String = "",
                                var notes: String = ""){
}
