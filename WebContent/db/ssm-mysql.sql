DROP TABLE IF EXISTS `STUDENT`;

CREATE TABLE `STUDENT` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) DEFAULT NULL,
  `AGE` INT(11) DEFAULT NULL,
  PRIMARY KEY  (`ID`)
);

DROP TABLE IF EXISTS `MAIL`;
CREATE TABLE `MAIL` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT, 
  `TITLE` VARCHAR(2048),
  `CONTENT` TEXT ,
  `SEND_ID` INT(11),
  `SEND_DATE` DATE,
  PRIMARY KEY (`ID`)
);
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `ID` INT (11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` VARCHAR (55),
  `REAL_NAME` VARCHAR (55),
  `PASSWORD` VARCHAR (128),
  `DEPT_NAME` VARCHAR (55),
  `MEMBER_ID` VARCHAR (55),
  PRIMARY KEY (`ID`)
);
DROP TABLE IF EXISTS `CHECKING_IN`;
CREATE TABLE `CHECKING_IN` (
  `ID` INT (11) NOT NULL AUTO_INCREMENT,
  `USER_ID` INT (11),
  `HOUR` DOUBLE,
  `START_DATE` DATETIME,
  `END_DATE` DATETIME,
  `MONTH` INT (2),
  `STATUS` INT (2), PRIMARY KEY (`ID`)
) ;
DROP TABLE IF EXISTS `CHECKING_IN_RECORD`;
CREATE TABLE `CHECKING_IN_RECORD` (
  `ID` INT (11) NOT NULL AUTO_INCREMENT,
  `USER_ID` INT (11),
  `CHECKING_IN_DATE` DATETIME,
  `MONTH` INT (2),
  PRIMARY KEY (`ID`)
) ;

