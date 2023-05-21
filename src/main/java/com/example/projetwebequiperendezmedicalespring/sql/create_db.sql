-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema medimeets
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `medimeets` ;
-- -----------------------------------------------------
-- Schema medimeets
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `medimeets` DEFAULT CHARACTER SET utf8 ;
USE `medimeets` ;
-- -----------------------------------------------------
-- Table `medimeets`.`clinique`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`cliniques` ;

CREATE TABLE `cliniques` (
    `id_clinique` int NOT NULL AUTO_INCREMENT,
    `coordonnees` varchar(255) NOT NULL,
    `email` varchar(128) NOT NULL,
    `nom` varchar(64) NOT NULL,
    `numtele` varchar(15) NOT NULL,
    PRIMARY KEY (`id_clinique`),
    UNIQUE KEY `UK_ktt5evtkr97lrmw1lqy0x3aqr` (`email`),
    UNIQUE KEY `UK_1h9djfydgdntbc5y4kerfuj3r` (`numtele`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `medimeets`.`cliniques`
(`id_clinique`,
 `coordonnees`,
 `email`,
 `nom`,
 `numtele`
)
VALUES (1,'ShiningPlace','shiner123@gmail.com','AbsoluteHealing','+1 514-074-6743'),
       (2,'MedPlace','midhealer456@gmail.com','MidHeals','+1 514-845-6849'),
       (3,'HealingShack','lowheal789@gmail.com','LowTierHeal','+1 514-548-8402'),
       (4,'DirtMountain','dirt012@gmail.com','DirtRough','+1 514-655-1435'),
       (5,'Anywhere','imagina345@gmail.com','ImagiHeal','+1 514-587-1254');

-- -----------------------------------------------------
-- Table `medimeets`.`medecin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`medecins` ;

-- -----------------------------------------------------
-- Table `medimeets`.`medecin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`medecins` ;

CREATE TABLE `medecins` (
    `id_medecin` int NOT NULL AUTO_INCREMENT,
    `disponibilite` date DEFAULT NULL,
    `email` varchar(128) NOT NULL,
    `mot_passe` varchar(64) NOT NULL,
    `nom` varchar(64) NOT NULL,
    `num_prof` int NOT NULL,
    `numtele` varchar(15) NOT NULL,
    `prenom` varchar(64) NOT NULL,
    `tarifconsultation` int NOT NULL,
    `clinique_id` int NOT NULL,
    PRIMARY KEY (`id_medecin`),
    UNIQUE KEY `UK_5tsq4vmsih8adkcep1whb7m5i` (`email`),
    UNIQUE KEY `UK_eqvys3ahilyuhbjki8qm62cuc` (`num_prof`),
    UNIQUE KEY `UK_o5r5wg060as6pxv52w0syw5eo` (`numtele`),
    KEY `FK28mjv8ak2u1daiyd9l5uiad8v` (`clinique_id`),
    CONSTRAINT `FK28mjv8ak2u1daiyd9l5uiad8v` FOREIGN KEY (`clinique_id`) REFERENCES `cliniques` (`id_clinique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `medimeets`.`medecins`
(`id_medecin`,
 `disponibilite`,
 `email`,
 `mot_passe`,
 `nom`,
 `num_prof`,
 `numtele`,
 `prenom`,
 `tarifconsultation`,
 `clinique_id`)
VALUES
    (1,'2013-10-18 22:00:00','peterler098@gmail.com','guava','Peter',54128,'+1 514-548-1384','Healer',30,1),
    (2,'2013-10-18 10:00:00','ringer765@gmail.com','horace','Ringo',45877,'+1 514-987-2649','Hingo',50,2),
    (3,'2013-10-18 11:00:00','queenaid432@gmail.com','ingus','Queen',78535,'+1 514-145-1794','Bandaid',20,3),
    (4,'2013-10-18 12:00:00','oleodoleo109@gmail.com','juice','Oleo',15816,'+1 514-218-3843','Jones',10,4),
    (5,'2013-10-18 09:00:00','johnerson876@gmail.com','ecorce','John',26947,'+1 514-487-1658','Beterson',40,5);

-- -----------------------------------------------------
-- Table `medimeets`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`patients` ;

CREATE TABLE `patients` (
    `id_patient` int NOT NULL AUTO_INCREMENT,
    `email` varchar(128) NOT NULL,
    `mot_passe` varchar(64) NOT NULL,
    `nom` varchar(64) NOT NULL,
    `num_ass` varchar(15) NOT NULL,
    `prenom` varchar(64) NOT NULL,
    `medecin_id` int NOT NULL,
    PRIMARY KEY (`id_patient`),
    UNIQUE KEY `UK_a370hmxgv0l5c9panryr1ji7d` (`email`),
    UNIQUE KEY `UK_oced9mp6mjwpdcy24orbo77x6` (`num_ass`),
    KEY `FKtak3ckgn0atgqcue0o91xsyoy` (`medecin_id`),
    CONSTRAINT `FKtak3ckgn0atgqcue0o91xsyoy` FOREIGN KEY (`medecin_id`) REFERENCES `medecins` (`id_medecin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `medimeets`.`patients`
(`id_patient`,
 `email`,
 `mot_passe`,
 `nom`,
 `num_ass`,
 `prenom`,
 `medecin_id`)
VALUES
    (1,'Steve@gmail.com','banana','Steve','WILL 2134 9458','Williams',1),
    (2,'Sandrah@gmail.com','apple','Sandrah','JINK 4587 5332','Jinkins',2),
    (3,'Jenna@gmail.com','coconut','Jenna','JENN 8535 0471','Marbles',3),
    (4,'Duke@gmail.com','durian','Duke','DUKE 5816 5194','Winters',4),
    (5,'Ben@gmail.com','ecorce','Ben','BENP 6947 5016','Parker',5);

-- -----------------------------------------------------
-- Table `medimeets`.`rendezvous`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`rendezvous` ;

CREATE TABLE `rendezvous` (
    `id_rendezvous` int NOT NULL AUTO_INCREMENT,
    `date` date DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `raison` varchar(255) NOT NULL,
    `id_clinique` int NOT NULL,
    `id_medecin` int NOT NULL,
    `id_patient` int NOT NULL,
    PRIMARY KEY (`id_rendezvous`),
    UNIQUE KEY `UK_hrr6gxun0ugwmnr34xdee3g9x` (`id_clinique`),
    UNIQUE KEY `UK_e4xc69gjl6r22h6sfne563gpf` (`id_medecin`),
    UNIQUE KEY `UK_6nuo3dhwy2e6rce9gdim5elww` (`id_patient`),
    CONSTRAINT `FK7ais3k72iyogqa3b7400y2mfj` FOREIGN KEY (`id_medecin`) REFERENCES `medecins` (`id_medecin`),
    CONSTRAINT `FKe7br9nexbvvknwdplh2riprc6` FOREIGN KEY (`id_clinique`) REFERENCES `cliniques` (`id_clinique`),
    CONSTRAINT `FKssb5xlb08hu6fdmtykk8pb029` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `medimeets`.`rendezvous`
(`id_rendezvous`,
 `date`,
 `description`,
 `raison`,
 `id_clinique`,
 `id_medecin`,
 `id_patient`
 )
VALUES
    (1,'2013-10-18 21:00:00','At the Surgery room','Broken spatula',1,1,1),
    (2,'2013-10-20 12:00:00','At the Blood room','Hemoglophilia',2,2,2),
    (3,'2013-10-18 10:00:00','At the pharmacy room','Diarhhea',3,3,3),
    (4,'2013-10-08 15:00:00','At the Surgery room','Knee Injury',4,4,4),
    (5,'2013-10-2 18:00:00','At the Health room','25% HP',5,5,5);

-- -----------------------------------------------------
-- Table `medimeets`.`message_patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`message_patient` ;

CREATE TABLE IF NOT EXISTS `medimeets`.`message_patient` (
    `id_message` INT NOT NULL,
    `message` VARCHAR(255) NULL,
    `date` DATETIME NULL,
    `document` VARCHAR(100) NULL,
    `patient_id_patient` INT NOT NULL,
    `medecin_id_medecin` INT NOT NULL,
    PRIMARY KEY (`id_message`),
    INDEX `fk_message_patient_patient1_idx` (`patient_id_patient` ASC) VISIBLE,
    INDEX `fk_message_patient_medecin1_idx` (`medecin_id_medecin` ASC) VISIBLE,
    CONSTRAINT `fk_message_patient_patient1`
    FOREIGN KEY (`patient_id_patient`)
    REFERENCES `medimeets`.`patients` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_message_patient_medecin1`
    FOREIGN KEY (`medecin_id_medecin`)
    REFERENCES `medimeets`.`medecins` (`id_medecin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

INSERT INTO `medimeets`.`message_patient`
(`id_message`,
 `message`,
 `date`,
 `document`,
 `patient_id_patient`,
 `medecin_id_medecin`)
VALUES
    (2,'Oui je suis bien','2013-10-18 21:01:00','',1,1),
    (3,'Est-ce que vous etes pret demain?','2013-10-19 12:00:00','',2,2),
    (6,'Ah desole , je les perds hier','2013-10-18 19:00:00','',3,3);
-- -----------------------------------------------------
-- Table `medimeets`.`message_medecin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`message_medecin` ;

CREATE TABLE `message_medecin` (
    `id_message` int NOT NULL AUTO_INCREMENT,
    `message` varchar(255) DEFAULT NULL,
    `date` datetime DEFAULT NULL,
    `document` varchar(100) DEFAULT NULL,
    `medecin_id_medecin` int DEFAULT NULL,
    `patient_id_patient` int DEFAULT NULL,
    `sujet` varchar(255) NOT NULL,
    PRIMARY KEY (`id_message`),
    KEY `fk_message_medecin_medecin1_idx` (`medecin_id_medecin`),
    KEY `fk_message_medecin_patient1_idx` (`patient_id_patient`),
    CONSTRAINT `message_medecin_ibfk_1` FOREIGN KEY (`medecin_id_medecin`) REFERENCES `medecins` (`id_medecin`),
    CONSTRAINT `message_medecin_ibfk_2` FOREIGN KEY (`patient_id_patient`) REFERENCES `patients` (`id_patient`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;


INSERT INTO `medimeets`.`message_medecin`
(`id_message`,
 `message`,
 `date`,
 `document`,
 `medecin_id_medecin`,
 `patient_id_patient`)
VALUES
    (1,'Ca va bien?','2013-10-18 21:00:00','',1,1),
    (4,'Oui a 13h','2013-10-18 12:10:00','',2,2),
    (5,'Avez-vous pris votre medicaments?','2013-10-20 18:00:00','',3,3);

-- -----------------------------------------------------
-- Table `medimeets`.`message_patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`message_patient` ;

CREATE TABLE IF NOT EXISTS `medimeets`.`message_patient` (
    `id_message` INT NOT NULL AUTO_INCREMENT,
    `message` VARCHAR(255) NULL,
    `date` DATETIME NULL,
    `document` VARCHAR(100) NULL,
    `patient_id_patient` INT NOT NULL,
    `medecin_id_medecin` INT NOT NULL,
    PRIMARY KEY (`id_message`),
    INDEX `fk_message_patient_patient1_idx` (`patient_id_patient` ASC) VISIBLE,
    INDEX `fk_message_patient_medecin1_idx` (`medecin_id_medecin` ASC) VISIBLE,
    CONSTRAINT `fk_message_patient_patient1`
    FOREIGN KEY (`patient_id_patient`)
    REFERENCES `medimeets`.`patients` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_message_patient_medecin1`
    FOREIGN KEY (`medecin_id_medecin`)
    REFERENCES `medimeets`.`medecins` (`id_medecin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

INSERT INTO `medimeets`.`message_patient`
(`id_message`,
 `message`,
 `date`,
 `document`,
 `patient_id_patient`,
 `medecin_id_medecin`)
VALUES
    (2,'Oui je suis bien','2013-10-18 21:01:00','',1,1),
    (3,'Est-ce que vous etes pret demain?','2013-10-19 12:00:00','',2,2),
    (6,'Ah desole , je les perds hier','2013-10-18 19:00:00','',3,3);
-- -----------------------------------------------------
-- Table `medimeets`.`message_medecin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medimeets`.`message_medecin` ;

CREATE TABLE IF NOT EXISTS `medimeets`.`message_medecin` (
    `id_message` INT NOT NULL,
    `message` VARCHAR(255) NULL,
    `date` DATETIME NULL,
    `document` VARCHAR(100) NULL,
    `medecin_id_medecin` INT NOT NULL,
    `patient_id_patient` INT NOT NULL,
    PRIMARY KEY (`id_message`),
    INDEX `fk_message_medecin_medecin1_idx` (`medecin_id_medecin` ASC) VISIBLE,
    INDEX `fk_message_medecin_patient1_idx` (`patient_id_patient` ASC) VISIBLE,
    CONSTRAINT `fk_message_medecin_medecin1`
    FOREIGN KEY (`medecin_id_medecin`)
    REFERENCES `medimeets`.`medecins` (`id_medecin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_message_medecin_patient1`
    FOREIGN KEY (`patient_id_patient`)
    REFERENCES `medimeets`.`patients` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

INSERT INTO `medimeets`.`message_medecin`
(`id_message`,
 `message`,
 `date`,
 `document`,
 `medecin_id_medecin`,
 `patient_id_patient`)
VALUES
    (1,'Ca va bien?','2013-10-18 21:00:00','',1,1),
    (4,'Oui a 13h','2013-10-18 12:10:00','',2,2),
    (5,'Avez-vous pris votre medicaments?','2013-10-20 18:00:00','',3,3);


CREATE TABLE `services` (
    `id_services` int NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `nom` varchar(64) NOT NULL,
    PRIMARY KEY (`id_services`),
    UNIQUE KEY `UK_a8gl7rkip47f6xtow3x12pbqs` (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `medimeets`.`services`
(`id_services`,
 `nom`,
 `description`)
VALUES
    (1,'Full Recovery and Resurrection','FULL HP'),
    (2,'Mid Recovery and Regeneration','MID HP'),
    (3,'Low heal and Potions','LOW HP');

CREATE TABLE `cliniques_services` (
    `clinique_id` int NOT NULL,
    `service_id` int NOT NULL,
    KEY `FKkad5sgogrbylqbm5cmmhl5rhd` (`service_id`),
    KEY `FKhtjnatqhi8tle7dkkrvpw6mxl` (`clinique_id`),
    CONSTRAINT `FKhtjnatqhi8tle7dkkrvpw6mxl` FOREIGN KEY (`clinique_id`) REFERENCES `cliniques` (`id_clinique`),
    CONSTRAINT `FKkad5sgogrbylqbm5cmmhl5rhd` FOREIGN KEY (`service_id`) REFERENCES `services` (`id_services`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `medimeets`.`cliniques_services`
(`clinique_id`,
 `service_id`)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5);

CREATE TABLE `medecins_services` (
    `medecin_id` int NOT NULL,
    `service_id` int NOT NULL,
    KEY `FKe36wht6s3a3g2l6kqi55dtdaj` (`service_id`),
    KEY `FKmkrt6p45rulvq0kgqaykeq04d` (`medecin_id`),
    CONSTRAINT `FKe36wht6s3a3g2l6kqi55dtdaj` FOREIGN KEY (`service_id`) REFERENCES `services` (`id_services`),
    CONSTRAINT `FKmkrt6p45rulvq0kgqaykeq04d` FOREIGN KEY (`medecin_id`) REFERENCES `medecins` (`id_medecin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `medimeets`.`medecins_services`
(`medecin_id`,
 `service_id`)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
