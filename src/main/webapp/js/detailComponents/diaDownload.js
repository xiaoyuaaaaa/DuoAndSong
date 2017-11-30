if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
define(["text!../../views/detailComponents/diaDownload.html","$", 'loadash',
        'ajaxResumeDetail', 'tool','info'],
    function (textModule, $,_, ajaxResumeDetail, tool,info) {
        var Detail = {
            template: textModule,
            mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                });
            },
            data: function () {
            	return{
                    download:{
                        dialog:false,
                        downFormat:'word',
                        downReady:false,
                        downLength: 0
                    },//下载简历
            	}
            },
            methods: {
            	
              	/*关闭下载*/
                downloadClose:function () {
                    var that = this;
                    that.download.dialog = false;
                    setTimeout(function () {
                        that.download.downReady = false;
                    },500)
                },
                /*选择下载类型下一步*/
               /*
              excel
								（单份、多份）准备：/downLoad/createExcel
								（单份、多份）下载：/downLoad/downloadBatchResume
								
							word/pdf
								单份准备：/downLoad/createPdfWord
								单份下载：/downLoad/downloadSingleResume
								
								多份准备：/downLoad/createPdfWordZip
								多份下载：/downLoad/downloadBatchResume
							*/
                downNext:function () {
                    var that = this
                    var downloadData = {
                        userId:that.$root.resumeData.resumeNumber,
                        type:that.download.downFormat
                    };
                    this.downLength = downloadData.userId.split(',').length;
                    if(that.download.downFormat=='word'||that.download.downFormat=='pdf'){
                    	if(this.downLength < 2){
                    		ajaxResumeDetail.createPdfWord(downloadData,function (response) {
                    		    if(response.code==200){
                    		        that.download.downReady = true
                    		    }
                    		},'get');
                    	}else{
                    		ajaxResumeDetail.createPdfWordZip(downloadData,function (response) {
                    		    if(response.code==200){
                    		        that.download.downReady = true
                    		    }
                    		},'get');
                    	}
                    }else if(that.download.downFormat=='excel'){
                        ajaxResumeDetail.createExcel(downloadData,function (response) {
                            if (response.code==200){
                                that.download.downReady = true
                            }
                        },'get');
                    }
                },
            }
        };
        return Detail;
    });