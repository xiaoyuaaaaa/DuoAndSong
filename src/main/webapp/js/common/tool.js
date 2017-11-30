if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(['$', 'axios','base','vue'], function($, axios, base, Vue) {
	var tool = {
		
		//URL参数操作
		url : {
			get: function(name,type) {
		        var query = window.location.hash;
		        if(type == 'search'){
		        	query = window.location.search;
		        }
				if (name) {
					var r = new RegExp("[?&]" + name + "=([^&]*)");
					var m = r.exec(query);
					if (m) {
						return m[1];
					} else {
						return null;
					}
				} else {
					return this.parseQueryString(query);
				}  
		    },
			toQueryString: function(obj, q) {	//tool.toQueryString({a:'a01',b:2})   ->  '?a=01&b=2'
				q = q || '?';
				var array = [],
					k, v;
				for (k in obj) {
					v = obj[k];
					if (typeof v === 'function') {
						continue;
					}
					array.push(k + '=' + encodeURIComponent(v));
				}
				return (array.length > 0) ? (q + array.join('&')) : '';
			},
			parseQueryString: function(query) {
				if (query[0] === '?') query = query.substring(1);
				var args = {};
				var fragments = query.split('&');
				var fragment, pos, i, length = fragments.length;
				for (i = 0; i < length; i++) {
					fragment = fragments[i];
					pos = fragment.indexOf('=');
					if (pos > 0) {
						args[fragment.substring(0, pos)] = fragment.substring(pos + 1);
					} else {
						args[fragment] = null;
					}
				}
				return args;
			},
		},
		
		LocalStorage: {
			on: function() {
				this.events.addListener.apply(this.events, arguments);
			},
			off: function() {
				this.events.removeListener.apply(this.events, arguments);
			},
			fireEvent: function(name, event) {
				if (this.events) {
					this.events.fireEvent(name, event);
				}
			},
			get: function get(name) {
				var s = localStorage.getItem(name),
					obj;
				try {
					obj = s ? JSON.parse(s) : null;
				} catch (e) {
					console.log(name + " : " + s);
				}
				return obj;
			},
			set: function(name, obj) {
				var args = {};
				args[name] = {
					oldValue: this.get(name),
					value: null
				};
				if (obj === undefined) {
					localStorage.removeItem(name);
				} else {
					try {
				        localStorage.setItem(name, JSON.stringify(obj));
				    } catch (e) {
				    	if(e.name == 'QuotaExceededError'){  
					        localStorage.clear();  
					    }
				    }
				}
				args[name].value = obj;
				this.fireEvent("PropertyChanged", args);
			},
			destory: function(name) {
				localStorage.removeItem(name);
			}
		},
		
		cookie: {
			set: function(name, value, flag) {
				var Days = 30;
				var exp = new Date();
				exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
				if( flag == true ) {
					document.cookie = name + "=" + window.escape(value) ;
					return;
				}
				document.cookie = name + "=" + window.escape(value) + ";expires=" + exp.toGMTString()+"; path=/";
			},
			get: function(name) {
				var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
				if (arr !== null) {
					return window.unescape(arr[2]);
				}
				return null;
			},
			del: function(name) {
				var exp = new Date();
				exp.setTime(exp.getTime() - 1);
				var cval = cookie.get(name);
				if (cval !== null) {
					document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
				}
			}
		},
		
		encodeURI: function(str){
			return encodeURI(str);
		},
		
		decodeURI: function(str){
			return decodeURI(str);
		},
		
		encodeURIComponent: function(str){
			return encodeURIComponent(str);
		},
		
		decodeURIComponent: function(str){
			return decodeURIComponent(str);
		},
		
		//获取浏览器版本
		getBrowserVersion: function(){
			var agent = navigator.userAgent.toLowerCase() ;
			
			var regStr_ie = /msie [\d.]+;/gi ;
			var regStr_ff = /firefox\/[\d.]+/gi
			var regStr_chrome = /chrome\/[\d.]+/gi ;
			var regStr_saf = /safari\/[\d.]+/gi ;
			//IE
			if(agent.indexOf("msie") > 0){
				return ["msie",(agent.match(regStr_ie)+"").replace(/[^0-9.]/ig,"")] ;
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
		console: function(data){
			console.clear();
			if(console.table){
				var consoleArr = [];
				for(var key in data){
					var value=data[key];
					if(typeof value !== 'string'){
						value = JSON.stringify(data[key]);
					}
					consoleArr.push([key,value])
				}
				console.table(consoleArr);
			}else{
				console.log(data);
			}
		},
		
		ajax: function(url,data,successFn,failFn,loading,reway){
			//loading：true = show; loading：false = hide;
			if(!loading) tool.loading(true);
			axios.defaults.withCredentials = true;
			var ajax;
			if(reway=='get'){
				ajax = axios.get(url+'?'+$.param(data));
			}else{
				ajax = axios.post(url,$.param(data));
			}	
	    ajax.then(function (response) {
	    	if(response.data.code == 444){
	    		//base.neadLogin();
	    		window.location.href="/login/login?backUrl="+encodeURIComponent(window.location.href);
	    	}else if(typeof successFn == "function"){
				successFn(response.data);
			};
			if(!loading) tool.loading(false);
	    })
	    .catch(function (error) {
	    	tool.loading(false);
	    	if(typeof failFn == "function"){
				failFn(error);
			}
			console.log(error + ' on ' + url)
	    });
		},
		ajax2 (param){
			var ajax;
			var config = {
				url: param.url,									//请求url
				data: param.data || {},					//请求参数
				type: param.type || 'post',			//请求方式，默认为post
				loading: param.loading,					//是否需要loading动画，默认为true
				success: param.success,					//请求成功回调函数
				error: param.error							//请求失败回调函数
			}
			if(!config.url){
				alert('url required');
				return
			}
			if(config.loading === undefined) config.loading=true;
			if(config.loading) tool.loading(true);
			axios.defaults.withCredentials = true;
			if(config.type =='post'){
				ajax = axios.post(config.url,$.param(config.data));
			}else{
				ajax = axios.get(config.url+'?'+$.param(config.data));
			}	
	    ajax.then(function (response) {
	    	if(response.data.code == 444){
	    		window.location.href="/login/login?backUrl="+encodeURIComponent(window.location.href);
	    	}else if(typeof config.success == "function"){
					config.success(response.data);
				};
				if(config.loading) tool.loading(false);
	    })
	    .catch(function (error) {
	    	if(config.loading) tool.loading(false);
	    	if(typeof config.error == "function"){
					config.error(error);
				}
				console.error(error + ' on ' + config.url);
	    });
		},
		
		/*静默请求*/
		ajaxSilent: function(url,data,successFn){
			axios.defaults.withCredentials = true;
		    axios.post(url,$.param(data))
		    .then(function (response) {
		    	if(response.data.code == 444){
		    		window.location.href="/base/signin?backUrl="+encodeURIComponent(window.location.href);
		    	}else if(typeof successFn == "function"){
					successFn(response.data);
				};
		    })
		    .catch(function (error) {
		    	if(typeof failFn == "function"){
					failFn(error);
				}
				console.log(error + ' on ' + url)
		    });
		},
		
		/*JSON转GET请求参数*/
		jsonToGet:function(data){
			data = data.replace(/\t/g,"");
			data = data.replace(/\"/g,"").replace("{","").replace("}","").replace(",","&").replace(":","=");
			data = data.replace(/\"/g,"").replace(/{/g,"").replace(/}/g,"").replace(/,/g,"&").replace(/:/g,"=");
			return data
		},
		
		//验证网址格式
		verifyWebsite: function(value) {
			var regExp = /^((https?|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
			if(regExp.test(value)){
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'网址格式不正确'};
			}
		},

		//验证是否有值
		verifyHasValue: function(value) {
			if(!!value){
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'不能为空'};
			}
		},
		
		//验证邮件格式
		verifyEmail: function(value) {
			var result = new RegExp("^([a-z0-9A-Z]+[-|_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").test(value);
			if(result){
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'邮箱格式不正确'};
			}
		},
		
		//验证手机号
		verifyMobile: function(value) {
			if(!value){
				return {flag:false,message:'手机号不能为空'};
			}else if(/^[1][34578]\d{9}$/.test(value)){
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'手机号格式不正确'};
			}
		},
		
		//验证电话号码号 支持固话，支持验证分机号
		verifyLandline: function(value) {
			if(!value){
				return {flag:false,message:'电话号不能为空'};
			}else if(/^[0][0-9]{2,3}-[0-9]{5,10}(-[0-9]{3,5})?$/.test(value)){
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'电话号格式不正确'};
			}
		},
		
		//6位验证手机验证码
		verifyMobileCode: function(value) {
			if(value.length < 6) {
				return {flag:false,message:'请输入6位数字的手机验证码'};
			}else if(!/[0-9]{6}/.test(value)){
				return {flag:false,message:'验证码不正确'};
			}else{
				return {flag:true,message:'验证成功'};
			}
		},
		
		//验证密码格式
		verifyPwd: function(value) {
			if(value.length < 6 || value.length > 16) {
				return {flag:false,message:'请输入6-16位密码'};
			}else if(!/^[0-9A-Za-z_]{6,16}$/.test(value)){
				return {flag:false,message:'密码只能是字母、数字、下划线'};
			}else{
				return {flag:true,message:'验证成功'};
			}
		},
		
		//验证两个字符是否一致
		verifySame: function(value1,value2) {
			if(value1 == value2) {
				return {flag:true,message:'验证成功'};
			}else{
				return {flag:false,message:'两次输入不一致'};
			}
		},
		
		//验证图形验证码
		verifyImgCode: function(value) {
			if(!value){
				return {flag:false,message:'验证码不能为空'};
			}else if(!/^[0-9A-Za-z_]{4}$/.test(value)) {
				return {flag:false,message:'验证码只能是数字或字母'};
			}else{
				return {flag:true,message:'验证成功'};
			}
		},
		
		/* 倒计时*/
		countTime: function(eleId,sec,initFn,callbackFn) {
			tool.dealIEHTML5CSS3();
			var time = sec;
			obj = document.getElementById(eleId);
			obj.classList.add('hr-btn-disabled');
			document.getElementById(eleId).innerHTML = sec+'秒后可重试';
			initFn();
			
			var handle = setInterval(function() {
				time --;
				obj.innerHTML = time+'秒后可重试';
				if( time < 1 ) {
					clearInterval(handle);
					obj.innerHTML = '获取验证码';
					obj.classList.remove('hr-btn-disabled');
					if(typeof callbackFn == "function"){
						callbackFn();
					}
				} 
			}, 1000);
		},
		
		/*查询当前值是否在数组中*/
		findInArr:function(arr,value){
			for(var index in arr){
				if(arr[index] == value){
					return true;
					break;
				}
			}
			return false;
		},
		
		/*查询当前值在数组中的索引*/
		findIndexInArr:function(arr,value){
			for(var index in arr){
				if(arr[index] == value){
					return index;
					break;
				}
			}
			return false;
		},
		
		/*删除数组的指定索引元素*/
		removFromArr:function(arr,index,callbackFn){
			arr.splice(index,1);	//删除指定位置的元素
			if(typeof callbackFn == "function"){
				callbackFn();
			}
		},
		
		/*是否按下键盘的数字键*/
		isNumKey:function(){
			var keyCode = event.keyCode;    
		    if ((keyCode >= 48 && keyCode <= 57)){    
		        event.returnValue = true;    
		    } else {    
		        event.returnValue = false;    
		    } 
		},
		
		//日期转换格式转换----函数
		formatDate: function(date,format){
			if(!date) return false;
			format = format || "yyyy/MM/dd hh:mm:ss";
			date = date || new Date();
			if(typeof date == "string") date = new Date(Date.parse(date.replace(/-/g, "/")));	//Safari不支持 2017-01-01的格式
			//if(typeof date == "string") date = new Date(date);
			  var o = {
			    "M+" : date.getMonth()+1, //month
			    "d+" : date.getDate(),    //day
			    "h+" : date.getHours(),   //hour
			    "m+" : date.getMinutes(), //minute
			    "s+" : date.getSeconds(), //second
			    "q+" : Math.floor((date.getMonth()+3)/3),  //quarter
			    "S" : date.getMilliseconds() //millisecond
			  }
			  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));
			  for(var k in o) if(new RegExp("("+ k +")").test(format))
			      format = format.replace(RegExp.$1,
			      RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
			  return format;
		},
		
		dealTime: function(time) { //可以注入依赖
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
		},
		
		dealIEHTML5CSS3: function(){
			if (!("classList" in document.documentElement)) {
			    Object.defineProperty(HTMLElement.prototype, 'classList', {
			        get: function() {
			            var self = this;
			            function update(fn) {
			                return function(value) {
			                    var classes = self.className.split(/\s+/g),
			                        index = classes.indexOf(value);
			                    
			                    fn(classes, index, value);
			                    self.className = classes.join(" ");
			                }
			            }
			            
			            return {                    
			                add: update(function(classes, index, value) {
			                    if (!~index) classes.push(value);
			                }),
			                
			                remove: update(function(classes, index) {
			                    if (~index) classes.splice(index, 1);
			                }),
			                
			                toggle: update(function(classes, index, value) {
			                    if (~index)
			                        classes.splice(index, 1);
			                    else
			                        classes.push(value);
			                }),
			                
			                contains: function(value) {
			                    return !!~self.className.split(/\s+/g).indexOf(value);
			                },
			                
			                item: function(i) {
			                    return self.className.split(/\s+/g)[i] || null;
			                }
			            };
			        }
			    });
			}
		},
		
		maxLength: function(str,subLength){
			if(!!str){
				return str.substr(0,subLength)+'...';
			}
		},
		
		//数组arr1后追加arr2
		concat: function(arr1,arr2){
			arr1.push.apply(arr1,arr2);
		},

		//util.toDate(1399599725)   ->  Fri May 09 2014 09:42:05 GMT+0800  (Date Object)
		toDate: function(secondTicket) {
			return new Date(secondTicket * 1000);
		},
		
		loading: function(state,url) {
			var target = document.querySelector('.hr-loading');
			if(!!state){
				if(!!target){
					target.style.display = 'block';
				}else{
					var fragment = document.createDocumentFragment();
					var div = document.createElement('div');
					div.className = 'hr-loading';
					div.innerHTML = '<div class="hr-loading-bg bg-white"></div><img src="/img/common/loading.gif"/>';
					fragment.appendChild(div);
					document.querySelector('body').appendChild(fragment);
				}
			}else{
				if(!!target){
					target.style.display = 'none';
				}
			}
			
		},
		
		/*返回解码后的unicode码数组,配合Base64解码使用*/
		unicode:function(arr){
			var str = '';  
			for(var i = 0 , len =  arr.length ; i < len ;++i){  
			      str += String.fromCharCode(arr[i]);  
			}
			return str
		},
		
		towDecimal:function(num){
			return Math.round(num*100)/100
		},

		//智联插件版本
		zlVersion:function (need) {
			if(!tool.cookie.get('zl_version')){
				return false;
			}
			var current =tool.cookie.get('zl_version').split('v')[1];//当前插件版本号
			need = need.split('v')[1];
			if(need&&(need-0>current-0)){
            	new Vue().message('请下载最新亿封助手');
            	return false;
			}else {
				return true;
			}
        }
	};
	
	/*//给ie9添加promise
	if(tool.getBrowserVersion()[0]=='msie' && parseInt(tool.getBrowserVersion()[1])<10){
		require(['/js/lib/axios/promise.min.js']);
	}*/
	return tool;
});