package marylandweatherapp

import com.marylandweatherapp.Admin
import com.marylandweatherapp.State

class BootStrap {

    def init = { servletContext ->
        new Admin(username: "admin", password: "admin").save(flush: true)

        new State(name: "Kano", lgas: 51, latitude: 11.761188, longitude: 8.525929).save flush: true
        new State(name: "Lagos", lgas: 45, latitude: 6.546861, longitude: 3.290361).save flush: true
        new State(name: "Jigawa", lgas: 40, latitude: 12.316797, longitude: 9.654513).save flush: true
        new State(name: "Borno", lgas: 40, latitude: 11.977642, longitude: 13.275013).save flush: true
        new State(name: "Katsina", lgas: 40, latitude: 12.660923, longitude: 7.558504).save flush: true
        new State(name: "Kaduna", lgas: 40, latitude: 10.494342, longitude: 7.791574).save flush: true
        new State(name: "Niger", lgas: 35, latitude: 9.844666, longitude: 5.57108).save flush: true
        new State(name: "Kogi", lgas: 35, latitude: 7.882319, longitude: 6.397751).save flush: true
        new State(name: "Sokoto", lgas: 40, latitude: 13.176207, longitude: 5.404249).save flush: true
        new State(name: "Yobe", lgas: 30, latitude: 12.440596, longitude: 11.528813).save flush: true
        new State(name: "Zamfara", lgas: 30, latitude: 12.14775, longitude: 6.333568).save flush: true
        new State(name: "Kwara", lgas: 35, latitude: 8.603696, longitude: 4.836899).save flush: true
        new State(name: "Adamawa", lgas: 35, latitude: 9.449994, longitude: 12.336487).save flush: true
        new State(name: "Taraba", lgas: 30, latitude: 7.965071, longitude: 10.611363).save flush: true
        new State(name: "Benue", lgas: 30, latitude: 7.402394, longitude: 8.777689).save flush: true
        new State(name: "Plateau", lgas: 30, latitude: 8.994308, longitude: 9.618483).save flush: true
        new State(name: "Ondo", lgas: 37, latitude: 6.799543, longitude: 4.823791).save flush: true
        new State(name: "Ogun", lgas: 36, latitude: 7.022632, longitude: 3.316519).save flush: true
        new State(name: "Edo", lgas: 37, latitude: 6.780523, longitude: 6.187509).save flush: true
        new State(name: "Delta", lgas: 37, latitude: 5.452163, longitude: 5.946445).save flush: true
        new State(name: "Ekiti", lgas: 28, latitude: 7.697777, longitude: 5.312719).save flush: true
        new State(name: "Imo", lgas: 20, latitude: 5.50996, longitude: 7.047582).save flush: true
        new State(name: "Anambra", lgas: 27, latitude: 6.112734, longitude: 6.962771).save flush: true
        new State(name: "Enugu", lgas: 20, latitude: 6.511938, longitude: 7.388298).save flush: true
        new State(name: "Ebonyi", lgas: 14, latitude: 6.265731, longitude: 8.001858).save flush: true
        new State(name: "Bayelsa", lgas: 14, latitude: 4.767964, longitude: 6.038319).save flush: true
        new State(name: "Akwa Ibom", lgas: 12, latitude: 4.873172, longitude: 7.810395).save flush: true
        new State(name: "Rivers", lgas: 24, latitude: 4.962613, longitude: 6.941695).save flush: true
        new State(name: "Oyo", lgas: 49, latitude: 8.166674, longitude: 3.536703).save flush: true
        new State(name: "Osun", lgas: 36, latitude: 7.50651, longitude: 4.545603).save flush: true
        new State(name: "Cross River", lgas: 15, latitude: 5.586943, longitude: 8.391459).save flush: true
        new State(name: "Kebbi", lgas: 25, latitude: 11.521516, longitude: 4.069754).save flush: true
        new State(name: "Gombe", lgas: 20, latitude: 10.396391, longitude: 11.231048).save flush: true
        new State(name: "Nassarawa", lgas: 24, latitude: 8.502352, longitude: 8.078848).save flush: true
        new State(name: "Abia", lgas: 17, latitude: 5.443824, longitude: 7.504366).save flush: true
        new State(name: "Bauchi", lgas: 17, latitude: 10.40009, longitude: 9.708599).save flush: true
    }
    def destroy = {
    }
}
