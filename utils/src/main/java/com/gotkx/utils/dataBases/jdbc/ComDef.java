package com.gotkx.utils.dataBases.jdbc;

public class ComDef {

	public static final int ARCH_TYPE_SINGLE = 0; // 单件
	public static final int ARCH_TYPE_ROLL_SINGLE = 1; // 卷内
	public static final int ARCH_TYPE_ROLL = 2; // 案卷
	public static final int ARCH_TYPE_PROJECT = 6;// 项目

	// 旧系统
	public static final String DOC_SINGLE_TABLE_OLD = "archive_description_jh"; // 单件表
	public static final String ROLL_TABLE_OLD = "folder_description"; // 案卷表
	public static final String DOC_IN_ROLL_TABLE_OLD = "archive_description"; // 卷内表
	public static final String[] OLD_TABLE = { DOC_SINGLE_TABLE_OLD,
			DOC_IN_ROLL_TABLE_OLD, ROLL_TABLE_OLD };

	// 新系统
	public static final String DOC_TABLE_NEW = "doc_card"; // 单件表
	public static final String ROLL_TABLE_NEW = "vol_card"; // 案卷表

	public static final int SQL_SERVER = 0; // SQL SERVER 2008数据库
	public static final int ORACLE = 1; // Oracle数据库

	public static final int ARCHIVE_ID = 1; // 档案馆ID
	public static final String ARCHIVE_CODE = "443002"; // 档案馆代码
	public static final String ARCHIVE_NAME = "长沙市国家综合档案馆";// 档案馆名称

	public static final int MODULE_TYPE_ARCHIVE = 0; // 档案
	public static final int MODULE_TYPE_IMPORT = 6;// 导入

	public static final int DISABLED = 0; // 不可用
	public static final int USABLE = 1; // 可用

	public static final int NO = 0; // 否
	public static final int YES = 1; // 是

	public static final String ARCHIVE_CATE = "A1"; // 文书归档
	public static final String ROLL_CATE = "A2"; // 文书案卷
	public static final String ZXJS_CATE = "SZXJS";// 在线接收

	public static final int QUALITY_YES = 2; // 已入库

	public static final int COLLECT_TYPE_MIGRATE = 3; // 迁移数据

	public static final int HANDOVER_STATE_NO = 0; // 未移交
	public static final int HANDOVER_STATE_OK = 1; // 档案室已移交 档案馆已接收

	public static final int DOC_OPEN_UNIDENTIFY = 0; // 未鉴定 2014/5/8 add by hd
	public static final int DOC_OPEN_NO = 1; // 不开放 2014/5/8 由0改为1 change by hd
	public static final int DOC_OPEN_OK = 2; // 已开放 2014/5/8 由1改为2 change by hd
	public static final int DOC_OPEN_UNSURE = 3; // 不确定 2014/5/7 add by hd
	public static final int DOC_OPEN_SURE = 4; // 已经生成了鉴定表，即已最终确定为开放状态.

	// 密级
	public static final int SHARE_OPEN = 1; // 公开
	public static final int SHARE_CHINA = 2; // 国内
	public static final int SHARE_INSIDE = 3; // 内部
	public static final int SHARE_SECRIT = 4; // 秘密
	public static final int SHARE_CONFIDENTIAL = 5;// 机密
	public static final int SHARE_TOPSECRET = 6;// 绝密

	// 公开级别
	public static final int OPEN_LEVEL_PUBLIC = 4;// 公开
	public static final int OPEN_LEVEL_CHINA = 3;// 国内
	public static final int OPEN_LEVEL_UNITINSIDE = 2;// 单位内部
	public static final int OPEN_LEVEL_SECRET = 0;// 密件

	// 根分类号
	public static final String ROOT_CATE_CODE_SINGLE = "A1";
	public static final String ROOT_CATE_CODE_VOL = "A2";

	public static final int INT_DEFUALT_VALUE = -1;// int类型默认值

	public static final String UNDERLINE = "_"; // 下划线

	public static final String STRIKE = "-";// 中划线

	public static final String MID_DOT = "·"; // 中文的点

	public static final String SHUN_SLASH = "/"; // 正斜杠

	public static final String DOT = "."; // 点

	public static final String UPRIGHT = "|"; // 竖线

	public static final String[] SYMBOLS = { UNDERLINE, STRIKE, MID_DOT,
			SHUN_SLASH, DOT, UPRIGHT };

}
