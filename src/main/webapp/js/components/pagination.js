if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}

define(["text!../../views/components/pagination.html"], function(textModule) {
	var dialogJob = {
		template: textModule,
		props: ['currentPage','totalPage', 'teamNum', 'pageSizeHide'],
		data: function() {
			return {
				//tool: tool,
				pageSizeAll:[15,30,60],
				pageSize:30,
			}
		},
		methods: {
			dealWith: function(){
				var totalPage = this.totalPage;
				var currentPage = this.currentPage;
				var teamNum = this.teamNum || 5; //允许最大出现连续4个数字,比如  1... 3 4 5 6 7... 9
				var returnArr = new Array();
				for(var index=0;index<totalPage;index++){
					returnArr[index] = index+1;
				};
				
				if(totalPage <= teamNum+2){
					return returnArr;
				}else{
					if(currentPage < teamNum){	//例如当前第3页， 1 2 3 4...9
						var tempArr = returnArr.slice(0);	//复制当前数组
						returnArr = returnArr.slice(0,teamNum).concat('...');
					}else if(currentPage <= totalPage - teamNum){
						var tempArr = returnArr.slice(0);	//复制当前数组
						returnArr = tempArr.slice(0,1).concat('.....').concat(currentPage-1,currentPage,currentPage+1,currentPage+2).concat('...');
					}else if(currentPage <= totalPage - teamNum+1){
						var tempArr = returnArr.slice(0);	//复制当前数组
						returnArr = tempArr.slice(0,1).concat('.....').concat(tempArr.slice(totalPage - teamNum-1));
					}else{
						var tempArr = returnArr.slice(0);	//复制当前数组
						returnArr = tempArr.slice(0,1).concat('.......').concat(tempArr.slice(totalPage - teamNum));
					};
					return returnArr;
				};
			},
			getPageData: function(page){
				this.$emit('change', page);
			},
			getPageSizeData: function(pageSize){
				if(this.pageSizeHide) return
				this.$emit('changesize', pageSize);
			},
		},
		computed:{
			pageNumArr: function(){
				return this.dealWith();
			}
		}
	};
	return dialogJob;
});