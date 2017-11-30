requirejs(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', 'ajaxUser', "basePage", 'headerPage', 'userRouter', 'webuploader'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, ajaxUser, basePage, headerPage, userRouter, webuploader) {
        "use strict";

        Vue.use(ELEMENT);
        Vue.use(VueRouter);

        var router = new VueRouter({
            routes: userRouter
        });

        var app = new Vue({
            el: '#app',
            router: router,
            mounted: function () {
                this.$nextTick(function () {
                    var that = this;
                    var hash = window.location.hash;
                    if(hash=="#/user/companyAuth"){
                        that.pagename='companyAuth'
                    }else {
                        that.pagename=''
                    }
                    router.beforeEach(function (to,from,next) {
                        if(to.path=="/user/companyAuth"){
                            that.pagename='companyAuth'
                        }else {
                            that.pagename=''
                        }
                        next()
                    });
                    /*获取用户信息*/
                    this.getUserInfo();
                    //上传头像
                    this.userAvatar = new webuploader.create({
                        auto: true,// 选完文件后，需手动上传。
                        swf: '/js/lib/webuploader-0.1.5/Uploader.swf',// swf文件路径
                        server: '/upload/heandImgUpload',// 文件接收服务端。
                        pick: {
                            id: '#avatarBtn,#avatar',
                            // innerHTML: '',
                            multiple: false	//false:只允许单选，true:多选
                        },// 选择文件的按钮。可选。
                        accept: {// 只允许选择图片文件。
                            title: 'Images',
                            extensions: 'gif,jpg,jpeg,bmp,png',
                            mimeTypes: 'image/jpg,image/jpeg,image/png,image/bmp,image/gif'
                        },
                        fileSingleSizeLimit: 1024 * 1024 * 3,
                        fileVal: 'file',	//设置文件上传域的name
                        duplicate: true	//去重
                    });
                    // 当有文件添加进来的时候
                    this.userAvatar.on('fileQueued', function (file) {
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
                    this.userAvatar.on('uploadError', function (file) {
                        that.message('上传出错', false);
                    });
                    //文件选择失败反馈
                    this.userAvatar.on('error', function (type) {
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
                    this.userAvatar.on('uploadSuccess', function(file,data) {
                        if(data.code == 200){
                            that.$message.success('头像上传成功！')
                        }else{
                            that.$message.error(data.message)
                        }
                    });
                });
                // ajaxUser.getHeadImg(function (response) {
                //
                // })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
            },
            data: function () {
                return {
                    userinfo: {},
                    pageDes: {
                        name: '个人中心',
                        des: ''
                    },
                    pagename:''
                }
            },
            methods: {
            	/*获取用户信息*/
            	getUserInfo:function(){
            		var that = this;
            		ajaxUser.getUserInfo({},function(response){
        				if (response.code==200){
	                    	that.userinfo = response;
        				}
            		},'get');
            	}
            	
            },
            watch: {},
            computed: {}
        })
    });
