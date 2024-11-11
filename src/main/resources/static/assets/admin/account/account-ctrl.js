app.controller('account-ctrl', function($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    
    $scope.initialize =function(){
		$http.get("/rest/accounts").then(resp =>{
			$scope.items = resp.data;
		});
	}
	
	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			image: "cloud-upload.jpg"
		}
	}
	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post("/rest/accounts", item).then(resp =>{
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Has created account success!");
		}).catch(error =>{
			alert("Create account error!");
			console.log(error);
		});
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put("/rest/accounts/" + item.username, item).then(resp =>{
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			alert("Has updated account success!");
		}).catch(error =>{
			alert("Update account error!");
			console.log(error);
		});
	}
	$scope.delete = function(item){
		$http.delete("/rest/accounts/" + item.username).then(resp =>{
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Has deleted account success!");
		}).catch(error =>{
			alert("Delete account error!");
			console.log(error);
		});
	}
	
	$scope.imageChanged = function(files){
		var data = new FormData();
        data.append("file", files[0]);
        
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.photo = resp.data.name;
        }).catch(error => {
			alert("Lỗi upload ảnh");
            console.log("Errors", error);
        });
	}
	
	$scope.pager = {
	    page: 0,
	    size: 10,
	    get items() {
	        var start = this.page * this.size;
	        return $scope.items.slice(start, start + this.size);
	    },
	    get totalPages() {
	        return Math.ceil($scope.items.length / this.size);
	    },
	    next() {
	        if (this.page < this.totalPages - 1) {
	            this.page++;
	        }
	    },
	    previous() {
	        if (this.page > 0) {
	            this.page--;
	        }
	    },
	    setPage(page) {
	        if (page >= 0 && page < this.totalPages) {
	            this.page = page;
	        }
	    }
	};

});