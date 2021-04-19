USE doctorsdb;
CREATE TABLE doctor(
id int AUTO_INCREMENT PRIMARY KEY,
first_name varchar(25),
last_name varchar(25),
specialty varchar (50), 
office_number int
);

INSERT INTO doctor (first_name, last_name, specialty, office_number)
VALUES ('Elena', 'Mirza', 'Dermatology', 3), ('Ion', 'Vasilean', 'Family medicine', 10),
('Ana', 'Muntean', 'Medical genetics', 9), ('John', 'Peterson', 'Anesthesiology', 2),
('Radu', 'Morari', 'Dermatology', 3), ('Angela', 'Tulbu', 'Diagnostic radiology', 5),
('Adrian', 'Ciurin', 'Cardiology', 7), ('Ana', 'Gavrilita', 'Family medicine', 8),
('Ionela', 'Bogos', 'Gastroenterology', 4), ('Adrian', 'Nichita', 'Neurology', 6);
