DRIVER 'org.apache.derby.jdbc.ClientDriver';
CONNECT 'jdbc:derby://localhost:1527/BookStore;create=true' USER 'guest' PASSWORD 'password';

INSERT INTO Store VALUES (DEFAULT, 'B&N Desert Ridge', '21001 N. Tatum Blvd. Suite 42', '', 'Phoenix', 'AZ', '85050', 1);
INSERT INTO Store VALUES (DEFAULT, 'B&N Pima & Shea', '10500 N. 90th Street', '', 'Scottsdale', 'AZ', '85258', 1);

INSERT INTO Book VALUES (DEFAULT, '1590595963', 'Beginning POJOs', '2006-03-24', 32.57, 1);
INSERT INTO Book VALUES (DEFAULT, '1590597923', 'Beginning JBoss Seam', '2007-02-28', 27.04, 2);
INSERT INTO Book VALUES (DEFAULT, '0596519788', 'The Productive Programmer', '2008-07-10', 26.39, 3);
INSERT INTO Book VALUES (DEFAULT, '1934356093', 'Programming Groovy', '2008-04-08', 23.07, 4);
INSERT INTO Book VALUES (DEFAULT, '0978739299', 'Groovy Recipes', '2008-01-11', 23.07, 5);
INSERT INTO Book VALUES (DEFAULT, '1590595823', 'Foundations of Ajax', '2008-07-09', 39.99, 6); 
INSERT INTO Book VALUES (DEFAULT, '0321130006', 'Effective Enterprise Java', '2008-07-09', 36.51, 7); 
INSERT INTO Book VALUES (DEFAULT, '1932394842', 'Groovy in Action', '2007-01-17', 31.49, 8);

INSERT INTO BookAuthors VALUES (1, 'Brian', 'Sam-Bodden');
INSERT INTO BookAuthors VALUES (2, 'Joseph', 'Nusairat');
INSERT INTO BookAuthors VALUES (3, 'Neal', 'Ford');
INSERT INTO BookAuthors VALUES (4, 'Venkat', 'Subramanian');
INSERT INTO BookAuthors VALUES (5, 'Scott', 'Davis');
INSERT INTO BookAuthors VALUES (6, 'Ryan', 'Asleson');
INSERT INTO BookAuthors VALUES (6, 'Nathaniel', 'Schutta');
INSERT INTO BookAuthors VALUES (7, 'Ted', 'Neward');
INSERT INTO BookAuthors VALUES (8, 'Dierk', 'Koenig');
INSERT INTO BookAuthors VALUES (8, 'Paul', 'King');
INSERT INTO BookAuthors VALUES (8, 'Andrew', 'Glover');
INSERT INTO BookAuthors VALUES (8, 'Guillaume', 'Laforge');

INSERT INTO ElectronicBook VALUES (1, 'http://www.apress.com/resource/bookfile/2713','PDF');
INSERT INTO ElectronicBook VALUES (2, 'http://www.apress.com/resource/bookfile/3382', 'PDF');

INSERT INTO Inventory VALUES (DEFAULT, 1, 1, 5, 1);
INSERT INTO Inventory VALUES (DEFAULT, 1, 2, 25, 1);
INSERT INTO Inventory VALUES (DEFAULT, 2, 1, 32, 1);
INSERT INTO Inventory VALUES (DEFAULT, 2, 2, 11, 1);
INSERT INTO Inventory VALUES (DEFAULT, 3, 1, 4, 1);
INSERT INTO Inventory VALUES (DEFAULT, 4, 2, 5, 1);
INSERT INTO Inventory VALUES (DEFAULT, 5, 1, 8, 1);
INSERT INTO Inventory VALUES (DEFAULT, 5, 2, 18, 1);
INSERT INTO Inventory VALUES (DEFAULT, 6, 1, 10, 1);
INSERT INTO Inventory VALUES (DEFAULT, 6, 2, 10, 1);
INSERT INTO Inventory VALUES (DEFAULT, 7, 1, 99, 1);
INSERT INTO Inventory VALUES (DEFAULT, 8, 1, 1, 1);
INSERT INTO Inventory VALUES (DEFAULT, 8, 2, 5, 1);

COMMIT;