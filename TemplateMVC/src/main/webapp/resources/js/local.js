var id_global;

function deleteUser(url_){
	  $.ajax({
	        url: url_,
	        type: 'GET',
	        cache: false,
	        success: function(data){
	        	$("#msg").html(data);
	        },
	        error: function (error) {
	        	$("#msg").html("Internal Error!");
	        }	  
	    });
}
