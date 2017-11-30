if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(["text!../../views/components/dialogJob.html", 'info', 'tool', 'loadash',"css!../../css/components/multiSelectDialog.css"], 
function(textModule, info, tool, _) {
	var dialogJob = {
		template: textModule,
		//props: ['jobList','jobLevelCanSelet','maxSelect','dialog', 'rowNum', 'jobTheme'],
		props: {
			jobList: {
	      type: Array,
	      default: []
	    },
			jobLevelCanSelet: {
	      type: Number,
	      default: 1
	    },
			maxSelect: {
	      type: Number,
	      default: 3
	    },
			dialog: {
	      type: Object,
	      default: {status: false, searchKey: 'job', title: "职业", belongDialog: 'job'}
	    },
			rowNum: {
	      type: Number,
	      default: 3
	    },
			jobTheme: {
	      type: Number,
	      default: 1
	    },
		},
		/*created: function(){
			this.$on('clearData',this.clearData1);
		},*/
		data: function() {
			return {
				tool: tool,
				//jobList: [],		
				//jobLevel1: info.getJobListKeyValue(),	//获取职位类别一级数据
				itemTemp2: {id:''},
				slt: {
					job:{item:[],value:[],id:[]}
				},
				//dialog: this.$parent.dialog
			}
		},
		methods: {
			setDataList: function(data){
				this.slt.job.item = _.cloneDeep(data);
				this.slt.job.value = _.pluck(data,'value');
				this.slt.job.id = _.pluck(data,'id');
			},
			//打开更多选项对话框
			openMoreDialog: function(type){
				this.slt[type].item = [];
				this.slt[type].name = [];
				this.slt[type].id = [];
				this.dialog[type].status = true;
			},
			showItemlevel2: function(item1,index,event,type){
				if(!item1.childList || item1.childList.length < 1) return false;
				var currentDialog = '.dialog-'+type;
				var target = $(currentDialog+" [dialog-item2-wrap]");
				var rowNum = this.rowNum || 3;
				if(this.itemTemp2.id == item1.id){
					this.itemTemp2 = {id:''};
					$(currentDialog+" [dialog-item2-wrap="+item1.id+"]").hide();
				}else{
					this.itemTemp2 = item1;
					var appendIndex = Math.ceil((parseInt(index)+1)/rowNum)*rowNum-1;
					if(this.jobTheme != '2'){
						var endEle = $(currentDialog+' .dialog-item1-wrap').children('.dialog-item1').eq(appendIndex);
						if(endEle.length < 1){
							endEle = $(currentDialog+' .dialog-item1-wrap').children('.dialog-item1').last();
						}
					}else{
						var endEle = $(event.target).closest('.level2-wrap').children('.dialog-item1').eq(appendIndex);
						if(endEle.length < 1){
							endEle = $(event.target).closest('.level2-wrap').children('.dialog-item1').last();
						}
						
					}
					endEle.after(target);	
				}
				
			},
			sltDialogItem: function(type,item,event,level){
				item.level = level;
				if(level=='1'){
					var deleteArr = [];
					for(var index in this.slt[type].item){
						var obj = this.slt[type].item[index];
						if(obj.superId && obj.superId == item.id){
							deleteArr.push(index);
						}
					}
					if(deleteArr.length > 0){	//从后往前删除
						for(var index=deleteArr.length-1;index>=0;index--){
							this.removeSltItem(type,deleteArr[index]);
						}
					}
				}
				var max=this.maxSelect;	//最多可选多少个
				this.sltChose(type,item,event,max);
			},
			
			//弹出框 选择
			sltChose: function(type,item,event,maxLength){
				if(event.target.checked){
					if(maxLength < 2){
						this.slt[type].item = [];
						this.slt[type].id = [];
						this.slt[type].value = [];
					}
					if(this.slt[type].item.length < maxLength && !tool.findInArr(this.slt[type].id,item.id)){
						this.slt[type].item.push(item);
						this.slt[type].id.push(item.id);
						this.slt[type].value.push(item.value);
					}else{
						event.target.checked = false;
				        this.message('最多只能选择'+ maxLength +'个',0);
						return false;
					}
					
				}else{
					//删除指定位置的元素
					var index = tool.findIndexInArr(this.slt[type].id,item.id);
					this.removeSltItem(type,index);
				}
			},
			//移除选项
			removeSltItem: function(type,index){
				this.slt[type].item.splice(index,1);
				this.slt[type].id.splice(index,1);
				this.slt[type].value.splice(index,1);
			},
			//清除选项
			clearSltItem: function(){
				this.itemTemp2 = {id:''};
				this.slt= {job:{item:[],value:[],id:[]}};
			},
			//点击确认
			dialogConfirm: function(type){
				var that = this;
				this.itemTemp2 = {id:''};
				this.$emit('submit',this.slt[type].item,this.dialog.job.belongDialog);
				this.dialog[type].status = false;
			},
			sltLanguage: function(item){		//主要用于智联的语言能力选择
				if(!!item.read && !item.listen){
					item.listen = '100';
				}else if(!item.read){
					item.listen = '';
				}
			}
		}
	};
	return dialogJob;
});