/*!
 * nzh v1.0.0
 * Homepage undefined
 * License BSD-2-Clause
 */
(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
(function (name, factory) {
	if (typeof define === 'function' && (define.amd || define.cmd)) {
		define([], factory);
	} else if (typeof window !== "undefined" || typeof self !== "undefined") {
		var global = typeof window !== "undefined" ? window : self;
		global[name] = factory();
	} else {
		throw new Error("加载 " + name + " 模块失败！，请检查您的环境！")
	}
}('Nzh', function () {
	return require("../nzh");
}))
},{"../nzh":2}],2:[function(require,module,exports){
var nzhClass = require("./src/");
var utils = require("./src/utils")
var getNzhObjByLang = require("./src/autoGet");
var langs = {
    s:require("./src/langs/cn_s"),
    b:require("./src/langs/cn_b"),
    hk_s:require("./src/langs/hk_s"),
    hk_b:require("./src/langs/hk_b")
}
var Nzh = function(lang){
    this.lang = lang;
    this.encode = function(){return nzhClass.CL.apply(lang,arguments)}
    this.decode = function(){return nzhClass.unCL.apply(lang,arguments)}
    this.toMoney = function(){return nzhClass.toMoney.apply(lang,arguments)}
}
Nzh.langs = langs;
Nzh.cn = getNzhObjByLang(langs.s,langs.b);
Nzh.hk = getNzhObjByLang(langs.hk_s,langs.hk_b);
module.exports = Nzh;
},{"./src/":4,"./src/autoGet":3,"./src/langs/cn_b":5,"./src/langs/cn_s":6,"./src/langs/hk_b":7,"./src/langs/hk_s":8,"./src/utils":9}],3:[function(require,module,exports){
var nzhClass = require("./");
var utils = require("./utils")
function getNzhObjByLang(lang_s, lang_b) {
	return {
		encodeS: function (num, options) {
			options = utils.extend({ ww: true, tenMin: true }, options);
			return nzhClass.CL.call(lang_s, num, options);
		},
		encodeB: function (num, options) {
			options = utils.extend({ ww: true }, options);
			return nzhClass.CL.call(lang_b, num, options);
		},
		decodeS: function () {
			return nzhClass.unCL.apply(lang_s, arguments);
		},
		decodeB: function () {
			return nzhClass.unCL.apply(lang_b, arguments);
		},
		toMoney: function (num, options) {
			options = utils.extend({ ww: true }, options);
			return nzhClass.toMoney.call(lang_b, num, options);
		}
	}
}
module.exports = getNzhObjByLang;
},{"./":4,"./utils":9}],4:[function(require,module,exports){
var utils = require("./utils");

//数组头部插0
function unshift0(arr, n , number) {
    if (n == null) n = 1;
    for (; n--;) arr.unshift(number || 0);
}

function zero_comm(str, char_0, type) {
    if(str == null) return "";
    var reg0 = ~"*.?+$^[](){}|\\/".indexOf(char_0) ? "\\" + char_0 : char_0; 
    var arg_s = new RegExp("^"+reg0+"+")
        ,arg_e = new RegExp(reg0+"+$")
        ,arg_d = new RegExp(reg0+"{2}","g")
    str = str.toString();
    if (type == "^") {
        str = str.replace(arg_s,"");
    }
    if (!type || type == "$") {
        str = str.replace(arg_e,"");
    }
    if (!type || type == "nto1") {
        str = str.replace(arg_d,char_0);
    }
    return str;
}

/**
 * 阿拉伯数字转中文数字
 * 
 * @param {String} num 阿拉伯数字/字符串 , 科学记数法字符串
 * @param {Object} opration 转换配置
 *                          {
 *                              ww: {万万化单位 | false}
 *                              tenMin: {十的口语化 | false}
 *                          }
 * @returns String
 */
function CL(num, options) {
    var result = utils.getNumbResult(num)
    if (!result) {
        return num;
    }
    options = options ? options : {};
    var ch = this.ch             //数字
        , ch_u = this.ch_u       //单位
        , ch_f = this.ch_f || "" //负
        , ch_d = this.ch_d || "" //点
        , n0 = ch.charAt(0); //零
    var _int = result.int             //整数部分
        , _decimal = result.decimal   //小数部分
        , _minus = result.minus;      //负数标识
    var int = ""
        , dicimal = ""
        , minus = _minus ? ch_f : ''; //符号位
   
    var encodeInt = function encodeInt(_int,_m,_dg){
        _int = utils.getNumbResult(_int).int;
        var int = ""
            , tenm = arguments.length > 1 ? arguments[1] : options.tenMin
            _length = _int.length;
        //一位整数 
        if (_length == 1) return ch.charAt(+_int);
        if (_length <= 4) { //小于四位
            for (var i = 0, n = _length; n--;) {
                var _num = +_int.charAt(i);
                int += (tenm && _length == 2 && i == 0 && _num == 1) ? "" : ch.charAt(_num);
                int += (_num && n ? ch_u.charAt(n) : '')
                i++;
            }
        } else {  //大数递归
            var d = _int.length / 4 >> 0
                , y = _int.length % 4;
            while (y == 0 || !ch_u.charAt(3 + d)) {
                y += 4;
                d--;
            }
            int = encodeInt(_int.substr(0, y),tenm) + ch_u.charAt(3 + d)
                + (~(_int.substr(y - 1, 2).indexOf("0")) ? n0 : '')
                + encodeInt(_int.substr(y), tenm)
        }
        int = zero_comm(int, n0); //修整零
        return int;
    }

    //转换小数部分
    if (_decimal) {
        _decimal = zero_comm(_decimal,"0","$"); //去除尾部0
        for (var x = 0; x < _decimal.length; x++) {
            dicimal += ch.charAt(+_decimal.charAt(x));
        }
        dicimal = dicimal ? ch_d + dicimal : "";
    }

    //转换整数部分
    int = encodeInt(_int);  //转换整数
    
    //超级大数的万万化 
    if (options.ww && ch_u.length > 5) {
        var dw_w = ch_u.charAt(4)
            , dw_y = ch_u.charAt(5);
        var lasty = int.lastIndexOf(dw_y);
        if (~lasty) {
            int = int.substring(0, lasty).replace(new RegExp(dw_y, 'g'), dw_w + dw_w) + int.substring(lasty);
        }
    }
    return minus + int + dicimal;
}

/**
 * 中文数字转阿拉伯数字
 * 
 * @param {string} cnnumb 中文数字字符串
 * @returns Number
 */
function unCL(cnnumb) {
    cnnumb = cnnumb.toString();
    var result = cnnumb.split(this.ch_d);
    var _int = result[0].replace(this.ch_f, "")
        , _decimal = result[1]
        , _minus = !!~result[0].indexOf(this.ch_f);

    var dw_s = this.ch_u.charAt(1)
        ,dw_w = this.ch_u.charAt(4)
        ,dw_y = this.ch_u.charAt(5);

        _int = _int.replace(new RegExp(dw_w + "{2}", "g"), dw_y);

    var cnarr = _int.split('');
    var rnum = 0, num = 0, _num = 0, dw = 0, maxdw = 0;
    var rnum_a = [], num_a = [], _num_a = [];
    function wei(u) {
        return u >= 5 ? (u - 4) * 4 + 4 : u;
    }
    for (var i = 0; i < cnarr.length; i++) {
        var chr = cnarr[i];
        var n = 0, u = 0;
        if (~(n = this.ch.indexOf(chr))) {
            //_num = _num*10 + n;
            if(n>0) _num_a.unshift(n);
            //_num_a.unshift(n);
        } else if (~(u = this.ch_u.indexOf(chr))) {
            if (dw > u) {//正常情况
                unshift0(_num_a, wei(u));
                utils.centerArray(num_a, _num_a);
            } else if (u >= maxdw) {//后跟大单位
                maxdw = u;
                if (i == 0) _num_a = [1];
                utils.centerArray(rnum_a, num_a, _num_a);
                if(rnum_a.length>0) unshift0(rnum_a, wei(u));
            } else {
                dw = u;
                if(_num_a.length == 0 && dw_s == chr) _num_a = [1];
                utils.centerArray(num_a, _num_a);
                unshift0(num_a, wei(u));
            }
        }
    }
    utils.centerArray(rnum_a, num_a, _num_a).reverse();
    if(rnum_a.length == 0)rnum_a.push(0);
    var decimal = 0;
    if (_decimal) {
        rnum_a.push('.')
        decimal = '0.'
        for (var i = 0; i < _decimal.length; i++) {
            decimal += this.ch.indexOf(_decimal.charAt(i));
            rnum_a.push(this.ch.indexOf(_decimal.charAt(i)));
        }
        decimal = +decimal;

    }
    rnum = (rnum + num + _num + decimal) * (_minus ? -1 : 1);
    return rnum_a.join('');
}

/**
 * 阿拉伯数字转金额
 * 
 * @param {String} num 阿拉伯数字/字符串 , 科学记数法字符串
 * @param {Object} options 转换配置
 *                         {
 *                             ww:{万万化开关 | true},
 *                             complete:{完整金额格式 | false},
 *                             outSymbol:{就否输出金额符号 | true}                             
 *                         }
 * @returns String
 */
function toMoney(num, options) {
    var def = {ww:true,complete:false,outSymbol:true};
    var result = utils.getNumbResult(num);
    var ch_0 = this.ch.charAt(0);
    options = typeof options == "object" ? options : {};
    if (!result) { return num; }
    options = utils.extend(def,options);

    var _num = result.num
        , _decimal = result.decimal || "";
    var t_str = options.outSymbol ? this.m_t : ""
        , zs_str = _decimal ? "" : this.m_z
        , xs_str = "";

    if(options.complete){
        for(var i=1; i < this.m_u.length; i++){
            xs_str += CL.call(this, _decimal.charAt(i-1) || "0") + this.m_u.charAt(i);
        }
        zs_str = CL.call(this, _num, options) + this.m_u.charAt(0);
    }else{
        _decimal = zero_comm(_decimal,"0","$");//去除尾部的0
        if(_decimal){
            var mark_0;
            for (var i = 0; i < this.m_u.length - 1; i++) {
                if(_decimal.charAt(i) && _decimal.charAt(i) != "0"){
                    xs_str += CL.call(this, _decimal.charAt(i)) + this.m_u.charAt(i + 1);
                    mark_0 = false;
                }
                if(_decimal.charAt(i) === "0" && !mark_0){
                    if(i != 0 || _num != "0") xs_str += ch_0; //当整为0时,小数前无需加零
                    mark_0 = true;
                }
            }
            //if(_num == "0"){xs_str = zero_comm(xs_str,ch_0,"^")}
        }
        if(_num != "0" || zs_str || !xs_str){
            zs_str = CL.call(this, _num, options) + this.m_u.charAt(0) + zs_str;
        }
    }
    return t_str + zs_str + xs_str;
}

module.exports = {
    CL:CL,
    unCL:unCL,
    toMoney:toMoney
}
},{"./utils":9}],5:[function(require,module,exports){
module.exports = {
    ch: '零壹贰叁肆伍陆柒捌玖'
    ,ch_u: '个拾佰仟万亿'
    ,ch_f: '负'
    ,ch_d: '点'
    ,m_t: '人民币'
    ,m_z: '整'
    ,m_u: '元角分'
}
},{}],6:[function(require,module,exports){
module.exports = {
    ch: '零一二三四五六七八九'
    ,ch_u: '个十百千万亿'
    ,ch_f: '负'
    ,ch_d: '点'
}
},{}],7:[function(require,module,exports){
module.exports = {
    ch: '零壹貳參肆伍陸柒捌玖'
    ,ch_u: '個拾佰仟萬億'
    ,ch_f: '負'
    ,ch_d: '點'
    ,m_t: '$'
    ,m_z: '整'
    ,m_u: '圓角分'
}
},{}],8:[function(require,module,exports){
module.exports = {
    ch: '零一二三四五六七八九'
    ,ch_u: '個十百千萬億'
    ,ch_f: '負'
    ,ch_d: '點'
}
},{}],9:[function(require,module,exports){
'use strict';
var REG_NUMBER = /^([+-])?0*(\d+)(\.(\d+))?$/;
var REG_E = /^([+-])?0*(\d+)(\.(\d+))?e(([+-])?(\d+))$/i;

/**
 * 科学计数法转十进制
 * 
 * @param {string} num 科学记数法字符串
 * @returns string 
 */
var e2ten = exports.e2ten = function (num) {
	var result = REG_E.exec(num.toString());
	if (!result) return num;
	var zs = result[2]
		, xs = result[4] || ""
		, e = result[5] ? +result[5] : 0;
	if (e > 0) {
		var _zs = xs.substr(0, e);
		_zs = _zs.length < e ? _zs + new Array(e - _zs.length + 1).join("0") : _zs;
		xs = xs.substr(e);
		zs += _zs;
	} else {
		e = -e;
		var s_start = zs.length - e;
		s_start = s_start < 0 ? 0 : s_start;
		var _xs = zs.substr(s_start, e);
		_xs = _xs.length < e ? new Array(e - _xs.length + 1).join("0") + _xs : _xs;
		zs = zs.substring(0, s_start);
		xs = _xs + xs;
	}
	zs = zs == "" ? "0" : zs;
	return (result[1] == "-" ? "-" : "") + zs + (xs ? "." + xs : "");
}


/**
 * 分板数字字符串
 * 
 * @param {string} num NumberString
 * @returns object
 */
exports.getNumbResult = function (num) {
	var result = REG_NUMBER.exec(num.toString());
	if (!result && REG_E.test(num.toString())) {
		result = REG_NUMBER.exec(e2ten(num.toString()))
	}
	if (result) {
		return {
			int: result[2],
			decimal: result[4],
			minus: result[1] == "-",
			num: result.slice(1, 3).join('')
		}
	}
}

/**
 * 数组归一 (按索引覆盖合并数组,并清空被合并的数组)
 * 
 * @param {array} baseArray 基础数组
 * @param {...array} array1 
 * @returns array
 */
exports.centerArray = function centerArray(baseArray, array1 /*[, array2[, ...[, arrayN]]]*/) {
    baseArray.splice.apply(baseArray,[0,array1.length].concat(array1.splice(0, array1.length)));
    if (arguments.length > 2) {
        var r = [].slice.call(arguments, 2);
        r.unshift(baseArray);
        centerArray.apply(null, r);
    }
    return baseArray;
}

/**
 * 检查对像属性 (非原型链)
 * 
 * @param {any} obj
 * @param {any} key
 * @returns
 */
var hasAttr = exports.hasAttr = function(obj,key){
	return Object.prototype.hasOwnProperty.call(obj,key);
}

/**
 * 扩展对像(浅复制)
 * 
 * @param {any} obj
 * @param {any} obj1
 * @returns
 */
exports.extend = function(obj){
	var name 
		,target = arguments[ 0 ] || {};
	var objs = Array.prototype.slice.call(arguments,1);

	for(var i=0; i<objs.length; i++){
		var _obj = objs[i];
		for(name in _obj){
			if(hasAttr(_obj,name)){
				target[name] = _obj[name];
			}
		}
	}
	return target;
}




},{}]},{},[1])