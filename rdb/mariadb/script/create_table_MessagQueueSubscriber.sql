USE mercuil;

CREATE TABLE IF NOT EXISTS MessageQueueSubscriber (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT
	,`messageQueueId` INT UNSIGNED NOT NULL 
	,`subscriberApiUrl` VARCHAR(500) NOT NULL
	,`retryCount` TINYINT UNSIGNED NULL DEFAULT 0
	,`isAutoReplay` BOOLEAN NULL DEFAULT 0
	,`autoReplayInterval` SMALLINT UNSIGNED NULL DEFAULT 0
	,`failedNotifyEmail` VARCHAR(100) NULL
	,`isActive` BOOLEAN NOT NULL DEFAULT 0
	,`inUser` VARCHAR(50) NOT NULL
	,`inDate` DATETIME(3) NOT NULL 
	,`lastEditUser` VARCHAR(50) NOT NULL
	,`lastEditDate` DATETIME(3) NOT NULL
	,CONSTRAINT PK_MessageQueueSubscriber_id PRIMARY KEY (id)
)engine=InnoDB default charset utf8;

CREATE INDEX IX_MessageQueueSubscriber_messageQueueId ON MessageQueueSubscriber(messageQueueId);
