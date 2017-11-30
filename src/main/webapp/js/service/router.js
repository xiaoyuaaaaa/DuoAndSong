if (typeof define !== 'function') {
  var define = require('amdefine')(module);
}
require.config({
  paths:{
  	'accountSet': '../../js/components/accountSet.js',
    'companyAuthentication': '../../js/components/companyAuthentication.js',
    'password': '../../js/components/password.js',
    'emailCheck': '../../js/components/emailCheck.js',
    'phoneCheck': '../../js/components/phoneCheck.js',
  }
});

define([], function(){
  var router = [
    { path: '/user/accountSet', component: function (resolve) {require(['accountSet'], resolve)}, name: '用户信息',meta: { keepAlive: false }},
    { path: '/user/companyAuth', component: function (resolve) {require(['companyAuthentication'], resolve)}, name: '企业认证',meta: { keepAlive: false }},
    { path: '/user/password', component: function (resolve) {require(['password'], resolve)}, name: '修改密码',meta: { keepAlive: false }},
    { path: '/user/emailCheck', component: function (resolve) {require(['emailCheck'], resolve)}, name: '邮箱验证',meta: { keepAlive: false }},
    { path: '/user/phoneCheck', component: function (resolve) {require(['phoneCheck'], resolve)}, name: '修改手机号码',meta: { keepAlive: false }},
    { path: '/', redirect: '/user/accountSet' }	//默认
  ];

  return router;
});