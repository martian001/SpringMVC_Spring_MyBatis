
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
  START_DATE DATE,
  END_DATE DATE,
  "MONTH" INTEGER,
  STATUS INTEGER,
  OVER_TIME NUMBER(12,2)
  
) ;

CREATE TABLE CHECKING_IN_RECORD (
  ID VARCHAR2(55) PRIMARY KEY,
  USER_ID VARCHAR2(55),
  CHECKING_IN_DATE DATE,
  MONTH INTEGER
);

--系统菜单表
CREATE TABLE SYS_MENU 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   PARENT_ID            VARCHAR2(55),
   MENU_NAME            VARCHAR2(255),
   ICON_CLS             VARCHAR2(255),
   MENU_URL             VARCHAR2(255),
   MENU_INDEX           NUMBER,
   STATUS               NUMBER,
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_MENU IS
'系统菜单';
COMMENT ON COLUMN SYS_MENU.ID IS
'ID';
COMMENT ON COLUMN SYS_MENU.PARENT_ID IS
'父ID';
COMMENT ON COLUMN SYS_MENU.MENU_NAME IS
'菜单名称';
COMMENT ON COLUMN SYS_MENU.ICON_CLS IS
'菜单图标';
COMMENT ON COLUMN SYS_MENU.MENU_URL IS
'菜单URL';
COMMENT ON COLUMN SYS_MENU.MENU_INDEX IS
'显示顺序';
COMMENT ON COLUMN SYS_MENU.STATUS IS
'状态';
--系统功能权限表
CREATE TABLE SYS_PERMISSION 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   PERMIS_TYPE          VARCHAR2(255),
   PERMIS_NAME          VARCHAR2(255),
   PERMIS_DESC          VARCHAR2(2048),
   PERMIS_CODE          VARCHAR2(255),
   MENU_ID              VARCHAR2(55),
   STATUS               NUMBER,
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_PERMISSION IS
'系统功能权限';
COMMENT ON COLUMN SYS_PERMISSION.ID IS
'主键';
COMMENT ON COLUMN SYS_PERMISSION.PERMIS_TYPE IS
'权限类型（数据/操作权限）';
COMMENT ON COLUMN SYS_PERMISSION.PERMIS_NAME IS
'权限名称';
COMMENT ON COLUMN SYS_PERMISSION.PERMIS_DESC IS
'权限描述';
COMMENT ON COLUMN SYS_PERMISSION.PERMIS_CODE IS
'权限代码，系统唯一';
COMMENT ON COLUMN SYS_PERMISSION.MENU_ID IS
'菜单ID';
COMMENT ON COLUMN SYS_PERMISSION.STATUS IS
'状态';
--系统角色表
CREATE TABLE SYS_ROLE 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   ROLE_NAME            VARCHAR2(255),
   ROLE_DESC            VARCHAR2(1024),
   ROLE_CODE            VARCHAR2(255),
   STATUS               NUMBER,
   PARENT_ID            VARCHAR2(55),
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_ROLE IS
'系统角色';
COMMENT ON COLUMN SYS_ROLE.ID IS
'主键';
COMMENT ON COLUMN SYS_ROLE.ROLE_NAME IS
'角色名称';
COMMENT ON COLUMN SYS_ROLE.ROLE_DESC IS
'角色描述';
COMMENT ON COLUMN SYS_ROLE.ROLE_CODE IS
'角色编码';
COMMENT ON COLUMN SYS_ROLE.STATUS IS
'状态';
COMMENT ON COLUMN SYS_ROLE.PARENT_ID IS
'从属角色ID';
--角色-权限关系表
CREATE TABLE SYS_ROLE_PERMISSION 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   ROLE_ID              VARCHAR2(55),
   PERMIS_ID            VARCHAR2(55),
   STATUS               NUMBER,
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_ROLE_PERMISSION IS
'角色-权限关系表';
COMMENT ON COLUMN SYS_ROLE_PERMISSION.ID IS
'主键';
COMMENT ON COLUMN SYS_ROLE_PERMISSION.ROLE_ID IS
'角色ID';
COMMENT ON COLUMN SYS_ROLE_PERMISSION.PERMIS_ID IS
'功能权限ID';
COMMENT ON COLUMN SYS_ROLE_PERMISSION.STATUS IS
'状态';
--系统用户表
CREATE TABLE SYS_USER 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   USER_NAME            VARCHAR2(255) Unique,
   MEMBER_ID            VARCHAR2(255) Unique,
   JOB_TITLE            VARCHAR2(255),
   MAIL                 VARCHAR2(255),
   PWD                  VARCHAR2(255),
   PHOTO_URL            VARCHAR2(255),
   TOKEN                VARCHAR2(255),
   PERSONAL_QQ          VARCHAR2(255),
   ENTERPRISE_QQ        VARCHAR2(255),
   PHONE                VARCHAR2(255) Unique,
   WORK_PHONE           VARCHAR2(255),
   EXTENSION            VARCHAR2(255),
   REAL_NAME            VARCHAR2(255),
   STATUS               NUMBER,
   SUPERIOR_USER_ID     VARCHAR2(55),
   DEPT_NAME            VARCHAR2(55),
   DEVICE_TOKEN         VARCHAR2(100),
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_USER IS
'系统用户';
COMMENT ON COLUMN SYS_USER.ID IS
'主键';
COMMENT ON COLUMN SYS_USER.USER_NAME IS
'用户账户号';
COMMENT ON COLUMN SYS_USER.MEMBER_ID IS
'员工工号';
COMMENT ON COLUMN SYS_USER.JOB_TITLE IS
'职位';
COMMENT ON COLUMN SYS_USER.MAIL IS
'电子邮箱';
COMMENT ON COLUMN SYS_USER.PWD IS
'密码';
COMMENT ON COLUMN SYS_USER.PHOTO_URL IS
'头像';
COMMENT ON COLUMN SYS_USER.TOKEN IS
'令牌';
COMMENT ON COLUMN SYS_USER.PERSONAL_QQ IS
'个人QQ';
COMMENT ON COLUMN SYS_USER.ENTERPRISE_QQ IS
'企业QQ';
COMMENT ON COLUMN SYS_USER.PHONE IS
'手机号码';
COMMENT ON COLUMN SYS_USER.WORK_PHONE IS
'工作电话';
COMMENT ON COLUMN SYS_USER.EXTENSION IS
'分机';
COMMENT ON COLUMN SYS_USER.REAL_NAME IS
'姓名';
COMMENT ON COLUMN SYS_USER.STATUS IS
'状态 (0 已删除 1正常)';
COMMENT ON COLUMN SYS_USER.SUPERIOR_USER_ID IS
'直属上级';
COMMENT ON COLUMN SYS_USER.DEPT_NAME IS
'部门名称';
COMMENT ON COLUMN SYS_USER.DEVICE_TOKEN IS
'设备TOKEN';
--用户角色关系表
CREATE TABLE SYS_USER_ROLE 
(
   ID                  VARCHAR2(55) PRIMARY KEY,
   USER_ID              VARCHAR2(55),
   ROLE_ID              VARCHAR2(55),
   STATUS               NUMBER,
   CREATOR_DATE       DATE,
   CREATOR_ID         INTEGER,
   UPDATE_ID          INTEGER,
   UPDATE_DATE        DATE
);
COMMENT ON TABLE SYS_USER_ROLE IS
'用户角色关系表';
COMMENT ON COLUMN SYS_USER_ROLE.ID IS
'主键';
COMMENT ON COLUMN SYS_USER_ROLE.USER_ID IS
'用户ID';
COMMENT ON COLUMN SYS_USER_ROLE.ROLE_ID IS
'角色ID';
COMMENT ON COLUMN SYS_USER_ROLE.STATUS IS
'状态';



