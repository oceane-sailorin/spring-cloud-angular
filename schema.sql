psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = bcc'" | grep -q 1 || psql -U postgres -c "CREATE DATABASE bcc"

or 

CREATE EXTENSION IF NOT EXISTS dblink;

DO $$
BEGIN
PERFORM dblink_exec('', 'CREATE DATABASE bcc
EXCEPTION WHEN duplicate_database THEN RAISE NOTICE '%, skipping', SQLERRM USING ERRCODE = SQLSTATE;
END
$$;

CREATE TABLE IF NOT EXISTS bcc.permissions (
permission_id SERIAL PRIMARY KEY,
permission_code varchar(50) unique not null,
permission_naming varchar(50) unique not null 
) ;
CREATE UNIQUE INDEX permissions_code_idx ON bcc.permissions (permission_code);


CREATE TABLE IF NOT EXISTS bcc.profiles (
profile_id SERIAL PRIMARY KEY,
profile_naming varchar(50) unique not null
) ;


CREATE TABLE IF NOT EXISTS bcc.profiles_permissions (
profile_permission_id SERIAL PRIMARY KEY,
profile_permission_permission integer REFERENCES bcc.permissions(permission_id),
profile_permission_profile integer REFERENCES bcc.profiles(profile_id)
);
CREATE UNIQUE INDEX profiles_permissions_idx ON bcc.profiles_permissions (profile_permission_permission,profile_permission_profile);

CREATE TABLE IF NOT EXISTS bcc.users_status (
user_status_id SERIAL PRIMARY KEY,
user_status_code varchar(20) unique not null,
user_status_naming varchar(50) unique not null
);
CREATE UNIQUE INDEX users_status_idx ON bcc.users_status (user_status_code);


CREATE TABLE IF NOT EXISTS bcc.users (
user_id  SERIAL PRIMARY KEY,
user_profile integer REFERENCES bcc.profiles(profile_id),
user_status integer REFERENCES  bcc.users_status(user_status_id),
user_login varchar(50) unique not null,
user_lastname varchar(50) not null,
user_firstname varchar(50) not null,
user_date_creation timestamptz default now() ,
user_date_activation timestamptz,
user_date_last_connexion timestamptz,
user_date_archive timestamptz
);
CREATE UNIQUE INDEX users_idx ON bcc.users (user_login);


CREATE TABLE IF NOT EXISTS bcc.clients (
client_id SERIAL PRIMARY KEY,
client_code varchar(50) unique not null,
client_naming varchar(50) not null,
client_date_creation timestamptz default now(),
client_date_archive timestamptz
);
CREATE UNIQUE INDEX clients_idx ON bcc.clients (client_code);



CREATE TABLE IF NOT EXISTS bcc.needs_status (
need_status_id SERIAL PRIMARY KEY,
need_status_code varchar(50) unique not null,
need_status_naming varchar(50) unique not null
);
CREATE UNIQUE INDEX needs_status_idx ON bcc.needs_status (need_status_code);

CREATE TABLE IF NOT EXISTS bcc.priorities(
priority_id SERIAL PRIMARY KEY,
priority_code varchar(50) unique not null,
priority_naming varchar(50) unique not null
);
CREATE UNIQUE INDEX priority_idx ON bcc.priorities(priority_code);

CREATE TABLE IF NOT EXISTS bcc.need_candidate_status (
need_candidate_status_id SERIAL PRIMARY KEY,
need_candidate_status_code varchar(50) unique not null,
need_candidate_status_naming varchar(50) unique not null
);
CREATE UNIQUE INDEX need_candidate_status_idx ON bcc.need_candidate_status (need_candidate_status_code);
 

CREATE TABLE IF NOT EXISTS bcc.keywords (
keyword_id SERIAL PRIMARY KEY,
keyword_code varchar(50) unique not null
);
CREATE UNIQUE INDEX keywords_idx ON bcc.keywords (keyword_code);

CREATE TYPE bcc.status_for_need AS ENUM ('Open', 'Closed');
CREATE TYPE bcc.priority AS ENUM ('High', 'Medium','Low');

CREATE TABLE IF NOT EXISTS bcc.needs (
need_id  SERIAL PRIMARY KEY,
need_title varchar(255) not null,
need_client integer     REFERENCES bcc.clients(client_id),
need_commercial integer REFERENCES bcc.users(user_id),
need_status integer REFERENCES bcc.needs_status(need_status_id),
need_priority integer REFERENCES bcc.priorities(priority_id),
need_poste varchar(255),
need_departement varchar(255),
need_description text,
need_date_creation timestamptz default now() ,
need_date_need date
need_date_end timestamptz CHECK (need_date_end > need_date_creation),
need_filename varchar(255)
);


-- DROP INDEX bcc.needs_client_idx;
-- DROP INDEX bcc.needs_user_idx;
CREATE  INDEX needs_user_idx ON bcc.needs (need_commercial);
CREATE  INDEX needs_client_idx ON bcc.needs (need_client);

CREATE TABLE IF NOT EXISTS bcc.keywords_needs (
keyword_need_id  SERIAL PRIMARY KEY,
keyword_need_keyword  integer REFERENCES bcc.keywords(keyword_id),
keyword_need  integer REFERENCES bcc.needs(need_id)
);

CREATE TABLE IF NOT EXISTS bcc.candidates_status (
candidate_status_id  SERIAL PRIMARY KEY,
candidate_status_code varchar(50) unique not null,
candidate_status_naming varchar(50) unique not null,
candidate_status_order int not null
);
CREATE UNIQUE INDEX candidates_status_idx ON bcc.candidates_status (candidate_status_code);


CREATE TABLE IF NOT EXISTS bcc.countries (
country_id  SERIAL PRIMARY KEY,
country_code varchar(3) unique not null,
country_naming_en varchar(255) unique not null,
country_naming varchar(255) unique not null
);
CREATE UNIQUE INDEX countries_idx ON bcc.countries (country_code);

CREATE TABLE IF NOT EXISTS bcc.sources (
source_id  SERIAL PRIMARY KEY,
source_naming varchar(50) not null
);


CREATE TABLE IF NOT EXISTS bcc.candidates (
candidate_id SERIAL PRIMARY KEY,
candidate_identity varchar(50) unique not null,
candidate_gender varchar(3) not null,
candidate_lastname varchar(50) not null,
candidate_firstname varchar(50) not null,
candidate_email varchar(255),
candidate_phone varchar(20),
candidate_address varchar(255),
candidate_country integer REFERENCES bcc.countries(country_id),
candidate_recruiter integer REFERENCES bcc.users(user_id),
candidate_sourcer integer REFERENCES bcc.users(user_id),
candidate_source integer REFERENCES bcc.sources(source_id),
candidate_candidate_status integer REFERENCES bcc.candidates_status(candidate_status_id),
candidate_stack varchar(20),
candidate_profile varchar(255),
candidate_salary varchar(20),
candidate_date_disponibility  date,
candidate_date_interview date,
candidate_date_sourcing  date,
candidate_date_proposition  date,
candidate_date_signature  date,
candidate_date_last_update  timestamptz,
candidate_filename varchar(255)
);
CREATE UNIQUE INDEX candidates_idx ON bcc.candidates (candidate_identity);
CREATE INDEX candidates_recruiter_idx ON bcc.candidates (candidate_recruiter);
CREATE INDEX candidate_candidates_status_idx ON bcc.candidates (candidate_candidate_status);


CREATE TABLE IF NOT EXISTS bcc.keywords_candidates (
keyword_candidate_id  SERIAL PRIMARY KEY,
keyword_candidate_keyword  integer REFERENCES bcc.keywords(keyword_id),
keyword_candidate_candidate  integer REFERENCES bcc.candidates(candidate_id)
);


CREATE TABLE IF NOT EXISTS bcc.candidates_needs (
candidate_need_id                         SERIAL PRIMARY KEY,
candidate_need_need integer               REFERENCES bcc.needs(need_id),
candidate_need_need_candidate_status integer        REFERENCES bcc.need_candidate_status(need_candidate_status_id),
candidate_need_candidate integer          REFERENCES bcc.candidates(candidate_id),
candidate_need_date_creation timestamptz default now(),
candidate_need_date_sent date,
candidate_need_date_interview date,
candidate_need_date_waiting_feedback date,
candidate_need_date_ok date,
candidate_need_date_ko_client date,
candidate_need_date_ko_candidate date
);
CREATE INDEX candidates_needs_idx ON bcc.candidates_needs (candidate_need_need);
CREATE INDEX candidates_needs_candidate_idx ON bcc.candidates_needs (candidate_need_candidate);
CREATE INDEX candidates_needs_status_idx ON bcc.candidates_needs (candidate_need_need_candidate_status);

-- ALTER TABLE bcc.candidates_needs DROP CONSTRAINT candidates_needs_candidate_need_need_status_fkey;

-- ALTER TABLE bcc.candidates_needs
--    ADD CONSTRAINT candidates_needs_candidate_need_need_status_fkey FOREIGN KEY (candidate_need_need_status)
--    REFERENCES bcc.need_candidate_status (need_candidate_status_id) MATCH SIMPLE
--    ON UPDATE NO ACTION
--    ON DELETE NO ACTION;


CREATE TABLE IF NOT EXISTS bcc.history (
history_id SERIAL PRIMARY KEY,
history_user integer REFERENCES bcc.users(user_id),
history_date_creation timestamptz default now(),
history_query text,
history_comment text
);
CREATE UNIQUE INDEX history_idx ON bcc.history (history_user);


CREATE TABLE IF NOT EXISTS bcc.preferences(
    preference_id SERIAL PRIMARY KEY,  
    preference_user integer REFERENCES bcc.users(user_id), 
    preference_clients  integer[]
);






