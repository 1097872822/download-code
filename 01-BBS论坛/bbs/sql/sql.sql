create database db_bbs;
use db_bbs;
#用户信息表
create table tb_user(
	id int(11) auto_increment primary key not null,
	username varchar(20) not null,
	password varchar(20) not null,
	sex varchar(2) not null,
	email varchar(50) not null,
	oicq varchar(20) default null,
	signature varchar(300) default null,
	grade varchar(20) default null,
	lxdz varchar(50),
	tx varchar(30),
	grzy varchar(50),
	realname varchar(30)
);
insert into tb_user(username,password,sex,email,oicq,signature,grade,lxdz,tx,grzy,realname) values('TSoft','111','1','xiaoyu*****@sina.com','123','test','admin','test','2.gif','test','test');
#论坛信息表
create table tb_forum(
	id int(11) auto_increment primary key not null,
	forumname varchar(20) not null,
	manager varchar(100) default null,
	createtime timestamp default current_timestamp
);
insert into tb_forum(forumname,manager)values('ASP','fish');
insert into tb_forum(forumname,manager)values('PHP','fish');
insert into tb_forum(forumname,manager)values('C#','fish');
insert into tb_forum(forumname,manager)values('.NET','fish');
insert into tb_forum(forumname,manager)values('VB','fish');
insert into tb_forum(forumname,manager)values('JSP','fish');
#主题信息表
create table tb_topic(
	id int(4)  auto_increment primary key not null,
	content text,
	author varchar(20) not null,
	submittime timestamp(8) default current_timestamp,
	forumid int(4) default 0,
	title varchar(300) not null,
	xq varchar(30) not null,
	rq int(4) default 0,
	forumname varchar(20)
);
#insert into tb_topic(content,author,submittime,forumid,title)values('测试','快饿死的鱼','2007-08-20','1','测试');


#回复信息表
create table tb_response(
	id int(11) not null auto_increment primary key,
	title varchar(300) not null,
	content text,
	author varchar(20) not null,
	submittime timestamp default current_timestamp,
	topicid int(4) not null,
	topicname varchar(100),
	xq varchar(20) not null
);





	
	
