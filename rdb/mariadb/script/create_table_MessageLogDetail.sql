USE mercuil;

CREATE TABLE IF NOT EXISTS MessageLogDetail (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT
    ,`messageLogId` INT UNSIGNED NOT NULL
    ,`messageQueueSubscriberId` INT UNSIGNED NOT NULL
	,`messageStatus` TINYINT UNSIGNED NULL DEFAULT 0
	,`failedReason` TEXT NULL
	,`failedRetryCount` TINYINT UNSIGNED NULL
	,`lastReplayFinishedDate` DATETIME(3) NULL
	,`inUser` VARCHAR(50) NOT NULL
	,`inDate` DATETIME(3) NOT NULL 
	,`lastEditUser` VARCHAR(50) NOT NULL
	,`lastEditDate` DATETIME(3) NOT NULL
	,CONSTRAINT PK_MessageLogDetail_id PRIMARY KEY (id)
)engine=InnoDB default charset utf8;

CREATE INDEX IX_MessageLogDetail_messageLogId ON MessageLogDetail(messageLogId);
CREATE INDEX IX_MessageLogDetail_messageQueueSubscriberId ON MessageLogDetail(messageQueueSubscriberId);
