/*==============================================================*/
/* Database name:  db_net                                       */
/* DBMS name:      Microsoft SQL Server 2000                    */
/* Created on:     2007-12-5 10:13:30                           */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('tb_business')
            and   type = 'U')
   drop table tb_business
go


if exists (select 1
            from  sysobjects
           where  id = object_id('tb_category')
            and   type = 'U')
   drop table tb_category
go


if exists (select 1
            from  sysobjects
           where  id = object_id('tb_news')
            and   type = 'U')
   drop table tb_news
go


if exists (select 1
            from  sysobjects
           where  id = object_id('tb_usertable')
            and   type = 'U')
   drop table tb_usertable
go


/*==============================================================*/
/* Table: tb_business                                           */
/*==============================================================*/
create table tb_business (
   id                   int                  not null,
   name                 varchar(50)          null,
   img                  varchar(50)          null,
   ms                   varchar(100)         null,
   category             varchar(20)          null,
   submittime           datetime             null,
   constraint PK_TB_BUSINESS primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_category                                           */
/*==============================================================*/
create table tb_category (
   id                   int                  not null,
   categoryname         varchar(50)          null,
   submittime           datetime             null,
   constraint PK_TB_CATEGORY primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_news                                               */
/*==============================================================*/
create table tb_news (
   id                   int                  not null,
   title                varchar(50)          null,
   content              varchar(100)         null,
   author               varchar(20)          null,
   submittime           datetime             null,
   constraint PK_TB_NEWS primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_usertable                                          */
/*==============================================================*/
create table tb_usertable (
   id                   int                  not null,
   name                 varchar(50)          null,
   password             varchar(50)          null,
   constraint PK_TB_USERTABLE primary key  (id)
)
go


