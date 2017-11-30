require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage','pagination', 'ajaxData','leftPage'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage,pagination, ajaxData,leftPage) {
        "use strict";

        Vue.use(ELEMENT);

        var app = new Vue({
            el: '#app',
            mounted: function () {
                var that = this
                this.$nextTick(function () {
                    that.getNoticeList()
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'pagination':pagination,
                'left-page': leftPage,
            },
            data: function () {
                return {
                    userinfo: {
                    },
                    notice:{
                        page:1,
                        pageSize:30,
                    },
                    listData:[],
                    noticeDetail:{
                        noticeImg:''
                    },
                    bigimg:false,
                    bigImgUrl:'',
                    noticeAllId:[],
                    checkedNotices:[],
                    checkAll:false
                }
            },
            methods: {
                changePageSize:function (pageSize) {
                    this.notice.page = 1;
                    this.notice.pageSize = pageSize;
                    this.getNoticeList()
                },
                changePage:function (page) {
                    this.notice.page = page;
                    this.getNoticeList()
                },
                //获取消息通知列表
                getNoticeList:function () {
                    var that = this;
                    ajaxData.getNoticeList(that.notice,function (response) {
                        if(response.success){
                            that.notice = response.pageModel;
                            that.listData = response.listData;
                            that.noticeAllId=[];
                            for (var i in that.listData){
                                that.noticeAllId.push(that.listData[i].id)
                            }
                        }
                    })
                },
                //查看详情
                viewDetail:function (id) {
                    var that = this;
                    that.editNoticeStatus(id);
                    ajaxData.getNoticeInfo({noticeId:id},function (response) {
                        if(response.success){
                            that.bigimg = true;
                            that.noticeDetail = response.data
                        }else {
                            that.$message.error(response.message)
                        }
                    })
                },
                //全部已读
                allRead:function () {
                    var that = this;
                    if(that.checkedNotices.length==0){
                        that.$message.error('请选择消息通知')
                    }else {
                        that.editNoticeStatus(that.checkedNotices.join(','),true)
                    }


                },
                //改变状态
                editNoticeStatus:function (id,type) {
                    var that = this;

                    ajaxData.editNoticeStatus({noticeIds:id},function (response) {
                        if(type){
                            that.checkedNotices=[];
                            that.checkAll = false;
                            that.$message.success('操作成功')
                        }
                        that.getNoticeList()
                    })
                },
                handleCheckAllChange(event) {
                    this.checkedNotices = event.target.checked ? this.noticeAllId : [];
                },
                handleCheckedNoticeChange:function (value) {
                    let checkedCount = value.length;
                    this.checkAll = checkedCount === this.noticeAllId.length;
                }

            },

        })
    });
