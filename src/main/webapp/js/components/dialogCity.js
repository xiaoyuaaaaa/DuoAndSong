if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(["text!../../views/components/dialogCity.html", 'info', 'tool', 'loadash', "css!../../css/components/multiSelectDialog.css"],
function(textModule, info, tool, _) {
	var dialogCity = {
		template: textModule,
		//props: ['cityList','hotCity','maxSelect','dialog'],
		props: {
			cityList: {
	      type: Object,
	      default: []
	    },
			hotCity: {
	      type: Object,
	      default: []
	    },
			maxSelect: {
	      type: Number,
	      default: 3
	    },
			dialog: {
	      type: Object,
	      default: {status: false, searchKey: 'city', title: "城市", belongDialog: 'city'},
	    }
		},
		data: function() {
			return {
				tool: tool,
				//cityList: [],		
				//cityLevel1: info.getCityListKeyValue(),	//获取城市类别一级数据
				itemTemp2: {id:''},
				itemTemp3: {ids:''},
				slt: {
					city:{item:[],value:[],id:[]}
				},
				item2WrapBelong:'',
				item3WrapBelong:'',
				//dialog: this.$parent.dialog
			}
		},
		methods: {
			setDataList: function(data){
				this.slt.city.item = _.cloneDeep(data);
				this.slt.city.value = _.pluck(data,'value');
				this.slt.city.id = _.pluck(data,'id');
			},
			//打开对话框
			/*openMoreDialog: function(type){
				this.slt[type].item = [];
				this.slt[type].value = [];
				this.slt[type].id = [];
				this.dialog[type].status = true;
			},*/
			showItemlevel2: function(item1,index,event,type,belong){
				if(!item1.childList || item1.childList.length < 1) return false;
				this.item2WrapBelong = belong;
				var currentDialog = '.'+belong+'-'+this.dialog.city.belongDialog;
				var target = $(currentDialog+" .dialog-item2-wrap");
				if(this.itemTemp2.id == item1.id){
					this.itemTemp2 = {id:''};
					this.itemTemp3 = {id:''};
					$(currentDialog+" [dialog-item2-wrap="+item1.id+"]").hide();
				}else{
					this.itemTemp2 = item1;
					this.itemTemp3 = {id:''};
					var appendIndex = Math.ceil((parseInt(index)+1)/7)*7-1;
					var endEle = $(currentDialog+'.dialog-item1-wrap').children('.dialog-item1').eq(appendIndex);
					if(endEle.length < 1){
						endEle = $(currentDialog+'.dialog-item1-wrap').children('.dialog-item1').last();
					}
					endEle.after(target);	
				}
				
			},
			showItemlevel3: function(item2,index,event,type,belong){
				if(!item2.childList || item2.childList.length < 1) return false;
				this.item3WrapBelong = belong;
				var currentDialog = '.'+belong+'-'+this.dialog.city.belongDialog;
				var target = $(currentDialog+" .dialog-item3-wrap");
				if(this.itemTemp3.id == item2.id){
					//$(currentDialog+" [dialog-item3-wrap="+item2.ids+"]").hide();
					this.itemTemp3 = {id:''};
				}else{
					this.itemTemp3 = item2;
					var appendIndex = Math.ceil((parseInt(index)+1)/7)*7-1;
					var endEle = $(currentDialog+' .dialog-item2-wrap').children('.dialog-item2').eq(appendIndex);
					if(endEle.length < 1){
						endEle = $(currentDialog+' .dialog-item2-wrap').children('.dialog-item2').last();
					}
					endEle.after(target);	
				}
				
			},
			sltDialogItem: function(type,item,event,level){
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
				var max=this.maxSelect || 3;	//最多可选多少个
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
				        this.message('最多只能选择'+maxLength+'个',0);
						return false;
					}
					
				}else{
					//删除指定位置的元素
					var index = tool.findIndexInArr(this.slt[type].id,item.id);
					this.removeSltItem(type,index);
				}
			},
			removeSltItem: function(type,index){
				this.slt[type].item.splice(index,1);
				this.slt[type].id.splice(index,1);
				this.slt[type].value.splice(index,1);
			},
			//清除选项
			clearSltItem: function(){
				this.itemTemp2 = {id:''};
				this.slt= {city:{item:[],value:[],id:[]}};
			},
			dialogConfirm: function(type){
				var that = this;
				this.itemTemp2 = {id:''};
				/*if(this.slt[type].item.length < 1){
					this.dialog[type].status = false;
					return false;
				}*/
				/*this.slt[type].item.forEach(function(item){
					item.belong = that.dialog[type].searchKey;
					item.key = item.id;
					item.des = that.dialog[type].des;
					that.$parent.conditionChange(item);
				});
				this.$parent.submitResumeConditionChoice(that.dialog[type].searchKey);
				this.dialog[type].status = false;*/
				this.$emit('submit',this.slt[type].item,this.dialog.city.belongDialog);
				this.dialog[type].status = false;
				
			},
		}
	};
	return dialogCity;
});