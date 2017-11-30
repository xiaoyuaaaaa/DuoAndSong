if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

requirejs(['vue', 'vueMethods', 'ELEMENT', 'loadash', 'tool', "$", "ajaxUser"],
function(Vue, vueMethods, ELEMENT, _, tool,$, ajaxUser) {
	Vue.use(ELEMENT);
	var signin = new Vue({
		el: '#app',
		mounted: function(){
			this.$nextTick(function(){
				tool.addPvUv();
			})
		},
		data: function() {
			return {
				tool: tool,
				DrawImage: new Date().getTime(),
				userInfo: {
					telephone:'',
					imgCode:'',
					mobile_code:'',
					password:'',
					userName:'',
					agreementBox:0,
				},
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
                sendSMSType:false,
			}
		},
		methods: {
			//切换验证图片
			dramImg: function(){
				this.DrawImage = new Date().getTime(); 
				this.userInfo.imgCode = '';
			},
			//注册
			forgotPassword:function(){
				var that = this;
				if(!this._checkUpwd(this.userInfo)) return
				ajaxUser.forgotPassword(this.userInfo,function(res) {
					if(res.code==200){
						that.message('修改成功！跳转登录页',res.code==200)
						setTimeout(function(){
							window.location.href = '/login/login'
						},1500);
					}else{
						that.imgCodeCheck = 0;
						that.userInfo.mobileCode = '';
						that.dramImg();
						that.passWordErr = res.message;
					}
				});
			},
			//注册验证
			_checkUpwd:function(data){
				var checkPhone = tool.verifyMobile(data.telephone);
				var checkPwd = tool.verifyPwd(data.password);
				if(!checkPhone.flag){
//					$('#userPhone').focus();
        			this.userPhoneErr = checkPhone.message;
        			return false
        		}else if(!data.imgCode){
//					$('#imgCode').focus();
        			this.imgCodeErr = '请输入图形验证码';
        			return false
        		}else if(!data.mobile_code){
//					$('#mobileCode').focus();
        			this.mobileCodeErr = '请输入短信验证码';
        			return false
        		}else if(!checkPwd.flag){
//					$('#passWord').focus();
        			this.passWordErr = checkPwd.message;
        			return false
        		}
        		return true;
			},
//			//后台实时验证邮箱
//			checkEmail:function(){
//				var that = this;
//				var checkEmail = tool.verifyEmail(this.userInfo.email);
//				if(!checkEmail.flag){
//					$('#email').focus();
//					this.emailErr = checkEmail.message;
//					return
//				}
//				ajaxUser.emailVerify({email: this.userInfo.email},function(res){
//					if(res.code != 200) {
//						$('#email').focus();
//						that.emailErr = res.message;
//					}
//				});
//			},
			//实时验证手机号
			checkPhone:function(){
				var that = this;
				var checkPhone = tool.verifyMobile(this.userInfo.telephone);
				if(!checkPhone.flag){
//					$('#userPhone').focus();
					this.userPhoneErr = checkPhone.message;
					return
				}
			},
			//验证图形验证码及发送短信验证码
			sendCode: function() {
				var that = this;
				if(this.sendSMSType) return
				if(!this._checkSendCode(this.userInfo)) return
				if(this.codeBtning) return
				var config = {
					mobile:this.userInfo.telephone,
					imgCode:this.userInfo.imgCode,
					type:1
				}
                this.sendSMSType = true;
				ajaxUser.sendSMS(config,function(result) {
                    that.sendSMSType = false;
					if(result.code == 305){
//						$('#imgCode').focus();
						that.imgCodeErr = '验证码错误';
					}else if(result.code != 200){
//						$('#imgCode').focus();
						that.imgCodeErr = result.message;
						that.userInfo.imgCode = '';
						that.dramImg();
						return 
					}else {
						that.imgCodeCheck = 1;
						that.countTime();
						that.supCode = '发送中...'
						that.codeBtnType = 1
//						dom.text('发送中...').css({'background-color' : '#ccc'});
					}
				}, function(err) {
//					$('#imgCode').focus();
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
					that.supCode = time+'秒后可重发'
					if( time < 1 ) {
						clearInterval(handle);
						that.supCode = '发送验证码';
						that.codeBtnType = 0;
					} 
				}, 1000);
			},
			//图形验证前前端验证
			_checkSendCode:function(data){
				var checkPhone = tool.verifyMobile(data.telephone);
				if(!checkPhone.flag){
//					$('#userPhone').focus();
        			this.userPhoneErr = checkPhone.message;
        			return false
        		}else if(!data.imgCode){
//					$('#imgCode').focus();
        			this.imgCodeErr = '请输入图形验证码';
        			return false
        		}
        		return true;
			},
		}
	});
	return signin;
});