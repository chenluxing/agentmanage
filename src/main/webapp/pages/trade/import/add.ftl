<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增交易记录</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript" src="${base}/resources/js/input.js"></script>
        <script type="text/javascript" src="${base}/resources/js/list.js"></script>
        <script type="text/javascript">
            $().ready(function () {
                // 表单验证
                $("#inputForm").validate({
                    rules: {
                        file: {
                            required: true
                        }
                    },
                    messages: {
                    }
                });

                $("#btnSubmit").click(function () {
                    disableBtn();
                    var filepath = $("input[name='file']").val();
                    var extStart = filepath.lastIndexOf(".");
                    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
                    if (ext != ".XLS") {
                        alert("上传文件格式仅限xls(即：excel2003版本)");
                        removedisableBtn();
                        return;
                    }
                    $("#inputForm").submit();
                    removedisableBtn();
                })
            });

            function showTemplate(){
                $("#imgTemplate").css('display','block');
            }
        </script>
    </head>
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;交易管理</span>
            &nbsp;><span>&nbsp;导入交易记录</span>
        </div>
        <form id="inputForm" action="${base}/trade/import/add.html" method="post" enctype="multipart/form-data">
            <div style="width:700px;">
                <table class="table_input">
                    <tbody>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">请选择附件</td>
                            <td class="td_content">
                                <input type="file" id="file" name="file" class="text"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">
                                <a href="javascript:void(0)" onclick="showTemplate()">显示导入文件示例</a>
                            </td>
                            <td class="td_content">
                                <img id="imgTemplate" src="${base}/resources/images/agentm/import_template.jpg" style="display: none;">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td_content" style="text-align: center;">
                                <input type="button" id="btnSubmit" class="btn_normal" value="上传" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <#if (errorLines)?? && (errorLines?size > 0)>
        <div style="width: 100%;">
            <table class="table_gray">
                <thead>
                <tr>
                    <th>行号</th>
                    <th>错误提示</th>
                </tr>
                </thead>
                <tbody>
                    <#list errorLines as errorLine>
                        <#list errorLine.message as msg>
                        <tr>
                            <td style="color: red;">第${errorLine.line}行</td>
                            <td style="color: red;">${msg}</td>
                        </tr>
                        </#list>
                    </#list>
                </tbody>
            </table>
        </div>
        </#if>
    </body>
</html>