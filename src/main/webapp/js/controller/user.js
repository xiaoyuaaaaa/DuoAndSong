require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage', 'leftPage', 'ajaxUser','ajaxData','webuploader'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage, leftPage, ajaxUser,ajaxData,webuploader) {
        "use strict";

        Vue.use(ELEMENT);
        Vue.use(VueRouter);

        /*var router = new VueRouter({
         routes: routerConfig
         });*/
        var handle;//手机验证码计时器
        var app = new Vue({
            el: '#app',
//  router: router,
            mounted: function () {
                var that = this;
                this.$nextTick(function () {
                    this.getBindStatus();
                    //上传头像
                    that.userAvatar = new webuploader.create({
                        auto: true,// 选完文件后，需手动上传。
                        swf: '/js/lib/webuploader-0.1.5/Uploader.swf',// swf文件路径
                        server: '/upload/heandImgUpload',// 文件接收服务端。
                        pick: {
                            id: '#avatar',
                            // innerHTML: '',
                            multiple: false	//false:只允许单选，true:多选
                        },// 选择文件的按钮。可选。
                        accept: {// 只允许选择图片文件。
                            title: 'Images',
                            extensions: 'gif,jpg,jpeg,bmp,png',
                            mimeTypes: 'image/jpg,image/jpeg,image/png,image/bmp,image/gif'
                        },
                        fileSingleSizeLimit: 1024 * 1024 * 2,
                        fileVal: 'file',	//设置文件上传域的name
                        duplicate: true	//去重
                    });
                    // 当有文件添加进来的时候
                    that.userAvatar.on('fileQueued', function (file) {
                        if (file.type.indexOf('image') < 0) {
                            that.message('请选择图片文件!', false);
                            that.userAvatar.removeFile(file);
                            return false;
                        }
                        if (that.fileLogoId) {
                            that.userAvatar.removeFile(that.fileLogoId);
                        }
                        that.fileLogoId = file.id;
                        that.logoUpstatr = true;

                        that.userAvatar.makeThumb(file, function (error, src) {
                            if (error) {
                                $img.replaceWith('<span>不能预览</span>');
                                return;
                            } else {
                                $("#avatar img").attr('src', src);
                            }
//				        $img.attr( 'src', src );
                        }, 1, 1);
                    });

                    // 当有文件添加进来的时候
                    that.userAvatar.on('uploadError', function (file) {
                        that.message('上传出错', false);
                    });
                    //文件选择失败反馈
                    that.userAvatar.on('error', function (type) {
                        switch (type) {
                            case 'Q_EXCEED_NUM_LIMIT':
                                that.message('错误：上传文件数量过多！', false);
                                break;
                            case 'Q_EXCEED_SIZE_LIMIT':
                                that.message('错误：文件总大小超出限制！', false);
                                break;
                            case 'F_EXCEED_SIZE':
                                that.message('错误：文件大小超出限制！', false);
                                break;
                            case 'Q_TYPE_DENIED':
                                that.message('错误：禁止上传该类型文件！', false);
                                break;
                            case 'F_DUPLICATE':
                                that.message('错误：请勿重复上传该文件！', false);
                                break;
                            default:
                                that.message('上传出错！请检查后重新上传！', false);
                                break;
                        }
                    });
                    //上传成功
                    that.userAvatar.on('uploadSuccess', function(file,data) {
                        that.userinfo.heandImg = data.data
                    });
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'left-page': leftPage,
            },
            data: function () {
                var that = this;
                var validatePass = function (rule, value, callback) {
                    var checkPwd = tool.verifyPwd(value);
                    if (!checkPwd.flag) {
                        callback(new Error(checkPwd.message));
                    } else {
                        callback();
                    }
                };
                var validataPhone = function (rule, value, callback) {
                    var checkPhone = tool.verifyMobile(value);
                    if (!checkPhone.flag) {
                        callback(new Error(checkPhone.message));
                    } else {
                        callback();
                    }
                }
                return {
                    userAvatar:'',
                    userinfo: {
                        heandImg:''
                    },
                    passwordForm: {
                        oldPassWord: '',
                        newPassWord: '',
                        confirmPassWord: ''
                    },
                    upInfo: {
                        email: '',
                        newEmail: '',
                        imgCode: '',
                        check: '',
                    },
                    checkEmail: {
                        email: '',
                        imgCode: '',
                        email_code: '',
                    },
                    phone: {
                        telephone: '',
                        newPhone: '',
                        imgCode: '',
                        mobile_code: '',
                    },
                    DrawImage: new Date().getTime(),
                    areaName: info.getIndustry(),
                    rules: {
                        name: [
                            {required: true, message: '请输入你的姓名', trigger: 'blur'},
                        ],
                        oldPassWord: [
                            {required: true, message: '请输入当前密码', trigger: 'blur'},
                            {validator: validatePass, trigger: 'blur'}
                        ],
                        newPassWord: [
                            {required: true, message: '请输入新密码', trigger: 'blur'},
                            {validator: validatePass, trigger: 'blur'}
                        ],
                        confirmPassWord: [
                            {
                                validator: function (rule, value, callback) {
                                    if (value === '') {
                                        callback(new Error('请再次输入新的密码'));
                                    } else if (value !== that.passwordForm.newPassWord) {
                                        callback(new Error('两次输入密码不一致!'));
                                    } else {
                                        callback();
                                    }
                                }, trigger: 'blur', required: true
                            }
                        ],
                        newPhone: [{validator: validataPhone, trigger: 'blur'}]
                    },
                    status:{
                        userinfo:false,
                        password:false,
                        email:false,
                        phone:false,
                        checkEmail:false

                    },
                    imgCodeErr: '',
                    email_codeErr: '',
                    newEmailErr: '',
                    newImgCodeErr: '',
                    newEmail_codeErr: '',
                    supCode: '发送验证',
                    codeBtnType: 0,
                    newPhoneErr: '',
                    mobile_codeErr: '',

                    //企业认证
                    bind:{
                        type:'',//绑定类型 1 前程无忧、2 智联、3 拉钩、4 赶集、5 英才
                        compName:'',//会员名
                        userName:'',//用户名
                        pwd:'',//密码
                    },
                    bindStatus:{
                        qc:'',
                        zl:'',
                        lg:'',
                        gj:'',
                        wb:'',
                        lp:'',
                        yc:''
                    },
                    qcBind:false,
                    zlBind:false,
                    tcBind:false,
                    lpBind:false,
                    lgBind:false,
                    gjBind:false,
                    ycBind:false,
                    compNameErr:'',
                    userNameErr:'',
                    zlErrText:'',
                    qcErrText:'',
                    pwdErr:'',
                    supmit:false // 重复提交
                }
            },
            methods: {
                /*编辑用户信息*/

                submitForm: function (formName) {
                    var that = this;
                    this.$refs[formName].validate(function (valid) {
                        if (valid) {
                            if (formName == 'userinfo') {
                                that.userinfo.QQ = that.userinfo.qq;
                                ajaxUser.editBaseInfo(that.userinfo, function (res) {
                                    if (res.code == 200) {
                                        that.$root.$refs.basePageChild.getUserInfo();
                                    }
                                    that.message(res.message, res.code == 200)
                                })
                            }
                            if (formName == 'passwordForm') {
                                ajaxUser.editPassword(that.passwordForm, function (response) {
                                    if (response.code == 200) {
                                        that.$message.success('密码修改成功');
                                        that.$refs.passwordForm.resetFields();
                                    } else {
                                        that.$message.error(response.message)
                                    }
                                })
                            }
                            if (formName == 'upInfo') {
                                if (!that.upInfo.newEmail) {
                                    that.newEmailErr = '请输入新邮箱';
                                    return
                                } else if (!that.upInfo.imgCode) {
                                    that.newImgCodeErr = '请输入图形验证码';
                                    return
                                } else if (!that.upInfo.email_code) {
                                    that.newEmail_codeErr = '请输入邮箱验证码';
                                    return
                                }
                                var config = {
                                    email: that.upInfo.newEmail,
                                    email_code: that.upInfo.email_code,
                                }
                                ajaxUser.editEmail(config, function (res) {
                                    if (res.code == 200) {
                                        that.$message.success('邮箱修改成功！');
                                        that.$refs.upInfo.resetFields();
                                        that.upInfo.email_code = '';
                                        that.$root.userinfo.isSuccess = 1;
                                        that.$root.$refs.basePageChild.getUserInfo();
                                        that.dramImg();

                                    } else {
                                        that.dramImg();
                                        that.$message.error(res.message)
                                    }

                                }, 'get')
                            }
                            if (formName == 'checkEmail') {
                                if (!that.checkEmail.email_code) {
                                    that.email_codeErr = '请输入邮箱验证码';
                                    return
                                }
                                ajaxUser.checkEmail(that.checkEmail, function (res) {
                                    if (res.code == 200) {
                                        that.checkEmail.email_code = ''
                                        that.$root.userinfo.isSuccess = 1;
                                        that.$root.$refs.basePageChild.getUserInfo();
                                        that.dramImg();
                                    }
                                    that.message(res.message, res.code == 200);
                                }, 'get')
                            }
                            if (formName == 'phone') {

                                if(!that.phone.newPhone){
                                    that.newPhoneErr = '请输入新手机号';
                                    return
                                }else if(!that.phone.imgCode){
                                    that.imgCodeErr = '请输入图形验证码';
                                    return
                                }else if(!that.phone.mobile_code){
                                    that.mobile_codeErr = '请输入短信验证码';
                                    return
                                }
                                var config = {
                                    telephone:that.phone.newPhone,
                                    mobile_code:that.phone.mobile_code,
                                }
                                ajaxUser.editPhone(config,function(res){
                                    clearInterval(handle);
                                    that.supCode = '发送验证';
                                    that.codeBtnType = 0;
                                    that.dramImg();
                                    if(res.code==200){
                                        that.$message.success('手机修改成功!');
                                        that.$refs.phone.resetFields();
                                        that.$root.$refs.basePageChild.getUserInfo();
                                        that.closeData();
                                    }else {
                                        that.$message.error(res.message)
                                    }

                                },'get')
                            }
                        }
                    })
                },
                //切换验证图片
                dramImg: function () {
                    this.DrawImage = new Date().getTime();
                    this.upInfo.imgCode = '';
                    this.checkEmail.imgCode = '';
                },
                //修改邮箱发送Email
                sendEidtEmail: function () {
                    var that = this;
                    if (!this.upInfo.newEmail) {
                        this.newEmailErr = '请输入新邮箱';
                        return
                    } else if (!this.upInfo.imgCode) {
                        this.newImgCodeErr = '请输入图形验证码';
                        return
                    }
                    var config = {
                        email: this.upInfo.newEmail,
                        imgCode: this.upInfo.imgCode,
                    }
                    ajaxUser.sendEidtEmail(config, function (res) {
                        if (res.code == 200) {
                            that.message('邮件验证码已发送，请登录邮箱查收', res.code == 200);
                        } else {
                            that.dramImg();
                            that.message(res.message, res.code == 200);
                        }
                    }, 'get')
                },
                //发送邮箱验证Email
                sendCheckEmail: function () {
                    var that = this;
                    if (!this.checkEmail.imgCode) {
                        this.imgCodeErr = '请输入图形验证码';
                        return
                    }
                    ajaxUser.sendCheckEmail(this.checkEmail, function (res) {
                        if (res.code == 200) {
                            that.$message.success('邮件验证码已发送，请登录邮箱查收');
                        } else {
                            that.dramImg();
                            that.$message.error(res.message);
                        }
                    }, 'get')
                },
                //发送短信验证码
                sendSMS: function () {
                    var that = this;
                    if(this.codeBtnType){
                        this.message('请稍候重试',0);
                        return
                    }
                    if(!this.phone.newPhone){
                        this.newPhoneErr = '请输入新手机号';
                        return
                    }else if(!this.phone.imgCode){
                        this.imgCodeErr = '请输入图形验证码';
                        return
                    }
                    var config = {
                        mobile:this.phone.newPhone,
                        imgCode:this.phone.imgCode,
                    };

                    ajaxUser.sendSMS(config,function(res){
                        if(res.code==305){
                            that.dramImg();
                            that.imgCodeErr = '验证码错误';
                        }else if(res.code!=200){
                            that.dramImg();
                            that.imgCodeErr = res.message;
                        }else{
                            that.supCode = '发送中...';
                            that.countTime();
                            that.codeBtnType = 1
                        }
                    })
                },
                //短信倒计时
                countTime: function () {
                    var time = 59;
                    var that = this;
                    that.supCode = time + '秒可重发'

                    handle = setInterval(function () {
                        time--;
                        that.supCode = time + '秒后重发'
                        if (time < 1) {
                            clearInterval(handle);
                            that.supCode = '发送验证';
                            that.codeBtnType = 0;
                        }
                    }, 1000);
                },

                //企业认证
                accountBinding:function(type){
                    var that = this;
                    this.bind.type = type;
                    if(type==1){
                        if(!this.bind.compName){
                            this.compNameErr = '请输入会员名';
                            return
                        }
                    }
                    if(!this.bind.userName){
                        this.userNameErr = '请输入用户名';
                        return
                    }else if(!this.bind.pwd){
                        this.pwdErr = '请输入密码';
                        return
                    }

                    if(this.supmit) return;
                    that.supmit = true;
                    console.log(that.bind);
                    ajaxData.accountBinding(that.bind,function (response) {
                        that.supmit = false;
                        if(response.code==200){
                            that.getBindStatus()
                            that.$message.success('绑定成功！')
                            if(type==1){
                                that.qcBind = false;
                            }else if(type==2){
                                that.zlBind = false;
                            }else if(type==3){
                                that.lgBind = false;
                            }else if(type==4){
                                that.gjBind = false;
                            }else if(type==5){
                                that.ycBind = false;
                            }

                        }else {
                            that.$message.error(response.message)
                        }

                    })
                },
                handleClose:function (done) {
                    this.compNameErr='';
                    this.userNameErr='';
                    this.zlErrText='';
                    this.qcErrText='';
                    this.pwdErr='';
                    this.supmit = false;
                    done();
                    console.log(this.supmit)

                },
                getBindStatus:function () {
                    var that = this;
                    ajaxData.getUserBingdingStatus(function (response) {
                        that.bindStatus = response
                    })
                },

            },
            watch: {},
            computed: {}
        })
    });
