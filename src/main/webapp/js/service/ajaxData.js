if (typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(['tool'], function(tool) {
	var ajaxData = {
		/*简历搜索*/
		cvsearch: function(data,successFn){
			//tool.ajax('/search/cvsearch',data,successFn);
			tool.ajax2({
				data,
				url: '/search/cvsearch',
				success: successFn
			});
		},
		/*获取简历搜索历史记录*/
		getSearchLogs: function(successFn){
			tool.ajax('/search/getSearchLogs','',successFn);
		},
		/*暂存简历*/
		storageCv: function(data,successFn){
			tool.ajax('/oper/storageCv',data,successFn);
		},
		/*获取记录列表*/
		getGainLogs: function(data,successFn){
			tool.ajax('/record/getGainLogs',data,successFn);
		},
		/*获取暂存列表*/
		getStorageLogs: function(data,successFn){
			tool.ajax('/record/getStorageLogs',data,successFn);
		},
		/*取消暂存*/
		cancelStorageCv: function(data,successFn){
			tool.ajax('/oper/cancelStorageCv',data,successFn);
		},
		/*企业验证*/
    accountBinding:function(data,successFn) {
      tool.ajax('/binding/accountBinding', data, successFn);
    },
		/*金币记录*/
        getGoldLogs:function(data,successFn) {
      tool.ajax('/record/getGoldLogs', data, successFn,'',true);
    },
		//获取绑定状态
        getUserBingdingStatus:function(successFn){
		tool.ajax2({
			url: '/binding/getUserBingdingStatus',
			type: 'get',
			success: successFn
		});
	},
		/*获取消息通知列表*/
        getNoticeList: function(data,successFn){
            tool.ajax('/notice/getNoticeList',data,successFn,'',true);
        },
		/*根据消息通知ID获取通知详情*/
        getNoticeInfo: function(data,successFn){
            tool.ajax('/notice/getNoticeInfo',data,successFn,'',true);
        },
		/*更新消息通知阅读状态*/
        editNoticeStatus: function(data,successFn){
            tool.ajax('/notice/editNoticeStatus',data,successFn,'',true,'get');
        },
		
	};

	return ajaxData;
});