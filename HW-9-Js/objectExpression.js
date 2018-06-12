var name = {
    x: 0,
    y: 1,
    z: 2
};

function Expression(func, operands, str) {
    this.func = func;
    this.str = str;
    this.operands = [];

    for (var i in operands) {
        if (operands.hasOwnProperty(i)) {
            this.operands.push(operands[i]);
        }
    }
}

Expression.prototype = {
    evaluate: function () {
        var evalArgs = [];

        for (var i in this.operands) {
            evalArgs.push(this.operands[i].evaluate.apply(this.operands[i], arguments));
        }

        return this.func.apply(null, evalArgs)
    },
    toString: function () {
        var ret = "";
        for (var i in this.operands) {
            ret += this.operands[i].toString();
            ret += " ";
        }

        ret += this.str;

        return ret;
    }
};

function makeClass(func, str) {
    function Class() {
        return Expression.call(this, func, arguments, str);
    }

    Class.prototype = Object.create(Expression.prototype);
    return Class;
}

function Variable(nameVar) {
    Expression.call(this, function () {
        return arguments[name[nameVar]]
    }, [], nameVar);
}

Variable.prototype = Object.create(Expression.prototype);

Variable.prototype.evaluate = function () {
    return this.func.apply(null, arguments);
};

function Const(value) {
    Expression.call(this, function () {
        return value
    }, [], value.toString())
}

Const.prototype = Object.create(Expression.prototype);

var Add = makeClass(function (a, b) {
    return a + b;
}, "+");
var Subtract = makeClass(function (a, b) {
    return a - b;
}, "-");
var Multiply = makeClass(function (a, b) {
    return a * b;
}, "*");
var Divide = makeClass(function (a, b) {
    return a / b;
}, "/");
var Negate = makeClass(function (a) {
    return -a;
}, "negate");
var Square = makeClass(function (a) {
    return Math.pow(a, 2);
}, "square");
var Sqrt = makeClass(function (a) {
    return Math.pow(Math.abs(a), 1 / 2);
}, "sqrt");