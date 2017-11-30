if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}
define(['tool'], function(tool) {
	var domain = 'http://ats.yifengjianli.com';
	var ajaxUser = {
		/*导入51*/
		triggerExportJobResume:function (successFn) {
			tool.ajaxSilent(domain+'/resume/triggerExportJobResume',{},successFn);
		},
		//请求PV、UV
		addPvUv:function (data,successFn) {
			tool.ajaxSilent('/pvuv/addPvUv',data,successFn);
		},
		/*登录*/
		userLogin:function(data,successFn){
			tool.ajaxSilent('/login/userLogin',data,successFn);
		},
		/*退出*/
		userCancel:function(successFn){
			tool.ajax2({
				url: '/user/userCancel',
				type: 'get',
				success: successFn
			});
		},
		/*发送短信验证*/
		sendSMS:function(data,successFn){
			tool.ajaxSilent('/sms/sendSMS',data,successFn);
		},
		/*用户注册接口*/
		userRegist:function(data,successFn){
			tool.ajaxSilent('/regist/userRegist',data,successFn);
		},
		//用户信息
    getUserInfo:function (successFn) {
      //tool.ajax('/user/getUserInfo',data,successFn,'','',reway);
      tool.ajax2({
      	url: '/user/getUserInfo',
				type: 'get',
				success: successFn,
				loading: false
      });
    },
    	/*修改用户基本信息*/
		editBaseInfo:function(data,successFn){
			tool.ajaxSilent('/edit/editBaseInfo',data,successFn);
		},
		//修改密码
        editPassword:function (data,successFn) {
            tool.ajax('/edit/editPassword',data,successFn);
        },
		//发送邮箱验证Email
        sendCheckEmail:function (data,successFn,reway) {
            tool.ajax('/user/sendCheckEmail',data,successFn,'','',reway);
        },
        //邮箱验证
        checkEmail:function (data,successFn,reway) {
            tool.ajax('/user/checkEmail',data,successFn,'','',reway);
        },
        //修改邮箱发送Email
        sendEidtEmail:function (data,successFn,reway) {
            tool.ajax('/edit/sendEidtEmail',data,successFn,'','',reway);
        },
        //邮箱修改
        editEmail:function (data,successFn,reway) {
            tool.ajax('/edit/editEmail',data,successFn,'','',reway);
        },
        //手机修改
        editPhone:function (data,successFn,reway) {
            tool.ajax('/edit/editPhone',data,successFn,'','',reway);
        },
        //找回密码
        forgotPassword:function (data,successFn) {
            tool.ajax('/forget/forgotPassword',data,successFn);
        },
	};

	return ajaxUser;
});