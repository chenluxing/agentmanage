create table tb_user(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
  user_name varchar(20) not null comment '用户名',
  `password` varchar(64) not null comment '密码',
  salt varchar(6) not null comment '加密参数',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id),
	UNIQUE uq_user_name(user_name)
)COMMENT = '系统用户表';

create table tb_agent_info(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
  mobile_no varchar(11) not null comment '手机号',
  real_name varchar(100) not null comment '真实姓名',
  merchant_id varchar(32) comment '商户ID',
  alipay_no varchar(64) not null comment '支付宝帐号',
  agent_percent decimal(10,4) not null comment '代理人佣金利率',
	`level` INT(11) comment '代理人层级',
  parent_agent_id INT(11) comment '上级代理人ID',
  user_id INT(11) not null comment '关联用户ID',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '代理人信息表';

create table tb_agent_account(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
  agent_id INT(11) not null comment '代理人ID',
  total_amount decimal(11,2) not null comment '总交易金额', 
  total_agent_amount decimal(11,2) not null comment '总佣金',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '代理人账户表';

create table tb_agent_account_detail(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
  account_id INT(11) not null comment '关联账户ID',
  trade_amount decimal(11,2) comment '交易金额', 
  trade_agent_amount decimal(11,2) not null comment '交易佣金', 
  total_amount decimal(11,2) comment '交易总交易金额', 
  total_agent_amount decimal(11,2) not null comment '交易后总佣金', 
  data_id INT(11) comment '关联数据ID',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '代理人账户明细表';

create table tb_trade_record(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
  agent_id INT(11) not null comment '代理人ID',
  trade_count INT(11) comment '交易数量',
  trade_amount decimal(11,2) comment '交易金额',
  agent_percent decimal(10,4) comment '交易时佣金利率',
  agent_amount decimal(11,2) comment '佣金',
  creator_id INT(11) comment '创建者ID',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '代理人交易明细表';

create table tb_trade_import_log(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
	data_count INT(11) comment '导入数据量',
  useful_count varchar(32) comment '有效数据量',
  total_trade_amount decimal(11,2) comment '总交易金额',
  total_trade_count INT(11) comment '总交易数量',
  creator_id INT(11) not null comment '创建者ID',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '导入记录表';

create table tb_trade_import_detail(
  id INT(11) NOT NULL COMMENT '主键ID' AUTO_INCREMENT,
	merchant_name varchar(100) comment '真实姓名',
  merchant_id varchar(32) comment '商户ID',
  trade_amount decimal(11,2) comment '交易金额',
  trade_count INT(11) comment '交易数量',
  agent_id INT(11) not null comment '代理人ID',
  parent_agent_id INT(11) comment '代理人ID',
  agent_level INT(11) comment '代理人层级',
  log_id INT(11) not null comment '导入记录ID',
	is_original TINYINT(1) not null comment '是否原始数据0：是；1：否',
  gmt_created datetime COMMENT '创建时间',
  gmt_modified datetime COMMENT '修改时间',
  PRIMARY KEY (id)
)comment = '导入记录明细表';