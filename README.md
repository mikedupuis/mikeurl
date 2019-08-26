# MikeURL - A Heroku-based URL shortener

## Perisistence
MikeURL uses a Heroku-provided PostgreSQL database.

### Schema

#### SHORTENED_URLS Table
```
CREATE TABLE SHORTENED_URLS (
   SHORT_URL_ID serial PRIMARY KEY,
   FULL_URL text not null,
   SHORT_URL text not null,
   USED_COUNT BIGINT default 0,
   CREATED_TIME TIMESTAMP NOT NULL,
   LAST_USED_TIME TIMESTAMP
);
```
