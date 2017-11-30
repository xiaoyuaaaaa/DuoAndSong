require(['$', 'vue', 'VueRouter', 'vueMethods', 'ELEMENT', 'loadash', 'info', 'tool', "basePage", 'headerPage'],
	function($, Vue, VueRouter, vueMethods, ELEMENT,  _, info, tool, basePage, headerPage) {
	"use strict";
		
	Vue.use(ELEMENT);
	Vue.use(VueRouter);
	
	/*var router = new VueRouter({
		routes: routerConfig
	});*/

  var app = new Vue({
    el: '#app',
//  router: router,
    mounted: function(){
      this.$nextTick(function(){
        var that = this;
      })
    },
    components: {
      'base-page': basePage,
      'header-page': headerPage,
    },
    data: function() {
      return {
      	userinfo: {},
      	pageDes: {
      		name: '页面',
      		des: '页面描述'
      	}
      }
    },
    methods: {},
		watch: {},
		computed: {}
	})
});
