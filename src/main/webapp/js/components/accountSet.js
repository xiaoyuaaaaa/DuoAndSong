if(typeof define !== 'function') {
    var define = require('amdefine')(module);
}

define(["text!../../views/components/accountSet.html", "$", 'info', 'ajaxUser', "css!../../css/components/accountSet.css"],
    function(textModule, $, info, ajaxUser) {
        var accountSet = {
            template: textModule,
			mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                	this.userInfo = this.$root.userinfo;
                })
            },
            data: function() {
                return {
                	userInfo:{
                		name:'',
                		nickName:'',
                		jobTitle:'',
                		qq:'',
                		industry:'',
                	},
					areaName:info.getIndustry(),
					rules:{
						name:[
							{ required: true, message: '请输入你的姓名', trigger: 'blur'},
						],
					},
                }
            },
            methods: {
            	/*编辑用户信息*/
				setAccount:function(){
					var that = this;
					this.$refs.userInfo.validate(function(valid){
                    	if(valid){
                    		that.userInfo.QQ = that.userInfo.qq;
	                        ajaxUser.editBaseInfo(that.userInfo,function (res) {
	                            if (res.code==200){
	                                that.$root.$refs.basePageChild.getUserInfo();
	                            }
	                            that.message(res.message,res.code == 200)
	                        })
                    	}
                	})
				},
            }
        };
        return accountSet;
    });