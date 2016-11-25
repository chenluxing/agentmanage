/*手机号码验证方法*/
function checkMoblie(code) {
    if (/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i.test(code)) {
        return true;
    } else {
        return false;
    }
}

/*验证长度在制定范围内*/
function checkLenSection(code, min, max) {
    if(code.length >= min && code.length <= max) {
        return true;
    }else {
        return false;
    }
}

/*验证字段需为字母,数字*/
function checkCharNum(code) {
    if (/^[0-9A-Za-z]+$/.test(code)) {
        return true;
    } else {
        return false;
    }
}