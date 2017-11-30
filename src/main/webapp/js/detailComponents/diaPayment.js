if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
define(["text!../../views/detailComponents/diaPayment.html","$", 'loadash',
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
            		tool:tool,
                    contact:'',
                    gainNum:'',//余额
                    payment:{
                        dialog: false,
                        msgDialog:false,
                        paymentType:'4'
                    },                       //支付简历
                    payUrl:'',  //支付路径
                    getGtype:info.getGtype(),
            	}
            },
            methods: {
            	
                
                /*立即支付*/
                paymentBtn:function () {  //立即支付
                    var that = this;
                    if(that.payment.paymentType==4){
                    	if(this.gainNum<this.$root.$refs.detail_tem.dealMoney) {
	                		that.message('抱歉，您的余额不足！请及时充值。',response.code==200);
	                		return
	                	} 
	                	this.gainResume();
                    }
                },
                
                /*亿封支付获取联系*/
                gainResume:function(){
                	var that = this;
                    ajaxResumeDetail.gainResume(this.$root.resumeData,function (response) {
                        if (response.code==200){
                        	if(that.$root.$refs.detail_tem.detailInfo.isOwn == undefined){
	                    		that.$set(that.$root.$refs.detail_tem.detailInfo,'isOwn',1);
	                    	}else{
	                    		that.$root.$refs.detail_tem.detailInfo.isOwn = 1;
	                    	}
                        	that.gainNum = that.gainNum-that.$root.$refs.detail_tem.dealMoney;
                        	that.$root.$refs.detail_tem.detailInfo.gainNum = that.gainNum;
                            that.$root.$refs.detail_tem.isGain('','',response)
                        }
                        that.message(response.message,response.code==200);
                        that.payment.dialog = false;
                    });//用户
                },
                
                /*关闭支付*/
                paymentClose:function () {
                    var that = this;
                    that.payment.dialog = false;
                    setTimeout(function () {
                        that.payment.msgDialog = false;
                    },500)
                },
            }
        };
        return Detail;
    });