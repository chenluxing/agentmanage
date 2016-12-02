// 页码跳转
$.pageSkip = function(pageNumber) {
    $("#pageNumber").val(pageNumber);
    $("#listForm").submit();
    return false;
}

// 按钮暂停
function interruptedBtn(){
    $("button").attr("disabled","true");
    $("input[type=button]").attr("disabled","true");

    setTimeout(function(){
        $("button").removeAttr("disabled");
        $("input[type=button]").removeAttr("disabled");
    }, 1000);
}