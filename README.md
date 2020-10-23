For authentication JWT is used in combination with spring security.

It is necessary to 
enter email and password and send request
- user gets created (with encrypted password)
- and jwt token is returned in header

That token is later used for other requests where user has to be authenticated. 

For authorization part, it's very simple all authenticated users have ROLE_USER, nothing complicated.

For login jwt token is used also, after successful login it is returned and used
eventually for all subsequent calls.

Regarding parsing links, when nothing is found link entity is not created.
Only in cases where something is actually found on page, to be tags.

search of links by tags is done

Probably the most important class regarding parsing/analyzing text is HtmlParser.java.
One nice library , didn't know before for it, called jsoup is used :)

also add tags is done, together with some Unit tests.

To get started there is mysql-database-init.sql script, to create db, add user, grant priviledges.
Tests use H2 db

