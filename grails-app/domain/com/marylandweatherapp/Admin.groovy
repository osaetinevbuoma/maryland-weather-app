package com.marylandweatherapp

class Admin {
    String username
    String password

    static constraints = {
        username nullable: false, unique: true
        password nullable: false
    }
}
