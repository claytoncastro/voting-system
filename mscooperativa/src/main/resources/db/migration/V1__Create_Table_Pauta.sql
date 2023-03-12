CREATE TABLE IF NOT EXISTS `pauta` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tema` varchar(100) NOT NULL,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);