
CREATE TABLE STUDENT (
  ID VARCHAR2(55) PRIMARY KEY,
  NAME VARCHAR2(255) ,
  AGE INTEGER   
);

CREATE TABLE MAIL (
  ID VARCHAR2(55) PRIMARY KEY, 
  TITLE VARCHAR2(2048),
  CONTENT VARCHAR2(2048) ,
  SEND_ID INTEGER,
  SEND_DATE DATE
);
/*
CREATE TABLE SYS_USER (
  ID VARCHAR2(55) PRIMARY KEY,
  USER_NAME VARCHAR2 (55),
  REAL_NAME VARCHAR2 (55),
  PASSWORD VARCHAR2 (128),
  DEPT_NAME VARCHAR2 (55),
  MEMBER_ID VARCHAR2 (55)
);*/

CREATE TABLE CHECKING_IN (
  ID VARCHAR2(55) PRIMARY KEY,
  USER_ID VARCHAR2(55),
  HOUR INTEGER,
  START_DATE date,
  END_DATE date,
  "MONTH" INTEGER,
  STATUS INTEGER,
  over_time number(12,2)
  
) ;

CREATE TABLE CHECKING_IN_RECORD (
  ID VARCHAR2(55) PRIMARY KEY,
  USER_ID VARCHAR2(55),
  CHECKING_IN_DATE date,
  MONTH INTEGER
);

--系统菜单表
create table SYS_MENU 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   PARENT_ID            VARCHAR2(55),
   MENU_NAME            VARCHAR2(255),
   ICON_CLS             VARCHAR2(255),
   MENU_URL             VARCHAR2(255),
   MENU_INDEX           NUMBER,
   STATUS               NUMBER
);
comment on table SYS_MENU is
'系统菜单';
comment on column SYS_MENU.ID is
'ID';
comment on column SYS_MENU.PARENT_ID is
'父ID';
comment on column SYS_MENU.MENU_NAME is
'菜单名称';
comment on column SYS_MENU.ICON_CLS is
'菜单图标';
comment on column SYS_MENU.MENU_URL is
'菜单URL';
comment on column SYS_MENU.MENU_INDEX is
'显示顺序';
comment on column SYS_MENU.STATUS is
'状态';
--系统功能权限表
create table SYS_PERMISSION 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   PERMIS_TYPE          VARCHAR2(255),
   PERMIS_NAME          VARCHAR2(255),
   PERMIS_DESC          VARCHAR2(2048),
   PERMIS_CODE          VARCHAR2(255),
   MENU_ID              VARCHAR2(55),
   STATUS               NUMBER
);
comment on table SYS_PERMISSION is
'系统功能权限';
comment on column SYS_PERMISSION.ID is
'主键';
comment on column SYS_PERMISSION.PERMIS_TYPE is
'权限类型（数据/操作权限）';
comment on column SYS_PERMISSION.PERMIS_NAME is
'权限名称';
comment on column SYS_PERMISSION.PERMIS_DESC is
'权限描述';
comment on column SYS_PERMISSION.PERMIS_CODE is
'权限代码，系统唯一';
comment on column SYS_PERMISSION.MENU_ID is
'菜单ID';
comment on column SYS_PERMISSION.STATUS is
'状态';
--系统角色表
create table SYS_ROLE 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   ROLE_NAME            VARCHAR2(255),
   ROLE_DESC            VARCHAR2(1024),
   ROLE_CODE            VARCHAR2(255),
   STATUS               NUMBER,
   PARENT_ID            VARCHAR2(55)
);
comment on table SYS_ROLE is
'系统角色';
comment on column SYS_ROLE.ID is
'主键';
comment on column SYS_ROLE.ROLE_NAME is
'角色名称';
comment on column SYS_ROLE.ROLE_DESC is
'角色描述';
comment on column SYS_ROLE.ROLE_CODE is
'角色编码';
comment on column SYS_ROLE.STATUS is
'状态';
comment on column SYS_ROLE.PARENT_ID is
'从属角色ID';
--角色-权限关系表
create table SYS_ROLE_PERMISSION 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   ROLE_ID              VARCHAR2(55),
   PERMIS_ID            VARCHAR2(55),
   STATUS               NUMBER
);
comment on table SYS_ROLE_PERMISSION is
'角色-权限关系表';
comment on column SYS_ROLE_PERMISSION.ID is
'主键';
comment on column SYS_ROLE_PERMISSION.ROLE_ID is
'角色ID';
comment on column SYS_ROLE_PERMISSION.PERMIS_ID is
'功能权限ID';
comment on column SYS_ROLE_PERMISSION.STATUS is
'状态';
--系统用户表
create table SYS_USER 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   USER_NAME            VARCHAR2(255),
   MEMBER_ID            VARCHAR2(255),
   DEPARTMENT           VARCHAR2(255),
   JOB_TITLE            VARCHAR2(255),
   MAIL                 VARCHAR2(255),
   PWD                  VARCHAR2(255),
   PHOTO_URL            VARCHAR2(255),
   TOKEN                VARCHAR2(255),
   PERSONAL_QQ          VARCHAR2(255),
   ENTERPRISE_QQ        VARCHAR2(255),
   PHONE                VARCHAR2(255),
   WORK_PHONE           VARCHAR2(255),
   EXTENSION            VARCHAR2(255),
   REAL_NAME            VARCHAR2(255),
   STATUS               NUMBER,
   SUPERIOR_USER_ID     VARCHAR2(55),
   DEPT_NAME            VARCHAR2(55),
   DEVICE_TOKEN         VARCHAR2(100)
);
comment on table SYS_USER is
'系统用户';
comment on column SYS_USER.ID is
'主键';
comment on column SYS_USER.USER_NAME is
'用户账户号';
comment on column SYS_USER.MEMBER_ID is
'员工工号';
comment on column SYS_USER.DEPARTMENT is
'部门';
comment on column SYS_USER.JOB_TITLE is
'职位';
comment on column SYS_USER.MAIL is
'电子邮箱';
comment on column SYS_USER.PWD is
'密码';
comment on column SYS_USER.PHOTO_URL is
'头像';
comment on column SYS_USER.TOKEN is
'令牌';
comment on column SYS_USER.PERSONAL_QQ is
'个人QQ';
comment on column SYS_USER.ENTERPRISE_QQ is
'企业QQ';
comment on column SYS_USER.PHONE is
'手机号码';
comment on column SYS_USER.WORK_PHONE is
'工作电话';
comment on column SYS_USER.EXTENSION is
'分机';
comment on column SYS_USER.REAL_NAME is
'姓名';
comment on column SYS_USER.STATUS is
'状态 (0 已删除 1正常)';
comment on column SYS_USER.SUPERIOR_USER_ID is
'直属上级';
comment on column SYS_USER.DEPT_NAME is
'部门名称';
comment on column SYS_USER.DEVICE_TOKEN is
'设备token';
--用户角色关系表
create table SYS_USER_ROLE 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   USER_ID              VARCHAR2(55),
   ROLE_ID              VARCHAR2(55),
   STATUS               NUMBER
);
comment on table SYS_USER_ROLE is
'用户角色关系表';
comment on column SYS_USER_ROLE.ID is
'主键';
comment on column SYS_USER_ROLE.USER_ID is
'用户ID';
comment on column SYS_USER_ROLE.ROLE_ID is
'角色ID';
comment on column SYS_USER_ROLE.STATUS is
'状态';



