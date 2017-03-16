"use strict";

(function () {
    angular.module("WeatherAppControllers", []).
        controller("StateManagementController", ["$scope", "WeatherAppService", function ($scope, WeatherAppService) {

        $scope.currentPage = 0; // current page viewed - pagination
        $scope.limit = 10; // number of items to show per page
        $scope.search = ""; // filter search query
        $scope.states = []; // an array of state objects
        $scope.edit = false; // hide edit form
        $(".add-dialog").hide(); // hide dialog
        $(".delete-dialog").hide(); // hide dialog

        // Fetch states from DB
        WeatherAppService.fetchStates(function (states) {
            $scope.states = states.data;
            $scope.totalPages = Math.ceil(states.data.length/$scope.limit);
        });

        /**
         * Display add state dialog
         */
        $scope.addState = function () {
            $(".add-dialog").dialog({
                title: "Add State",
                modal: true,
                closeOnEscape: true,
                draggable: false,
                resizable: false,
                height: "auto",
                position: {
                    my: "center top+15%",
                    at: "center top+15%",
                    of: window
                },
                show: "puff",
                hide: "puff",
                buttons: {
                    Close: function () {
                        $(this).find(".js-save-loading").html("");
                        $(this).find(".js-save-form input").val("");
                        $(this).dialog("close");
                    }
                }
            });
        };

        /**
         * Save a state object
         */
        $scope.saveState = function () {
            var object = {
                name: $scope.saveName,
                city: $scope.saveCity,
                lgas: parseInt($scope.saveLgas),
                latitude: $scope.saveLat,
                longitude: $scope.saveLng
            };

            $(".js-save-form input").attr({ disabled: true });
            $(".js-save-form button").attr({ disabled: true });
            $(".js-save-loading").html("<i class='fa fa-spinner fa-pulse'></i>");

            WeatherAppService.saveState(object, function (data) {
                $(".js-save-form input").attr({ disabled: false });
                $(".js-save-form button").attr({ disabled: false });
                $(".js-save-loading").html("<i class='text-success'>Saved. Add more?</i>");

                $scope.saveName = null;
                $scope.saveCity = null;
                $scope.saveLgas = null;
                $scope.saveLat = null;
                $scope.saveLng = null;
                $scope.states.push(data.data);
            }, function (error) {
                $(".js-save-form input").attr({ disabled: false });
                $(".js-save-form button").attr({ disabled: false });
                $(".js-save-loading").html("<i class='text-danger'>Error occurred while updating state</i>");
            });
        };

        /**
         * Display edit form with fields populated
         * @param index array index of state object
         */
        $scope.editState = function (index) {
            $scope.edit = true;
            $scope.editName = $scope.states[index].name;
            $scope.editCity = $scope.states[index].city;
            $scope.editLgas = $scope.states[index].lgas;
            $scope.editLat = $scope.states[index].latitude;
            $scope.editLng = $scope.states[index].longitude;
            $scope.editId = $scope.states[index].id;
            $scope.editIndex = index;
        };

        /**
         * Update state object by saving in database
         * Update states scope array with updated object
         */
        $scope.updateState = function () {
            $(".js-update-form input").attr({ disabled: true });
            $(".js-update-form button").attr({ disabled: true });
            $(".js-update-loading").html("<i class='fa fa-spinner fa-pulse'></i>");

            var object = {
                id: $scope.editId,
                name: $scope.editName,
                city: $scope.editCity,
                lgas: $scope.editLgas,
                latitude: $scope.editLat,
                longitude: $scope.editLng
            };

            WeatherAppService.updateState(object, function (data) {
                $(".js-update-form input").attr({ disabled: false });
                $(".js-update-form button").attr({ disabled: false });
                $(".js-update-loading").html("");

                $scope.states[$scope.editIndex].name = $scope.editName;
                $scope.states[$scope.editIndex].city = $scope.editCity;
                $scope.states[$scope.editIndex].lgas = $scope.editLgas;
                $scope.states[$scope.editIndex].latitude = $scope.editLat;
                $scope.states[$scope.editIndex].longitude = $scope.editLng;
                $scope.edit = false;
            }, function (error) {
                $(".js-update-form input").attr({ disabled: false });
                $(".js-update-form button").attr({ disabled: false });
                $(".js-update-loading").html("<i class='text-danger'>Error occurred while updating state</i>");
            });
        };

        /**
         * Close edit form
         */
        $scope.closeEditForm = function () {
            $scope.edit = false;
        };

        /**
         * Delete a state
         * @param index     index of state object in $scope.states array
         */
        $scope.deleteState = function (index) {
            $scope.stateName = $scope.states[index].name;

            $(".delete-dialog").dialog({
                title: "Delete Confirmation",
                modal: true,
                closeOnEscape: true,
                draggable: false,
                resizable: false,
                height: "auto",
                position: {
                    my: "center top+15%",
                    at: "center top+15%",
                    of: window
                },
                show: "puff",
                hide: "puff",
                buttons: {
                    Delete: function () {
                        WeatherAppService.deleteState($scope.states[index].id, function () {
                            $scope.states.splice(index, 1);
                        });
                        $(this).dialog("close");
                    },
                    Close: function () {
                        $(this).dialog("close");
                    }
                }
            });
        };

    }]);
})();