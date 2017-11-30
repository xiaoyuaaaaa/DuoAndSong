require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage','pagination', 'ajaxData'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage,pagination, ajaxData) {
        "use strict";

        Vue.use(ELEMENT);

        var app = new Vue({
            el: '#app',
            mounted: function () {
                var that = this;
                this.$nextTick(function () {
                    that.getNoticeList();
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'pagination':pagination
            },
            data: function () {
                return {
                    userinfo: {
                    },
                    pageDes: {
                        name: '消息通知'
                    },
                    pageModel:{
                        page:1,
                        pageSize:30
                    },
                    listData:[],
                    bigimg:false,
                    noticeDetail:{},
                    noticeAllId:[],
                }
            },
            methods: {
                changePageSize:function (pageSize) {
                    this.pageModel.page = 1;
                    this.pageModel.pageSize = pageSize;
                    this.getNoticeList()
                },
                changePage:function (page) {
                    this.pageModel.page = page;
                    this.getNoticeList()
                },
                //获取消息通知
                getNoticeList:function () {
                    var that = this;
                    ajaxData.getNoticeList(that.pageModel,function (response) {
                        if(response.success){
                            that.listData = response.listData;
                            that.pageModel = response.pageModel;
                            that.noticeAllId = [];

                            //展示更多详情
                            for (var i in that.listData){
                                that.noticeAllId.push(that.listData[i].id);
                                that.$set(that.listData[i],'more',false);
                                if(that.listData[i].title.length+that.listData[i].content.length<60){
                                    that.$set(that.listData[i],'more',true);
                                }
                            }
                            that.editNoticeStatus();
                            that.$root.$refs.basePageChild.getUserInfo();
                        }
                    })
                },
                //获取消息详情
                getNoticeInfo:function (id,img) {
                    var that = this;
                    if (img){
                        ajaxData.getNoticeInfo({noticeId:id},function (response) {
                            that.noticeDetail = response.data;
                            that.bigimg = true;
                        })
                    }

                },
                //改变消息状态
                editNoticeStatus:function () {
                    var that = this;
                    ajaxData.editNoticeStatus({noticeIds:that.noticeAllId.join(',')})
                },
                moreCon:function (item) {
                    item.more = true;
                    var that = this;
                    for(var i in that.listData){
                        if (item.id == that.listData[i].id){
                            that.listData[i]=item
                        }
                    }
                }
            },
        })
    });
