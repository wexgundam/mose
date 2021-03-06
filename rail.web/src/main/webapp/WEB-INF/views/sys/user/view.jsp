<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>
<head>
    <title>用户管理</title>
</head>
<critc-css>
    <link rel="stylesheet"
          href="${staticServer}/assets/cropper3.0/cropper.min.css"/>
    <link rel="stylesheet" href="${staticServer}/assets/cropper3.0/main.css"/>
    <link href="${staticServer}/assets/metronic4.7/pages/css/profile.min.css" rel="stylesheet" type="text/css"/>
    <link href="${staticServer }/assets/zTree3.5/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css"/>
</critc-css>
<body class="no-skin">

<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i> <a href="${dynamicServer}/index.htm">首页</a>
        </li>
        <li>
            >个人信息
        </li>


    </ul>
</div>


<h1 class="page-title inline"> 个人信息
</h1>


<div class="row  col-md-10 col-md-offset-1">
    <form id="userForm" name="userForm" class="form-horizontal "
          action="update.htm" method="post">
        <div class="tabbable-custom  nav-justified">
            <ul class="nav nav-tabs ">
                <li class="active" id="pt">
                    <a href="#tab_1_1" data-toggle="tab"> 基本信息 </a>
                </li>
                <li class="" id="yx">
                    <a href="#tab_1_2" data-toggle="tab"> 更多信息 </a>
                </li>
                <li class="pull-right m-grid-col m-grid-col-bottom">
                    <a id="completion" > </a>
                </li>
            </ul>
            <input type="hidden" name="tab" value="1" id="tab2">
            <%--<div class="portlet light bordered  col-md-12 col-md-offset-1">--%>
            <div class="tab-content ">

                <div class="tab-pane fade active in " id="tab_1_1">
                    <div class="row ">
                        <div class="col-md-7">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" id="id" value="${sysUser.id}">
                            <div class="form-group">
                                <label class="col-md-3 control-label">账号：</label>
                                <div class="col-md-9">
                                    <input id="username" type="text" name="username"
                                           class="form-control input-inline input-medium " readonly
                                           placeholder="" value="${sysUser.username}"
                                           style="border: hidden;background: none">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">真实姓名：</label>
                                <div class="col-md-9">
                                    <input id="realName" type="text" name="realName"
                                           class="form-control input-inline input-medium "
                                           placeholder="" value="${sysUser.realName}"><label
                                        id="realNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名拼音：</label>
                                <div class="col-md-9">
                                    <input id="pinyin" type="text" name="pinyin"
                                           class="form-control input-inline input-medium"
                                           placeholder="" value="${sysUser.pinyin}"><label
                                        id="pinyinTip"></label>
                                </div>
                            </div>
                            <div class="form-group" style="margin-bottom:-20px;">
                                <label class="col-md-3 control-label">性别：</label>
                                <div class="col-md-9 ">
                                    <div class="mt-radio-inline">
                                        <label class="mt-radio">
                                            <input type="radio" class="input-inline" name="gender"
                                                   id="male" value="男"/>男
                                            <span></span>
                                        </label>
                                        <label class="mt-radio">
                                            <input type="radio" class="input-inline" name="gender"
                                                   id="female"
                                                   value="女"/>女
                                            <span></span>
                                        </label>
                                    </div>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">手机：</label>
                                <div class="col-md-9">
                                    <input id="mobile" type="text" name="mobile"
                                           class="form-control input-inline input-medium"
                                           placeholder="" value="${sysUser.mobile}"/><label
                                        id="mobileTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">部门：</label>
                                <div class="col-md-9">
                                    <input type="hidden" id="departmentId" name="departmentId"
                                           class="form-control" value="${sysUser.departmentId}"/>
                                    <input id="departmentName" type="text" name="departmentName"
                                           class="form-control input-inline input-medium " readonly
                                           placeholder="" value="${sysUser.departmentName}"
                                           style="border: hidden;background: none">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">角色：</label>

                                <div class="col-md-9">
                                    <input type="hidden" id="roleId" name="roleId"
                                           class="form-control" value="${sysUser.roleId}"/>
                                    <input id="roleName" type="text" name="roleName"
                                           class="form-control input-inline input-medium " readonly
                                           placeholder="" value="${sysUser.roleName}"
                                           style="border: hidden;background: none">
                                </div>


                            </div>
                        </div>

                        <div class=" col-md-4">
                            <!-- PORTLET MAIN -->
                            <div class="portlet light   bg-inverse" style="width:200px;height:250px;margin:0 auto">
                                <!-- SIDEBAR USERPIC -->
                                <div class="row" style="width:90%;margin:0 auto">
                                    <input type="hidden" class="avatar" name="avatar" value="${sysUser.avatar}">
                                    <img id="imgAvatar" src="${imageServer}${sysUser.avatar}"
                                         onerror="this.onerror=null;this.src =
                                                 '${imageServer}/sys/user/avatar/male.jpg'"
                                         class="img-responsive" alt="">
                                </div>
                                <!-- END SIDEBAR USERPIC -->
                                <!-- SIDEBAR BUTTONS -->
                                <br/>
                                <div class="row" style="text-align:center">
                                    <button type="button"
                                            class="btn btn-circle blue btn-sm"
                                            id="btnEditAvatar">修改头像
                                    </button>

                                </div>
                                <br/>
                                <!-- END SIDEBAR BUTTONS -->
                            </div>
                        </div>

                    </div>

                </div>
                <%--<div class="portlet light bordered col-md-12  col-md-offset-1">--%>
                <div class="tab-pane fade active in" id="tab_1_2">

                    <div class="row ">

                        <input type="hidden" name="backUrl" value="${backUrl }"/>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">邮箱：</label>
                                <div class="col-md-8">
                                    <input id="email" type="text" name="email"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.email}"><label
                                        id="emailTip"></label>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">职务：</label>
                                <div class="col-md-8 ">
                                    <form:select path="sysUser.jobTitle" name="jobTitle"
                                                 class="form-control input-inline" style="width:90%"
                                                 id="jobTitle">
                                        <option value="">请选择职务</option>
                                        <form:options items="${listJobTitle }" itemValue="name"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">职称：</label>
                                <div class="col-md-8">
                                    <form:select path="sysUser.post" name="post"
                                                 class="form-control input-inline" style="width:90%"
                                                 id="post">
                                        <option value="">请选择职称</option>
                                        <form:options items="${listPost }" itemValue="name"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">身份证号：</label>
                                <div class="col-md-8">
                                    <input id="idcard" type="text" name="idcard"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.idcard}"><label
                                        id="idcardTip"></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">政治面貌：</label>
                                <div class="col-md-8">
                                    <form:select path="sysUser.political" name="political"
                                                 class="form-control input-inline " style="width:90%"
                                                 id="political">
                                        <option value="">请选择政治面貌</option>
                                        <form:options items="${listPolitical}" itemValue="name"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">民族：</label>
                                <div class="col-md-8">
                                    <form:select path="sysUser.nation" name="nation"
                                                 class="form-control input-inline " style="width:90%"
                                                 id="nation">
                                        <option value="">请选择民族</option>
                                        <form:options items="${listNation }" itemValue="name"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">教育程度：</label>
                                <div class="col-md-8">
                                    <form:select path="sysUser.education" name="education "
                                                 class="form-control input-inline " style="width:90%"
                                                 id="education ">
                                        <option value="">请选择教育程度</option>
                                        <form:options items="${listEducation}" itemValue="name"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">毕业院校：</label>
                                <div class="col-md-8">
                                    <input id="graduatedSchool" type="text" name="graduatedSchool"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.graduatedSchool}"/><label
                                        id="graduatedSchoolTip"></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">座机：</label>
                                <div class="col-md-8">
                                    <input id="telephone" type="text" name="telephone"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.telephone}"><label
                                        id="telephoneTip"></label>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">专业：</label>
                                <div class="col-md-8">
                                    <input id="major" type="text" name="major"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.major}"><label
                                        id="majorTip"></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-4 control-label">排序：</label>
                                <div class="col-md-8">
                                    <input id="displayOrder" type="text" name="displayOrder"
                                           class="form-control input-inline " style="width:90%"
                                           placeholder="" value="${sysUser.displayOrder}">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>
        <div class="form-actions col-md-12">
            <div class="row">
                <div class=" col-md-offset-4 col-md-6">
                    <button type="commit" class="btn btn-primary"><i class="fa fa-save"/></i> 保存
                    </button>
                    <button type="button" class="btn default" onclick="history.back(-1)"><i
                            class="fa fa-undo"/></i>  取消
                    </button>
                </div>
            </div>
        </div>


    </form>

