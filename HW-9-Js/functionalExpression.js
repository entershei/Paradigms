function cnst(x) {
    return function () {
        return x;
    }
}

var name = {
    x: 0,
    y: 1,
    z: 2
};

function variable(x) {
    return function () {
        return arguments[name[x]];
    }
}

function binary_operation(f) {
    return function (x, y) {
        return function () {
            return f(x.apply(null, arguments), y.apply(null, arguments));
        }
    }
}

function unary_operation(f) {
    return function (x) {
        return function () {
            return f(x.apply(null, arguments));
        }
    }
}

var add = binary_operation(function (a, b) {
    return a + b;
});

var subtract = binary_operation(function (a, b) {
    return a - b;
});

var multiply = binary_operation(function (a, b) {
    return a * b;
});

var divide = binary_operation(function (a, b) {
    return a / b;
});

var negate = unary_operation(function (a) {
    return -a;
});

var cube = unary_operation(function (a) {
    return Math.pow(a, 3);
});

var cuberoot = unary_operation(function (a) {
    return Math.pow(a, 1 / 3);
});
