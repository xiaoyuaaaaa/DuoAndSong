if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

requirejs(['vue', 'vueMethods', 'ELEMENT', 'loadash', 'tool', "$", "ajaxUser"],
function(Vue, vueMethods, ELEMENT, _, tool, $, ajaxUser) {
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
				userInfo:{
					userName: '',
					passWord: '',
				}, 
				userNameErr:'',
				passWordErr:'',
			}
		}, 
		methods: {
			signIn:function(){
				var that = this;
				this.userInfo.status=0;
				if(!this._checkSignin(this.userInfo)) return
				ajaxUser.userLogin(this.userInfo,function(res) {
					if(res.code==200){
						window.location.href = '/search/index'
					}else{
						that.passWordErr = res.message;
					}
				});
			},
			//登录验证
			_checkSignin: function(data){
				var checkEmail = tool.verifyEmail(data.userName);
				var checkPhone = tool.verifyMobile(data.userName);
				
				if(!checkEmail.flag&&!checkPhone.flag){
//					$("#userName input").focus()
					this.userNameErr = '请输入用户邮箱或手机号码';
					return false;
				}else if(!data.passWord){
//					$('#passWord input').focus();
					this.passWordErr = '请输入密码';
					return false;
				}
				
				return true;
			},
		}
	});
	return signin;
});