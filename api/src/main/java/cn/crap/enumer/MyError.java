package cn.crap.enumer;

public enum MyError {
	E000001("系统未知错误!", "Unknow Error!"),
	E000002("重复的errorCode"),
	E000003("权限不足或登录超时，操作失败！"),
	E000004("重复的接口url地址！(同一模块下不允许重复的url)"),
	E000005("接口url不能为空！"),
	E000006("重复的key！"),
	E000007("请输入访问密码"),
	E000008("数据类型有误"),
	E000009("系统数据，不允许删除"),
	E000010("验证码输入有误"),
	E000011("图形验证码已失效，请刷新验证码"),
	E000012("查找数据失败"),
	E000013("用户名不存在"),
	E000014("用户密码有误"),
	E000015("用户名已被使用，请更换用户名"),
	E000016("文件地址不能为空"),
	E000017("文件名有误，无法识别的版本号：文件必须包含“.CAV.文件标识.版本号”"),
	E000018("文件标识有误，更新文件时，文件名必须包含“.CAV.文件标识.版本号”，且【文件标识】必须和原文件相同"),
	E000019("文件版本号已经过期，请重新下载文件编辑后再上传"),

	E000020("参数有误，查询失败"),
	E000021("Login Timeout!「登录超时」"),
	E000022("权限不足"),
	E000023("子模块数量大于0，不允许删除该模块"),
	E000024("接口数量大于0，不允许删除该模块"),
	E000025("子菜单数量大于0，不允许删除该菜单"),
	E000026("GitHub授权登陆异常"),
	E000027("模块的父项目不能为模块自身"),
	E000028("账号含有非法字符，账号只能为大小写字母、-、_、.及数字组合，长度5-20位"),
	E000029("参数有误，操作失败"),


	E000030("该邮箱尚未注册，操作失败"),
	E000031("邮箱验证码有误，请重新发送验证码"),
	E000032("邮箱格式不正确能"),
	E000033("错误码数量大于0，不允许删除该模块"),
	E000034("模块文章数量大于0，不允许删除该模块"),
	E000035("模块文件数量大于0，不允许删除该模块"),
	E000036("模块数据库表数量大于0，不允许删除该模块"),
	E000037("评论数量大于0，不允许删除该文章"),
	E000038("项目成员大于0，不允许删除项目"),
	E000039("成员已存在，不允许重复添加"),
	E000040("用户不存在"),
	E000041("私有项目，必须登录才能访问"),
	E000042("权限不够，禁止访问该项目"),
	E000043("站内搜索/全文检索需要登陆后方可使用"),
	E000044("非公开项目不支持静态化"),
	E000045("地址有误，静态化失败，请确认配置文件config.properties中的网站域名配置是否正确！"),
	E000046("SQL格式有误，必须以create开头"),
	E000047("接口只能在同一项目下的模块中移动"),
	E000048("模块已经删除，恢复接口是失败，请先恢复模块"),
	E000049("项目已经删除，恢复模块是失败，请先恢复项目"),
	E000050("抱歉，模块的修改操作不允许恢复（需要关联更新接口数据）"),
	E000051("抱歉，系统出现未知空指针异常（你查找的数据可能已被删除）"),
	E000052("添加失败，超过允许的最大长度"),
	E000053("超级管理员信息只能自己修改"),
	E000054("管理员自能修改普通用户信息"),
	E000055("admin账号为内置账号，不能修改用户名"),
	E000056("非法请求，拒绝响应"),
	E000057("尚未静态化，静态化后方可下载"),
	E000058("最多允许同步100个接口，请删除部分接口再试，或联系管理员修改数量！", "Up to 100 interfaces are allowed at most. Delete some interfaces, try again, or contact us to modify the quantity!"),
	E000059("非法的url，前端首页地址正能为站内相对地址：以font/、index.do开头的地址"),
	E000060("不允许匿名评论，请使用github、码云或注册账号后评论（便于问题解决后及时通知）"),
	E000061("用户密码不能为空"),
	E000062("该邮箱已经被其他用户绑定，不能为该邮箱设置登陆密码，请更换账号邮箱"),
	E000063("数据不存在"),
	E000064("未登录，请登录后再试"),
	E000065(""),
	E000066("key不能为空"),
	E000067("apiDebug 浏览器插件项目，不允许添加模块，请通过插件添加模块"),
	E000068("项目超过最大允许数量"),
	E000069("登陆超时，请重新登陆"),
	E000070("安全校验未通过"),
    E000071("超过项目允许的最大模块数"),
	E000072("超过项目允许的最大错误码数量");
	private final String message;
	private final String enMessage;

    MyError(String message){
        this.message = message;
        this.enMessage = "";
    }

	MyError(String message, String enMessage){
		this.message = message;
		this.enMessage = enMessage;
	}

	public String getMessage(){
		return message;
	}
	public String getEnMessage(){
	    return enMessage;
    }
}
