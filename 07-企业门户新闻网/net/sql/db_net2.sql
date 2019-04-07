/*==============================================================*/
/* Database name:  tb_net2                                      */
/* DBMS name:      Microsoft SQL Server 2000                    */
/* Created on:     2007-12-4 15:43:15                           */
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
   category             varchar(50)          not null,
   name                 varchar(50)          null,
   submittime           datetime             null,
   ms                   varchar(50)          null,
   img                  varchar(20)          null,
   constraint PK__tb_business__4AB81AF0 primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_category                                           */
/*==============================================================*/
create table tb_category (
   id                   int                  identity,
   categoryname         varchar(50)          not null,
   submittime           datetime             null,
   constraint PK__tb_category__48CFD27E primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_news                                               */
/*==============================================================*/
create table tb_news (
   id                   int                  identity,
   title                varchar(50)          null,
   content              varchar(100)         null,
   author               varchar(50)          null,
   submittime           datetime             null default getdate(),
   constraint PK_TB_NEWS primary key  (id)
)
go


/*==============================================================*/
/* Table: tb_usertable                                          */
/*==============================================================*/
create table tb_usertable (
   id                   int                  identity,
   name                 varchar(20)          null,
   password             varchar(20)          null,
   constraint PK_TB_USERTABLE primary key  (id)
)
go


