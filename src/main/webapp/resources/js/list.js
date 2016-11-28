// 页码跳转
$.pageSkip = function(pageNumber) {
    $("#pageNumber").val(pageNumber);
    $("#listForm").submit();
    return false;
}