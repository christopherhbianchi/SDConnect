-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sdconnectdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sdconnectdb` ;

-- -----------------------------------------------------
-- Schema sdconnectdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sdconnectdb` DEFAULT CHARACTER SET utf8 ;
USE `sdconnectdb` ;

-- -----------------------------------------------------
-- Table `user_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_type` ;

CREATE TABLE IF NOT EXISTS `user_type` (
  `id` INT NOT NULL,
  `type` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cohort`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cohort` ;

CREATE TABLE IF NOT EXISTS `cohort` (
  `id` INT NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `start_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `cohort_num` INT NOT NULL,
  `mascot_img_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `cohort_num_UNIQUE` (`cohort_num` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `user_type_id` INT NOT NULL DEFAULT 2,
  `cohort_id` INT NOT NULL,
  `active` VARCHAR(45) NOT NULL DEFAULT 'on',
  PRIMARY KEY (`id`),
  INDEX `fk_user_cohort1_idx` (`cohort_id` ASC),
  INDEX `fk_user_user_type_idx` (`user_type_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_user_user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `user_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_cohort1`
    FOREIGN KEY (`cohort_id`)
    REFERENCES `cohort` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `form`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `form` ;

CREATE TABLE IF NOT EXISTS `form` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `has_forms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `has_forms` ;

CREATE TABLE IF NOT EXISTS `has_forms` (
  `form_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`form_id`, `user_id`),
  INDEX `fk_has_forms_forms1_idx` (`form_id` ASC),
  INDEX `fk_has_forms_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_has_forms_forms1`
    FOREIGN KEY (`form_id`)
    REFERENCES `form` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_has_forms_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tag` ;

CREATE TABLE IF NOT EXISTS `tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topic` ;

CREATE TABLE IF NOT EXISTS `topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_topic_tag1_idx` (`tag_id` ASC),
  CONSTRAINT `fk_topic_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(5000) NULL,
  `post_ts` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'date time, but don’t want to name it that to mess up the sql statements',
  `user_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  `link` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_thread1_idx` (`topic_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_thread1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project` ;

CREATE TABLE IF NOT EXISTS `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(85) NOT NULL,
  `description` VARCHAR(500) NULL,
  `estimated_hours` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img_url` VARCHAR(500) NULL DEFAULT 'img/profilePictures/profile.png',
  `background_desc` VARCHAR(5000) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `prev_industry` VARCHAR(45) NULL,
  `coding_exp` VARCHAR(45) NULL,
  `shirt_size` VARCHAR(2) NULL,
  `user_id` INT NOT NULL,
  `website_url` VARCHAR(500) NULL,
  `github_url` VARCHAR(500) NULL,
  `linkedin_url` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_profile_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_profile_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `career_resources`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `career_resources` ;

CREATE TABLE IF NOT EXISTS `career_resources` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `upload_id` VARCHAR(45) NULL,
  `link` VARCHAR(45) NULL COMMENT 'now will this be a link or an actual document?\nOr should we have a column for both?…\nuser wants to post a doc from a link or just straight upload it',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(85) NOT NULL DEFAULT 'No name',
  `description` VARCHAR(500) NULL,
  `date` DATETIME NULL,
  `public` VARCHAR(45) NULL,
  `projects_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_projects1_idx` (`projects_id` ASC),
  CONSTRAINT `fk_event_projects1`
    FOREIGN KEY (`projects_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cohort_has_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cohort_has_event` ;

CREATE TABLE IF NOT EXISTS `cohort_has_event` (
  `cohort_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`cohort_id`, `event_id`),
  INDEX `fk_cohort_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_cohort_has_event_cohort1_idx` (`cohort_id` ASC),
  CONSTRAINT `fk_cohort_has_event_cohort1`
    FOREIGN KEY (`cohort_id`)
    REFERENCES `cohort` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cohort_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO sdconnector;
 DROP USER sdconnector;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'sdconnector' IDENTIFIED BY 'root';

GRANT ALL ON * TO 'sdconnector';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO guest;
 DROP USER guest;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'guest' IDENTIFIED BY 'guest';

GRANT SELECT ON TABLE * TO 'guest';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `user_type` (`id`, `type`) VALUES (1, 'admin');
INSERT INTO `user_type` (`id`, `type`) VALUES (2, 'student');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cohort`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (1, 'Lambkhins', '2017-07-31', '2017-11-21', 12, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (2, 'Manatees', '2017-10-01', '2017-12-23', 13, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (3, 'Neanderthals', '2018-10-01', '2018-12-23', 14, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (4, 'Octothorpes', '2019-10-01', '2019-12-23', 15, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (5, 'Pomeranians', '2020-10-01', '2020-12-23', 16, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (6, 'Quails', '2021-10-01', '2021-12-23', 17, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (7, 'Raccoons', '2022-10-01', '2022-12-23', 18, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (8, 'Sharks', '2023-10-01', '2023-12-23', 19, NULL);
INSERT INTO `cohort` (`id`, `name`, `start_date`, `end_date`, `cohort_num`, `mascot_img_url`) VALUES (9, 'Tigers', '2024-10-01', '2024-12-23', 20, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `user` (`id`, `email`, `password`, `user_type_id`, `cohort_id`, `active`) VALUES (1, 'chrishbianchi@gmail.com', '$2a$10$CZ13mvoyPnO4nHjq82p.7.rWh0uizvHhk79domk8Lk06OtJGsjury', 1, 1, 'on');
INSERT INTO `user` (`id`, `email`, `password`, `user_type_id`, `cohort_id`, `active`) VALUES (2, 'jmckenna@gmail.com', '$2a$10$CZ13mvoyPnO4nHjq82p.7.rWh0uizvHhk79domk8Lk06OtJGsjury', 2, 1, 'on');
INSERT INTO `user` (`id`, `email`, `password`, `user_type_id`, `cohort_id`, `active`) VALUES (3, 'kkane@gmail.com', '$2a$10$CZ13mvoyPnO4nHjq82p.7.rWh0uizvHhk79domk8Lk06OtJGsjury', 1, 1, 'on');
INSERT INTO `user` (`id`, `email`, `password`, `user_type_id`, `cohort_id`, `active`) VALUES (4, 'mpentermann@gmail.com', '$2a$10$CZ13mvoyPnO4nHjq82p.7.rWh0uizvHhk79domk8Lk06OtJGsjury', 2, 1, 'on');
INSERT INTO `user` (`id`, `email`, `password`, `user_type_id`, `cohort_id`, `active`) VALUES (5, 'sryzek@gmail.com', '$2a$10$CZ13mvoyPnO4nHjq82p.7.rWh0uizvHhk79domk8Lk06OtJGsjury', 2, 1, 'on');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tag`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `tag` (`id`, `type`) VALUES (1, 'Roommate');
INSERT INTO `tag` (`id`, `type`) VALUES (2, 'Weekend');
INSERT INTO `tag` (`id`, `type`) VALUES (3, 'Resume');
INSERT INTO `tag` (`id`, `type`) VALUES (5, 'CoverLetter');
INSERT INTO `tag` (`id`, `type`) VALUES (4, 'Interview');
INSERT INTO `tag` (`id`, `type`) VALUES (6, 'General');
INSERT INTO `tag` (`id`, `type`) VALUES (7, 'CohortBoard');
INSERT INTO `tag` (`id`, `type`) VALUES (8, 'Java');
INSERT INTO `tag` (`id`, `type`) VALUES (9, 'JavaScript');
INSERT INTO `tag` (`id`, `type`) VALUES (10, 'SQL');
INSERT INTO `tag` (`id`, `type`) VALUES (11, 'Tools');
INSERT INTO `tag` (`id`, `type`) VALUES (12, 'Angular');
INSERT INTO `tag` (`id`, `type`) VALUES (13, 'AWS');
INSERT INTO `tag` (`id`, `type`) VALUES (14, 'Git');
INSERT INTO `tag` (`id`, `type`) VALUES (15, 'MidtermProject');
INSERT INTO `tag` (`id`, `type`) VALUES (16, 'FinalProject');

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (1, 'Searching for roommate.', 1);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (2, 'Here\'s my resume.', 3);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (3, 'Interview at Google. Look at some interview questions!', 4);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (4, 'Cover Letter yes or no? Thoughts?', 5);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (5, 'Skill Disitllery BBQ this weekend?', 2);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (6, 'Looking for Guidance/Tips for job Hunting', 6);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (7, 'Job Fairs', 7);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (8, 'Interview Questions', 8);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (9, 'Interview Tips', 9);
INSERT INTO `topic` (`id`, `name`, `tag_id`) VALUES (10 , 'Skills to Focus on After SD', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (1, 'Hi Everyone. Searching for a roommate in SD 12. I\'m coming from California and would like to share rent with someone.', DEFAULT, 1, 1, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (2, 'What\'s up Mr. Bianchi! I\'m an instructor here at SD and have a room available if you\'re interested. Contact me via email at kkris@gmail.com.', DEFAULT, 3, 1, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (3, 'Interested in an entry-level full stack development position at Amazon. Would love to hear your thoughts.', DEFAULT, 2, 2, 'img/resume/McKenna_26Oct17PDF.pdf');
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (4, 'Hi Jacqualine, the resume looks great. I would highlight most applicable work experience.', DEFAULT, 1, 2, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (5, 'Thank you! I\'ll put in those changes. Wish me luck.', DEFAULT, 2, 2, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (6, 'Just completed my Google superday. Notable questions: What is OOP? What is the benefit of using an MVC framework? How many ping pong balls could you fit in an entire 747 boeing jet?', DEFAULT, 3, 3, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (7, 'Thanks Kris. I know what I\'m going to go study now. Hope it went well.', DEFAULT, 2, 3, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (8, 'Just finished my Cover Letter. Would love to hear what you all think. The more feedback the better!', DEFAULT, 1, 4, 'img/resume/Christopher_Bianchi_Resume.pdf');
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (9, 'Put the name of somebody who referred you in the first sentence. If nobody referred you, do some LinkedIn research to find mutual connections and mention one of them. A warm introduction is likely to elicit response.', DEFAULT, 2, 4, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (10, 'Hi! I am in SD 12 and looking for a job. I was wondering if anyone had any tips to improve my chances of getting hired? Thank you!', DEFAULT, DEFAULT, 6, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (11, 'Sure so I had 3 total interviews before landing my first job. My first was probably my hardest. I was up at a laptop which was projected onto a screen in the room and was told I had an hour to debug a program that the company had built. It was more of a game where the goal was to \"out program\" their pre-built logic that was controlling the computers, so that the \"human\" character could get more points during a run of the game. It was all in eclipse and simply ran the code in the console. Just being familiar with implementing interfaces/abstract classes was enough to get me through that one. What they also liked was when I got up from the computer and walked over to the whiteboard and talked through my thought process. Always use a whiteboard if there is one in the room. My second was a more standard tech interview. It was me with a whiteboard and was asked a couple of questions. Write a loop that returns each individual word from a string. Write a palindrome checker in Java. Write out a sql query that would return what the interviewer asked of me. This included using an outer join. Be familiar with outer joins, they came up in every interview. My last was a non tech interview followed by a take home test of sorts. I had a week to make an ROI Calculator using angularjs. Not too difficult, ran it by Andrew and Kris for tips. In general just be very familiar with java and sql. Angular comes up if the company uses it. Obviously research the company before hand be able to talk about general work they do. Hope these tips help!', DEFAULT, DEFAULT, 6, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (12, 'I looked on Indeed and LinkedIn as well. I am from Wisconsin and the jobs here are not as abundant as some of the bigger cities, so I applied to any position that was located in Wisconsin and I got lucky landing the job with Enterprise. Definitely is a great company to work for. Tips on the job search, don\'t sell yourself short. Coming out of the bootcamp you do know a lot and are well qualified for positions. It is easier to see once you get hired on to a place and see what other new hires know and even how much people that have been in the industry for a year know. Second, if possible, don\'t limit yourself in geographic location. Enterprise is hiring for their St Louis location.', DEFAULT, DEFAULT, 6, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (13, 'Make your inexperience look more presentable even if they do not bring it up. Remind them that you are malleable, havent developed any bad habits, look forward to learning everyday. \r\rQuite a few applicants can answer technical questions correctly, but showing you have an excellent work ethic and willingness to learn will put you above other aspiring junior devs.', DEFAULT, DEFAULT, 6, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (14, 'Tips to get in to Googlie? First off, Skill Distillery does an amazing job of teaching what you need to know to be successful here, but paradoxically they do almost nothing to prepare you for the interviews. Cracking the Coding Interview is a good resource, but an even better one is HackerRank. Make an account and solve at least one problem a day. My interview questions were almost all about string manipulation and binary trees. I know all the advice out there says to practice on a whiteboard, but I don\'t agree. Practice on a blank Googlie Doc with no syntax highlighting or correction. Your initial phone interview will be in exactly that setting, and then they give you the option now for your in-person interviews to type your solutions on a text editor in Chromebook. \r\rBe prepared for the process to take forever. It took six months from first contact to job offer for me. Seriously, apply now! You\'ll be ready by the time they interview you. Also, they have a really nice veterans program. They assigned me a coach who was also a vet and he did a practice phone interview with me, which I completely flubbed and was therefore much better prepared for the real one. \r\rDon\'t be afraid to apply for other jobs too. I failed a couple of coding challenges from other companies, but learned a lot in the process. I also turned down an offer from another company in expectation of getting a Googlie offer. That was a mistake. I could have started at Googlie with 3 months of experience somewhere else instead of fresh out of school. Yeah, it\'s generally bad form to quit a job right after starting, but working for Googlie is an exception that people will understand. ', DEFAULT, DEFAULT, 6, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (15, 'Anyone know of any job fairs that will happen in the next few weeks?', DEFAULT, DEFAULT, 7, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (16, 'Yes, Raytheon has one this upcoming Thursday (9 November 2017); here\'s the site to register! https://raytheon.recsolucampus.com/exeventreg.php?file=CampusList&event_loc_id=678&eventid=25850&language_id=0', DEFAULT, DEFAULT, 7, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (17, 'What were some of the interview questions you were asked?', DEFAULT, DEFAULT, 8, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (18, 'First interview with Cap: I was asked to swap two numbers without using a third variable (x =7, y =5 basically x = x + y so x is now 12; y = x - y so y is now 7; x = x - y so now x is 5 and the numbers are swapped) I was asked about how JSON gets converted to a java object (object mapped class) I was asked about the list interface (I stumbled on this one) I was asked about removing something from a list (use a for loop and call remove on it) I was asked about the difference between the String class and Stringbuilder class (when you concatinate on a string, it creates a new object in memory while string builder does not create a new object in memory). I was asked about how the array list works in memory (array list creates a new array in memory Every time you have enough objects to fill half of the of it. E.g. array list will create a new primative array with two spots at one object, create a new primative array witg four spots at two objects and so on. The previous arrays are still in memory and will have to be garbage collected). I was asked about the volatile keyword (didn\'t know it) I was asked in words I can\'t recall about how stuff gets sent from the front end (with angular requests get sent back as JSON, if querying for stuff, it comes back as a promise). I was asked about views in Spring (requests hit the controller route which sets the view to whatever HTML or JSP you provided) I was asked about bad requests (can validate with HTML, the controller, dao implementation or your database). I was asked about exceptions. Method One with a try finally calls Method Two that also has a try finally. Which finally executes first? (Method Two finally block executed first. Got that one right).', DEFAULT, DEFAULT, 8, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (19, 'Second interview:\r\rWhat is inheritance (you know the answer)\r\rHow do you troubleshoot (back end use stack traces and sysouts to see if I\'m hitting my controller or calling my methods, front end use console logs)\r\rWhat are access modifiers? (Again, easy)\r\rWhat is dependency injection (I used http session as an example, you can say a method takes a http session as an argument, and it is provided by the browser)\r\rIf you weren\'t in the spring framework, how would you use session objects? (An obvious follow up. Basically you will have to import the web stuff like you would when you import collections to use array lists)', DEFAULT, DEFAULT, 8, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (20, 'I haven\'t had an interview yet. Anyone have any tips?', DEFAULT, DEFAULT, 9, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (21, 'Interview: Sounds cliche, but be yourself. It’s okay to say “I don’t know, but it’s something I will look into.” Many interviews turn sour when a candidate boasts knowledge about a topic, but then struggles to answer detailed questions about that topic. By the way, that answer is okay for all professionals! The good news for you, is… as an “entry level” developer, the interview isn’t the tough part. The tough part is getting to the point of being interviewed. Long story short, it’s all about networking (face-to-face is better than virtual). Go out and meet Talent Acquisition folks and hiring managers at job fairs. Hand people your resume. Relying only on online job sites is not good enough.', DEFAULT, DEFAULT, 9, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (22, 'Does anyone have any advice in regards to skills we should hone on as we transition from students to future developers?', DEFAULT, DEFAULT, 10, NULL);
INSERT INTO `post` (`id`, `message`, `post_ts`, `user_id`, `topic_id`, `link`) VALUES (23, 'This came from a hiring manager: Skills to focus on: My recommendation, is to pick a few technologies and really focus in on them. For example, Java developers will always find work, so that’s a good pick. Learn how to interface with different databases (you don’t need to learn to be a DB admin). Learn about unit testing and always do it when developing (I ask about unit testing in the interviews I lead). Don’t just do the examples in a textbook. Come up with your own mid-term-type project and develop it yourself, if you can find the time.', DEFAULT, DEFAULT, 10, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `project` (`id`, `name`, `description`, `estimated_hours`) VALUES (1, 'JSCalculator', 'Individual Project', 6);
INSERT INTO `project` (`id`, `name`, `description`, `estimated_hours`) VALUES (2, 'MVCCRUD Project Pt. 1', 'Individual Project', 12);
INSERT INTO `project` (`id`, `name`, `description`, `estimated_hours`) VALUES (3, 'Jets', 'Individual Project', 10);
INSERT INTO `project` (`id`, `name`, `description`, `estimated_hours`) VALUES (4, 'Angular Event Tracker', 'Individual Project', 4);
INSERT INTO `project` (`id`, `name`, `description`, `estimated_hours`) VALUES (5, 'US President\'s Project', 'Team Project', 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (1, 'img/profilePictures/ChrisBProfileImage.jpg', 'Software solutions architect for B2Bi commerce.', 'Christopher', 'Bianchi', 'Business Commerce', '2 years', 'L', 1, NULL, NULL, NULL);
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (2, 'img/profilePictures/JacqualineProfileImage.jpg', '8 years in the military.', 'Jacqualine', 'McKenna', 'Intelligence', '1 year', 'S', 2, NULL, NULL, NULL);
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (3, 'img/profilePictures/KrisProfileImage.jpg', 'Extensive history in projects implementing variety of technologies', 'Kris', 'Kane', 'Application Development', '21 years', 'XL', 3, NULL, NULL, NULL);
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (4, NULL, NULL, 'Jimmy', 'Easter', NULL, NULL, NULL, 10, NULL, NULL, NULL);
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (5, NULL, NULL, 'Doug ', 'Kucera', 'Program Manager', NULL, NULL, 13, NULL, NULL, NULL);
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (6, NULL, NULL, 'Andrew ', 'Conlin', NULL, NULL, NULL, 16, NULL, NULL, 'https://www.linkedin.com/in/andrewcconlin/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (7, 'img/profile/ErikProfileImage.jpg', NULL, 'Erik', 'Ernst', 'Architect', 'None', 'L', 5, 'http://18.220.214.189/', NULL, 'https://www.linkedin.com/in/erik-ernst-00536458/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (8, NULL, 'USMC Veteran', 'Daniel', 'Clark', 'Military', NULL, 'L', 8, 'http://52.14.139.50/', 'https://github.com/dclarkasu', 'https://www.linkedin.com/in/daniel-clark-26552a115/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (9, 'img/profile/BrianProfileImage.jpg', NULL, 'Brian', 'Thomas', NULL, NULL, NULL, 11, 'http://18.220.25.115/', 'https://github.com/flycal6', 'https://www.linkedin.com/in/brian-thomas-332874150/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (10, 'img/profile/SteveProfileimage.jpg', 'From Michigan', 'Steve', 'Thompson', 'Always Coding', '10+ Years', 'M', 14, NULL, NULL, 'https://www.linkedin.com/in/steve-thompson-34182712a/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (11, 'img/profile/BruceBProfileImage.jpg', 'Co-Founder of Skill Distillery', 'Bruce', 'Batky', '20+ Years', NULL, 'M', 17, NULL, NULL, 'https://www.linkedin.com/in/bruce-batky-a24525/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (12, 'img/profile/ChrisBartProfileImage.jpg', 'From Cali went to school in Michigan', 'Christopher', 'Bartkewicz', 'Air Force Mechanic', '2+ Years', 'L', 6, 'http://christophermichael.us/', 'https://github.com/msupg21', 'https://www.linkedin.com/in/cbartkewicz/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (13, 'img/profile/StanProfileImage.jpg', NULL, 'Stanton', 'Viaduc', 'Marketing', NULL, 'L', 9, 'http://stantonviaduc.com/', 'https://github.com/sviaduc', 'https://www.linkedin.com/in/stanton-viaduc/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (14, NULL, NULL, 'Mustafa', 'Alici', 'Baker', '2+ Years', 'L', 12, NULL, NULL, 'https://www.linkedin.com/in/mustafa-alici-448906118/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (15, 'img/profile/RobProfileImage.jpg', NULL, 'Rob', 'Roselius', NULL, NULL, NULL, 15, NULL, NULL, 'https://www.linkedin.com/in/rob-roselius-675b428/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (16, 'img/profile/MeiraProfileImage.jpg', 'Mother, writer, Physics Guru', 'Meira', 'Penterman', 'Accounting', 'Python', 'M', 4, 'http://18.220.194.202/#/', 'https://github.com/Meira1427', 'https://www.linkedin.com/in/meira-pentermann-02449110/');
INSERT INTO `profile` (`id`, `img_url`, `background_desc`, `first_name`, `last_name`, `prev_industry`, `coding_exp`, `shirt_size`, `user_id`, `website_url`, `github_url`, `linkedin_url`) VALUES (17, 'img/profile/SamProfileImage.jpg', 'Former Naval Aviator', 'Samuel', 'Ryzek', 'Defense', 'None', 'L', 7, 'http://18.220.137.48/', 'https://github.com/SamRyzek', 'https://www.linkedin.com/in/samuel-ryzek-msia-4292046/');

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `event` (`id`, `name`, `description`, `date`, `public`, `projects_id`) VALUES (1, 'Dave and Buster\'s Extravaganza', 'Have a ball on the company dime. Free games for all!', '2017-11-13', 'YES', NULL);
INSERT INTO `event` (`id`, `name`, `description`, `date`, `public`, `projects_id`) VALUES (2, 'Check in on Cole\'s Work', 'See if Cole is actually doing any work.', '2017-11-03', 'YES', NULL);
INSERT INTO `event` (`id`, `name`, `description`, `date`, `public`, `projects_id`) VALUES (3, 'MVCCRUD Project Pt. 1', 'Do your MVCCRUD Project part 1. Due at end of weekend.', '2017-11-01', 'NO', 2);
INSERT INTO `event` (`id`, `name`, `description`, `date`, `public`, `projects_id`) VALUES (4, 'JSCalculator', 'Build your calculator using jQuery. Due at end of weekend.', '2017-11-10', 'NO', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cohort_has_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdconnectdb`;
INSERT INTO `cohort_has_event` (`cohort_id`, `event_id`) VALUES (1, 1);
INSERT INTO `cohort_has_event` (`cohort_id`, `event_id`) VALUES (1, 2);
INSERT INTO `cohort_has_event` (`cohort_id`, `event_id`) VALUES (1, 3);
INSERT INTO `cohort_has_event` (`cohort_id`, `event_id`) VALUES (2, 1);

COMMIT;

