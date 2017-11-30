var requirejs = {
	baseUrl: '/',
	paths: {
		'vue': '../../js/lib/vue/vue.js',
		'VueRouter': '../../js/lib/vue/vue-router.min.js',
		'Vuex': '../../js/lib/vue/vuex.min.js',
		'vueMethods': '../../js/common/vue-methods.js',					//扩展Vue方法
		'ELEMENT': '../../js/lib/element/element.min.js',					//饿了么Vue UI
		'$': '../../js/lib/jquery/jquery-1.11.1.min.js',
		'axios': '../../js/lib/axios/axios.min.js',						//HTTP请求
		'promise': '../../js/lib/axios/promise.min.js',						//HTTP请求
		'base': '../../js/common/base.js',								//全局js方法
		'ajaxData': '../../js/service/ajaxData.js',						//全局异步请求数据方法
		'ajaxResumeSearch': '../../js/service/ajaxResumeSearch.js',		//简历搜索相关
		'tool': '../../js/common/tool.js',								//自定义的js util类
		'info': '../../js/service/info.js',								//提供固定筛选条件
		'loadash': '../../js/lib/loadash/lodash.min.js',					//JavaScript 工具库
		'text': '../../js/lib/require/text.js',							//require扩展插件，加载文件
		'css': '../../js/lib/require/css.js',								//require扩展插件，加载css单文件
		'html': '../../js/lib/require/html.js',
		'basePage': '../../js/components/basePage.js',					//公共页面
  	'headerPage': '../../js/components/headerPage.js',						//头部页面
  	'leftPage': '../../js/components/leftPage.js',						//左侧页面
		'mCustomScrollbar': '../../js/lib/mCustomScrollbar/jquery.mCustomScrollbar.js',		   //滚动条插件
		'mousewheel': '../../js/lib/mCustomScrollbar/jquery.mousewheel.js',		   //滚动条插件
		'dialogIndustry': '../../js/components/dialogIndustry.js',		//对话框，行业
		'dialogJob': '../../js/components/dialogJob.js',					//对话框，职位
		'dialogCity': '../../js/components/dialogCity.js',				//对话框，城市
		'pagination': '../../js/components/pagination.js',  
    'ajaxUser': '../../js/service/ajaxUser.js',         //用户登录、注册等信息相关
    'ajaxRecordManage': '../../js/service/ajaxRecordManage.js',         //记录管理接口
    'ajaxCompany': '../../js/service/ajaxCompany.js',         //企业信息接口
    'Clipboard': '../../js/lib/clipboard/clipboard.min.js',
	    
    'ajaxResumeDetail': '../../js/service/ajaxResumeDetail.js',         //简历详情接口
    'detailInfo': '../../js/detailComponents/detailInfo.js',             //简历详情
    'diaConfirm': '../../js/detailComponents/diaConfirm.js',             //确认框
    'diaPayment': '../../js/detailComponents/diaPayment.js',			 //支付弹框 
    'diaDownload': '../../js/detailComponents/diaDownload.js',			 //下载弹框
  	'detailTop': '../../js/detailComponents/detailTop.js',	 			 //简历详情头部功能模块
		'userRouter': '../../js/service/router.js',	 	 //个人中心路由
    'webuploader':'../../js/lib/webuploader-0.1.5/webuploader.js',	//上传控件
  },
	shim: {
		'$': { exports: '$' },
		'VueRouter': {
			deps: ['vue'],
			exports: 'VueRouter'
		},
		'Vuex': {
			deps: ['vue'],
			exports: 'Vuex'
		},
		'webuploader': {
			deps: ['$'],
			exports: 'WebUploader'
		},
		'axios':{
			deps: ['promise']
		},
		'Clipboard':{
			deps:['$'],
			exports:'Clipboard'
		}
	},
	packages: [],
  //urlArgs: 'v='+new Date().getTime()
};
