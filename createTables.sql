CREATE TABLE Item (
		itemId INTEGER PRIMARY KEY AUTOINCREMENT,
		title VARCHAR(30) NOT NULL,
		genre VARCHAR(30),
		rating INTEGER(2) NOT NULL DEFAULT 0,
		year INTEGER(4),
		dateAdded DATE NOT NULL,
		notes VARCHAR
);

CREATE TABLE Creator (
		creatorId INTEGER PRIMARY KEY AUTOINCREMENT,
		name VARCHAR(30) NOT NULL
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
		author_creatorId INTEGER,
		PRIMARY KEY (book_itemId, author_creatorId),
		FOREIGN KEY (book_itemId) REFERENCES Book(itemId),
		FOREIGN KEY (author_creatorId) REFERENCES Creator(creatorId)
);

CREATE TABLE CDArtist (
		cd_itemId INTEGER,
		artist_creatorId INTEGER,
		PRIMARY KEY (cd_itemId, artist_creatorId),
		FOREIGN KEY (cd_itemId) REFERENCES CD(itemId),
		FOREIGN KEY (artist_creatorId) REFERENCES Creator(creatorId)
);

CREATE TABLE ItemTag (
		itemId INTEGER,
		tagId INTEGER,
		PRIMARY KEY (itemId, tagId),
		FOREIGN KEY (itemId) REFERENCES Item(itemId),
		FOREIGN KEY (tagId) REFERENCES Tag(tagId)
);
