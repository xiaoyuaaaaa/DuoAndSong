if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(['vue', 'tool'], function(Vue, tool) {
    function install(Vue) {
    	var messageTips;
    	//获取用户信息
		Vue.prototype.getUserInfo = function (that,_callback) {
		    tool.ajax(
				'/common/getUserInfo', 
				{},
				function(res) {
					if(res.code == 200){
						that.userInfo = res;
						if(typeof _callback == "function"){
							_callback();
						}
					}
				},
				function(res) {
					
				}
			);
		}
		Vue.prototype.win = function () {
		  return window;
		}
		
		Vue.prototype.message = function(str,flag,showClose,duration){
			if(messageTips)	messageTips.close();	//如果已经有提示了，先把之前的关掉。防止重复渲染
			var option = {
				message: str,
				type: flag?'success':'error',
				showClose: showClose,
				duration:duration!=undefined?duration:3000,
			}
			messageTips = this.$message(option);
		};
		
		Vue.prototype.needCompanyVerify = function(){
			this.$confirm('你还没进行企业认证，进行企业认证后才能获取简历，点击认证？', '提示', {
 	        	confirmButtonText: '认证',
 	        	showCancelButton: true,
 	        	customClass: 'needFullUserInfo',
 	        	closeOnClickModal: false,
 	        	type: 'warning'
 	        }).then(function(){
 	        	window.location.href = '/user/user#/user/companyAuth';
 	        }).catch(function(){
 	        });
		};
		
		Vue.prototype.getKeyMap = function (obj,id,defaultStr) {
			var obj = obj[id];
		    if(!!obj){
		    	return obj.value;
		    }else if(defaultStr != undefined){
		    	return defaultStr;
		    }else{
		    	return '--';
		    }
		}
		
		Vue.prototype.init = function (obj,keyName,value) {
			obj,keyName,value;
			if(obj[keyName] == undefined){
				this.$set(obj,keyName,value);
			}
		}
		
		Vue.filter('dealTime', function(time) { 
		    return function(time) {
		    	if(!time) return false;
		    	var s = (new Date() - new Date(time)) / 1000;
		        
		        if(s<60*10){//十分钟内
			        return '刚刚';
			    }else if((s<60*60)&&(s>=60*10)){
			        //超过十分钟少于1小时
			        s = Math.floor(s/60);
			        return  s+"分钟前";
			    }else if((s<60*60*24)&&(s>=60*60)){ 
			        //超过1小时少于24小时
			        s = Math.floor(s/60/60);
			        return  s+"小时前";
			    }else if((s<60*60*24*3)&&(s>=60*60*24)){ 
			        //超过1天少于3天内
			        s = Math.floor(s/60/60/24);
			        return s+"天前";
			    }else{ 
			        //超过3天
			        /*var date= new Date(time);
			        return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();*/
			       return time.split(' ')[0]
			    }
		    }
		});
		
		Vue.directive('focus', {
		  	// 当绑定元素插入到 DOM 中。
			inserted: function (el) {
		    	// 聚焦元素
				el.focus()
			}
		})
		
	}

    Vue.use(install)
});