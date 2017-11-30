if (typeof define !== 'function') {
    var define = require('amdefine')(module);
}
function zlGet(){
	document.getElementById('getResume').click();
}
define(["text!../../views/detailComponents/detailInfo.html","$", 'loadash', 
		"detailTop",
        'ajaxResumeDetail', 'tool','info'],
    function (textModule, $, _, detailTop, ajaxResumeDetail, tool, info) {
        var Detail = {
            template: textModule,
            components: {
                'resume-top': detailTop,       //简历详情头部功能模块
            },
            mounted: function () {
                this.$nextTick(function () {
                	var that = this;
//              	that.$root.$refs.dia_confirm.confirmOpen('暂无简历信息')
                	tool.loading(true);
                });
            },
            data: function () {
            	return{
            		tool:tool,
                    userInfo:'',   //联系方式
                    gainNum:'',//余额
                    detailInfo: { //初始化简历详情
                        eduList: [],
                        languagesList: [],
                        projectList: [],
                        skillsList: [],
                        trainList: [],
                        workList: [],
                    },  
                    payment:{//支付简历
                        dialog: false,
                        msgDialog:false,
                        paymentType:'4'
                    },                       
                    userId: tool.url.get('userId','search'),
                    getGtype:info.getGtype(),
			        interviewState:false,
			        accountInfo:{},
			        HighlightState:false, //关键词请求次数
			        getUserResumeTryNum: 0,		//简历详情请求多次
            	}
            },
            methods: {
            	
            	/*遍历简历价格*/
               	dealMoneyFunc:function(){
               		var that = this
               		if(this.$root.source=='rc'){
            			this.dealMoney = 5;
            		}
              	},
            	
            	/*获取简历信息*/
            	getResumeInfo:function(){
            		var that = this;
            		var urlName
            		if(this.$root.source=='rc'){
            			urlName = 'getCvDetail'        //简历云 /cv/getCvDetail
            		}
                	ajaxResumeDetail[urlName](this.$root.resumeData,function(res){
	            		if (res.code == 200) {
	            			
	            			res.isStorage = res.isStorage||0;
	            			
//	                    	that.city = res.password?res.password.split('-')[0]:'';  //前程关键字
	                    	res.keyword = res.keyword?res.keyword:res.province   //前程关键字
	                    	
	                    	that.dealMoneyFunc();    //获取简历价格
	                    	
	                        that.$refs.resume_top.detailInfoOriginal = res;  //保存简历详情原始数据
	                        
	                        res = that._fomartResume(_.clone(res,true));   //简历数据id匹配value格式化
	                        
	                        res.selfEvaluate = $.trim(res.selfEvaluate);
	                        
	                        that.detailInfo = res;     //生成简历详情视图层
	                        
	                        that.isGain(that.$root.resumeData);
	                        
	                    }else {
	                        that.$root.$refs.dia_confirm.confirmOpen(res.message)
	                    }
                	},true);
            	},
            	
            	
                /*获取简历联系*/
                isGain:function (netWorkData,cashType,response) {
                    var that = this;
                    var gain = ''
                    if(this.$root.source=='rc'){
                    	gain = 'isGain';
                    }
                    if (response.code==200){
                    	if(response.email && response.email.indexOf('yifengjianli')>1){
            				response.email = '';
	            		}
                    	if(response.telephone.indexOf('@')!=-1){
                      		response.telephoneKey = response.telephone.split('@')[1];
                      		response.telephone = response.telephone.split('@')[0];
                      	}
                        that.userInfo = response;
                        
                    	if(that.detailInfo.isOwn == undefined){
                    		that.$set(that.detailInfo,'isOwn',1);
                    	}else{
                    		that.detailInfo.isOwn = 1;
                    	}
                         that.detailInfo.isPay = 1;
                    }else if(cashType){
                    	that.$root.$refs.dia_payment.payment.dialog = false;
                    	that.message('支付失败！',0);
                    }
                    
                    that.traverseEach();
                    tool.loading(false);
                },
                
                /* 格式化简历信息*/
                _fomartResume: function (data) {
                    var that = this;
                    if (data.jobTitle) {
                        data.jobTitle = that._match('skill', data.jobTitle)||data.jobTitle;
                    }
                    if(data.sex){
                        data.sex = that._match('gender',data.sex)||data.sex;
                    }
                    if (data.province) {
                        data.province = that._match('city', data.province)||data.province;
                    }
                    if (data.city) {
                        data.city = that._match('city', data.city)||data.city;
                    }
                    if (data.hukouProvince) {
                        data.hukouProvince = that._match('city', data.hukouProvince)||data.hukouProvince;
                    }
                    if (data.hukouCity) {
                        data.hukouCity = that._match('city', data.hukouCity)||data.hukouCity;
                    }
                    if (data.education) {
                        data.education = that._match('edu', data.education)||data.education;
                    }
                    if (data.expectWorkType) {
                        data.expectWorkType = info.getWorkType()[data.expectWorkType - 1].value||data.expectWorkTyp;
                    }
                    if (data.expectCity) {
                        data.expectCity = that._match('city', data.expectCity)||data.expectCity;
                    }
                    if (data.expectSalary) {
                        data.expectSalary = that._match('salary', data.expectSalary)||data.expectSalary;
                    }
                    if (data.jobState) {
                        data.jobState = info.getNewJobStatus()[data.jobState].value||data.jobState;
                    }
                    if (data.expectIndustry) {
                        data.expectIndustry = that._match('industry', data.expectIndustry)||data.expectIndustry;
                    }

                    return data;
                },
                _match : function(status,id){
                    var dataList = [];
                    var value = [];
                    if(status == 'city'){
                        dataList = info.getCity();  //城市
                    }else if(status == 'skill'){
                        dataList = info.getSkill();  //职位
                    }else if(status == 'gender'){
                        dataList = info.getGender();  //性别
                    }else if(status == 'industry'){
                        dataList = info.getIndustry();  //行业
                    }else if(status == 'salary'){
                        dataList = info.getSalary();   //薪资
                    }else if(status == 'edu'){
                        dataList = info.getNewEducation();  //学历
                    }else if(status == 'jobState'){
                        dataList = info.getNewJobStatus();  //求职状态
                    }else if(status == 'ComProperty'){
                        dataList = info.getComProperty();  //公司性质
                    }else if(status == 'compSize'){
                        dataList = info.getResumeCompSize();  //公司规模
                    };
                    id = id.toString().split(',');
                    for(var i = 0; i<id.length;i++) {
                        for (var j = 0; j < dataList.length; j++) {
                            if(dataList[j].id == id[i]) {
                                if(dataList[j].name){
                                    value[i] = dataList[j].name;
                                }else{
                                    value[i] = dataList[j].value;
                                }
                                break;
                            }
                        }
                    }
                    return value.join('，');
                },
                
                /*遍历关键词*/
                traverseEach:function(){
                	var that = this;
                	if(tool.url.get('keyword','search')&&!that.HighlightState){
	            		that.HighlightState = true;
		            	setTimeout(function(){  //遍历关键字
		            		var s = decodeURI(tool.url.get('keyword','search'));
							if(s.indexOf("，")!=-1){
								s = s.replace(/，/g, " ");
							}
							if(s.indexOf(",")!=-1){
								s = s.replace(/,/g, " ");
							}
							if(s.indexOf("。")!=-1){
								s = s.replace(/。/g, " ");
							}
							s = s.split(' ');
							for(var i=0;i<=s.length;i++){
						        if(s[i]==''){
						            s.splice(i,1);
						        	i--; 
						        }
						        
						    }
			            	that.eachHighlight(s);
		            	},150)
	            	}
                },
                
                /*关键字高亮*/
                eachHighlight:function(array){
                	$(".eachHighlight").each(function(){
					     //取得标签的文本
					    var t = $(this).html();
					     //取得需要查出的关键字，我们这里假定是多关键字以","间隔
		//			     var array = $scope.highlightText.split(",");
					     //开始用关键字遍历标签文本
					    for(var i=0;i<array.length;i++){
					         //判断标签是否包含关键字
				             //定义正则表达式对象  array[i]是关键字   "g"是指全局范围
				            var a = new RegExp(array[i],'gi')
				             //对标签文本进行全局替换，包含关键字的位置替换为加红字span对象
				            t = t.replace(a,("##########" + array[i] + "########"));
//				             t = t.replace(a,("<mark class='highlight'>" + array[i] + "</mark>"));
				             //将替换完的文本对象赋给此对象中A标签对象的html值中
				            $(this).html(t);
					    }
					    var a = new RegExp('##########','gi')
					    var b = new RegExp('########','gi')
					    t = t.replace(a,("<mark class='highlight'>"))
					    t = t.replace(b,("</mark>"))
					    $(this).html(t);
					});
                },
            }
        };
        return Detail;
    });