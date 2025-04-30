-- Position des joueurs
CREATE TYPE player_position AS ENUM ('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER');

-- Statut d'une saison ou d'un match
CREATE TYPE season_status AS ENUM ('NOT_STARTED', 'STARTED', 'FINISHED');
CREATE TYPE match_status AS ENUM ('NOT_STARTED', 'STARTED', 'FINISHED');

-- Unité de durée
CREATE TYPE duration_unit AS ENUM ('SECOND', 'MINUTE', 'HOUR');

-- Nom des championnats
CREATE TYPE championship_name AS ENUM ('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1');


CREATE TABLE club (
                       id VARCHAR(36) PRIMARY KEY,
                       name VARCHAR(100) UNIQUE NOT NULL,
                       acronym VARCHAR(3) NOT NULL,
                       year_creation INT NOT NULL,
                       stadium VARCHAR(100) NOT NULL,
                       coach_name VARCHAR(100) NOT NULL,
                       coach_nationality VARCHAR(50) NOT NULL
);


CREATE TABLE player (
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         number INT NOT NULL,
                         player_position positon NOT NULL,
                         nationality VARCHAR(50) NOT NULL,
                         age INT NOT NULL,
                         club_id VARCHAR(36),
                         FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE SET NULL
);


CREATE TABLE season (
                         id VARCHAR(36) PRIMARY KEY,
                         year INT NOT NULL,
                         alias VARCHAR(20) NOT NULL,
                         season_status status NOT NULL DEFAULT 'NOT_STARTED',
                         UNIQUE KEY (year)
);


CREATE TABLE match (
                         id VARCHAR(36) PRIMARY KEY,
                         season_year INT NOT NULL,
                         club_home_id VARCHAR(36) NOT NULL,
                         club_away_id VARCHAR(36) NOT NULL,
                         stadium VARCHAR(100) NOT NULL,
                         match_datetime DATETIME NOT NULL,
                         match_status status NOT NULL DEFAULT 'NOT_STARTED',
                         home_score INT DEFAULT 0,
                         away_score INT DEFAULT 0,
                         FOREIGN KEY (club_home_id) REFERENCES clubs(id),
                         FOREIGN KEY (club_away_id) REFERENCES clubs(id),
                         FOREIGN KEY (season_year) REFERENCES seasons(year)
);


CREATE TABLE goal (
                       id VARCHAR(36) PRIMARY KEY,
                       match_id VARCHAR(36) NOT NULL,
                       club_id VARCHAR(36) NOT NULL,
                       player_id VARCHAR(36) NOT NULL,
                       minute_of_goal INT NOT NULL CHECK (minute_of_goal BETWEEN 1 AND 90),
                       own_goal BOOLEAN NOT NULL DEFAULT FALSE,
                       FOREIGN KEY (match_id) REFERENCES matches(id),
                       FOREIGN KEY (club_id) REFERENCES clubs(id),
                       FOREIGN KEY (player_id) REFERENCES players(id)
);


CREATE TABLE club_statistic (
                                 club_id VARCHAR(36) NOT NULL,
                                 season_year INT NOT NULL,
                                 ranking_points INT NOT NULL DEFAULT 0,
                                 scored_goals INT NOT NULL DEFAULT 0,
                                 conceded_goals INT NOT NULL DEFAULT 0,
                                 clean_sheet_number INT NOT NULL DEFAULT 0,
                                 PRIMARY KEY (club_id, season_year),
                                 FOREIGN KEY (club_id) REFERENCES clubs(id),
                                 FOREIGN KEY (season_year) REFERENCES seasons(year)
);


CREATE TABLE player_statistic (
                                   player_id VARCHAR(36) NOT NULL,
                                   season_year INT NOT NULL,
                                   scored_goals INT NOT NULL DEFAULT 0,
                                   playing_time_value DECIMAL(10,2) NOT NULL DEFAULT 0,
                                   duration_unit playing_time_unit NOT NULL DEFAULT 'MINUTE',
                                   PRIMARY KEY (player_id, season_year),
                                   FOREIGN KEY (player_id) REFERENCES players(id),
                                   FOREIGN KEY (season_year) REFERENCES seasons(year)
);

Central API

CREATE TABLE championship (
                               championship_name name PRIMARY KEY,
                               difference_goals_median DECIMAL(10,2) NOT NULL,
                               last_sync TIMESTAMP NOT NULL
);


CREATE TABLE central_player (
                                 id VARCHAR(36) PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 number INT NOT NULL,
                                 player_position position NOT NULL,
                                 nationality VARCHAR(50) NOT NULL,
                                 age INT NOT NULL,
                                 championship_name championship NOT NULL,
                                 scored_goals INT NOT NULL DEFAULT 0,
                                 playing_time_value DECIMAL(10,2) NOT NULL DEFAULT 0,
                                 duration_unit playing_time_unit NOT NULL DEFAULT 'MINUTE',
                                 last_sync TIMESTAMP NOT NULL
);


CREATE TABLE central_club (
                               id VARCHAR(36) PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               acronym VARCHAR(3) NOT NULL,
                               year_creation INT NOT NULL,
                               stadium VARCHAR(100) NOT NULL,
                               coach_name VARCHAR(100) NOT NULL,
                               coach_nationality VARCHAR(50) NOT NULL,
                               championship_name championship NOT NULL,
                               ranking_points INT NOT NULL DEFAULT 0,
                               scored_goals INT NOT NULL DEFAULT 0,
                               conceded_goals INT NOT NULL DEFAULT 0,
                               clean_sheet_number INT NOT NULL DEFAULT 0,
                               last_sync TIMESTAMP NOT NULL
);