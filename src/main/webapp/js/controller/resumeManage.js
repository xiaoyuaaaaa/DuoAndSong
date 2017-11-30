require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage',
        'dialogJob', 'dialogCity', 'pagination', 'ajaxData', "diaDownload", 'leftPage'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage,
              dialogJob, dialogCity, pagination, ajaxData, diaDownload, leftPage) {
        "use strict";

        Vue.use(ELEMENT);

        /*Vue.use(VueRouter);
         var router = new VueRouter({
         routes: routerConfig
         });*/

        var app = new Vue({
            el: '#app',
//  router: router,
            mounted: function () {
                this.$nextTick(function () {
                    var that = this;
                    this.educationList1.unshift({value: '不限', id: ''});
                    //this.getResumeList(this.resumeManage);
                    this.submitResumeManage(1);
                    setTimeout(function () {
                        that.loadHistoryFinish = true;
                    }, 700);
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'left-page': leftPage,
                'dialog-job': dialogJob,
                'dialog-city': dialogCity,
                'pagination': pagination,
                'dia-download': diaDownload,
            },
            data: function () {
                return {
                    userinfo: {
                        jianli: 299,
                        jifen: 1495,
                        zancun: 3,
                        yixiazai: 0,
                        id: 95,
                        isAuditThrough: 1
                    },
                    showConditionAll: true,
                    pageDes: {
                        name: '简历管理'
                    },
                    manageType: tool.url.get('type', 'search') || 1,
                    info: info,
                    tool: tool,
                    checkAll: false,
                    checkUserId1: [],
                    checkUserId2: [],
                    checkAllUserId1: [],
                    checkAllUserId2: [],
                    salaryList: info.getSalary(),				//期望薪资
                    jobStateList: info.getNewJobStatus(),		//求职状态
                    sexList: info.getGender(),					//性别
                    updateTimeList: info.getUpdateTime(),		//更新时间
                    educationList1: info.getNewEducation(),		//最低学历
                    educationList2: info.getEndEducation(),		//最高学历
                    jobList: info.getJobList(),					//职业数据
                    cityList: {									//城市列表
                        list: info.getCityList(),
                        showLevel3: false,
                        level1CanSelect: false
                    },
                    hotCity: {									//热门城市
                        list: info.getHotCityList(),
                        showLevel3: false
                    },
                    resumeAjaxData: {},
                    dialog: {
                        job: {status: false, searchKey: 'job', title: "职业", belongDialog: 'job'},
                        city: {status: false, searchKey: 'city', title: "期望城市", belongDialog: 'city'},
                    },
                    slt: {
                        job: {item: [], value: '', id: ''},
                        city: {item: [], value: '', id: ''},
                    },
                    resumeData: {resumeNumber: ''},		//下载的简历id
                    conditionShowStr: '',						//展示的搜索条件
                    historyData: {},							//历史条件
                    tempSearch: {
                        searches: {value: '', des: '关键字', match: 'searches'},											//match表示提交搜索时的字段需要从这里取值
                        name: {value: '', des: '姓名', match: 'name'},
                        tel: {value: '', des: '电话', match: 'tel'},
                        email: {value: '', des: '邮箱', match: 'email'},
                        company: {value: '', des: '公司', checked: false, ref: ['companyName', 'latelyCompName']},		//ref,跟搜索字段哪些关联，清除条件时用到
                        job: {value: '', des: '职业', slt: 'job'},															//slt表示，弹框选择的字段，清除条件时用到
                        city: {value: '', id: '', des: '城市', slt: 'city'},
                        salary: {item: {value: '', id: ''}, des: '薪资', match: 'salary'},								//item表示这个是下拉选择项
                        jobState: {item: {value: '', id: ''}, des: '状态', match: 'jobState'},
                        sex: {item: {value: '', id: ''}, des: '性别', match: 'sex'},
                        updateTime: {item: {value: '', id: ''}, des: '更新', match: 'updateTime'},
                        edu1: {item: {value: '', id: ''}, des: '学历', notInclue: true, match: 'education'},				//notInclue表示显示搜索条件文字时，不需要遍历此字段
                        edu2: {item: {value: '', id: ''}, des: '学历', notInclue: true, match: 'endEducation'},
                        education: {value: '', des: '学历'},
                        age: {value: '', des: '年龄', ref: ['startAge', 'endAge']},
                        experience: {value: '', des: '经验', ref: ['startYear', 'endYear']},
                    },
                    resumeManage: {
                        page: 1,
                        pageSize: 30,
                        searches: '',			//关键字
                        sex: '',
                        education: '',
                        endEducation: '',
                        startYear: '',
                        endYear: '',
                        salary: '',
                        updateTime: '',
                        jobState: '',
                        city: '',
                        job: '',
                        startAge: '',
                        endAge: '',
                        companyName: '',
                        latelyCompName: '',		//当前公司，如果此项有值，companyName传空
                        jobType: ''				//已职位对应的层级
                    }
                }
            },
            methods: {
                //根据传入的职位或城市id，返回匹配的文字
                mapJobOrCity(type, ids){
                    var dataList;
                    if (type == 'city') {
                        dataList = info.getCityList();
                    } else if (type == 'job') {
                        dataList = info.getJobList();
                    }
                    var repeatNum = ids.split(',');
                    var matchArr = [];
                    for (var index in repeatNum) {
                        var currentId = repeatNum[index];
                        var matchObj = _.find(dataList, function (current) {		//先查找一级数据有没有匹配的
                            return current.id == repeatNum[index];
                        });
                        //热门城市
                        if (type == 'city') {
                            matchObj = _.find(info.getHotCityList(), function (current) {		//先查找一级数据有没有匹配的
                                return current.id == repeatNum[index];
                            });
                        }
                        if (!matchObj) {		//如果一级找到了，就不再遍历二级数据
                            for (var index1 in dataList) {
                                var dataList2 = dataList[index1].childList;
                                var matchObj2 = _.find(dataList2, function (current2) {
                                    return current2.id == repeatNum[index];
                                })
                                if (matchObj2) {
                                    var tempData = _.cloneDeep(matchObj2);

                                    matchArr.push(tempData);
                                }
                            }
                        } else {
                            var tempData = _.cloneDeep(matchObj);
                            matchArr.push(tempData);
                        }
                    }
//				console.log(_.pluck(matchArr, 'value').join(','));
                    return _.pluck(matchArr, 'value').join(',');
                },
                //切换导航栏
                changeType(type){
                    this.manageType = type;
                    this.clearCondition();
                    this.checkAll = false;
                    this.checkUserId1 = [];
                    this.checkUserId2 = [];
                    this.submitResumeManage(1);
                },
                verify(type){
                    var value = this.tempSearch[type].value;
                    if (!value.trim()) return true;

                    if (type == 'email' && !new RegExp("^([a-z0-9A-Z]+[-|_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").test(value)) {
                        this.message('邮箱格式不正确', 0);
                        return false;
                    } else if (type == 'tel' && !/^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9])\d{8}$/.test(value)) {
                        this.message('手机号格式不正确', 0);
                        return false;
                    }
                    return true;
                },
                //处理年龄，工龄的数字
                dealNum: function (type, obj, current, compare, limit) {
                    if (!!isNaN(obj[current])) {
                        obj[current] = '';
                        return;
                    }
                    if (type == 'min') {
                        if (limit && obj[current] < limit && !!obj[current]) {
                            obj[current] = limit;
                        }
                        if (obj[compare] && (obj[current] > obj[compare] || isNaN(obj[compare]))) {		//最大值不能小余最小值
                            obj[compare] = obj[current];
                        }
                    } else if (type == 'max') {	//最大值不能小余最小值
                        if (limit && obj[current] && obj[current] < limit) {
                            obj[current] = limit;
                        }
                        if (obj[current] && (obj[current] < obj[compare])) {
                            obj[compare] = obj[current];
                        }
                    }
                },
                //判断年龄
                ageJudge: function (current, compare) {
                    var type = current == 'startAge' ? 'min' : 'max';
                    this.dealNum(type, this.resumeManage, current, compare, 18);
                },
                //判断工龄
                yearJudge: function (current, compare) {
                    var type = current == 'startYear' ? 'min' : 'max';
                    this.dealNum(type, this.resumeManage, current, compare);
                },
                //处理学历，如果选择了'初中及以下'，'不限'，'其他'，只显示最低学历
                dealEducation: function (value) {
                    var index = _.findIndex(this.educationList1, 'id', value);
                    this.educationList2 = _.drop(info.getNewEducation(), index)		//将 array 中的前 n 个元素去掉
                    this.educationList2.unshift({id: '15', value: '及以下'});
                    this.educationList2.unshift({id: '14', value: '及以上'});
                },
                //下拉框数据处理
                select2Option: function (type, key1, key2, from) {
                    if (!this.loadHistoryFinish) return;		//防止回显时，立即触发了函数判断
                    var obj = {};
                    obj[key1] = this.tempSearch[key1].item.value;
                    obj[key2] = this.tempSearch[key2].item.value;
                    this.tempSearch[type].value = this.dealConcactCondition(obj, key1, key2);
                    if (type == 'education' && from) {
                        this.resumeManage.education = this.tempSearch[key1].item.id;
                        this.dealEducation(this.tempSearch[key1].item.id);
                    }
                },
                //全选
                handleCheckAllChange: function (event) {
                    this['checkUserId' + this.manageType] = event.target.checked ? this['checkAllUserId' + this.manageType] : [];
                },
                handleCheckedSingleChange1: function (value) {
                    var checkLength = value.length;
                    this.checkAll = checkLength === this.resumeAjaxData.logList.length;
                },
                handleCheckedSingleChange2: function (value) {
                    var checkLength = value.length;
                    this.checkAll = checkLength === this.resumeAjaxData.logList.length;
                },
                //打开对话框
                openDialog: function (type) {
                    this.dialog[type].status = true;
                },
                //处理对话框数据
                dealDialogData: function (type, data) {
                    this.slt[type].item = data;
                    this.tempSearch[type].value = _.pluck(data, 'value').join(',');
                    this.resumeManage[type] = _.pluck(data, 'id').join(',');
                    if (type == 'job') {
                        this.resumeManage.jobType = _.pluck(data, 'level').join(',');
                    }
                },
                //获取职业对话框提交的数据
                getDialogJobData: function (data, belongDialog) {
                    this.dealDialogData(belongDialog, data);
                },
                //获取城市对话框提交的数据
                getDialogCityData: function (data, belongDialog) {
                    this.dealDialogData(belongDialog, data);
                },
                //清除搜索条件
                clearCondition: function () {
                    for (var key in this.tempSearch) {
                        if (this.tempSearch[key].hasOwnProperty('match')) {		//含有，表示搜索字段需要从这里取值
                            var matchKey = this.tempSearch[key].match;
                            this.resumeManage[matchKey] = '';
                            if (this.tempSearch[key].hasOwnProperty('item')) {	//如果有item表示，是下拉框
                                this.tempSearch[key].item = {value: '', id: ''};
                            } else {
                                this.tempSearch[key].value = '';
                            }
                        } else if (this.tempSearch[key].hasOwnProperty('slt')) {
                            var slt = this.tempSearch[key].slt;
                            this.dealDialogData(slt, []);
                            this.$refs[slt + 'Child'].clearSltItem();
                        } else if (this.tempSearch[key].hasOwnProperty('ref')) {
                            var ref = this.tempSearch[key].ref;
                            this.tempSearch[key].value = '';
                            for (var index in ref) {
                                this.resumeManage[ref[index]] = '';
                            }
                        }
                    }

                },
                //处理需要拼接的搜索条件，比如学历，年龄，工龄
                dealConcactCondition: function (obj, key1, key2) {
                    if (obj[key1] && obj[key2]) {
                        return obj[key1] + '-' + obj[key2]
                    } else if (obj[key1] && !obj[key2]) {
                        return obj[key1] + '-不限';
                    } else if (!obj[key1] && obj[key2]) {
                        return '不限-' + obj[key2];
                    } else {
                        return '';
                    }
                },
                //处理分页请求
                changePage: function (page) {
                    this.resumeManage.page = page;
                    this.submitResumeManage();
                },
                //处理分页每页显示条数请求
                changePageSize: function (pageSize) {
                    this.resumeManage.page = 1;
                    this.resumeManage.pageSize = pageSize;
                    this.submitResumeManage();
                },
                //处理获取回来的简历列表
                dealResumeList: function (data) {
                    this.resumeAjaxData = data;
                    if (data.code != 200) {
                        this.$confirm(data.message, '提示', {
                            confirmButtonText: '确定',
                            showCancelButton: false,
                            //cancelButtonText: '取消',
                            type: 'warning'
                        }).then(function () {
                            /*that.$message({
                             type: 'success',
                             message: '删除成功!'
                             });*/
                        }).catch(function () {
                            /*that.$message({
                             type: 'info',
                             message: '已取消删除'
                             }); */
                        });
                    } else {
                        //保存所有列表的id
                        this['checkAllUserId' + this.manageType] = _.clone(data.logList);
                        for(var index in this.resumeAjaxData.logList){
                        	this.resumeAjaxData.logList[index].jobTitle_name = this.mapJobOrCity('job',this.resumeAjaxData.logList[index].jobTitle);
                        	this.resumeAjaxData.logList[index].expectCity_name = this.mapJobOrCity('city',this.resumeAjaxData.logList[index].expectCity);
                        }
                    }
                },
                //处理要给用户显示的条件
                dealConditionShow: function () {
                    var tempArr = [];
                    for (var key in this.tempSearch) {
                        var desStr = '';
                        if (this.tempSearch[key].hasOwnProperty('match')) {		//含有，表示搜索字段需要从这里取值
                            var matchKey = this.tempSearch[key].match;
                            if (this.tempSearch[key].hasOwnProperty('id')) {

                            } else if (this.tempSearch[key].hasOwnProperty('item')) {
                                this.resumeManage[matchKey] = this.tempSearch[key].item.id;
                                desStr = this.tempSearch[key].item.value;
                            } else if (this.tempSearch[key].hasOwnProperty('value')) {
                                desStr = this.tempSearch[key].value;
                                this.resumeManage[matchKey] = this.tempSearch[key].value;
                            }
                        } else {
                            switch (key) {
                                case 'age':
                                    desStr = this.dealConcactCondition(this.resumeManage, 'startAge', 'endAge');
                                    break;
                                case 'experience':
                                    desStr = this.dealConcactCondition(this.resumeManage, 'startYear', 'endYear');
                                    break;
                                default:
                                    desStr = this.tempSearch[key].value;
                                    break;
                            }

                        }
                        if (desStr && !this.tempSearch[key].notInclue) {
                            tempArr.push(this.tempSearch[key].des + '：' + desStr);
                        }
                    }
                    //tool.console(this.resumeManage);
                    this.conditionShowStr = tempArr.join(' + ');
                },
                //触发简历搜索
                submitResumeManage: function (page) {
                    function dealCondition(val1, val2) {
                        if (val1) {
                            return val1 + ',' + (val2 ? val2 : '99')
                        } else {
                            return '';
                        }
                    }

                    if (!this.verify('tel') || !this.verify('email')) {
                        return;
                    }
                    this.dealConditionShow();
                    var config = {
                        page: page || this.resumeManage.page,
                        pageSize: this.resumeManage.pageSize,
                        jobTitle: this.resumeManage.job,		//关键字
                        cityCode: this.resumeManage.city		//城市
                    };
                    if (this.manageType == 1) {
                        config.name = this.resumeManage.name;		//姓名
                        config.telephone = this.resumeManage.tel;		//电话
                        config.email = this.resumeManage.email;		//邮箱
                    } else {
                        config.storageTime = this.resumeManage.updateTime;		//暂存时间
                        config.education = this.resumeManage.education;		//学历
                        config.jobYear = dealCondition(this.resumeManage.startYear, this.resumeManage.endYear);		//工作经验
                    }
                    this.getResumeList(config);
                    //tool.console(config);
                },
                //获取已下载或暂存的简历记录
                getResumeList: function (config) {
                    if (this.manageType == 1) {
                        ajaxData.getGainLogs(config, this.dealResumeList);
                    } else {
                        ajaxData.getStorageLogs(config, this.dealResumeList);
                    }
                    //this.dealResumeList({"page":1,"pageSize":18,"rowCount":325936,"totalPage":18108,"poolStatus":2,"code":200,"message":"获取成功","bidPools":[{"sex":2,"latelyCompName":"唐山市启奥科技股份有限公司","schoolName":"唐山学院","updateTime":"2017-11-06 00:00:00","cityName":"北京","jobState":0,"searchId":2103999,"education":4,"jobYeay":4,"emailCheck":1,"isDownLoad":"0","userId":"495327","age":"24","expectedSalary":"","major":"计算机科学与技术","isRead":"1","jobTitle":"WEB前端开发"},{"sex":2,"latelyCompName":"印孚瑟斯技术（中国）有限公司","schoolName":"大连工业大学","updateTime":"2017-11-06 00:00:00","cityName":"大连","jobState":1,"searchId":2103999,"education":4,"jobYeay":5,"emailCheck":1,"isDownLoad":"1","userId":"6270908","age":"27","expectedSalary":"0000000000","major":"计算机科学与技术","isRead":"1","jobTitle":"软件测试"},{"sex":1,"latelyCompName":"诚品优选（北京）电子商务有限公司","schoolName":"首都师范大学","updateTime":"2017-11-06 00:00:00","cityName":"北京","jobState":0,"searchId":2103999,"education":4,"jobYeay":6,"emailCheck":1,"isDownLoad":"1","userId":"1276715","age":"29","expectedSalary":"","major":"软件工程","isRead":"1","jobTitle":"需求工程师, IT项目经理/主管"},{"sex":1,"latelyCompName":"天富盛世（厦门）资产管理有限公司","schoolName":"福建工程学院","updateTime":"2017-11-06 00:00:00","cityName":"厦门","jobState":1,"searchId":2103999,"education":5,"jobYeay":2,"emailCheck":1,"isDownLoad":"0","userId":"4735378","age":"22","expectedSalary":"0400106000","major":"软件工程","isRead":"0","jobTitle":"软件工程师"},{"sex":1,"latelyCompName":"南京劳伦斯制衣有限公司","schoolName":"南京航空航天大学","updateTime":"2017-11-06 00:00:00","cityName":"南京","jobState":2,"searchId":2103999,"education":5,"jobYeay":11,"emailCheck":1,"isDownLoad":"0","userId":"4073995","age":"30","expectedSalary":"0400106000","major":"计算机信息管理","isRead":"1","jobTitle":"系统管理员, 网络工程师"},{"sex":1,"latelyCompName":"中佳集团","schoolName":"十堰职业技术学院","updateTime":"2017-11-06 00:00:00","cityName":"武汉","jobState":0,"searchId":2103999,"education":5,"jobYeay":11,"emailCheck":1,"isDownLoad":"0","userId":"7721724","age":"36","expectedSalary":"","major":"计算机科学与技术","isRead":"0","jobTitle":"信息技术经理/主管, IT项目经理/主管, 网络工程师, IT技术支持/维护经理, IT技术/研发经理/主管"},{"sex":1,"latelyCompName":"杭州万霆科技股份有限公司","schoolName":"浙江建设职业技术学院","updateTime":"2017-11-06 00:00:00","cityName":"杭州","jobState":1,"searchId":2103999,"education":5,"jobYeay":7,"emailCheck":1,"isDownLoad":"0","userId":"8487610","age":"28","expectedSalary":"1000115000","major":"经济管理","isRead":"0","jobTitle":""},{"sex":1,"latelyCompName":"广东阿尔创通信技术股份有限公司","schoolName":"湖北经济学院","updateTime":"2017-11-06 00:00:00","cityName":"广州","jobState":2,"searchId":2103999,"education":4,"jobYeay":11,"emailCheck":1,"isDownLoad":"0","userId":"1027565","age":"35","expectedSalary":"0000000000","major":"计算机科学与技术","isRead":"0","jobTitle":"IT项目经理/主管, 高级软件工程师, 软件工程师"},{"sex":1,"latelyCompName":"北京益华鼎泰科技有限发展公司","schoolName":"太原科技大学","updateTime":"2017-11-06 00:00:00","cityName":"深圳","jobState":2,"searchId":2103999,"education":4,"jobYeay":3,"emailCheck":1,"isDownLoad":"0","userId":"927584","age":"29","expectedSalary":"1000115000","major":"通信工程","isRead":"0","jobTitle":"软件/互联网开发/系统集成"},{"sex":1,"latelyCompName":"计算机软件","schoolName":"湖南大学","updateTime":"2017-11-06 00:00:00","cityName":"","jobState":0,"searchId":2103999,"education":4,"jobYeay":7,"emailCheck":1,"isDownLoad":"0","userId":"8955501","age":"31","expectedSalary":"","major":"本科","isRead":"0","jobTitle":"软件工程师"},{"sex":1,"latelyCompName":"中软国际","schoolName":"中南大学","updateTime":"2017-11-06 00:00:00","cityName":"上海","jobState":1,"searchId":2103999,"education":4,"jobYeay":1,"emailCheck":1,"isDownLoad":"0","userId":"7628503","age":"23","expectedSalary":"0000000000","major":"其他","isRead":"0","jobTitle":"Java开发工程师"},{"sex":1,"latelyCompName":"埃森哲管理咨询公司","schoolName":"仲恺农业工程学院","updateTime":"2017-11-06 00:00:00","cityName":"广州","jobState":1,"searchId":2103999,"education":4,"jobYeay":6,"emailCheck":1,"isDownLoad":"0","userId":"7340198","age":"29","expectedSalary":"0000000000","major":"计算机科学与技术","isRead":"0","jobTitle":"软件工程师"},{"sex":1,"latelyCompName":"互动通天图信息技术有限公司","schoolName":"湖北职业技术学院","updateTime":"2017-11-06 00:00:00","cityName":"上海","jobState":1,"searchId":2103999,"education":5,"jobYeay":2,"emailCheck":1,"isDownLoad":"0","userId":"4319541","age":"21","expectedSalary":"1000115000","major":"软件工程","isRead":"0","jobTitle":"软件工程师"},{"sex":1,"latelyCompName":"武汉宝盒子网络科技有限公司","schoolName":"武汉东湖学院","updateTime":"2017-11-06 00:00:00","cityName":"武汉","jobState":1,"searchId":2103999,"education":5,"jobYeay":3,"emailCheck":1,"isDownLoad":"0","userId":"7625016","age":"24","expectedSalary":"0400106000","major":"计算机网络","isRead":"0","jobTitle":"软件工程师"},{"sex":2,"latelyCompName":"深信服电子科技有限公司","schoolName":"湘南学院","updateTime":"2017-11-06 00:00:00","cityName":"广州,佛山","jobState":1,"searchId":2103999,"education":4,"jobYeay":4,"emailCheck":1,"isDownLoad":"0","userId":"7457343","age":"25","expectedSalary":"","major":"通信工程","isRead":"0","jobTitle":"软件测试"},{"sex":2,"latelyCompName":"北京泰豪智能工程有限公司","schoolName":"北京工商大学","updateTime":"2017-11-06 00:00:00","cityName":"武汉","jobState":1,"searchId":2103999,"education":4,"jobYeay":9,"emailCheck":1,"isDownLoad":"0","userId":"1286808","age":"31","expectedSalary":"0200104000","major":"计算机科学与技术","isRead":"0","jobTitle":"售前/售后技术支持工程师, 商务经理/主管, IT项目执行/协调人员"},{"sex":1,"latelyCompName":"星美集团","schoolName":"长沙航空学院(军)","updateTime":"2017-11-06 00:00:00","cityName":"深圳","jobState":1,"searchId":2103999,"education":5,"jobYeay":10,"emailCheck":1,"isDownLoad":"0","userId":"6619776","age":"32","expectedSalary":"0000000000","major":"计算机应用","isRead":"0","jobTitle":"互联网软件工程师, 网站架构设计师, CTO/CIO"},{"sex":1,"latelyCompName":"华为技术有限公司","schoolName":"南京邮电大学","updateTime":"2017-11-06 00:00:00","cityName":"杭州","jobState":2,"searchId":2103999,"education":4,"jobYeay":11,"emailCheck":0,"isDownLoad":"0","userId":"562229","age":"34","expectedSalary":"0000000000","major":"通信工程","isRead":"0","jobTitle":"IT质量管理/测试/配置管理, IT运维/技术支持"}]});
                },
                //查看简历
                gotoResumeDetail: function (resumeDataList) {
                    tool.LocalStorage.set('rabsList', resumeDataList);
                    tool.cookie.set('rabsList', JSON.stringify(resumeDataList));
                    window.open('/cv/cv_detail?more=1&userId=' + resumeDataList[0].userId + '&searchId='
                        + resumeDataList[0].searchId + '&token=' + resumeDataList[0].searchToken);
                },
                //暂存简历
                /*tempSaveResume: function(resumeIdList){
                 console.log(resumeIdList);
                 },*/
                //取消暂存
                cancelStorageCv(targetId){
                    var that = this;
                    var config = {
                        ids: targetId
                    };
                    this.$confirm('确定取消暂存吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        ajaxData.cancelStorageCv(config, function () {
                            that.resumeManage.page = 1;
                            that.submitResumeManage();
                            //that.$root.basePageChild.$getUserInfo
                            that.getUserInfo();
                        });
                    }).catch(() => {
                    });
                },
                //下载单份简历
                downResume(resumeId){
                	this.resumeData.resumeNumber = resumeId;
                  this.$refs.dia_download.download.dialog = true;
                },
                //下载、查看、暂存
                operResume: function (type) {
                    if (this['checkUserId' + this.manageType].length < 1) {
                        this.message('请选择需要操作的简历', 0);
                        return false;
                    }
                    var resumeDataList = [];
                    var resumeIdList = [];
                    var storageIdList = [];
                    var target = this['checkUserId' + this.manageType];
                    for (var index in target) {
                        resumeDataList.push({
                            jobTitle: target[index].jobTitle_name,
                            userId: target[index].resumeId,
                            searchToken: target[index].searchToken,
                            searchId: target[index].searchId,
                        })
                        resumeIdList.push(target[index].resumeId)
                        storageIdList.push(target[index].id)
                    }
                    if (type == 1) {
                        //console.log(resumeIdList);
                        this.resumeData.resumeNumber = resumeIdList.join(',');
                        this.$refs.dia_download.download.dialog = true;
                    } else if (type == 2) {
                        this.gotoResumeDetail(resumeDataList);
                    } else if (type == 3) {	//取消暂存
                        this.cancelStorageCv(storageIdList.join(','));
                    }
                },
            },
            watch: {},
            computed: {
                showEducationList2: function () {
                    if (_.includes(['0', '8', '9'], this.tempSearch.edu1.item.id) || _.includes(['0', '8', '9'], this.resumeManage.education) || this.tempSearch.edu1.item.value == '不限') {
                        this.tempSearch.edu2.item = {value: '', id: ''};
                        return false;
                    }
                    return true;
                }
            }
        })
    });
