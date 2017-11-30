require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage', 'pagination','ajaxData','ajaxUser','Clipboard'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage, pagination,ajaxData,ajaxUser,Clipboard) {
        "use strict";

        Vue.use(ELEMENT);
        var app = new Vue({
            el: '#app',
            mounted: function () {
                var that = this;
                this.$nextTick(function () {
                    this.getGoldLogs();
                    var clipboard = new Clipboard('#copyBtn');
                    clipboard.on('success', function(e) {
                        that.$message.success('链接复制成功！');
                        e.clearSelection();
                    });
                    clipboard.on('error', function(e) {
                        that.$message.error('链接复制失败，请手动复制！');
                    });
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'pagination': pagination,
            },
            data: function () {
                return {
                    userinfo: {
                        userId:''
                    },
                    pageDes: {
                        name: '金币记录',
                        des: ''
                    },
                    type:true,
                    recordList:{
                        pageSize:'30',
                        page:'1',
                        totalPage:''
                    },
                    logList:[],
                    invitation:{
                        dialog:false,
                    }
                }
            },
            methods: {
                changePage: function (page) {
                    this.recordList.page = page;
                    this.getGoldLogs()
                },
                //处理分页每页显示条数请求
                changePageSize: function (pageSize) {
                    this.recordList.page = 1;
                    this.recordList.pageSize = pageSize;
                    this.getGoldLogs()
                },

                getGoldLogs:function () {
                    var that = this;
                    ajaxData.getGoldLogs(this.recordList,function (response) {
                        if(response.code==200){
                            that.logList = response.logList;
                            that.recordList.pageSize = response.pageSize;
                            that.recordList.page = response.page;
                            that.recordList.totalPage = response.totalPage;
                        }
                    })
                },
                _match:function (status) {
                    var result;
                    switch (status){
                        case 1:result='注册送金币';
                            break;
                        case 2:result='获取消耗金币';
                            break;
                        case 3:result='每日登录送金币';
                            break;
                        case 4:result='邮箱验证送金币';
                            break;
                        case 5:result='智联绑定送金币';
                            break;
                        case 6:result='前程绑定送金币';
                            break;
                        case 7:result='拉勾绑定送金币';
                            break;
                        case 8:result='赶集绑定送金币';
                            break;
                        case 9:result='58绑定送金币';
                            break;
                        case 10:result='猎聘绑定送金币';
                            break;
                        case 11:result='中华英才绑定送金币';
                            break;
                        case 12:result='好友推荐送金币';
                            break;
                    }
                    return result;
                }
            },

        })
    });
