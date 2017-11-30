if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
define(["text!../../views/detailComponents/detailTop.html","$", 'loadash',
        'ajaxResumeDetail', 'tool','info'],
    function (textModule, $,_, ajaxResumeDetail, tool,info) {
        var Detail = {
            template: textModule,
            mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                	if(!tool.url.get('source','search')){
                		if(window.location.href.indexOf('/cv/cv_detail')!=-1){
                			this.source = 'rc';
                		}
                	}
                	if(tool.url.get('l','search')){
                		this.rollingPX = parseInt(tool.url.get('l','search'))
                		$('.tabs-list').css({'margin-left':-this.rollingPX})
                	}
                });
            },
            data: function () {
            	return{
            		tool:tool,
                    getGtype:info.getGtype(),
                    qqfenxUrl:'',
                    detailInfoOriginal:{},
                    rollingPX: 0,
                    rabsList:tool.LocalStorage.get('rabsList')||'',
                    rabsActive:tool.url.get('userId','search'),
                    keyword:tool.url.get('keyword','search'),
                    resumeMore: parseInt(tool.url.get('more','search')),
                    searchId:tool.url.get('searchId','search'),
                    source:tool.url.get('source','search')||'rc',
                    token:tool.url.get('token','search'),
            	}
            },
            methods: {
            	//头部a标签连接地址
            	tabsHref:function(item,index){
            		var ml = this.rollingPX
            		if(this.rabsList.length>index+1&&ml/175+5==index+1){
            			ml = ml+175
            		}else if(this.rollingPX&&ml/175==index){
            			ml = ml-175
            		}
            		window.location.href = '/cv/cv_detail?userId='+item.userId+'&searchId='+this.searchId+'&source='+this.source+'&token='+this.token+'&more=1&l='+ml+'&keyword='+this.keyword
            	},
            	//暂存简历
                addTemporary:function () {
                    var that = this;
                    if(!that.$parent.detailInfo.isStorage){
                        ajaxResumeDetail.resumeTemporary(that.$root.resumeData,function (response) {
                            if(response.code==200){
                            	that.$parent.getResumeInfo();
                            }
                            that.message(response.message,response.code==200);
                        })
                    }

                },
                
                //取消暂存
                cancelStorageCv:function () {
                    var that = this;
                    if(that.$parent.detailInfo.isStorage){
                        ajaxResumeDetail.cancelStorageCv({ids:that.$parent.detailInfo.isStorage},function (response) {
                            if(response.code==200){
                                that.$parent.detailInfo.isStorage = 0
                            }
                            that.message(response.message,response.code==200);
                        })
                    }

                },
                
	            /*滚动标签*/
	            rolling:function(type){
	            	if(type=='+'){
	            		if(this.rabsList.length*175-175*5 <= this.rollingPX) return
	            		this.rollingPX = this.rollingPX+175
	            	}else if(type=='-'){
	            		if(!this.rollingPX) return
	            		this.rollingPX = this.rollingPX-175;
	            	}
	            	$('.tabs-list').animate({'margin-left':-this.rollingPX},150)
	            },
	            
	            /*删除Item*/
	            deleteItem:function(index){
	            	if(this.rabsList[index].userId == this.$root.resumeData.userId){
	            		this.rabsList.splice(index,1);
	            		tool.LocalStorage.set('rabsList',this.rabsList)
	            		if(this.rabsList.length<=index+1){
	            			var item = this.rabsList[0];
	            			this.rollingPX = 0;
	            		}else{
	            			var item = this.rabsList[index];
	            		}
	            		window.location.href = '/cv/cv_detail?userId='+item.userId+'&searchId='+this.searchId+'&source='+this.source+'&token='+this.token+'&more=1&l='+this.rollingPX+'&keyword='+this.keyword
	            	}else{
	            		this.rabsList.splice(index,1);
	            		tool.LocalStorage.set('rabsList',this.rabsList)
	            	}
	            },
	            
            }
        };
        return Detail;
    });