if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(["text!../../views/components/base.html",'tool','ajaxUser'],function(textModule,tool,ajaxUser) {
	var basePage = {
		template:textModule,
		props: {
			initUserinfo: {		//是否请求用户信息，默认true
				type: Boolean,
				default: true
			},
			userinfoCallback: {
				type: Function
			}
		},
		mounted: function(){
			this.$nextTick(function () {
				if(this.initUserinfo){
					this.getUserInfo();
					this.addPvUv();
				}
			})
		},
		data:function(){
			return {}
		},
		methods: {
			getUserInfo(callBack){
				var that = this;
				ajaxUser.getUserInfo(function(res){
					var userinfo = that.$root.userinfo;
                    res.headTime = +new Date();
					for(var key in res){
						if(userinfo.hasOwnProperty(key)){
							userinfo[key] = res[key];
						}else{
							that.$set(userinfo, key, res[key]);
						}
					}
					if(that.userinfoCallback !== undefined && typeof that.userinfoCallback == 'function'){
						that.userinfoCallback();
					}
					if(callBack !== undefined && typeof callBack == 'function'){
						callBack();
					}
				});
			},
			addPvUv() {
      	var that = this;
//    	var browser = tool.getBrowserVersion();
//    	var config = {
//    		name: browser?browser[0]:'',
//    		version:browser?browser[1].split('.')[0]:''
//    	}
//    	
//    	ajaxUser.addPvUv(config,function(result){})
	        	
      	/*百度统计*/
      	var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "https://hm.baidu.com/hm.js?50d13d049948122f57a3031f16ed6a58";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
      }
		}
	};
	return basePage;
});