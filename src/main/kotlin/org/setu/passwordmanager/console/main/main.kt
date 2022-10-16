package org.setu.passwordmanager.console.main

import org.setu.passwordmanager.console.controllers.PasswordManagerController
//import org.setu.passwordmanager.console.view.PasswordManagerUI
import tornadofx.*

fun main(args: Array<String>) {
    PasswordManagerController().start()
    //launch<UI>(args)
}

//class UI: App(PasswordManagerUI::class)