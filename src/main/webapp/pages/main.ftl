<#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<!DOCTYPE html>
<html>
    <head>
        <title>首页</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript">
            $().ready(function () {
            });
            if (self != top) {
                top.location = self.location;
            }
        </script>
        <style type="text/css">
            a {
                font-size: 12px;
            }
            .logoText {
                height: 60px;
                line-height: 60px;
                overflow: hidden;
                padding-left: 30px;
                font-family: "微软雅黑", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif, "Arial Narrow", HELVETICA;
                font-size: 20px;
                font-weight: bold;
                color: #ff5307;
            }
            .fl{
                float: left;
                display: inline-block;
            }
            .fr{
                float: right;
                display: inline-block;
            }
            .backlTit {
                height: 45px;
                cursor: pointer;
                color: #7d5930;
                font-weight: bold;
            }
            .wrap {
                min-width: 1200px;
            }
            .backother {
                line-height: 60px;
                margin-right: 60px;
                position: relative;
                font-size: 12px;
                color: #7d5930;
            }
            .backother a {
                font-size: 12px;
                color: #7d5930;
            }
            .clearfix {
                zoom: 1;
                display: block;
            }
            .menu {
                margin-left: 30px;
            }
            ul {
                list-style-type: none;
            }
            .menu li {
                float: left;
                margin-right: 20px;
                height: 45px;
            }
            .menu li a{display: block; height: 14px; line-height: 12px; *line-height: 14px; margin-top: 8px; padding: 7px; color:#fff; font-size: 14px; text-decoration: none;}
            .menu li.active a,.menu li a:hover{color: #ff5307; font-weight: bold; border-radius: 4px; background-color: #fff;}
            .menu li.active a{color: #ff5307;}
            .bgOran {
                background-color: #ff5307;
            }
            #backleft, #backleft:before {
                width: 180px;
                height: 100%;
                background-color: #fff9f3;
                border-right: 1px solid #decbbf;
                margin-top: 8px;
            }
            .vtitle {
                height: 36px;
                line-height: 36px;
                color: #776c62;
                cursor: pointer;
                background-color: #fef4e9;
                padding-left: 30px;
            }
            .tsy {
                background-color: #754723;
                color: #dfd4cb;
                font-size: 14px;
                font-weight: bold;
            }
            .vcon {
                border-bottom: 1px solid #decbbf;
                padding: 1px;
            }
            .bleftcon {
                height: 100%;
            }
            .vconlist li.select, .vconlist li:hover {
                background-color: #ffe8d2;
                border-color: #f4d3b3;
            }

            .vconlist li {
                overflow: hidden;
                cursor: pointer;
                border: 1px solid #fff9f3;
                border-left: 0px;
                border-right: 0px;
                padding-left: 50px;
            }
            .vconlist li a {
                height: 34px;
                width: 100%;
                line-height: 34px;
                float: left;
                display: block;
                color: #776c62;
                text-decoration: none;
            }
            #backmain {
                height: 100%;
            }
        </style>
    </head>
    <body>
        <!-- 头部 start-->
        <header>
            <div class="wrap clearfix">
                <div class="logoText fl">代理人管理系统</div>
                <div class="backother fr">
                    欢迎您,
                    <a href="javascript:void(0)" id="text">${user.realName}（${user.userName}）</a>
                    <a href="${base}/logout.html" target="_top">
                        [退出]
                    </a>
                </div>
            </div>
            <div class="wrap headmenu bgOran">
                <!-- 顶部主菜单 start -->
                <ul class="menu clearfix">
                    <li class="active" id="workbench">
                        <a href="#product">个人账户信息</a>
                    </li>
                    <li>
                        <a href="#product">交易管理</a>
                    </li>
                </ul>
                <!-- 顶部主菜单 end -->
            </div>
        </header>
        <!-- 头部end -->
        <!-- 内容部分 start -->
        <div id="backmain" class="wrap clearfix">
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
                                    <a href="${base}/agent/account/view.html?agentId=${user.agentId}" class="current" target="iframe"><i></i>账户信息</a>
                                </li>
                                <li>
                                    <a href="${base}/index.html" target="iframe"><i></i>修改密码</a>
                                </li>
                                <li>
                                    <a href="${base}/index.html" target="iframe"><i></i>交易记录</a>
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
                <iframe id="iframe" name="iframe" src="" frameborder="0">
                </iframe>
            </div>
            <!-- 右栏 end -->
        </div>
        <!-- 内容部分 end -->
    </body>
<script type="text/javascript">
</script>
</html>