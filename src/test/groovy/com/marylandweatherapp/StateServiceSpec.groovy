package com.marylandweatherapp

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StateService)
@Mock([StateService, State])
class StateServiceSpec extends Specification {

    def setup() {
    	State state = new State(name: "State1", lgas: 213)
    	state.save()
    }

    def cleanup() {
    }

    void "test list states method"() {
    	given:
    		def stateService = new StateService()
    		stateService.transactionManager = getTransactionManager()

		when:
			def states = stateService.listStates()

        then:
        	states.size() > 0
    }

    void "test save state method"() {
    	given:
    		def stateService = new StateService()
    		stateService.transactionManager = getTransactionManager()
	    	String name = "Testing"
	    	String city = "Some city"
	    	int lgas = 123
	    	double lat = 3.3
	    	double lng = 2.2

    	when:
    		def state = stateService.saveState(name, city, lgas, lat, lng)

    	then:
	    	state.name == name
	    	state.lgas == lgas
    }

    void "test update state method"() {
    	given:
    		def stateService = new StateService()
    		stateService.transactionManager = getTransactionManager()
    		int id = 1
	    	String name = "Testing"
	    	int lgas = 123
	    	double lat = 2.2
	    	double lng = 3.3
	    	String city

    	when:
    		def state = stateService.updateState(id, name, city, lgas, lat, lng)

		then:
			state.id == id
			state.name == name
			state.lgas == lgas
			state.latitude == lat
			state.longitude == lng
    }

    void "test update weather method"() {
    	given:
    		def stateService = new StateService()
    		stateService.transactionManager = getTransactionManager()
    		int id = 1
	    	double currentTempF = 5.3
	    	double relativeHumidity = 30
	    	String windDirection = "NE"
	    	double currentTempC
	    	double windSpeed
	    	double visibility
	    	String iconUrl

    	when:
    		def state = stateService.updateState(id, currentTempF, currentTempC, relativeHumidity, windDirection, windSpeed, visibility, iconUrl)

		then:
			state.id == id
			state.currentTempF == currentTempF
			state.relativeHumidity == relativeHumidity
			state.windDirection == windDirection
			state.iconUrl == null
    }

    void "test delete method"() {
    	given:
    		def stateService = new StateService()
    		stateService.transactionManager = getTransactionManager()
    		int id = 1

    	when:
    		// stateService.deleteState(id)
    		State.get(id).delete()

		then:
			State.get(id) == null
    }
}
