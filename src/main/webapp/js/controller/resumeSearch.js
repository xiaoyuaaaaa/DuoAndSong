require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage',
        'dialogJob', 'dialogCity', 'pagination', 'ajaxData', 'leftPage'],
    function ($, Vue, VueRouter, vueMethods, ELEMENT, _, info, tool, basePage, headerPage,
              dialogJob, dialogCity, pagination, ajaxData, leftPage) {
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
                    //this.submitResumeSearch();
                    ajaxData.getSearchLogs(this.dealHistoryLog);
                    setTimeout(function () {
                        that.loadHistoryFinish = true;
                    }, 1000);
                })
            },
            components: {
                'base-page': basePage,
                'header-page': headerPage,
                'left-page': leftPage,
                'dialog-job': dialogJob,
                'dialog-city': dialogCity,
                'pagination': pagination,
            },
            data: function () {
                return {
                    userinfo: {},
                    pageDes: {
                        name: '简历搜索',
                        des: '通过企业验证才能获取简历+简历获取次数哟'
                    },
                    showConditionAll: true,
                    info: info,
                    tool: tool,
                    checkAll: false,
                    checkUserId: [],
                    checkAllUserId: [],
                    shortDetail: {},
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
                    conditionShowStr: '',						//展示的搜索条件
                    historyData: [],							//历史条件
                    tempSearch: {
                        searches: {value: '', des: '关键字', match: 'searches'},											//match表示提交搜索时的字段需要从这里取值
                        company: {value: '', des: '公司', checked: false, match: 'companyName'},		//ref,跟搜索字段哪些关联，清除条件时用到
                        latelyCompName: {value: false, des: '当前公司', match: 'latelyCompName', notInclue: true},
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
                    resumeSearch: {
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
                    this.dealNum(type, this.resumeSearch, current, compare, 18);
                },
                //判断工龄
                yearJudge: function (current, compare) {
                    var type = current == 'startYear' ? 'min' : 'max';
                    this.dealNum(type, this.resumeSearch, current, compare);
                },
                //处理学历，如果选择了'初中及以下'，'不限'，'其他'，只显示最低学历
                dealEducation: function (value) {
                    var index = _.findIndex(this.educationList1, 'id', value);
                    this.educationList2 = _.drop(info.getNewEducation(), index)		//将 array 中的前 n 个元素去掉
                    this.educationList2.unshift({id: '99', value: '及以上'});
                },
                //下拉框数据处理
                select2Option: function (type, key1, key2, from) {
                    if (!this.loadHistoryFinish) return;		//防止回显时，立即触发了函数判断
                    var obj = {};
                    obj[key1] = this.tempSearch[key1].item.value;
                    obj[key2] = this.tempSearch[key2].item.value;
                    this.tempSearch[type].value = this.dealConcactCondition(obj, key1, key2);
                    if (type == 'education' && from) {
                        this.resumeSearch.education = this.tempSearch[key1].item.id;
                        this.dealEducation(this.tempSearch[key1].item.id);
                    }
                },
                //全选
                handleCheckAllChange: function (event) {
                    this.checkUserId = event.target.checked ? this.checkAllUserId : [];
                },
                handleCheckedSingleChange: function (value) {
                    var checkLength = value.length;
                    this.checkAll = checkLength === this.resumeAjaxData.resumeList.length;
                },
                //打开对话框
                openDialog: function (type) {
                    this.dialog[type].status = true;
                },
                //处理对话框数据
                dealDialogData: function (type, data) {
                    this.slt[type].item = data;
                    this.tempSearch[type].value = _.pluck(data, 'value').join(',');
                    this.resumeSearch[type] = _.pluck(data, 'id').join(',');
                    if (type == 'job') {
                        this.resumeSearch.jobType = _.pluck(data, 'level').join(',');
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
                            this.resumeSearch[matchKey] = '';
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
                                this.resumeSearch[ref[index]] = '';
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
                    this.resumeSearch.page = page;
                    this.getResumeList();
                },
                //处理分页每页显示条数请求
                changePageSize: function (pageSize) {
                    this.resumeSearch.page = 1;
                    this.resumeSearch.pageSize = pageSize;
                    this.getResumeList();
                },
                //
                selectHistory: function (item) {
                    this.clearCondition();
                    this.dealHistoryLogOne(item.conditonData);
                },
                //处理历史搜索
                dealHistorySearch: function (data) {
                    //console.log(data);
                    //tool.console(data.yfMap);
                    var that = this;
                    var showHistoryData = data;
                    var tempSearch = _.clone(this.tempSearch, true);
                    var resumeSearch = _.clone(this.resumeSearch, true);
                    data.yfMap = {
                        sex: showHistoryData.rm_S_1_7,	//性别
                        updateTime: showHistoryData.rm_S_1_8,	//更新日期
                        salary: showHistoryData.rm_S_1_5,	//期望薪资
                        jobState: showHistoryData.rm_S_1_6,	//求职状态
                        searches: showHistoryData.rm_S_1_12,	//关键字
                        company: showHistoryData.rm_S_1_3,		//公司
                        latelyCompName: !!showHistoryData.rm_S_1_4, //勾选当前公司
                        city: showHistoryData.rm_S_1_1,	//城市
                        job: showHistoryData.rm_S_1_2,	//职位
                        age: showHistoryData.rm_S_1_10,	//年龄
                        experience: showHistoryData.rm_S_1_11,	//经验
                        education: showHistoryData.rm_S_1_9,	//学历
                    };
                    for (var key in data.yfMap) {
                        //if(key == 'jobState') debugger;
                        var val = data.yfMap[key];
                        resumeSearch[key] = val;
                        if (!val) {
                            //this.resumeSearch[key] = '';
                            //this.tempSearch[key].value = '';
                            continue;
                        }
                        switch (key) {
                            case 'education':
                                tempSearch.edu1.item = _.cloneDeep(_.find(this.educationList1, 'id', val.split(',')[0]), true);
                                tempSearch.edu2.item = _.cloneDeep(_.find(this.educationList2, 'id', val.split(',')[1]), true);
                                break;
                            case 'age':
                                resumeSearch.startAge = val.split(',')[0];
                                resumeSearch.endAge = val.split(',')[1] == 99 ? '' : val.split(',')[1];
                                break;
                            case 'experience':
                                resumeSearch.startYear = val.split(',')[0];
                                resumeSearch.endYear = val.split(',')[1] == 99 ? '' : val.split(',')[1];
                                break;
                            default:
                                if (key == 'jobState' && val == '0') continue;
                                if (tempSearch[key] && tempSearch[key].hasOwnProperty('match')) {
                                    if (tempSearch[key].hasOwnProperty('item')) {
                                        tempSearch[key].item = _.find(this[key + 'List'], 'id', val);
                                    } else {
                                        tempSearch[key].value = val;
                                    }
                                }
                                if (tempSearch[key] && tempSearch[key].hasOwnProperty('slt')) {
                                    var dataList = this[key + 'List'];
                                    if (key == 'city') {
                                        dataList = this[key + 'List'].list;
                                    }
                                    var repeatNum = data.yfMap[key].split(',');
                                    var matchArr = [];
                                    for (var index in repeatNum) {
                                        var currentId = repeatNum[index];
                                        var matchObj = _.find(dataList, function (current) {		//先查找一级数据有没有匹配的
                                            return current.id == repeatNum[index];
                                        })
                                        if (!matchObj) {		//如果一级找到了，就不再遍历二级数据
                                            for (var index1 in dataList) {
                                                var dataList2 = dataList[index1].childList;
                                                var matchObj2 = _.find(dataList2, function (current2) {
                                                    return current2.id == repeatNum[index];
                                                })
                                                if (matchObj2) {
                                                    var tempData = _.cloneDeep(matchObj2);
                                                    if (key == 'job') {
                                                        //tempData.level = data.yfMap.jobType.split(',')[index];
                                                    }
                                                    matchArr.push(tempData);
                                                }
                                            }
                                        } else {
                                            var tempData = _.cloneDeep(matchObj);
                                            if (key == 'job') {
                                                //tempData.level = data.yfMap.jobType.split(',')[index];
                                            }
                                            matchArr.push(tempData);
                                        }
                                    }
                                    tempSearch[key].value = _.pluck(matchArr, 'value').join(',');
                                    //this.dealDialogData(key,matchArr);		//调用对话框确认之后的回显数据
                                    //this.$refs[key+'Child'].setDataList(matchArr);		//给对话框设置值
                                }
                                break;
                        }
                    }
                    return this.dealConditionShow('historySearch', tempSearch, resumeSearch);
                },
                //处理某一条历史回显
                dealHistoryLogOne: function (data) {
                    //console.log(data);
                    //tool.console(data.yfMap);
                    var that = this;
                    var showHistoryData = data;
                    data.yfMap = {
                        sex: showHistoryData.rm_S_1_7,	//性别
                        updateTime: showHistoryData.rm_S_1_8,	//更新日期
                        salary: showHistoryData.rm_S_1_5,	//期望薪资
                        jobState: showHistoryData.rm_S_1_6,	//求职状态
                        searches: showHistoryData.rm_S_1_12,	//关键字
                        company: showHistoryData.rm_S_1_3,		//公司
                        latelyCompName: !!showHistoryData.rm_S_1_4, //勾选当前公司
                        city: showHistoryData.rm_S_1_1,	//城市
                        job: showHistoryData.rm_S_1_2,	//职位
                        age: showHistoryData.rm_S_1_10,	//年龄
                        experience: showHistoryData.rm_S_1_11,	//经验
                        education: showHistoryData.rm_S_1_9,	//学历
                        test: ''
                    };
                    for (var key in data.yfMap) {
                        //if(key == 'jobState') debugger;
                        var val = data.yfMap[key];
                        this.resumeSearch[key] = val;
                        if (!val) {
                            //this.resumeSearch[key] = '';
                            //this.tempSearch[key].value = '';
                            continue;
                        }
                        switch (key) {
                            case 'education':
                                this.tempSearch.edu1.item = _.cloneDeep(_.find(this.educationList1, 'id', val.split(',')[0]), true);
                                this.tempSearch.edu2.item = _.cloneDeep(_.find(this.educationList2, 'id', val.split(',')[1]), true);
                                break;
                            case 'age':
                                this.resumeSearch.startAge = val.split(',')[0];
                                this.resumeSearch.endAge = val.split(',')[1] == 99 ? '' : val.split(',')[1];
                                break;
                            case 'experience':
                                this.resumeSearch.startYear = val.split(',')[0];
                                this.resumeSearch.endYear = val.split(',')[1] == 99 ? '' : val.split(',')[1];
                                break;
                            default:
                                if (key == 'jobState' && val == '0') continue;
                                if (this.tempSearch[key] && this.tempSearch[key].hasOwnProperty('match')) {
                                    if (this.tempSearch[key].hasOwnProperty('item')) {
                                        this.tempSearch[key].item = _.find(this[key + 'List'], 'id', val);
                                    } else {
                                        this.tempSearch[key].value = val;
                                    }
                                }
                                if (this.tempSearch[key] && this.tempSearch[key].hasOwnProperty('slt')) {
                                    var dataList = this[key + 'List'];
                                    if (key == 'city') {
                                        dataList = this[key + 'List'].list;
                                    }
                                    var repeatNum = data.yfMap[key].split(',');
                                    var matchArr = [];
                                    for (var index in repeatNum) {
                                        var currentId = repeatNum[index];
                                        var matchObj = _.find(dataList, function (current) {		//先查找一级数据有没有匹配的
                                            return current.id == repeatNum[index];
                                        })
                                        if (!matchObj) {		//如果一级找到了，就不再遍历二级数据
                                            for (var index1 in dataList) {
                                                var dataList2 = dataList[index1].childList;
                                                var matchObj2 = _.find(dataList2, function (current2) {
                                                    return current2.id == repeatNum[index];
                                                })
                                                if (matchObj2) {
                                                    var tempData = _.cloneDeep(matchObj2);
                                                    if (key == 'job') {
                                                        //tempData.level = data.yfMap.jobType.split(',')[index];
                                                    }
                                                    matchArr.push(tempData);
                                                }
                                            }
                                        } else {
                                            var tempData = _.cloneDeep(matchObj);
                                            if (key == 'job') {
                                                //tempData.level = data.yfMap.jobType.split(',')[index];
                                            }
                                            matchArr.push(tempData);
                                        }
                                    }
                                    this.dealDialogData(key, matchArr);		//调用对话框确认之后的回显数据
                                    this.$refs[key + 'Child'].setDataList(matchArr);		//给对话框设置值
                                }
                                break;
                        }
                    }
                    //this.resumeAjaxData = data;
                },
                dealHistoryLog: function (data) {
                    if (data.logList.length > 0) {
                        for (var index in data.logList) {
                            this.historyData.push({
                                conditionStr: this.dealHistorySearch(data.logList[index]),
                                conditonData: data.logList[index]
                            })
                        }
                        this.dealHistoryLogOne(data.logList[0]);	//回显第一条
                    }
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
                        //保存所有列表的
                        this.checkAllUserId = _.clone(data.resumeList);
                        //ajaxData.getSearchLogs(this.dealHistoryLog);
                    }
                },
                //处理要给用户显示的条件
                dealConditionShow: function (type, data, data2) {
                    var tempArr = [];		//搜索时显示条件
                    var tempArr2 = [];	//搜索时显示条件
                    var tempSearch;
                    if (type) {	//如果有type，表示是遍历最近搜索过的简历
                        tempSearch = data;
                    } else {
                        tempSearch = this.tempSearch;
                    }
                    for (var key in tempSearch) {
                        var desStr = '';
                        if (tempSearch[key].hasOwnProperty('match')) {		//含有，表示搜索字段需要从这里取值
                            var matchKey = tempSearch[key].match;
                            if (tempSearch[key].hasOwnProperty('id')) {

                            } else if (tempSearch[key].hasOwnProperty('item')) {
                                this.resumeSearch[matchKey] = tempSearch[key].item.id;
                                desStr = tempSearch[key].item.value;
                            } else if (tempSearch[key].hasOwnProperty('value')) {
                                desStr = tempSearch[key].value;
                                this.resumeSearch[matchKey] = tempSearch[key].value;
                            }
                        } else {
                            switch (key) {
                                case 'age':
                                    if (type) {
                                        desStr = this.dealConcactCondition(data2, 'startAge', 'endAge');
                                    } else {
                                        desStr = this.dealConcactCondition(this.resumeSearch, 'startAge', 'endAge');
                                    }
                                    break;
                                case 'education':
                                    if (tempSearch.edu1.item.value && tempSearch.edu2.item.value) {
                                        desStr = tempSearch.edu1.item.value + '-' + tempSearch.edu2.item.value;
                                    }
                                    break;
                                case 'experience':
                                    if (type) {
                                        desStr = this.dealConcactCondition(data2, 'startYear', 'endYear');
                                    } else {
                                        desStr = this.dealConcactCondition(this.resumeSearch, 'startYear', 'endYear');
                                    }
                                    break;
                                default:
                                    desStr = tempSearch[key].value;
                                    break;
                            }

                        }
                        if (desStr && !tempSearch[key].notInclue) {
                            tempArr.push(tempSearch[key].des + '：' + desStr);
                            tempArr2.push(desStr);
                        }
                    }
                    //tool.console(this.resumeSearch);
                    if (type) {
                        return tempArr2.join(' + ');
                    } else {
                        this.conditionShowStr = tempArr.join(' + ');
                    }
                },
                //触发简历搜索
                submitResumeSearch: function () {
                    this.resumeSearch.page = 1;
                    this.dealConditionShow();
                    if (this.conditionShowStr) {
                        this.getResumeList();
                    } else {
                        this.message('请选择搜索条件', 0);
                    }
                },
                //获取简历
                getResumeList: function () {
                    function dealCondition(val1, val2) {
                        if (val1) {
                            return val1 + ',' + (val2 ? val2 : '99')
                        } else {
                            return '';
                        }
                    }

                    $('.detail-list').hide();
                    var resumeSearch = _.clone(this.resumeSearch);
                    var config = {
                        page: resumeSearch.page,	//当前页数
                        pageSize: resumeSearch.pageSize,	//每页显示的条数
                        RM_S_1_1: resumeSearch.city,	//期望城市编码(多个英文逗号隔开)
                        RM_S_1_2: resumeSearch.job,	//期望职位(多个英文逗号隔开)
                        RM_S_1_3: resumeSearch.companyName,	//公司名称
                        RM_S_1_4: resumeSearch.latelyCompName ? 1 : '',	//是否勾选当前公司
                        RM_S_1_5: resumeSearch.salary,	//期望薪资
                        RM_S_1_6: resumeSearch.jobState,	//求职状态
                        RM_S_1_7: resumeSearch.sex,	//性别
                        RM_S_1_8: resumeSearch.updateTime,	//更新日期
                        RM_S_1_9: dealCondition(resumeSearch.education, resumeSearch.endEducation),	//学历
                        RM_S_1_10: dealCondition(resumeSearch.startAge, resumeSearch.endAge),	//年龄
                        RM_S_1_11: dealCondition(resumeSearch.startYear, resumeSearch.endYear),	//工作年限
                        RM_S_1_12: resumeSearch.searches,	//关键字
                    }
                    ajaxData.cvsearch(config, this.dealResumeList);
                },
                //查看简历
                gotoResumeDetail: function (resumeDataList) {
                    tool.LocalStorage.set('rabsList', resumeDataList);
                    tool.cookie.set('rabsList', JSON.stringify(resumeDataList));
                    window.open('/cv/cv_detail?more=1&userId=' + resumeDataList[0].userId + '&searchId='
                        + this.resumeAjaxData.searchId + '&token=' + this.resumeAjaxData.searchCode + '&source=rc&keyword=' + this.resumeSearch.searches);
                },
                //暂存简历
                tempSaveResume: function (resumeIdList) {
                    console.log(resumeIdList);
                    var that = this;
                    var data = {
                        resumeId: resumeIdList.join(','),
                        searchId: this.resumeAjaxData.searchId,
                        token: this.resumeAjaxData.searchCode,
                    }
                    ajaxData.storageCv(data, function (res) {
                        if (res.code == 200) {
                            resumeIdList.forEach(function (val) {
                                var target = _.find(that.resumeAjaxData.resumeList, {'userId': val});
                                if (!target.hasOwnProperty('isSave')) {
                                    that.$set(target, 'isSave', '');
                                }
                                target.isSave = 1;
                            });
                            that.$root.$refs.basePageChild.getUserInfo();
                        }
                        that.message(res.message, res.code == 200);
                    });
                },
                //查看或暂存
                operResume: function (type) {
                    if (this.checkUserId.length < 1) {
                        this.message('请选择需要操作的简历', 0);
                        return false;
                    }
                    var resumeDataList = [];
                    var resumeIdList = [];
                    for (var index in this.checkUserId) {
                        resumeDataList.push({
                            jobTitle: this.checkUserId[index].jobTitle,
                            userId: this.checkUserId[index].userId
                        })
                        resumeIdList.push(this.checkUserId[index].userId)
                    }
                    if (type == 1) {
                        this.gotoResumeDetail(resumeDataList);
                    } else {
                        this.tempSaveResume(resumeIdList);
                    }
                },
                //简历小详情
                appendDetail: function (scope) {
                    if (scope.row.showDetail == undefined) {
                        this.$set(scope.row, 'showDetail', '');
                    }
                    if (scope.row.showDetail) {
                        scope.row.showDetail = false;
                        $('.detail-list' + scope.$index).hide();
                    } else {
                        scope.row.showDetail = true;
                        $($('.resumeTable table:last tbody tr.resumeRow')[scope.$index]).after($('.detail-list' + scope.$index));
                        $('.detail-list' + scope.$index).show();
                    }
                }
            },
            watch: {},
            computed: {
                showEducationList2: function () {
                    if (_.includes(['0', '8', '9'], this.tempSearch.edu1.item.id) || _.includes(['0', '8', '9'], this.resumeSearch.education) || this.tempSearch.edu1.item.value == '不限') {
                        this.tempSearch.edu2.item = {value: '', id: ''};
                        return false;
                    }
                    return true;
                }
            }
        })
    });
