if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(['vue', 'vueMethods', 'ELEMENT', 'loadash', 'tool', "$", "ajaxUser"],
function(Vue, vueMethods, ELEMENT, _, tool,$, ajaxUser) {
	Vue.use(ELEMENT);
	var signin = new Vue({
		el: '#app',
		mounted: function(){
			this.$nextTick(function(){
				var that = this;
				if(tool.url.get('shareCode','search')){
					this.userInfo.shareCode = tool.url.get('shareCode','search');
				}
				var _hmt = _hmt || [];
				(function() {
				  var hm = document.createElement("script");
				  hm.src = "https://hm.baidu.com/hm.js?50d13d049948122f57a3031f16ed6a58";
				  var s = document.getElementsByTagName("script")[0]; 
				  s.parentNode.insertBefore(hm, s);
				})();
			})
		},
		data: function() {
			return {
				tool: tool,
				DrawImage: new Date().getTime(),
				userInfo: {
					userEmail:'',
					userPhone:'',
					imgCode:'',
					mobileCode:'',
					passWord:'',
					userName:'',
					agreementBox:0,
					shareCode:'',
				},
				userEmailErr:'',
				userPhoneErr:'',
				imgCodeErr:'',
				mobileCodeErr:'',
				passWordErr:'',
				userNameErr:'',
				agreementErr:'',
				supCode:'发送验证码',
//				supCode:'发送中...',
				codeBtnType:0,
				imgCodeCheck:0,
				submitCode:false,
				submitUp:false,
			}
		},
		methods: {
			//切换验证图片
			dramImg: function(){
				this.DrawImage = new Date().getTime(); 
				this.userInfo.imgCode = '';
			},
			//注册
			signUp:function(){
				var that = this;
				if(this.submitUp) return
				if(!this._checkSignup(this.userInfo)) return
				this.submitUp = true;
				ajaxUser.userRegist(this.userInfo,function(res) {
					that.submitUp = false
					if(res.code==200){
						that.message('注册成功！自动跳转',res.code==200)
						setTimeout(function(){
							window.location.href = '/search/index'
						},1500);
					}else if(res.code==302){
						that.mobileCodeErr = res.message;
						that.imgCodeCheck = 0;
						that.userInfo.mobileCode = '';
						that.dramImg();
						$('#imgCode input').focus();
					}else{
						that.imgCodeCheck = 0;
						that.userInfo.mobileCode = '';
						that.dramImg();
						that.agreementErr = res.message;
					}
				});
			},
			//注册验证
			_checkSignup:function(data){
				var checkEmail = tool.verifyEmail(data.userEmail);
				var checkPhone = tool.verifyMobile(data.userPhone);
				var checkPwd = tool.verifyPwd(data.passWord);
				if(!checkEmail.flag){
//					$('#userEmail input').focus();
					this.userEmailErr = checkEmail.message;
					return false
				}else if(!checkPhone.flag){
//					$('#userPhone input').focus();
        			this.userPhoneErr = checkPhone.message;
        			return false
        		}else if(!data.imgCode){
//					$('#imgCode input').focus();
        			this.imgCodeErr = '请输入图形验证码';
        			return false
        		}else if(!data.mobileCode){
//					$('#mobileCode input').focus();
        			this.mobileCodeErr = '请输入短信验证码';
        			return false
        		}else if(!checkPwd.flag){
//					$('#passWord input').focus();
        			this.passWordErr = checkPwd.message;
        			return false
        		}else if(!data.userName){
//					$('#userName input').focus();
        			this.userNameErr = '请输入姓名';
        			return false
        		}else if(!data.agreementBox){
        			this.agreementErr = '请接受用户服务协议';
        			return false
        		}
        		return true;
			},
//			//后台实时验证邮箱
//			checkEmail:function(){
//				var that = this;
//				var checkEmail = tool.verifyEmail(this.userInfo.email);
//				if(!checkEmail.flag){
//					$('#email input').focus();
//					this.emailErr = checkEmail.message;
//					return
//				}
//				ajaxUser.emailVerify({email: this.userInfo.email},function(res){
//					if(res.code != 200) {
//						$('#email input').focus();
//						that.emailErr = res.message;
//					}
//				});
//			},
			//实时验证手机号
			checkPhone:function(){
				var that = this;
				var checkPhone = tool.verifyMobile(this.userInfo.userPhone);
				if(!checkPhone.flag){
//					$('#userPhone input').focus();
					this.userPhoneErr = checkPhone.message;
					return
				}
			},
			//验证图形验证码及发送短信验证码
			sendCode: function() {
				var that = this;
				if(this.submitCode) return
				if(this.codeBtnType) return
				if(!this._checkSendCode(this.userInfo)) return
				var config = {
					mobile:this.userInfo.userPhone,
					imgCode:this.userInfo.imgCode,
				}
				this.submitCode = true;
				ajaxUser.sendSMS(config,function(result) {
					that.submitCode = false;
					if(result.code == 305){
//						$('#imgCode input').focus();
						that.imgCodeErr = '验证码错误';
						that.imgCodeCheck=0;
						that.dramImg();
					}else if(result.code != 200){
//						$('#imgCode input').focus();
						that.dramImg();
						that.imgCodeErr = result.message;
						that.imgCodeCheck=0;
//						that.userInfo.imgCode = '';
						return 
					}else {
						that.imgCodeCheck = 1;
						that.supCode = '发送中...'
						that.countTime();
						that.codeBtnType = 1
//						dom.text('发送中...').css({'background-color' : '#ccc'});
					}
				}, function(err) {
//					$('#imgCode input').focus();
//					that.imgCodeErr = result.message;
				});	
			},
			//短信倒计时
			countTime: function(){
				var time = 59;
				var that = this;
				that.supCode = time+'秒后可重发'
				
				var handle = setInterval(function() {
					time --;
					that.supCode = time+'秒后重发'
					if( time < 1 ) {
						clearInterval(handle);
						that.supCode = '发送验证码';
						that.codeBtnType = 0;
					} 
				}, 1000);
			},
			//图形验证前前端验证
			_checkSendCode:function(data){
				var checkEmail = tool.verifyEmail(data.userEmail);
				var checkPhone = tool.verifyMobile(data.userPhone);
				if(!checkEmail.flag){
//					$('#userEmail input').focus();
					this.userEmailErr = checkEmail.message;
					return false
				}else if(!checkPhone.flag){
//					$('#userPhone input').focus();
        			this.userPhoneErr = checkPhone.message;
        			return false
        		}else if(!data.imgCode&&this.imgCodeCheck){
        			this.imgCodeCheck = false;
//					$('#imgCode input').focus();
        			this.imgCodeErr = '请重新输入图形验证码';
        			return false
        		}else if(!data.imgCode){
//					$('#imgCode input').focus();
        			this.imgCodeErr = '请输入图形验证码';
        			return false
        		}
        		return true;
			},
		}
	});
	return signin;
});