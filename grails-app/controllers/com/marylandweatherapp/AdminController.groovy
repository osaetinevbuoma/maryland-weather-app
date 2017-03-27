package com.marylandweatherapp

import grails.converters.JSON

class AdminController {

    static allowedMethods = [saveState: "POST", updateState: "PUT", updateWeather: "PUT",
                             deleteState: "DELETE"]

    StateService stateService

    /**
     * Display admin log in page
     */
    def index() { }

    /**
     * Authenticate admin
     *
     * @param username      admin username
     * @param password      admin password
     */
    def authenticate(String username, String password) {
        Admin admin = Admin.findByUsernameAndPassword(username, password)
        if (!admin) {
            flash.error = "Wrong username/password combination"
            redirect([action: "index"])
        }

        session.admin = admin
        redirect([action: "dashboard"])
    }

    /**
     * Logout
     */
    def logout() {
        session.admin = null
        redirect([action: "index"])
    }

    /**
     * Display dashboard
     */
    def dashboard() {
        checkAdmin()
    }

    /**
     * List states in Nigeria
     */
    def listStates() {
        checkAdmin()

        List states = stateService.listStates()

        withFormat {
            json { render states as JSON }
        }
    }

    /**
     * Save a state object
     */
    def saveState() {
        checkAdmin()

        State state = stateService.saveState(request.JSON.name, request.JSON.city,
                Integer.valueOf(request.JSON.lgas), Double.valueOf(request.JSON.latitude),
                Double.valueOf(request.JSON.longitude))

        withFormat {
            json { render state as JSON }
        }
    }

    /**
     * Update a state object
     */
    def updateState() {
        checkAdmin()

        State state = stateService.updateState(Integer.valueOf(request.JSON.id), request.JSON.name,
                request.JSON.city, Integer.valueOf(request.JSON.lgas), Double.valueOf(request.JSON.latitude),
                Double.valueOf(request.JSON.longitude))

        withFormat {
            json { render state as JSON }
        }
    }

    /**
     * Update state weather information
     */
    def updateWeather() {
        checkAdmin()

        State state = stateService.updateState(Integer.valueOf(request.JSON.id), Double.valueOf(request.JSON.currentTempF),
                Double.valueOf(request.JSON.currentTempC), request.JSON.relativeHumidity,
                request.JSON.windDirection, Double.valueOf(request.JSON.windSpeed), Double.valueOf(request.JSON.visibility),
                request.JSON.iconUrl)

        withFormat {
            json { render state as JSON }
        }
    }

    /**
     * Delete a state
     */
    def deleteState() {
        checkAdmin()

        stateService.deleteState(Integer.valueOf(params.id))
        List ok = ["OK"]

        withFormat {
            json { render ok as JSON }
        }
    }

    /**
     * Download weather data of states as xls
     */
    def downloadFile() {
        checkAdmin()

        File file = stateService.generateExcelFile(params.id)
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=\"${file.name}\"")
        response.outputStream << file.bytes
    }

    /**
     * Check if admin is signed in
     */
    private checkAdmin() {
        if (!session.admin) {
            redirect([action: "index"])
        }
    }
}
