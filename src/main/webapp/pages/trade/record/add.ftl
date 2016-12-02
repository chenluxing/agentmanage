<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增交易记录</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript" src="${base}/resources/js/input.js"></script>
        <script type="text/javascript">
            $().ready(function () {
                // 表单验证
                $("#inputForm").validate({
                    rules: {
                        merchantId: {
                            required: true,
                            remote: {
                                type: "POST",
                                async:false,
                                url: "${base}/trade/record/checkMerchantIdIsLastLevel.json"
                            }
                        },
                        tradeAmount: {
                            required: true,
                            isDecimalTwo: true
                        },
                        tradeCount: {
                            required: true,
                            isNumber: true
                        }
                    },
                    messages: {
                        merchantId: {
                            remote:"商户ID信息不存在或不是最后一级代理"
                        }

                    }
                });

                $("#btnSubmit").click(function () {
                    disableBtn();
                    $("#inputForm").submit();
                    removedisableBtn();
                })
            });
        </script>
    </head>
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;交易管理</span>
            &nbsp;><span>&nbsp;新增交易记录</span>
        </div>
        <form id="inputForm" action="${base}/trade/record/add.html" method="post">
            <div style="width:620px;">
                <table class="table_input">
                    <tbody>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">商户ID</td>
                            <td class="td_content">
                                <input type="text" id="merchantId" name="merchantId" class="text" maxlength="32" placeholder="请填写商户ID" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">交易金额</td>
                            <td class="td_content">
                                <input type="text" id="tradeAmount" name="tradeAmount" class="text" maxlength="10" placeholder="请填写交易金额" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">交易数量</td>
                            <td class="td_content">
                                <input type="text" id="tradeCount" name="tradeCount" class="text" maxlength="10" placeholder="请填写交易数量" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td_content" style="text-align: center;">
                                <input type="button" id="btnSubmit" class="btn_normal" value="新增" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>