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

INSERT INTO `medimeets`.`services`
(`id_services`,
 `nom`,
 `description`)
VALUES
    (1,'Full Recovery and Resurrection','FULL HP'),
    (2,'Mid Recovery and Regeneration','MID HP'),
    (3,'Low heal and Potions','LOW HP');

INSERT INTO `medimeets`.`cliniques_services`
(`clinique_id`,
 `service_id`)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5);

INSERT INTO `medimeets`.`medecins_services`
(`medecin_id`,
 `service_id`)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5);