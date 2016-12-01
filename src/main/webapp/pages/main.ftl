<#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<!DOCTYPE html>
<html>
    <head>
        <title>首页</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                //初始化高度
                $("#backmain").height($(window).height() - 74 + "px");
                //当文档窗口发生改变时 触发
                $(window).resize(function(){
                    $("#backmain").height($(window).height() - 74 + "px");
                })
            })
            if (self != top) {
                top.location = self.location;
            }
        </script>
    </head>
    <body>
        <!-- 头部 start-->
        <header>
            <div class="wrap clearfix">
                <div class="logoText fl">XXX管理系统</div>
                <div class="backother fr">
                    欢迎您,
                    <a href="javascript:void(0)" id="text">${user.realName}（${user.userName}）</a>
                    <a href="${base}/logout.html" target="_top">
                        [退出]
                    </a>
                </div>
            </div>
            <div style="border: 2px solid red;" class="wrap headmenu bg_oran">
                <!-- 顶部主菜单 start -->
                <#--<ul class="menu clearfix">
                    <li class="active" id="workbench">
                        <a href="#product">个人账户信息</a>
                    </li>
                    <li>
                        <a href="#product">交易管理</a>
                    </li>
                </ul>-->
                <!-- 顶部主菜单 end -->
            </div>
        </header>
        <!-- 头部end -->
        <!-- 内容部分 start -->
        <div id="backmain" class="wrap clearfix" style="padding-top: 0px;">
        <!-- 左栏 start -->
            <div id="backleft" class="fl">
                <div class="bleftcon" style="display:block;">
                    <div>
                        <div class="vtitle tsy">
                            <i></i><em class="bleft bsty"></em>我的账户
                        </div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                <li class="select">
                                    <a href="${base}/self/account/view.html" class="current" target="iframe"><i></i>账户信息</a>
                                </li>
                                <li>
                                    <a href="${base}/user/toChangePassword.html" target="iframe"><i></i>修改密码</a>
                                </li>
                                <li>
                                    <a href="${base}/self/trade/record.html" target="iframe"><i></i>交易记录</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle tsy">
                            <i></i><em class="bleft bsty"></em>代理人管理
                        </div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                <li>
                                    <a href="${base}/agent/list.html" target="iframe"><i></i>代理人管理</a>
                                </li>
                                <li>
                                    <a href="${base}/agent/toAdd.html" target="iframe"><i></i>新增代理人</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle tsy">
                            <i></i><em class="bleft bsty"></em>交易管理
                        </div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                <li>
                                    <a href="${base}/trade/record/list.html" target="iframe"><i></i>交易记录列表</a>
                                </li>
                                <li>
                                    <a href="${base}/trade/record/toAdd.html" target="iframe"><i></i>新增交易记录</a>
                                </li>
                                <li>
                                    <a href="${base}/trade/import/list.html" target="iframe"><i></i>导入交易记录</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 左栏 end -->

            <!-- 右栏 start -->
            <div id="backcon">
                <iframe id="iframe" name="iframe" src="${base}/self/account/view.html" frameborder="0">
                </iframe>
            </div>
            <!-- 右栏 end -->
        </div>
        <!-- 内容部分 end -->
    </body>
</html>