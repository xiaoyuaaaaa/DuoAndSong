if(typeof define !== 'function') {
    var define = require('amdefine')(module);
}

define(["text!../../views/components/emailCheck.html", "$", 'info', 'ajaxUser', "css!../../css/components/accountSet.css"],
    function(textModule, $, info, ajaxUser) {
        var accountSet = {
            template: textModule,
			mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                	this.userInfo = this.$root.userinfo;
                	this.upInfo = this.$root.userinfo;
                })
            },
            data: function() {
                return {
                	DrawImage: new Date().getTime(),
                	userInfo:{
                		email:'',
                		imgCode:'',
						email_code:'',
                	},
                	emailChange:0,
					upInfo:{
						email:'',
						newEmail:'',
						imgCode:'',
						check:'',
					},
					imgCodeErr:'',
					email_codeErr:'',
					newEmailErr:'',
					newImgCodeErr:'',
					newEmail_codeErr:'',
                }
            },
            methods: {
				//切换验证图片
				dramImg: function(){
					this.DrawImage = new Date().getTime(); 
					this.upInfo.imgCode = '';
					this.userInfo.imgCode = '';
				},
				//发送邮箱验证Email
				sendCheckEmail:function(){
					var that = this;
					if(!this.userInfo.imgCode){
						this.imgCodeErr = '请输入图形验证码';
						return 
					}
					ajaxUser.sendCheckEmail(this.userInfo,function(res){
						if(res.code==200){
							that.message('邮件验证码已发送，请登录邮箱查收',res.code==200);
						}else{
							that.dramImg();
							that.message(res.message,res.code==200);
						}
					},'get')
				},
				//邮箱验证
				checkEmail:function(){
					var that = this;
					if(!this.userInfo.email_code){
						this.email_codeErr = '请输入邮箱验证码';
						return 
					} 
					ajaxUser.checkEmail(this.userInfo,function(res){
						if(res.code==200){
							that.$root.userinfo.isSuccess = 1;
							that.$root.$refs.basePageChild.getUserInfo();
							that.dramImg();
						}
						that.message(res.message,res.code==200);
					},'get')
				},
				//修改邮箱发送Email
				sendEidtEmail:function(){
					var that = this;
					if(!this.upInfo.newEmail){
						this.newEmailErr = '请输入新邮箱';
						return 
					}else if(!this.upInfo.imgCode){
						this.newImgCodeErr = '请输入图形验证码';
						return 
					}
					var config = {
						email:this.upInfo.newEmail,
						imgCode:this.upInfo.imgCode,
					}
					ajaxUser.sendEidtEmail(config,function(res){
						if(res.code==200){
							that.message('邮件验证码已发送，请登录邮箱查收',res.code==200);
						}else{
							that.dramImg();
							that.$message.error(res.message);
						}
					},'get')
				},
				//修改邮箱
				editEmail:function(){
					var that = this;
					if(!this.upInfo.newEmail){
						this.newEmailErr = '请输入新邮箱';
						return 
					}else if(!this.upInfo.imgCode){
						this.newImgCodeErr = '请输入图形验证码';
						return 
					}else if(!this.upInfo.email_code){
						this.newEmail_codeErr = '请输入邮箱验证码';
						return 
					} 
					var config = {
						email:this.upInfo.newEmail,
						email_code:this.upInfo.email_code,
					}
					ajaxUser.editEmail(config,function(res){
						if(res.code==200){
							that.$root.userinfo.isSuccess = 1;
							that.$root.$refs.basePageChild.getUserInfo();
							that.dramImg();
							that.closeData();
						}else{
							that.dramImg();
						}
						that.message(res.message,res.code==200);
					},'get')
				},
				//清空数据
				closeData:function(){
					this.upInfo.newEmail = '';
					this.upInfo.imgCode = '';
					this.upInfo.email_code = '';
				}
            }
        };
        return accountSet;
    });
