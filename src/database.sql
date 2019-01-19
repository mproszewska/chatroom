DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS messages;
CREATE TABLE users(
	login varchar(20) PRIMARY KEY,
	password varchar(20) 
);
CREATE TABLE messages(
	id serial PRIMARY KEY,
	login varchar(20),
	msg varchar(100),
	"date" varchar(20),
	time varchar(20)
)
