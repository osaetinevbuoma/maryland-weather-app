"use strict";

(function () {
    angular.module("WeatherAppFactory", []).
        factory("WeatherAppService", ["$http", function ($http) {

        return {
            /**
             * Fetch states from DB
             * @param callback  success callback containing state object
             */
            fetchStates: function (callback) {
                $http.get("/admin/listStates").then(callback);
            },
            /**
             * Save a state object
             * @param object    state object
             * @param success   success callback
             * @param error     error callback
             */
            saveState: function (object, success, error) {
                $http.post("/admin/saveState", object).then(success, error);
            },
            /**
             * Update a state object
             * @param object    state object
             * @param success   success callback
             * @param error     error callback
             */
            updateState: function (object, success, error) {
                $http.put("/admin/updateState", object).then(success, error);
            },
            /**
             * Update state's weather data
             * @param object    state's weather data
             * @param success   success callback
             * @param error     error callback
             */
            updateWeather: function (object, success, error) {
                $http.put("/admin/updateWeather", object).then(success, error);
            },
            /**
             * Delete a state
             * @param id        id of the state
             * @param success   success callback
             */
            deleteState: function (id, success) {
                $http.delete("/admin/deleteState/"+id).then(success);
            }
        };

    }]);
})();