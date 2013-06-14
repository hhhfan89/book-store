set autocommit=0;
start transaction;

drop database `book_store`;

create database `book_store`;

use `book_store`;

create table `book_search` (
  `book_id` int(10) unsigned not null,
  `title` varchar(25) not null,
  `description` text default null,
  primary key (`book_id`),
  FULLTEXT KEY `ft_index1` (`title`),
  FULLTEXT KEY `ft_index2` (`title`,`description`)
) ENGINE=MyISAM COMMENT='Tabella per la ricerca di un libro.';

create table `authors` (
`author_id` int(10) unsigned not null auto_increment,
`name` varchar(25) not null,
`surname` varchar(25) not null,
primary key (`author_id`)
) engine=InnoDB;

create table `roles` (
`role_id` int(1) unsigned not null auto_increment,
`title` varchar(25) not null,
primary key (`role_id`)
) engine=InnoDB;

create table `editors` (
`editor_id` int(10) unsigned not null auto_increment,
`name` varchar(25) not null,
primary key (`editor_id`)
) engine=InnoDB;

create table `categories` (
`category_id` int(10) unsigned not null auto_increment,
`name` varchar(25) not null,
primary key (`category_id`)
) engine=InnoDB;

create table `users` (
`user_id` int(10) unsigned not null auto_increment,
`name` varchar(25) not null,
`surname` varchar(25) not null,
`email` varchar(25) not null,
`username` varchar(25) not null,
`password` varchar(25) not null,
`role_id` int(1) unsigned default null,
primary key (`user_id`),
foreign key (`role_id`) references roles(`role_id`) on delete set NULL on update cascade
) engine=InnoDB;

create table `books` ( 
`book_id` int(10) unsigned not null,
`price` float not null,
`numpages` int(5) not null,
`title` varchar(50) not null,
`description` text,
`image` text not null,
`text` text not null,
`edition` int(5) not null,
primary key (`book_id`)
) engine=InnoDB;

create table `boca01` ( 
`book_id` int(10) unsigned not null,
`category_id` int(10) unsigned not null,
primary key (`book_id`, `category_id`),
foreign key (`book_id`) references books(`book_id`) on delete cascade on update cascade,
foreign key (`category_id`) references categories(`category_id`) on delete cascade on update cascade
) engine=InnoDB;

create table `boau01` (
`author_id` int(10) unsigned not null,
`book_id` int(10) unsigned not null,
primary key (`book_id`, `author_id`),
CONSTRAINT foreign key (`book_id`) references books(`book_id`) on delete cascade on update cascade,
CONSTRAINT foreign key (`author_id`) references authors(`author_id`) on delete cascade on update cascade
) engine=InnoDB;


create table `boed01` (
`editor_id` int(10) unsigned not null,
`book_id` int(10) unsigned not null, 
 primary key  (`book_id`, `editor_id`), 
foreign key (`book_id`) references books(`book_id`) on delete cascade on update cascade,
foreign key (`editor_id`) references editors(`editor_id`) on delete cascade on update cascade
) engine=InnoDB;



DELIMITER //
CREATE TRIGGER book_search_trin AFTER INSERT ON books
FOR EACH ROW
BEGIN
	INSERT INTO book_search (`book_id`, `title`, `description`) VALUES (NEW.book_id, NEW.title, NEW.description);
END; //

CREATE TRIGGER book_search_trup AFTER UPDATE ON books
FOR EACH ROW
BEGIN
	UPDATE book_search SET `book_id` = NEW.book_id, `title` = NEW.title, `description` = NEW.description WHERE `book_id` = OLD.book_id;
END; //

CREATE TRIGGER book_search_trdl AFTER DELETE ON books
FOR EACH ROW
BEGIN
	DELETE FROM book_search WHERE `book_id` = OLD.book_id;
END; //
DELIMITER ;

INSERT INTO roles (`title`) VALUES ("ROLE_ADMIN");
INSERT INTO roles (`title`) VALUES ("ROLE_USER");

INSERT INTO users (`name`, `surname`, `email`, `username` ,`password`, `role_id`) VALUES ("utentediprova", "utentediprova", "admin@bookstore.it", "admin", "admin","1");

INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) VALUES ("1000", "20", "200", "Harry Potter 1", "  Harry Potter Ë un predestinato: ha una cicatrice a forma di saetta sulla fronte e provoca strani fenomeni, come quello di farsi ricrescere in una notte i capelli inesorabilemte tagliati dai perfidi zii. Ma solo in occasione del suo undicesimo compleanno gli si rivelano la sua natura e il suo destino, e il mondo misterioso cui di diritto appartiene. Un mondo dove regna la magia; un universo popolato da gufi portalettere, scope volanti, caramelle al gusto di cavolini di Bruxelles, ritratti che scappano. Eta di lettura: da 8 anni.
","http://moryhp88.files.wordpress.com/2008/01/filosofale.jpg", "http://www.foruminsegnanti.it/articoli/Il_vero_volto_di_Vincenzo_De_Luca.pdf", "2005");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1001", "25", "200", "Harry Potter 2", 
"  Harry Potter Ë ormai celebre: durante il primo anno alla Scuola di Magia e Stregoneria di Hogwarts ha sconfitto il terribile Voldemort, vendicando la morte dei suoi genitori e coprendosi di gloria. Ma una spaventosa minaccia incombe sulla scuola: un incantesimo che colpisce i compagni di Harry uno dopo l'altro, e che sembra legato a un antico mistero racchiuso nella tenebrosa Camera dei Segreti. Harry e i suoi amici sfideranno oscure magie e terribili mostri, parleranno con i gufi e viaggeranno in automobili volanti, in un percorso magico dal ritmo incalzante e dalla sequenza infinita, come da scatole cinesi. Eta di lettura: da 8 anni."
,
"http://giotto.ibs.it/cop/copj170.asp?f=9788877827036", 
"http://www.istitutopesenti.it/dipartimenti/meccanica/Meccanica/LezMecc(Trasm+Attrito).pdf",
"2005");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1002", "26", "300", "Harry Potter 3", 
" Tra colpi di scena, mappe stregate e ippogrifi scontrosi, zie volanti e libri che mordono, Harry Potter conduce il lettore nel terzo capitolo delle sue avventure. Harry, giovane studente della prestigiosa Scuola di Magia e Stregoneria di Hogwarts, e questa volta alle prese con un famigerato assassino che, evaso dalla terribile prigione di Azkaban, gli sta dando la caccia per ucciderlo. Forse questa volta nemmeno la Scuola di Magia, nemmeno gli amici piu cari potranno aiutarlo, almeno fino a quando si nascondera tra di loro un traditore...
",
"http://giotto.ibs.it/cop/copj170.asp?f=9788884516121", 
"http://webusers.fis.uniroma3.it/~devincenzi/Esercizi_Esp2_Elena.pdf",
"2007");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1003", "19", "300", "Harry Potter 4", 
"Un momento cruciale nella vita di Harry Potter: ormai Ë un mago adolescente, vuole andarsene dalla casa dei pestiferi Dursley, vuole sognare la cercatrice del Corvonero per cui ha una cotta tremenda... E poi vuole scoprire quali sono i grandiosi avvenimenti che si terranno a Hogwarts e che riguarderanno altre due scuole di magia e una grande competizione che non si svolge da cento anni. Harry Potter vuole davvero essere un normale mago di quattordici anni. Ma sfortunatamente, Harry non Ë un mago normale. E stavolta la differenza puÚ essergli fatale.",
"http://giotto.ibs.it/cop/copj170.asp?f=9788884510495", 
"http://www.dieeasy.net/listing/papers/diveintopython-it.pdf",
"2008");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1004", "18", "300", "Harry Potter 5", 
"Quindicenne alle prese con improvvisi scoppi d'ira, la voglia di cambiare il mondo e le prime, cocenti, passioni amorose, Harry Potter sta per tornare alla Scuola di Alta Stregoneria di Hogwarts, dove frequenter‡ il quinto anno. Gi‡ infastidito dalle noie adolescenziali, il ragazzino Ë reduce da una noiosa estate con gli spregevoli Dursley e non vede l'ora di tornare alla movimentata vita degli apprendisti maghi. E, di certo, le avventure non mancheranno... Tessendo un'altra stupefacente trama, Joanne K. Rowling, questa volta d‡ voce alle inquietudini dell'adolescenza, mettendo in guardia contro la stupidit‡ del potere e di chi lo usa per combattere il talento, il coraggio, la fantasia e la diversita.", 
"http://giotto.ibs.it/cop/copj170.asp?f=9788884513441", 
"http://l.kinepolis.com/sharedmedia/be-nl/media/IO_NON_HO_PAURA_Def.pdf",
"2009");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1005", "18", "400", "Harry Potter 6", 
"Sesto appuntamento con la saga che ha appassionato bambini, ragazzi e lettori di tutte le eta. Harry Potter e solo, sconvolto e preoccupato. Il suo amato padrino Sirius Black e morto, e le parole di Albus Silente sulla profezia gli confermano che lo scontro con Lord Voldemort e ormai inevitabile.", 
"http://giotto.ibs.it/cop/copj170.asp?f=9788884516374", 
"http://www.mymovies.it/filmclub/2007/08/036/mymovies.pdf",
"2010");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1006", "18", "400", "Harry Potter 8", 
"Mi apro alla chiusura: E uno dei tanti enigmi lasciati da Silente con cui Harry Potter deve confrontarsi in questo ultimo, settimo libro.", 
"http://giotto.ibs.it/cop/copj170.asp?f=9788884518781", 
"http://www.vitellaro.it/silvio/storia%20e%20filosofia/Appunti%20storia/Lettera%20a%20una%20professoressa.pdf",
"2011");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1007", "20", "700", "Harry Potter 7", 
"Persistence is an important set of techniques and technologies for accessing and transacting data, and ensuring that data is mobile regardless of specific 	applications and contexts. In Java development, persistence is a key factor in enterprise, e-commerce, and other transaction-oriented applications.", 
"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQdUsp43eDLgOKIx9EpdvgwgOW9TkhLvyd898Df8wxaHRq7aDBckQ", 
"http://www.italotedesco.de/Texte/BREVE%20GRAMMATICA%20TEDESCO%20_2_.pdf",
"2011");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1008", "20", "1000", "Hibernate", 
"Harnessing Hibernate is an ideal introduction to the popular framework that lets Java developers work with information from a relational database easily and efficiently. Databases are a very different world than Java objects", 
"http://giotto.ibs.it/cop/copamj.asp?e=9780596517724", 
"http://franzato.xoom.it//franzato/PGI3_HTML/PGI31.pdf",
"2012");
INSERT INTO books (`book_id`, `price`, `numpages`, `title` ,`description`, `image`, `text`, `edition`) 
VALUES ("1009", "20", "1000", "High Performance MySql ", 
"With High Performance MySQL, youíll learn advanced techniques for everything from designing schemas, indexes, and queries to tuning ", 
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkofLNRy0padLGKSyAq-ewiSjc4VsJeUrs9-3UJBJGoTKDxC9d", 
"http://www.tesionline.com/__PDF/5790/5790p.pdf",
"2012");