/*DROP TABLE  STUDENT;
DROP TABLE  MAIL;
DROP TABLE  SYS_USER;
DROP TABLE  CHECKING_IN;
DROP TABLE  CHECKING_IN_RECORD;*/
SELECT SYS_GUID() FROM DUAL;
SELECT * FROM STUDENT;
SELECT * FROM MAIL;
SELECT * FROM SYS_USER T WHERE t.id='27EF608425F9499D980CC63FA63C5DA9' for update;
SELECT * FROM CHECKING_IN;
SELECT * FROM CHECKING_IN_RECORD;
SELECT * FROM SYS_MENU FOR UPDATE;
DELETE FROM SYS_USER;
DELETE FROM CHECKING_IN;
DELETE FROM CHECKING_IN_RECORD;

insert into SYS_USER (ID, USER_NAME, MEMBER_ID, JOB_TITLE, MAIL, PWD, PHOTO_URL, TOKEN, PERSONAL_QQ, ENTERPRISE_QQ, PHONE, WORK_PHONE, EXTENSION, REAL_NAME, STATUS, SUPERIOR_USER_ID, DEPT_NAME, DEVICE_TOKEN, CREATOR_DATE, CREATOR_ID, UPDATE_ID, UPDATE_DATE)
values ('27EF608425F9499D980CC63FA63C5DA9', 'admin', null, null, null, '123456', null, null, null, null, null, null, null, 'the_one', null, null, '', null, sysdate, null, null, sysdate);



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
