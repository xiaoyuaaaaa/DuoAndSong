if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}
define(["text!../../views/components/left.html", "tool", "ajaxUser"],

	function(textModule, tool, ajaxUser) {
		var leftPage = {
			template: textModule,
			props: {
				userinfo: {
					type: Object,
					default(){
						return {}
					} 
				},
				pagename: {
					type: String,
					default: ''
				}
			},
			mounted: function() {
				var that = this;
				that.getNotice()
			},
			components: {

			},
			data: function() {
				return {
					noticNum: '',
                    notice:{
                        unread:0,
                        page:2,
                        totalPage:5
                    }
				}
			},
			methods: {
				/*退出*/
				userCancel: function() {
					ajaxUser.userCancel(function(res) {
						if(res.code == 200){
							window.location.href = '/login/login';
						}
					});
				},
				getNotice:function () {
					var that = this;

                },

			}
		};
		return leftPage;
	});