INSERT INTO authors (`name`, `surname`) VALUES ("Dante", "Alighieri");
INSERT INTO authors (`name`, `surname`) VALUES ("Giacomo", "Leopardi");
INSERT INTO authors (`name`, `surname`) VALUES ("Oriana", "Fallaci");
INSERT INTO authors (`name`, `surname`) VALUES ("Federico", "Moccia");
INSERT INTO authors (`name`, `surname`) VALUES ("Fabio", "Volo");
INSERT INTO authors (`name`, `surname`) VALUES ("Stephenie", "Mayer");
INSERT INTO authors (`name`, `surname`) VALUES ("JK", "Rowling");

INSERT INTO categories (`name`) VALUES ("Giallo");
INSERT INTO categories (`name`) VALUES ("Nero");
INSERT INTO categories (`name`) VALUES ("Fantasy");
INSERT INTO categories (`name`) VALUES ("Hero");
INSERT INTO categories (`name`) VALUES ("Rosa");
INSERT INTO categories (`name`) VALUES ("Universita");


INSERT INTO editors (`name`) VALUES ("Mondadori");
INSERT INTO editors (`name`) VALUES ("RaiEri");
INSERT INTO editors (`name`) VALUES ("RCS");
INSERT INTO editors (`name`) VALUES ("GVE");
INSERT INTO editors (`name`) VALUES ("Hoepli");

INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1001", "1");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1001", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1000", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1002", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1003", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1004", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1005", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1006", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1007", "3");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1008", "6");
INSERT INTO boca01 (`book_id`, `category_id`) VALUES ("1009", "6");

INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1000", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1001", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1002", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1003", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1004", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1005", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1006", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1007", "7");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1008", "5");
INSERT INTO boau01 (`book_id`, `author_id`) VALUES ("1009", "4");

INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1000", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1001", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1002", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1003", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1004", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1005", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1006", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1007", "1");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1008", "5");
INSERT INTO boed01 (`book_id`, `editor_id`) VALUES ("1009", "5");



