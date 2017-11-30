require([
        '$',
        'vue',
        'vueMethods',
        "detailInfo",
        "diaConfirm",
        "diaPayment",
        "diaDownload",
        "ELEMENT",
        "tool",
        "ajaxResumeDetail",
        'info',
    ],
    function($, Vue, vueMethods, 
    	detailTemplate, //简历详情
    	diaConfirm,     //确认框
    	diaPayment,     //支付弹框 
    	diaDownload,    //下载弹框
    	ELEMENT,tool, ajaxResumeDetail, info) {
        Vue.use(ELEMENT);
        var app = new Vue({
            el: '#app',
            components: {
                'detail-tem': detailTemplate,
                'dia-confirm': diaConfirm,
                'dia-payment': diaPayment, 
                'dia-download': diaDownload,
            },
            mounted: function () {
                this.$nextTick(function () {
                	var that = this;
                	tool.addPvUv();
                	tool.loading(true);
                	this.souce = this.source=='rc'?-1:this.source=='zl'?0:this.source=='qc'?1:2;//来源
                	this.userStatuJudge(); 
                	
                	if(!tool.url.get('source','search')){
                		if(window.location.href.indexOf('/cv/cv_detail')!=-1){
                			this.source = 'rc';
                		}
                	}
                	
//              	this.addPvUv();
//              	that.getSource();
                });
            },
            data: {
            	accountInfo:{},
            	resumeId:tool.url.get('userId','search'),
            	gainNum:'',
            	source: tool.url.get('source','search')||'rc',
            	resumeData:{},  //简历请求参数
            	souce:'',//来源
            },
            methods: {
            	
            	userStatuJudge:function(){
            		var that = this;
        			/*获取联系人信息*/
        			ajaxResumeDetail.getAccountInfo({},function(response){
        				if (response.code==200){
	            			that.gainNum = response.gainNum //设置金币数
	                    	that.$refs.dia_payment.gainNum = that.gainNum;
	                    	
	                    	that.getSource()
        				}else{
	                    	that.$refs.dia_confirm.confirmOpen(response.message)
	                    }
            		},true,'get');
            	},
            	
            	/*遍历简历详情请求参数*/
            	getSource:function(){
            		switch (this.source){
            			case 'rc':
            				this.resumeData = {
            					userId: this.resumeId,
								searchId:tool.url.get('searchId','search')||'',
								resumeId: this.resumeId,
								resumeNumber:this.resumeId,
								token:tool.url.get('token','search') || '',
								r_t:$("#rto").val(),
            				}
            				break;
            			default:
            				break;
            		}
            		this.$refs.detail_tem.getResumeInfo();
            	},
            	
            }
        });

    }
);