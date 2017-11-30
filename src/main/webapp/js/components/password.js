if(typeof define !== 'function') {
    var define = require('amdefine')(module);
}

define(["text!../../views/components/password.html", "$", 'info', "ajaxUser","tool","css!../../css/components/accountSet.css"],
    function(textModule, $, info,ajaxUser,tool) {
        var password = {
            template: textModule,
            data: function() {
            	var that = this;
            	var validatePass = function (rule, value, callback) {
                    var checkPwd = tool.verifyPwd(value);
                    if (!checkPwd.flag) {
                        callback(new Error(checkPwd.message));
                    } else {
                        callback();
                    }
                };
                return {
                    name:this.$root.userinfo.name,
                	userInfo:{
                        oldPassWord :'',
                        newPassWord :'',
                        confirmPassWord:''
                	},
					rules:{
                        oldPassWord :[
							{ required: true, message: '请输入当前密码', trigger: 'blur'},
                            {validator:validatePass,trigger: 'blur'}
						],
                        newPassWord :[
                            { required: true, message: '请输入新密码', trigger: 'blur'},
                            {validator:validatePass,trigger: 'blur'}
                        ],
                        confirmPassWord:[
                            { validator:function (rule, value, callback) {
                                if (value === '') {
                                    callback(new Error('请再次输入新的密码'));
                                } else if (value !== that.userInfo.newPassWord ) {
                                    callback(new Error('两次输入密码不一致!'));
                                } else {
                                    callback();
                                }
                            }, trigger: 'blur',required: true}
                        ]
					}
                }
            },
            methods: {
                modifyPwd:function () {
                    var that = this;
                    that.$refs.userInfo.validate(function (valid) {
                        if (valid) {
                        	//验证成功
                            ajaxUser.editPassword(that.userInfo,function (response) {
                                if(response.code==200){
                                    that.$message.success('密码修改成功');
                                    that.$refs.userInfo.resetFields();
                                }else {
                                    that.$message.error(response.message)
                                }
                            })
                        } else {
                            return false;
                        }
                    })
                }
            }
        };
        return password;
    });
