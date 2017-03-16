package com.marylandweatherapp

class State {
    String name
    String city
    int lgas
    double latitude
    double longitude
    double currentTempF
    double currentTempC
    String iconUrl
    double relativeHumidity
    String windDirection
    double windSpeed
    double visibility
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, unique: true
        city nullable: true, unique: true
        lgas nullable: true
        latitude nullable: true
        longitude nullable: true
        currentTempF nullable :true
        currentTempC nullable: true
        iconUrl nullable: true
        relativeHumidity nullable: true
        windDirection nullable: true
        windSpeed nullable: true
        visibility nullable: true
    }

    static mapping = {
        autoTimestamp: true
    }
}
