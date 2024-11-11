app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/assets/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .when("/account", {
            templateUrl: "/assets/admin/account/index.html",
            controller: "account-ctrl"
        })
        .when("/adminhome", {
            templateUrl: "/assets/admin/adminhome.html"
        })
        .otherwise({
            template: "<h1 class='text-center'>Luxury Store Admin</h1>"
        });
})