CREATE TABLE Item (
		itemId INTEGER(9),
		title VARCHAR(30),
		genreId INTEGER(9),
		rating INTEGER(2),
		year INTEGER(4),
		dateAdded DATE,
		notes VARCHAR,
		PRIMARY KEY (itemId)
);

CREATE TABLE Person (
		personId INTEGER(9),
		fName VARCHAR(30),
		lName VARCHAR(30),
		PRIMARY KEY (personId)
);

CREATE TABLE Tag (
		tagId INTEGER(9),
		name VARCHAR(30),
		PRIMARY KEY (tagId)
);

CREATE TABLE DVD (
		itemId INTEGER(9),
		directorName VARCHAR(60),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE CD (
		itemId INTEGER(9),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE VideoGame (
		itemId INTEGER(9),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE Book (
		itemId INTEGER(9),
		publisher VARCHAR(30),
		isbn CHAR(13),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemid)
);
