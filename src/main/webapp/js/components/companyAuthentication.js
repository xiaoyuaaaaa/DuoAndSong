if(typeof define !== 'function') {
    var define = require('amdefine')(module);
}

define(["text!../../views/components/companyAuthentication.html", "$","ajaxData"],
    function(textModule, $,ajaxData) {
        var companyAuthentication = {
            template: textModule,
            mounted:function () {
                this.getBindStatus()
            },
            data: function() {
                return {
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
                }
            }
        };
        return companyAuthentication;
    });