/*DROP TABLE  STUDENT;
DROP TABLE  MAIL;
DROP TABLE  SYS_USER;
DROP TABLE  CHECKING_IN;
DROP TABLE  CHECKING_IN_RECORD;*/
SELECT SYS_GUID() FROM DUAL;
SELECT * FROM STUDENT;
SELECT * FROM MAIL;
SELECT * FROM SYS_USER;
SELECT * FROM CHECKING_IN;
SELECT * FROM CHECKING_IN_RECORD;
SELECT * FROM sys_menu for update;
DELETE FROM sys_menu

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('0','','ROOT','','#',1,2);

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('ADB3BA34FC734DA6871FEAB074A6BFD3','','系统管理','fa fa-home','#',1,2);

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('1177A99FDCC84ED7B26A7BA5718ABFD6','ADB3BA34FC734DA6871FEAB074A6BFD3','学生','','to_studentAction_list.do',1,2);
insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('F8B588250B534CB7B3773C79EB52899E','ADB3BA34FC734DA6871FEAB074A6BFD3','考勤','','checkingInAction/to_checkingInAction_list.do',2,2);

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('D01F308C37EF4C7A92F2DE84A625BEB1','ADB3BA34FC734DA6871FEAB074A6BFD3','菜单','','checkingInAction/to_checkingInAction_list.do',2,3);


insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('D6D975BFFB0240FA8B7AECDE471384EB','','其他管理','fa fa-home','#',1,2);

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('62B2845A47E740D38329DE165D02E01D','D6D975BFFB0240FA8B7AECDE471384EB','其他1','','to_studentAction_list.do',1,2);
insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('FDD62B1C756F4ACFA97E4C826D349D99','D6D975BFFB0240FA8B7AECDE471384EB','其他2','','checkingInAction/to_checkingInAction_list.do',2,2);

insert into sys_menu(ID,parent_id,menu_name,icon_cls,menu_url,menu_index,status)
values('EF6BE58AF8F54E54B5E8733E0C688305','FDD62B1C756F4ACFA97E4C826D349D99','其他2_Q','','checkingInAction/to_checkingInAction_list.do',2,2);