</div>
<div class="row" id="crop-avatar">
    <!-- Cropping modal -->
    <div class="modal fade" id="avatar-modal" aria-hidden="false"
         aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form class="avatar-form" id="avatar-form"
                      action="${dynamicServer}/sys/user/uploadAvatar.htm"
                      enctype="multipart/form-data" method="post" accept="image/*">
                    <div class="modal-header">
                        <button type="button" class="close" onclick="javascript:closeAvatar();">&times;</button>
                        <h4 class="modal-title" id="avatar-modal-label">修改头像</h4>
                    </div>
                    <div class="modal-body">
                        <div class="avatar-body">
                            <!-- Upload image and data -->
                            <div class="avatar-upload">
                                <input type="hidden" class="avatar-src" name="avatar_src">
                                <input type="hidden" class="avatar-data" name="avatar_data">
                                <input type="hidden" name="userId" value="0">
                                <label for="avatarInput" class="btn btn-primary">选择图片</label> <input
                                    type="file" class="avatar-input" id="avatarInput"
                                    name="avatar_file" style="display: none;" accept="image/*">
                            </div>

                            <!-- Crop and preview -->
                            <div class="row">
                                <div class="col-md-9">
                                    <div id="preview" class="avatar-wrapper"></div>
                                </div>
                                <div class="col-md-3">
                                    <div class="avatar-preview preview-lg"></div>
                                    <div class="avatar-preview preview-md"></div>
                                    <div class="avatar-preview preview-sm"></div>
                                </div>
                            </div>

                            <div class="row avatar-btns">
                                <div class="col-md-6">

                                    <button type="button" onclick="confirm()" class="btn btn-primary avatar-save">
                                        <i class="fa fa-save"/></i> 保存
                                    </button>
                                    <button type="button" class="btn btn-default"
                                            onclick="javascript:closeAvatar();">
                                        <i class="fa fa-undo"/></i> 取消

                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- /.modal -->
    <!-- Loading state -->
    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
