<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Weather Map</title>
    <asset:stylesheet src="leaflet.css" />
    <asset:stylesheet src="jquery-ui.min.css" />
</head>
<body class="container">
    <div class="menu">
        <button type="button" class="menu-toggle" data-toggle="tooltip"
                data-placement="bottom" title="Menu" id="menu">
            Menu
        </button>
    </div>

    <div class="sidenav">
        <div class="logo divs">
            <a href="#" class="close-side-nav pull-right" title="Close Menu">
                <i class="fa fa-angle-double-left"></i> Close
            </a>
            <br />
        </div>
        
        <div style="padding: 20px;">
            <h4>Navigation</h4>
            <ul class="list-unstyled">
                <li>
                    <g:link controller="admin">
                        <i class="fa fa-user-secret"></i> Admin Log In
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="info-dialog"></div>

    <div class="map" id="map"></div>

    <asset:javascript src="lib/jquery-ui.min.js" />
    <asset:javascript src="lib/leaflet.js" />
    <asset:javascript src="nigeria.js" />
    <asset:javascript src="app/map/map.config.js" />
    <asset:javascript src="app/map/map.js" />
</body>
</html>
