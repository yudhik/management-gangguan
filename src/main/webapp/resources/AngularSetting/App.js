/**
 * Created by rizki on 06/10/14.
 */
angular.module('App',['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/' ,{templateUrl:'/management-gangguan/resources/views/tags/banner.jsp'})
            .when('/ManageUser', {templateUrl:'/management-gangguan/resources/views/admin/user/summary.jsp'})
            .when('/ManageRole', {templateUrl:'/management-gangguan/resources/views/admin/role/summary.jsp'})
            .when('/DetailUser', {templateUrl:'/management-gangguan/resources/views/admin/user/detail.jsp'})
            .when('/DetailRole', {templateUrl:'/management-gangguan/resources/views/admin/role/detail.jsp'})
            .when('/UpdateUser', {templateUrl:'/management-gangguan/resources/views/admin/user/update.jsp'})
            .when('/UpdateRole', {templateUrl:'/management-gangguan/resources/views/admin/role/update.jsp'})
            .when('/AddUser', {templateUrl:'/management-gangguan/resources/views/admin/user/form.jsp'})
            .when('/AddRole', {templateUrl:'/management-gangguan/resources/views/admin/role/form.jsp'});
    }]);