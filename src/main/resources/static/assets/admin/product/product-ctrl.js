app.controller('product-ctrl', function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    
    $scope.initialize =function(){
		$http.get("/rest/products").then(resp =>{
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			});
		});
		$http.get("/rest/categories").then(resp =>{
			$scope.cates = resp.data;
		});
	}
	
	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			createDate: new Date(),
			image: "cloud-upload.jpg",
			available: true
		}
	}
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post("/rest/products", item).then(resp =>{
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Has created product success!");
		}).catch(error =>{
			alert("Create product error!");
			console.log(error);
		});
	}
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put("/rest/products/" + item.id, item).then(resp =>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Has updated product success!");
		}).catch(error =>{
			alert("Update product error!");
			console.log(error);
		});
	}
	$scope.delete = function(item){
		$http.delete("/rest/products/" + item.id).then(resp =>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Has deleted product success!");
		}).catch(error =>{
			alert("Delete product error!");
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
            $scope.form.image = resp.data.name;
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