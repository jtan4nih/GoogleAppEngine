/*! loglevel - v0.1.0 - 2013-04-02
 * https://github.com/pimterry/loglevel
 * Copyright (c) 2013 Tim Perry; Licensed MIT */
(function(e,n){"undefined"!=typeof module?module.exports=n():"function"==typeof define&&"object"==typeof define.amd?define(n):this.name=n()})("log",function(){function e(e){return"undefined"==typeof console?t:console[e]===void 0?console.log||t:n(console,e)}function n(e,n){var o=e[n];return o.bind===void 0?Function.prototype.bind===void 0?function(){o.apply(e,arguments)}:Function.prototype.bind.call(e[n],e):e[n].bind(e)}function o(){for(var e=0;i.length>e;e++)l[i[e]]=t}var l={},t=function(){};l.levels={TRACE:0,DEBUG:1,INFO:2,WARN:3,ERROR:4,SILENT:5};var i=["trace","debug","info","warn","error"];l.setLevel=function(n){if("number"==typeof n&&n>=0&&l.levels.SILENT>=n){if(n===l.levels.SILENT)return o(),void 0;if("undefined"==typeof console)throw o(),"No console available for logging";for(var r=0;i.length>r;r++){var f=i[r];l[f]=l.levels[f.toUpperCase()]>=n?e(f):t}}else{if("string"!=typeof n)throw"log.setLevel called with invalid level: "+n;l.setLevel(l.levels[n.toUpperCase()])}},l.enableAll=function(){l.setLevel(l.levels.TRACE)},l.disableAll=function(){l.setLevel(l.levels.SILENT)};try{l.setLevel(l.levels.WARN)}catch(r){l.setLevel(l.levels.SILENT)}return l});