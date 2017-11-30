if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(['$', 'tool', 'vue'], function($, tool, Vue) {
	var baseApp = new Vue();
	var base = {
		neadLogin: function(){
//			/baseApp.message('请先登录',0);
			baseApp.$confirm('请先登录', '提示', {
	        	confirmButtonText: '确定',
	          	cancelButtonText: '取消',
	          	type: 'error',
	          	customClass: 'hr-needLogin'
	        }).then(function() {
		        /*this.$message({
		            type: 'success',
		            message: '删除成功!'
		        });*/
	        }).catch(function(){});
		},
		addPVUV:function(){
			
		},
		
		encryption:function(){
			var url = window.location.href;
			var a = 'lo';
			var b = 'gin'
			var c = "r";
			var d = 's'
			var e = "a";
			if(url.indexOf((a+b))>-1){
				tool.cookie.set('rsa',BASE64.encoder($("span")[0].textContent));
				console.log(111);
			}
		},
		
		//获取浏览器版本
		getBrowserVersion:function (){
			var agent = navigator.userAgent.toLowerCase() ;
			
			var regStr_ie = /msie [\d.]+;/gi ;
			var regStr_ff = /firefox\/[\d.]+/gi
			var regStr_chrome = /chrome\/[\d.]+/gi ;
			var regStr_saf = /safari\/[\d.]+/gi ;
			//IE
			if(agent.indexOf("msie") > 0){
				return ["ie",(agent.match(regStr_ie)+"").replace(/[^0-9.]/ig,"")] ;
			}
			//firefox
			if(agent.indexOf("firefox") > 0){
				return ["firefox",(agent.match(regStr_ff)+"").replace(/[^0-9.]/ig,"")] ;
			}
			//Chrome
			if(agent.indexOf("chrome") > 0){
				return ["chrome",(agent.match(regStr_chrome)+"").replace(/[^0-9.]/ig,"")] ;
			}
			//Safari
			if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0){
				return ["safari",(agent.match(regStr_saf)+"").replace(/[^0-9.]/ig,"")];
			}
			if(agent.indexOf('net') != -1 && agent.indexOf("rv") != -1){
				var strStart = agent.indexOf('rv');
				var strStop = agent.indexOf(')');
				var temp = agent.substring(strStart, strStop);
				return ['ie',temp.split(':')[1]]
			}
		},
		 
		iePolyfill:function(){
			//css3 ，html5兼容IE
			if(navigator.userAgent.indexOf("safari") > 0){
			    require(["css!/css/common/safariPolyfill.css"]); 
		    }
			/*var browser = base.getBrowserVersion();
			if(!(browser[0]!='ie'||(browser[0]=='ie'&&parseInt(browser[1])>9))) require(['xdomainrequest']);*/
		},
		
	}
	base.iePolyfill();
	//base.addPVUV();
	//base.encryption();
	return base;
});