CREATE TABLE clubs (
                       id VARCHAR(36) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       acronym VARCHAR(3) NOT NULL,
                       year_creation INT NOT NULL,
                       stadium VARCHAR(100) NOT NULL,
                       coach_name VARCHAR(100) NOT NULL,
                       coach_nationality VARCHAR(50) NOT NULL
);


CREATE TABLE players (
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         number INT NOT NULL,
                         position ENUM('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER') NOT NULL,
                         nationality VARCHAR(50) NOT NULL,
                         age INT NOT NULL,
                         club_id VARCHAR(36),
                         FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE SET NULL
);


CREATE TABLE seasons (
                         id VARCHAR(36) PRIMARY KEY,
                         year INT NOT NULL,
                         alias VARCHAR(20) NOT NULL,
                         status ENUM('NOT_STARTED', 'STARTED', 'FINISHED') NOT NULL DEFAULT 'NOT_STARTED',
                         UNIQUE KEY (year)
);


CREATE TABLE matches (
                         id VARCHAR(36) PRIMARY KEY,
                         season_year INT NOT NULL,
                         club_home_id VARCHAR(36) NOT NULL,
                         club_away_id VARCHAR(36) NOT NULL,
                         stadium VARCHAR(100) NOT NULL,
                         match_datetime DATETIME NOT NULL,
                         status ENUM('NOT_STARTED', 'STARTED', 'FINISHED') NOT NULL DEFAULT 'NOT_STARTED',
                         home_score INT DEFAULT 0,
                         away_score INT DEFAULT 0,
                         FOREIGN KEY (club_home_id) REFERENCES clubs(id),
                         FOREIGN KEY (club_away_id) REFERENCES clubs(id),
                         FOREIGN KEY (season_year) REFERENCES seasons(year)
);


CREATE TABLE goals (
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


CREATE TABLE club_statistics (
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


CREATE TABLE player_statistics (
                                   player_id VARCHAR(36) NOT NULL,
                                   season_year INT NOT NULL,
                                   scored_goals INT NOT NULL DEFAULT 0,
                                   playing_time_value DECIMAL(10,2) NOT NULL DEFAULT 0,
                                   playing_time_unit ENUM('SECOND', 'MINUTE', 'HOUR') NOT NULL DEFAULT 'MINUTE',
                                   PRIMARY KEY (player_id, season_year),
                                   FOREIGN KEY (player_id) REFERENCES players(id),
                                   FOREIGN KEY (season_year) REFERENCES seasons(year)
);

Central API

CREATE TABLE championships (
                               name ENUM('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1') PRIMARY KEY,
                               difference_goals_median DECIMAL(10,2) NOT NULL,
                               last_sync TIMESTAMP NOT NULL
);


CREATE TABLE central_players (
                                 id VARCHAR(36) PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 number INT NOT NULL,
                                 position ENUM('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER') NOT NULL,
                                 nationality VARCHAR(50) NOT NULL,
                                 age INT NOT NULL,
                                 championship ENUM('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1') NOT NULL,
                                 scored_goals INT NOT NULL DEFAULT 0,
                                 playing_time_value DECIMAL(10,2) NOT NULL DEFAULT 0,
                                 playing_time_unit ENUM('SECOND', 'MINUTE', 'HOUR') NOT NULL DEFAULT 'MINUTE',
                                 last_sync TIMESTAMP NOT NULL
);


CREATE TABLE central_clubs (
                               id VARCHAR(36) PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               acronym VARCHAR(3) NOT NULL,
                               year_creation INT NOT NULL,
                               stadium VARCHAR(100) NOT NULL,
                               coach_name VARCHAR(100) NOT NULL,
                               coach_nationality VARCHAR(50) NOT NULL,
                               championship ENUM('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1') NOT NULL,
                               ranking_points INT NOT NULL DEFAULT 0,
                               scored_goals INT NOT NULL DEFAULT 0,
                               conceded_goals INT NOT NULL DEFAULT 0,
                               clean_sheet_number INT NOT NULL DEFAULT 0,
                               last_sync TIMESTAMP NOT NULL
);