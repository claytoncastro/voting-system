CREATE TABLE IF NOT EXISTS `votacao` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tema` varchar(100) NOT NULL,
    `status_votacao` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);