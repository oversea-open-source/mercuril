USE mercuil;

CREATE TABLE IF NOT EXISTS MessageLog (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT
	,`messageQueueSubscriberId` INT UNSIGNED NOT NULL 
	,`messageRaw` TEXT NOT NULL
	,`messageStatus` TINYINT UNSIGNED NULL DEFAULT 0
	,`failedReason` TEXT NULL
	,`failedRetryCount` TINYINT UNSIGNED NULL
	,`lastReplayFinishedDate` DATETIME(3) NULL
	,`inUser` VARCHAR(50) NOT NULL
	,`inDate` DATETIME(3) NOT NULL 
	,`lastEditUser` VARCHAR(50) NOT NULL
	,`lastEditDate` DATETIME(3) NOT NULL
	,CONSTRAINT PK_MessageLog_id PRIMARY KEY (id)
)engine=InnoDB default charset utf8;

CREATE INDEX IX_MessageLog_messageQueueSubscriberId ON MessageLog(messageQueueSubscriberId);
