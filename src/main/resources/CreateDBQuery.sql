CREATE SCHEMA `dbs_git_rep` ;

CREATE TABLE `dbs_git_rep`.`git_repo_details` (
  `id` INT NOT NULL,
  `repo_name` VARCHAR(100) NULL,
  `owner_name` VARCHAR(100) NULL,
  `description` VARCHAR(900) NULL,
  `stars` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

ALTER TABLE `dbs_git_rep`.`git_repo_details`
CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;