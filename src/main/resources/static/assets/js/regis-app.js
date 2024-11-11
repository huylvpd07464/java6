const app = angular.module("regis-app", []);

app.controller("regis-ctrl", function($scope, $http) {
    $scope.account = {};
    $scope.otpSent = false;

    $scope.register = function() {
		$http.post('/rest/register', $scope.account).then(resp => {
			alert('Registration successful! Check your email for OTP.');
            $scope.otpSent = true;
		}).catch(error => {
			alert("Registration error!")
			console.log(error);
		})
    };

    $scope.verifyOtp = function() {
        var data = {
            account: $scope.account,
            otp: $scope.otp
        };
        $http.post('/rest/verify-otp', data)
            .then(resp => {
				alert('OTP verified successfully!');
				location.href = "/security/login/form";
			}).catch(error => {
				console.log(error.data);
			})
    };

});