</div>
<div class="modal fade" id="departmentList" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">选择部门</h4>
            </div>
            <div class="modal-body">
                <ul id="tree" class="ztree" style="width: 560px; overflow: auto;"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="javascript:getSelected();">确认</button>
                <button type="button" class="btn " data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

</body>

<critc-script>
    <script
            src="${staticServer}/assets/cropper3.0/cropper.min.js"></script>
    <script
            src="${staticServer}/assets/cropper3.0/main.js"></script>
    <script src="${staticServer }/assets/zTree3.5/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
            init();
            showCompletion();
            $("input[type='text']").bind('change', completionCalculate);
            $("#btnEditAvatar").bind('click', showAvatar);
            $("#realName").bind('change', getpinyin);
            $("input[name='gender']").each(function () {
                if ($(this).val() == '${sysUser.gender}') {
                    $(this).attr("checked", true);
                }
            });
            $("input[type='text'],select").bind('change', completionCalculate);
        });
        function init() {
            $("#tab_1_1").addClass("active in");
            $("#tab_1_2").removeClass("active in");
            $("#pt").addClass("active");
            $("#yx").removeClass("active");

        }
        function closeAvatar() {
            $('#avatar-form')[0].reset();
            $('#avatar-modal').modal('hide');
        }
        function showAvatar() {
            $('#avatar-modal').modal('show');
        }

        var zTree;
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            }
        };
        var zNodes = [${ztree}];
        jQuery(document).ready(function () {
            var t = $("#tree");
            t = $.fn.zTree.init(t, setting, zNodes);
            var zTree = $.fn.zTree.getZTreeObj("tree");
        });

        function getSelected() {
            var treeObj = $.fn.zTree.getZTreeObj("tree");
            var nodes = treeObj.getSelectedNodes();
            if (nodes.length > 0) {
                $("#departmentId").val(nodes[0].id);
                $("#departmentName").val(nodes[0].name);
                $('#departmentList').modal('hide');
            }
            else return;

        }
        function getpinyin() {
            var realName = $("#realName").val();
            $.ajax({
                type: 'POST',
                url: 'getPinYin.htm',
                dataType: 'json',
                data: {
                    realName: realName
                },
                success: function (result) {
                    if (result["success"]) {
                        $('#pinyin').val(result.pinyin);


                    }
                }

            });

        }
        function showAvatar() {
            $('#avatar-modal').modal('show');
        }

        jQuery.validator.addMethod("isIdCardNo", function (value, element) {
            var regex = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            return this.optional(element) || regex.test(value);
        }, "请正确输入您的身份证号码");
        $(document).ready(function () {
            $("#userForm").validate({
                //debug : true,
                errorElement: "label",
                errorClass: "valiError",
                errorPlacement: function (error, element) {
                    error.appendTo($("#" + element.attr('id') + "Tip"));
                },
                rules: {
                    realName: {
                        required: true,
                        maxlength: 20
                    },
                    mobile: {
                        mobile: true,
                        required: true,
                        maxlength: 11,

                    },
                    roleId: {
                        required: true
                    },
                    email: {
                        maxlength: 30,
                        email: true,
                    },
                    idcard: {
                        isIdCardNo: true
                    }
                },
                messages: {
                    username: {
                        remote: "账号已存在！"
                    },
                    mobile: {
                        remote: "此手机号已注册过账号！"
                    },
                    idcard: {
                        idcard: "请输入正确的身份证号"
                    }
                },

                submitHandler: function (form) {
                    save();
                }
            });
        });

        var imgurl;

        function confirm() {
            if ($('#avatarInput').val()) {
                var dataURL = $("#preview > img").cropper("getCroppedCanvas");
                imgurl = dataURL.toDataURL("image/*", 0.3);
                $("#imgAvatar").attr("src", imgurl);
                imgurl = convertBase64UrlToBlob(imgurl);
                $('#avatar-modal').modal('hide');
            } else {
                bootbox.alert({
                    buttons: {
                        ok: {
                            label: '确定'
                        }
                    },
                    message: '还没有上传图片，请先选择图片！'
                });
            }

        }
        //保存用户信息
        function save() {
            var form = document.getElementById("userForm");
            var data = new FormData(form);
            data.append("file", imgurl);
            $.ajax({
                type: "POST",
                url: '${dynamicServer}/sys/user/update.htm',
                data: data,

                dataType: "json",
                processData: false,
                contentType: false,
                success: function (data) {
                    window.location.href = data.msgText + "&backUrl=${backUrl}" + "";
                }
            });
        }
        function convertBase64UrlToBlob(urlData) {

            var bytes = window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }

            return new Blob([ab], {type: 'image/*'});
        }
        //计算完整度
        function completionCalculate(){
            var form = document.getElementById("userForm");
            var data = new FormData(form);
            $.ajax({
                type: 'POST',
                url: 'completionCalculate.htm',
                data: data,
                dataType: 'json',
                processData: false,
                contentType: false,
                success: function (result) {

                    if (result["success"]) {
                        showCompletion(result["completion"]);
                    }
                }

            });
        }
        //显示不用颜色完整度
        function showCompletion(comple){
            if(comple == null){
                comple  =${sysUser.completion};
            }
            if(comple!=null&&comple>=80){
                $("#completion").html('完整度:'+comple+' %' ).css('color','green');
            }else if(comple!=null&&comple>=50&&comple<80){
                $("#completion").html('完整度:'+comple+' %' ).css('color','gold');
            }else{
                $("#completion").html('完整度:'+comple+' %' ).css('color','red');
            }
        }

    </script>
</critc-script>