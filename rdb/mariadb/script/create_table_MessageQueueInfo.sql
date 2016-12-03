USE mercuil;

CREATE TABLE IF NOT EXISTS MessageQueueInfo (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT
	,`messageQueueName` VARCHAR(100) NOT NULL
	,`summary` VARCHAR(500) NULL
	,`tags` VARCHAR(200) NULL
	,`ownerTeamName` VARCHAR(50) NOT NULL
	,`publishPassword` VARCHAR(100) NULL
	,`maxSize` INT UNSIGNED NULL
	,`maxPendingLength` INT UNSIGNED NULL
	,`isOrderRequired` BOOLEAN NOT NULL DEFAULT 0
	,`contactEmail` VARCHAR(100) NULL
	,`isActive` BOOLEAN NOT NULL DEFAULT 0
	,`inUser` VARCHAR(50) NOT NULL
	,`inDate` DATETIME(3) NOT NULL 
	,`lastEditUser` VARCHAR(50) NOT NULL
	,`lastEditDate` DATETIME(3) NOT NULL
	,CONSTRAINT PK_MessageQueueInfo_id PRIMARY KEY (id)
	,CONSTRAINT UC_MessageQueueInfo_messageQueueName UNIQUE (messageQueueName)  
)engine=InnoDB default charset utf8;

CREATE UNIQUE INDEX IUX_MessageQueueInfo_messageQueueName ON MessageQueueInfo(messageQueueName);
