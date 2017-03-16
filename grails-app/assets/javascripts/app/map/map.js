/**
 * Created by oevbuoma on 3/15/17.
 */

"use strict";

(function () {

    $(document).ready(function () {

        const ACCESS_TOKEN = map_config.access_token;
        const ID = map_config.id;
        const map = L.map("map").setView([map_config.latitude, map_config.longitude], map_config.min_zoom);
        var geojson;
        const info = L.control();

        info.onAdd = function (map) {
            this._div = L.DomUtil.create('div', 'info'); // create a div with a class "info"
            this.update();
            return this._div;
        };

        // method that we will use to update the control based on feature properties passed
        info.update = function (props) {
            this._div.innerHTML = '<h4>State</h4>' +  (props ?
                '<b>' + props.state + '</b>'
                    : 'Hover over a state');
        };

        info.addTo(map); // add control to map

        // Set tile layer of map
        L.tileLayer("https://api.mapbox.com/styles/v1/mapbox/streets-v10/tiles/256/{z}/{x}/{y}?access_token={accessToken}", {
            minZoom: map_config.min_zoom,
            maxZoom: map_config.max_zoom,
            attribution: "Imagery © <a href='http://mapbox.com'>Mapbox</a>",
            id: ID,
            accessToken: ACCESS_TOKEN
        }).addTo(map);

        // Style map
        function style(feature) {
            return {
                weight: 1,
                opacity: 9,
                color: "#888888",
                dashArray: "3"
            };
        }

        // on hovering a state highlight the state
        function highlightFeature(e) {
            var layer = e.target;

            layer.setStyle({
                weight: 1,
                color: "transparent",
                dashArray: "3",
                fillOpacity: 0
            });

            if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
                layer.bringToFront();
            }

            info.update(layer.feature.properties);
        }

        // reset state when not hovering
        function resetHighlight(e) {
            geojson.resetStyle(e.target);
            info.update();
        }

        // Clicking on a state gives information about the state
        // and some weather forecast from Underground API
        // State information is cached for 2 hours
        function clickToFeature(e) {
            var state = e.target.feature.properties.state;
            var latitude = e.target.feature.properties.latitude;
            var longitude = e.target.feature.properties.longitude;
            var city = e.target.feature.properties.city;
            var id = e.target.feature.properties.id;
            var lgas = e.target.feature.properties.lgas;

            localStorage.clear();

            if (localStorage.hasOwnProperty(state) && new Date(JSON.parse(localStorage[state]).expiry) > new Date()) {
                // display info in dialog
                displayStateInformation(JSON.parse(localStorage[state]));
            } else {
                // fetch weather data from underground
                if (latitude && longitude) {
                    // Display processing modal
                    $(".info-dialog").html("<h3 align='center'>Fetching data... <i class='fa fa-spinner fa-pulse'></i></h3>");
                    $(".info-dialog").dialog({
                        title: "Fetching Data",
                        modal: true,
                        closeOnEscape: true,
                        draggable: false,
                        resizable: false,
                        height: "auto"
                    });

                    var latlng = latitude+","+longitude;
                    $.ajax({
                        url: "http://api.wunderground.com/api/"+map_config.wunderground_key+"/geolookup/conditions/forecast/q/"+latlng+".json",
                        method: "GET",
                        success: function (data) {
                            // state's weather forecast
                            var forecast = [];
                            for (var i = 0; i < data.forecast.simpleforecast.forecastday.length; i++) {
                                var weather = {
                                    day: data.forecast.simpleforecast.forecastday[i].date.weekday,
                                    temp: {
                                        high: {
                                            celsius: data.forecast.simpleforecast.forecastday[i].high.celsius,
                                            fahrenheit: data.forecast.simpleforecast.forecastday[i].high.fahrenheit
                                        },
                                        low: {
                                            celsius: data.forecast.simpleforecast.forecastday[i].low.celsius,
                                            fahrenheit: data.forecast.simpleforecast.forecastday[i].low.fahrenheit
                                        }
                                    },
                                    icon_url: data.forecast.simpleforecast.forecastday[i].icon_url
                                };
                                forecast.push(weather);
                            }

                            // The state object to cache 
                            var object = {
                                id: id,
                                name: state,
                                city: city,
                                lgas: lgas,
                                latitude: latitude,
                                longitude: longitude,
                                currentTempF: data.current_observation.temp_f,
                                currentTempC: data.current_observation.temp_c,
                                relativeHumidity: data.current_observation.relative_humidity,
                                windDirection: data.current_observation.wind_dir,
                                windSpeed: data.current_observation.wind_kph,       
                                visibility: data.current_observation.visibility_km,
                                iconUrl: data.current_observation.icon_url,
                                forecast: forecast,
                                expiry: new Date(new Date().getTime() + (2*1000*60*60)) // object expires in 2 hours
                            };

                            localStorage.setItem(state, JSON.stringify(object)); // cache object

                            // display in dialog
                            displayStateInformation(object);

                            // save in db
                            $.ajax({
                                url: "/home/saveData",
                                method: "POST",
                                data: object,
                                success: function (data) {
                                    
                                }
                            });
                        }
                    });
                } else {
                    // dialog says state hasn't been created
                    $(".info-dialog").html("<h5 align='center'><i class='fa fa-exclamation-triangle'></i> State Coordinates cannot be determined</h3>");
                    $(".info-dialog").dialog({
                        title: "Fetching Data",
                        modal: true,
                        closeOnEscape: true,
                        draggable: false,
                        resizable: false,
                        height: "auto",
                        buttons: {
                            Close: function () {
                                $(this).dialog("close");
                            }
                        }
                    });
                }
            }
        }

        /**
         * Display a state's information in the dialog modal
         * @param object    The state object
         */
        function displayStateInformation(object) {
            var html = "" +
                " <h4>State</h4>" +
                " <p>" + object.name + "</p>" +
                " <h4>Capital City</h4>" +
                " <p>" + object.city + "</p>" +
                " <h4>Coordinates</h4>" +
                " <p>Latitude: " + object.latitude + ", Longitude: " + object.longitude + "</p>" +
                " <h4>Weather Forecast</h4>" +
                " "; 
            for (var i = 0; i < object.forecast.length; i++) {
                html += "<div class='pull-left weather-forecast'>";
                html += "<strong>" + object.forecast[i].day + "</strong> <br />";
                html += "<img src='" + object.forecast[i].icon_url + "' />";
                html += "<p><strong>Highest:</strong> " + object.forecast[i].temp.high.fahrenheit + "&deg;F / " + object.forecast[i].temp.high.celsius + "&deg;C</p>";
                html += "<p><strong>Lowest:</strong> " + object.forecast[i].temp.low.fahrenheit + "&deg;F / " + object.forecast[i].temp.low.celsius + "&deg;C</p>";
                html += "</div>";
            }
            html += "";

            $(".info-dialog").html(html);
            $(".info-dialog").dialog({
                title: "State Information",
                modal: true,
                closeOnEscape: true,
                draggable: false,
                resizable: false,
                width: "400",
                height: "auto",
                position: {
                    my: "center top+5%",
                    at: "center top+5%",
                    of: window
                },
                show: "puff",
                hide: "puff",
                buttons: {
                    Close: function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        function onEachFeature(feature, layer) {
            layer.on({
                mouseover: highlightFeature,
                mouseout: resetHighlight,
                click: clickToFeature
            });
        }


        // Load state data created in DB
        // To avoid having to make calls to the server to compare
        // data and get information on a state
        $(window).load(function (event) {
            event.preventDefault();

            $.ajax({
                url: "/home/loadData",
                method: "GET",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        for (var j = 0; j < nigeria.features.length; j++) {
                            if (data.length > 0 && data[i].name == nigeria.features[j].properties.state) {
                                nigeria.features[j].properties.id = data[i].id;
                                nigeria.features[j].properties.city = data[i].city;
                                nigeria.features[j].properties.lgas = data[i].lgas;
                                nigeria.features[j].properties.latitude = data[i].latitude;
                                nigeria.features[j].properties.longitude = data[i].longitude;
                            }
                        }
                    }
                }
            });
        });

        // Finally load map geojson data unto map
        geojson = L.geoJson(nigeria, {
            style: style,
            onEachFeature: onEachFeature
        }).addTo(map);


        // Toggle side menu on map page
        $(".menu").click(function (event) {
            event.preventDefault();
            $(".sidenav").css({width: "300px"});
            $("body").css({"background-color": "rgba(0,0,0,0.4)"});
            $(this).hide();
        }); 
        $(".close-side-nav").click(function (event) {
            event.preventDefault();
            $(".sidenav").css({width: "0"});
            $("body").css({"background-color": "transparent"});
            $(".menu").show();
        });

    });

})();