if(typeof define !== 'function') {
    var define = require('amdefine')(module);
}

define(["text!../../views/components/phoneCheck.html", "$", 'info', 'ajaxUser', "css!../../css/components/accountSet.css"],
    function(textModule, $, info, ajaxUser) {
        var accountSet = {
            template: textModule,
			mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                	this.upInfo = this.$root.userinfo;
                })
            },
            data: function() {
                return {
                	DrawImage: new Date().getTime(),
					upInfo:{
						telephone:'',
						newPhone:'',
						imgCode:'',
						mobile_code:'',
					},
					supCode:'发送验证',
					codeBtnType:0,
					newPhoneErr:'',
					imgCodeErr:'',
					mobile_codeErr:'',
                }
            },
            methods: {
				//切换验证图片
				dramImg: function(){
					this.DrawImage = new Date().getTime(); 
					this.upInfo.imgCode = '';
				},
				
				//发送短信验证码
				sendSMS:function(){
					var that = this;
					if(this.codeBtnType){
						this.message('请稍候重试',0);
						return
					}
					if(!this.upInfo.newPhone){
						this.newPhoneErr = '请输入新手机号';
						return 
					}else if(!this.upInfo.imgCode){
						this.imgCodeErr = '请输入图形验证码';
						return 
					}
					var config = {
						mobile:this.upInfo.newPhone,
						imgCode:this.upInfo.imgCode,
					}
					ajaxUser.sendSMS(config,function(res){
						if(res.code==305){
							that.dramImg();
							that.imgCodeErr = '验证码错误';
						}else if(res.code!=200){
							that.dramImg();
							that.imgCodeErr = res.message;
						}else{
							that.supCode = '发送中...'
							that.countTime();
							that.codeBtnType = 1
						}
					})
				},
				//短信倒计时
				countTime: function(){
					var time = 59;
					var that = this;
					that.supCode = time+'秒可重发'
					
					var handle = setInterval(function() {
						time --;
						that.supCode = time+'秒后重发'
						if( time < 1 ) {
							clearInterval(handle);
							that.supCode = '发送验证';
							that.codeBtnType = 0;
						} 
					}, 1000);
				},
				
				//修改手机号
				editPhone:function(){
					var that = this
					if(!this.upInfo.newPhone){
						this.newPhoneErr = '请输入新手机号';
						return 
					}else if(!this.upInfo.imgCode){
						this.imgCodeErr = '请输入图形验证码';
						return 
					}else if(!this.upInfo.mobile_code){
						this.mobile_codeErr = '请输入短信验证码';
						return 
					}
					var config = {
						telephone:this.upInfo.newPhone,
						mobile_code:this.upInfo.mobile_code,
					}
					ajaxUser.editPhone(config,function(res){
						if(res.code==200){
							that.$root.$refs.basePageChild.getUserInfo();
							that.dramImg();
							that.closeData();
						}
						that.message(res.message,res.code==200);
					},'get')
				},
				
				//清空数据
				closeData:function(){
					this.upInfo.newPhone = '';
					this.upInfo.imgCode = '';
					this.upInfo.mobile_code = '';
				}
            }
        };
        return accountSet;
    });
