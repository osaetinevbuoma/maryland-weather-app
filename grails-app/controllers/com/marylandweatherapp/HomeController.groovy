package com.marylandweatherapp

import grails.converters.JSON

class HomeController {

    static allowedMethods = [saveData: "POST"]

    StateService stateService

    /**
     * Display the map of Nigeria
     */
    def index() { }

    /**
     * Load state data to the map
     */
    def loadData() {
        List<State> states = stateService.listStates()

        withFormat {
            json { render states as JSON }
        }
    }

    /**
     * Save weather data of states
     */
    def saveData() {
        State state = stateService.updateState(Integer.valueOf(params.id), 
            Double.valueOf(params.currentTempF), Double.valueOf(params.currentTempC), 
            params.relativeHumidity, params.windDirection,
            Double.valueOf(params.windSpeed), Double.valueOf(params.visibility), 
            params.iconUrl)

        withFormat {
            json { render state as JSON }
        }
    }
}
