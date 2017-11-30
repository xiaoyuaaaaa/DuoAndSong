if(typeof define !== 'function') {
	var define = require('amdefine')(module);
}
define(["text!../../views/components/header.html", "tool", "ajaxUser"],

	function(textModule, tool, ajaxUser) {
		var headerPage = {
			template: textModule,
			props: {
				userinfo: {
					type: Object,
					default: {}
				},
				pagename: {
					type: String,
					default: ''
				}
			},
			mounted: function() {
				var that = this;
			},
			components: {

			},
			data: function() {
				return {
					noticNum: '',
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
			}
		};
		return headerPage;
	});