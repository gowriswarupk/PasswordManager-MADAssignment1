package org.setu.passwordmanager.console.models

import org.junit.jupiter.api.*
import org.junit.jupiter.*
import java.util.*
import kotlin.collections.ArrayList
import org.junit.jupiter.api.Assertions.*

class PasswordManagerMemStoreTest {

    val passwords = ArrayList<PasswordManagerModel>()

    @BeforeEach
    fun setUp(){
        val sampleData = PasswordManagerModel(
            2423423412413423,
            "tests",
            "mobileappdev",
            "20087165",
            "passw00rd!",
            "n/a"
        )
    }

    @BeforeEach
    fun tearDown(){

    }

    @Test
    fun create() {
        assertTrue(
            passwords.addAll(
                2423423412413423,
                "tests",
                "mobileappdev",
                "20087165",
                "passw00rd!",
                "n/a"
            )
        )
    }

    @Test
    fun update() {
        assertTrue(
            passwords.
        )
    }

    @Test
    fun delete() {
        assertTrue(
            passwords.delete()
        )
    }

    @Test
    fun findAll() {

    }


}