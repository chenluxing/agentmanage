var messages = {
    "admin.message.success": "操作成功",
    "admin.message.error": "操作错误",
    "admin.dialog.ok": "确&nbsp;&nbsp;定",
    "admin.dialog.cancel": "取&nbsp;&nbsp;消",
    "admin.dialog.deleteConfirm": "您确定要删除吗？",
    "admin.dialog.deleteConfirm1": "有相关数据，是否删除？",
    "admin.dialog.clearConfirm": "您确定要清空吗？",
    "admin.browser.title": "选择文件",
    "admin.browser.upload": "本地上传",
    "admin.browser.parent": "上级目录",
    "admin.browser.orderType": "排序方式",
    "admin.browser.name": "名称",
    "admin.browser.size": "大小",
    "admin.browser.type": "类型",
    "admin.browser.select": "选择文件",
    "admin.upload.sizeInvalid": "上传文件大小超出限制",
    "admin.upload.typeInvalid": "上传文件格式不正确",
    "admin.upload.invalid": "上传文件格式或大小不正确",
    "admin.validate.required": "必填项",
    "admin.validate.email": "E-mail格式错误",
    "admin.validate.url": "网址格式错误",
    "admin.validate.date": "日期格式错误",
    "admin.validate.dateISO": "日期格式错误",
    "admin.validate.pointcard": "信用卡格式错误",
    "admin.validate.number": "只允许输入数字",
    "admin.validate.digits": "只允许输入零或正整数",
    "admin.validate.minlength": "长度不允许小于{0}",
    "admin.validate.maxlength": "长度不允许大于{0}",
    "admin.validate.rangelength": "长度必须在{0}-{1}之间",
    "admin.validate.min": "不允许小于{0}",
    "admin.validate.max": "不允许大于{0}",
    "admin.validate.range": "必须在{0}-{1}之间",
    "admin.validate.accept": "输入后缀错误",
    "admin.validate.equalTo": "两次输入不一致",
    "admin.validate.remote": "输入错误",
    "admin.validate.integer": "只允许输入整数",
    "admin.validate.positive": "只允许输入正数",
    "admin.validate.negative": "只允许输入负数",
    "admin.validate.decimal": "数值超出了允许范围",
    "admin.validate.pattern": "格式错误",
    "admin.validate.extension": "文件格式错误",
    "admin.validate.isMobile": "请正确填写您的手机号码"
};

// 多语言
function message(code) {
    if (code != null) {
        var content = messages[code] != null ? messages[code] : code;
        if (arguments.length == 1) {
            return content;
        } else {
            if ($.isArray(arguments[1])) {
                $.each(arguments[1], function(i, n) {
                    content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                });
                return content;
            } else {
                $.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
                    content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                });
                return content;
            }
        }
    }
}

$().ready(function () {

    if ($.tools != null) {
        var $tab = $("#tab");
        var $title = $("#inputForm :input[title], #inputForm label[title]");

        // Tab效果
        $tab.tabs("table.tabContent, div.tabContent", {
            tabs: "input"
        });

        // 表单提示
        $title.tooltip({
            position: "center right",
            offset: [0, 4],
            effect: "fade"
        });
    }

    // 验证消息
    if ($.validator != null) {
        $.extend($.validator.messages, {
            required: message("admin.validate.required"),
            email: message("admin.validate.email"),
            url: message("admin.validate.url"),
            date: message("admin.validate.date"),
            dateISO: message("admin.validate.dateISO"),
            pointcard: message("admin.validate.pointcard"),
            number: message("admin.validate.number"),
            digits: message("admin.validate.digits"),
            minlength: $.validator.format(message("admin.validate.minlength")),
            maxlength: $.validator.format(message("admin.validate.maxlength")),
            rangelength: $.validator.format(message("admin.validate.rangelength")),
            min: $.validator.format(message("admin.validate.min")),
            max: $.validator.format(message("admin.validate.max")),
            range: $.validator.format(message("admin.validate.range")),
            accept: message("admin.validate.accept"),
            equalTo: message("admin.validate.equalTo"),
            remote: message("admin.validate.remote"),
            integer: message("admin.validate.integer"),
            positive: message("admin.validate.positive"),
            negative: message("admin.validate.negative"),
            decimal: message("admin.validate.decimal"),
            pattern: message("admin.validate.pattern"),
            extension: message("admin.validate.extension")
        });

        $.validator.setDefaults({
            errorClass: "fieldError",
            ignore: ".ignore",
            ignoreTitle: true,
            errorPlacement: function (error, element) {
                var fieldSet = element.closest("span.fieldSet");
                if (fieldSet.size() > 0) {
                    error.appendTo(fieldSet);
                } else {
                    error.insertAfter(element);
                }
            },
            submitHandler: function (form) {
                $(form).find(":submit").prop("disabled", true);
                form.submit();
            }
        });
    }
});

// 按钮禁用
function disableBtn(){
    $("button").attr("disabled","true");
    $("input[type=button]").attr("disabled","true");
}
// 按钮启用
function removedisableBtn(){
    setTimeout(function(){
        $("button").removeAttr("disabled");
        $("input[type=button]").removeAttr("disabled");
    }, 1000);
}