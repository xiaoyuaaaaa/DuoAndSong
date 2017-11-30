if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
define(["text!../../views/detailComponents/diaConfirm.html","tool"],
    function (textModule,tool) {
        var Detail = {
            template: textModule,
            mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                });
            },
            data: function () {
            	return{
            		tool:tool,
            		diaConfirm:false,
                    popup:{
                    	message:'',    //提示信息
                    },
            	}
            },
            methods: {
            	/*确认框打开*/
               	confirmOpen:function(message,status){
               		tool.loading(false);
               		this.diaConfirm = true;
               		this.popup.message = message;
               		//status  :1企业信息未完善，:2企业未认证
               		this.popup.status = status?status:0;
               	},
            	
            	/*确认框关闭*/ 
                confirmClose:function(data){
                	this.diaConfirm = true;
                	//status  :1企业信息未完善，:2企业未认证
                	if(!data.status){
                		window.location.href = '/search/index';
                	}else if(data.status==1){
                		window.location.href = '/user/user#/user/accountSet';
                	}else if(data.status==2){
                		window.location.href = '/user/user#/user/companyAuth';
//              		window.location.href = '/userset/accountCenter#/company/compdefault';
                	}
                },
            }
        };
        return Detail;
    });