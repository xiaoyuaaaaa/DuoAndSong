if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
define(['tool'], function(tool) {
    var ajaxData = {

        /***********亿封**************/
        // 获取简历详情
        getCvDetail:function (reqdata,successFn,loadingType) {
            tool.ajax('/cv/getCvDetail',reqdata,successFn,'',loadingType);
        },
        //联系方式
        isGain:function (reqdata,successFn,loadingType) {
            tool.ajax('/bidme/isGain',reqdata,successFn,'',loadingType);
        },
        //获取发布职位
        getPosition:function (reqdata,successFn,loadingType) {
            tool.ajax('/company/getPosition',reqdata,successFn,'',loadingType);
        },
        //用户信息
        getContacts:function (reqdata,successFn,loadingType) {
            tool.ajax('/user/getContacts',reqdata,successFn,'',loadingType);
        },
        //获取用户信息
        getAccountInfo:function (reqdata,successFn,loadingType,reway) {
            tool.ajax('/user/getUserInfo',reqdata,successFn,'',loadingType,reway);
        },
        //EXCEL生成接口
        createExcel:function (reqdata,successFn,reway) {
            tool.ajax('/downLoad/createExcel',reqdata,successFn,'','',reway);
        },
        //pdf word 生成接口
        createPdfWord:function (reqdata,successFn,reway) {
            tool.ajax('/downLoad/createPdfWord',reqdata,successFn,'','',reway);
        },
        //生成PDF/WORD压缩包
        createPdfWordZip:function (reqdata,successFn,reway) {
            tool.ajax('/downLoad/createPdfWordZip',reqdata,successFn,'','',reway);
        },
        //简历压缩包下载接口
        downloadBatchResume:function (reqdata,successFn,reway) {
            tool.ajax('/downLoad/downloadBatchResume',reqdata,successFn,'','',reway);
        },
        //简历压缩包下载接口
        downloadSingleResume:function (reqdata,successFn,reway) {
            tool.ajax('/downLoad/downloadSingleResume',reqdata,successFn,'','',reway);
        },

        //简历邀请
        jobInvitation:function (reqdata,successFn) {
            tool.ajax('/invite/jobInvitation',reqdata,successFn);
        },

        isLogin:function (reqdata,successFn) {
            tool.ajax('/common/isLogin',reqdata,successFn);
        },
        //确定余额支付
        gainResume:function (reqdata,successFn) {
            tool.ajax('/oper/getCvContact',reqdata,successFn);
        },
        //下载简历word||pdf
        downPdfWord:function (reqdata,successFn) {
            tool.ajax('/download/createPdfWord',reqdata,successFn);
        },
        //下载简历excel
        downExcel:function (reqdata,successFn) {
            tool.ajax('/download/createExcel',reqdata,successFn);
        },
        //脚部操作：待沟通、已沟通、已录用、不合适
        treatedGainLog:function (reqdata,successFn) {
            tool.ajax('/company/treatedGainLog',reqdata,successFn);
        },
        //添加备注
        editResumeMarks:function (reqdata,successFn) {
            tool.ajax('/company/editResumeMarks',reqdata,successFn);
        },
        //加入暂存
        resumeTemporary:function (reqdata,successFn) {
            tool.ajax('/oper/storageCv',reqdata,successFn);
        },
        //加入暂存
        cancelStorageCv:function (reqdata,successFn) {
            tool.ajax('/oper/cancelStorageCv',reqdata,successFn);
        },


        //***********51************//
        jobResuDetail_51:function (reqdata,successFn,loadingType) {
            tool.ajax('/jobResume/jobResuDetail',reqdata,successFn,'',loadingType);
        },
        //邀约
        addEmailModel:function (reqdata,successFn) {
            tool.ajax('/company/addEmailModel',reqdata,successFn);
        },
        //购买简历
        buyResuByCopies:function (reqdata,successFn,loadingType) {
            tool.ajax('/company/buyResuByCopies',reqdata,successFn,'',loadingType);
        },
        //购买成功后获取简历Id
        netWorkIsPaySuccess:function (reqdata,successFn,loadingType) {
            tool.ajax('/company/netWorkIsPaySuccess',reqdata,successFn,'',loadingType);
        },
        //根据ID获取私密信息
        getUserIdByResumeNumber:function (reqdata,successFn,loadingType) {
            tool.ajax('/bidme/getUserIdByResumeNumber',reqdata,successFn,'',loadingType);
        },
        //******58******//
        wbResuDetail_58:function (reqdata,successFn,loadingType) {
            tool.ajax('/wbresume/wbResuDetail',reqdata,successFn,'',loadingType);
        },

        //**********智联***********//
        zlResuDetail_zl:function (reqdata,successFn,loadingType) {
            tool.ajax('/zlresume/zlResuDetail',reqdata,successFn,'',loadingType);
        },
        
        //51发送面试邀约
        sendMaessage:function (reqdata,successFn,loadingType) {
            tool.ajax('/job51/sendMaessage',reqdata,successFn,'',loadingType);
        },
        
        //请求PV、UV
		addPvUv:function (data,successFn) {
			tool.ajaxSilent('/pvuv/addPvUv',data,successFn);
		},
		
		//投递简历修改沟通进度
		updateDeliveryStatus:function (reqdata,successFn,loadingType) {
            tool.ajax('/delivery/updateDeliveryStatus',reqdata,successFn,'',loadingType);
        },
        
        //获取简历操作动态
		getCvHandleLog:function (reqdata,successFn,loadingType) {
            tool.ajax('/handle/getCvHandleLog',reqdata,successFn,'',loadingType);
        },
    };

    return ajaxData;
});