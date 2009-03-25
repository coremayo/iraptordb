CREATE TABLE Item (
		itemId INTEGER PRIMARY KEY AUTOINCREMENT,
		title VARCHAR(30) NOT NULL,
		genre VARCHAR(30),
		rating INTEGER(2) NOT NULL DEFAULT 0,
		year INTEGER(4),
		dateAdded DATE NOT NULL,
		notes VARCHAR
);

CREATE TABLE Person (
		personId INTEGER PRIMARY KEY AUTOINCREMENT,
		fName VARCHAR(30) NOT NULL,
		lName VARCHAR(30) NOT NULL
);

CREATE TABLE Tag (
		tagId INTEGER PRIMARY KEY AUTOINCREMENT,
		name VARCHAR(30) NOT NULL
);

CREATE TABLE DVD (
		itemId INTEGER,
		directorName VARCHAR(60),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE CD (
		itemId INTEGER,
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE VideoGame (
		itemId INTEGER,
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE Book (
		itemId INTEGER,
		publisher VARCHAR(30),
		isbn CHAR(13),
		PRIMARY KEY (itemId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId)
);

CREATE TABLE BookAuthor (
		book_itemId INTEGER,
		author_personId INTEGER,
		PRIMARY KEY (book_itemId, author_personId),
		FOREIGN KEY (book_itemId) REFERENCES Book(itemId),
		FOREIGN KEY (author_personId) REFERENCES Person(personId)
);

CREATE TABLE CDArtist (
		cd_itemId INTEGER,
		artist_personId INTEGER,
		PRIMARY KEY (cd_itemId, artist_personId),
		FOREIGN KEY (cd_itemId) REFERENCES CD(itemId),
		FOREIGN KEY (artist_personId) REFERENCES Person(personId)
);

CREATE TABLE ItemTag (
		itemId INTEGER,
		tagId INTEGER,
		PRIMARY KEY (itemId, tagId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId),
		FOREIGN KEY (tagId) REFERENCES Tag(tagId)
);
