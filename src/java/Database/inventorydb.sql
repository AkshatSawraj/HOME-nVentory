DROP SCHEMA IF EXISTS `inventorydb`;
CREATE SCHEMA IF NOT EXISTS `inventorydb` DEFAULT CHARACTER SET latin1;
USE `inventorydb`;

-- -----------------------------------------------------
-- Table `inventorydb`.`company`
-- -----------------------------------------------------

create table if not exists `inventorydb`.`company`(
  `company_id` INT(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
   primary key (`company_id`)
); 


-- -----------------------------------------------------
-- Table `inventorydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventorydb`.`category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`));

-- -----------------------------------------------------
-- Table `inventorydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventorydb`.`role` (
  `role_id` INT(11) NOT NULL,
  `role_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`role_id`));

-- -----------------------------------------------------
-- Table `inventorydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventorydb`.`user` (
  `email` VARCHAR(40) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` INT(11) NOT NULL,
  `company_id` INT(11) ,

    
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role`)
    REFERENCES `inventorydb`.`role` (`role_id`)
);


-- ALTER TABLE user
-- ADD CONSTRAINT fk_comapny FOREIGN KEY (company_id) REFERENCES company (company_id);

-- -----------------------------------------------------
-- Table `inventorydb`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventorydb`.`item` (
  `item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `category` INT(11) NOT NULL,
  `item_name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `owner` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`item_id`),
  CONSTRAINT `fk_items_categories`
    FOREIGN KEY (`category`)
    REFERENCES `inventorydb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `role` VALUES (1, 'system admin');
INSERT INTO `role` VALUES (2, 'regular user');
INSERT INTO `role` VALUES (3, 'company admin');


INSERT INTO `company` VALUES (101, 'Rainbow');
INSERT INTO `company` VALUES (102, 'Goggle');
INSERT INTO `company` VALUES (103, 'Amazon');


INSERT INTO `category` (`category_name`) VALUES ('kitchen');
INSERT INTO `category` (`category_name`) VALUES ('bathroom');
INSERT INTO `category` (`category_name`) VALUES ('living room');
INSERT INTO `category` (`category_name`) VALUES ('basement');
INSERT INTO `category` (`category_name`) VALUES ('bedrooom');
INSERT INTO `category` (`category_name`) VALUES ('garage');
INSERT INTO `category` (`category_name`) VALUES ('office');
INSERT INTO `category` (`category_name`) VALUES ('utility room');
INSERT INTO `category` (`category_name`) VALUES ('storage');
INSERT INTO `category` (`category_name`) VALUES ('other');

INSERT INTO `user` (`email`,`active`,`first_name`,`last_name`,`password`,`role`,`company_id`)
	VALUES ('cprg352+admin@gmail.com', true, 'Admin','Admin', 'password', 1, 101);
INSERT INTO `user` (`email`,`active`,`first_name`,`last_name`,`password`,`role`,`company_id`)
	VALUES ('cprg352+admin2@gmail.com', true, 'Admin2','Admin2', 'password', 3,102);
INSERT INTO `user` (`email`,`active`,`first_name`,`last_name`,`password`,`role`,`company_id`)
	VALUES ('cprg352+anne@gmail.com', true, 'Anne','Annerson', 'password', 2,102);
INSERT INTO `user` (`email`,`active`,`first_name`,`last_name`,`password`,`role`,`company_id`)
	VALUES ('cprg352+barb@gmail.com', true, 'Barb','Barber', 'password', 2,103);


INSERT INTO `item` (`category`,`item_name`,`price`,`owner`) VALUES (1, 'blender',29.99,'cprg352+anne@gmail.com');
INSERT INTO `item` (`category`,`item_name`,`price`,`owner`) VALUES (1, 'toaster',19.99,'cprg352+anne@gmail.com');
INSERT INTO `item` (`category`,`item_name`,`price`,`owner`) VALUES (3, 'lamp',5,'cprg352+anne@gmail.com');
INSERT INTO `item` (`category`,`item_name`,`price`,`owner`) VALUES (6, 'winter tires',200,'cprg352+anne@gmail.com');
INSERT INTO `item` (`category`,`item_name`,`price`,`owner`) VALUES (5, 'dresser',50,'cprg352+anne@gmail.com');
