<!doctype html>
<html>
<head>
    <meta name="layout" content="admin_layout"/>
    <asset:stylesheet src="jquery-ui.min.css" />
</head>
<body>
    <div class="row" ng-app="WeatherApp" ng-controller="StateManagementController">
        <div class="col-lg-8">
            <a href="#" class="btn btn-primary" ng-click="addState()">
                <i class="fa fa-plus"></i> Add State
            </a>
            <div class="btn-group pull-right">
                <button type="button" class="btn btn-primary">
                    <i class="fa fa-cloud-download"></i> Download Data
                </button>
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li>
                        <g:link action="downloadFile" id="xls">
                            Download .xls
                        </g:link>
                    </li>
                    <li>
                        <g:link action="downloadFile" id="xlsx">
                            Download .xlsx
                        </g:link>
                    </li>
                </ul>
            </div>

            %{-- Add State dialog --}%
            <div class="add-dialog">
                <form ng-submit="saveState()" class="js-save-form">
                    <div class="form-group">
                        <g:textField name="name" class="form-control" placeholder="State" ng-model="saveName" />
                    </div>
                    <div class="form-group">
                        <g:textField name="city" class="form-control" placeholder="Capital City" ng-model="saveCity" />
                    </div>
                    <div class="form-group">
                        <g:textField name="lgas" class="form-control" placeholder="Number of LGAs" ng-model="saveLgas" />
                    </div>
                    <div class="form-group">
                        <g:textField name="latitude" class="form-control" placeholder="Latitude" ng-model="saveLat" />
                    </div>
                    <div class="form-group">
                        <g:textField name="longitude" class="form-control" placeholder="Longitude" ng-model="saveLng" />
                    </div>
                    <button class="btn btn-primary" name="save">Save</button>
                    <span class="js-save-loading"></span>
                </form>
            </div>

            %{-- Delete confirmation dialog --}%
            <div class="delete-dialog">
                <p>Are you sure you want to delete {{ stateName }}? This action cannot be reversed.</p>
            </div>

            <hr />

            %{--<input type="text" ng-model="search" class="form-control" placeholder="Search"
                   style="width: 40%;" /> <br />--}%

            <table class="table table-responsive table-bordered table-stripped table-hover">
                <thead>
                <tr>
                    <th>State</th>
                    <th>Capital</th>
                    <th>Number of LGAs</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                %{--<tr ng-repeat="state in states | filter:search | limitTo:limit:currentPage*limit">--}%
                %{--<tr ng-repeat="state in states | filter:search">--}%
                <tr ng-repeat="state in states">
                    <td>{{ state.name }}</td>
                    <td>{{ state.city }}</td>
                    <td>{{ state.lgas }}</td>
                    <td>
                        <a href="#" ng-click="editState($index)">
                            <i class="fa fa-edit"></i>
                        </a>
                    </td>
                    <td>
                        <a href="#" class="text-danger" ng-click="deleteState($index)">
                            <i class="fa fa-trash"></i>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>

            %{-- Pagination --}%
            %{--<div class="float-right">
                <ul class="pagination">
                    <li ng-class="{disabled: currentPage == 0}">
                        <a href="#" aria-label="Previous" ng-click="currentPage = currentPage - 1">Previous</a>
                    </li>
                    <li>
                        <a>Page {{ currentPage + 1 }} of {{ totalPages }}</a>
                    </li>
                    <li ng-class="{disabled: currentPage >= states.length/limit - 1}">
                        <a href="#" aria-label="Next" ng-click="currentPage = currentPage + 1">Next</a>
                    </li>
                </ul>
            </div>--}%
        </div>


        <div class="col-lg-4" ng-show="edit">
            <h2>Edit State</h2>
            <form ng-submit="updateState()" class="js-update-form">
                <div class="form-group">
                    <g:textField name="name" class="form-control" placeholder="State" ng-model="editName" />
                </div>
                <div class="form-group">
                    <g:textField name="city" class="form-control" placeholder="Capital City" ng-model="editCity" />
                </div>
                <div class="form-group">
                    <g:textField name="lgas" class="form-control" placeholder="Number of LGAs" ng-model="editLgas" />
                </div>
                <div class="form-group">
                    <g:textField name="latitude" class="form-control" placeholder="Latitude" ng-model="editLat" />
                </div>
                <div class="form-group">
                    <g:textField name="longitude" class="form-control" placeholder="Longitude" ng-model="editLng" />
                </div>

                <g:hiddenField name="id" ng-model="editId" />
                <g:hiddenField name="index" ng-model="editIndex" />
                <button type="submit" class="btn btn-primary">Save</button>
                <button class="btn btn-primary" name="close" ng-click="closeEditForm()">Close</button>
                <span class="js-update-loading"></span>
            </form>
        </div>
    </div>

    <asset:javascript src="app/angularjs/app.module.js" />
    <asset:javascript src="app/angularjs/app.controller.js" />
    <asset:javascript src="app/angularjs/app.service.js" />
    <asset:javascript src="lib/jquery-ui.min.js" />
</body>
</html>
