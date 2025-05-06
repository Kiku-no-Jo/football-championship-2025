-- Position des joueurs
CREATE TYPE player_position AS ENUM ('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER');

-- Statut d'une saison ou d'un match
CREATE TYPE season_status AS ENUM ('NOT_STARTED', 'STARTED', 'FINISHED');
CREATE TYPE match_status AS ENUM ('NOT_STARTED', 'STARTED', 'FINISHED');

-- Unité de durée
CREATE TYPE duration_unit AS ENUM ('SECOND', 'MINUTE', 'HOUR');

-- Nom des championnats
CREATE TYPE championship_name AS ENUM ('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1');


CREATE TABLE club
(
    id            VARCHAR(36) PRIMARY KEY,
    name          VARCHAR(100) UNIQUE NOT NULL,
    acronym       VARCHAR(3)          NOT NULL,
    year_creation INT                 NOT NULL,
    stadium       VARCHAR(100)        NOT NULL,
    coach_id      INT                 NOT NULL,
    FOREIGN KEY (coach_id) REFERENCES coach (id) ON DELETE SET NULL

);
CREATE TABLE coach
(
    id           int PRIMARY KEY,
    name         VARCHAR(100) UNIQUE NOT NULL,
    nationnality VARCHAR(100)        NOT NULL

);



CREATE TABLE player
(
    id          VARCHAR(36) PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    number      INT             NOT NULL,
    position    player_position NOT NULL, -- Eto novaiko
    nationality VARCHAR(50)     NOT NULL,
    age         INT             NOT NULL,
    club_id     VARCHAR(36),
    FOREIGN KEY (club_id) REFERENCES club (id) ON DELETE SET NULL,
    UNIQUE (club_id, number)
);



CREATE TABLE season
(
    id     VARCHAR(36) PRIMARY KEY,
    year   INT           NOT NULL,
    alias  VARCHAR(20)   NOT NULL,
    status season_status NOT NULL DEFAULT 'NOT_STARTED', -- Eto novaiko
    UNIQUE (year)
);


CREATE TABLE match
(
    id             VARCHAR(36) PRIMARY KEY,
    club_home_id   VARCHAR(36)  NOT NULL,
    club_away_id   VARCHAR(36)  NOT NULL,
    stadium        VARCHAR(100) NOT NULL,
    match_datetime TIMESTAMP    NOT NULL,
    status         match_status NOT NULL DEFAULT 'NOT_STARTED', -- Eto koa niova
    season_year    INT          NOT NULL,
    home_score INTEGER DEFAULT 0,
    away_score INTEGER DEFAULT 0,
    FOREIGN KEY (club_home_id) REFERENCES club (id),
    FOREIGN KEY (club_away_id) REFERENCES club (id),
    FOREIGN KEY (season_year) REFERENCES season (year)
);

CREATE TABLE match_scorers
(
    id          VARCHAR PRIMARY KEY,
    match_id    VARCHAR NOT NULL REFERENCES match (id),
    club_id     VARCHAR NOT NULL REFERENCES club (id),
    player_id   VARCHAR NOT NULL REFERENCES player (id),
    goal_time   INTEGER NOT NULL, -- in minutes
    is_own_goal BOOLEAN DEFAULT false,
    is_penalty  BOOLEAN DEFAULT false
);

CREATE TABLE goal
(
    id             VARCHAR(36) PRIMARY KEY,
    match_id       VARCHAR(36) NOT NULL,
    club_id        VARCHAR(36) NOT NULL,
    player_id      VARCHAR(36) NOT NULL,
    minute_of_goal INT         NOT NULL CHECK (minute_of_goal BETWEEN 1 AND 90),
    own_goal       BOOLEAN     NOT NULL DEFAULT FALSE,
    FOREIGN KEY (match_id) REFERENCES match (id),
    FOREIGN KEY (club_id) REFERENCES club (id),
    FOREIGN KEY (player_id) REFERENCES player (id)
);


CREATE TABLE club_statistic
(
    club_id            VARCHAR(36) NOT NULL,
    season_year        INT         NOT NULL,
    ranking_points     INT         NOT NULL DEFAULT 0,
    scored_goals       INT         NOT NULL DEFAULT 0,
    conceded_goals     INT         NOT NULL DEFAULT 0,
    clean_sheet_number INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (club_id, season_year),
    FOREIGN KEY (club_id) REFERENCES club (id),
    FOREIGN KEY (season_year) REFERENCES season (year)
);


CREATE TABLE player_statistic
(
    player_id          VARCHAR(36)    NOT NULL,
    season_year        INT            NOT NULL,
    scored_goals       INT            NOT NULL DEFAULT 0,
    playing_time_id    VARCHAR(36)  NOT NULL,
    PRIMARY KEY (player_id, season_year),
    FOREIGN KEY (player_id) REFERENCES player (id),
    FOREIGN KEY (season_year) REFERENCES season (year),
    FOREIGN KEY (playing_time_id) REFERENCES playing_time (id)
);

CREATE TABLE playing_time
    (
        id VARCHAR(36) NOT NULL PRIMARY KEY,
        value DECIMAL(10, 2) NOT NULL DEFAULT 0,
        time_unit duration_unit NOT NULL DEFAULT 'MINUTE'
);

Central
API

CREATE TABLE championship
(
    name                    championship_name PRIMARY KEY, -- miasa le enum
    difference_goals_median DECIMAL(10, 2) NOT NULL,
    last_sync               TIMESTAMP      NOT NULL
);

CREATE TABLE central_player
(
    id                 VARCHAR(36) PRIMARY KEY,
    name               VARCHAR(100)      NOT NULL,
    number             INT               NOT NULL,
    position           player_position   NOT NULL,                  --  Mandray en parametre
    nationality        VARCHAR(50)       NOT NULL,
    age                INT               NOT NULL,
    championship       championship_name NOT NULL,                  -- Anle type enum rehetra
    scored_goals       INT               NOT NULL DEFAULT 0,
    playing_time_value DECIMAL(10, 2)    NOT NULL DEFAULT 0,
    playing_time_unit  duration_unit     NOT NULL DEFAULT 'MINUTE', -- de raisiny par defaut le zavatra
    last_sync          TIMESTAMP         NOT NULL,
    FOREIGN KEY (championship) REFERENCES championship (name)
);

CREATE TABLE central_club
(
    id                 VARCHAR(36) PRIMARY KEY,
    name               VARCHAR(100)      NOT NULL,
    acronym            VARCHAR(3)        NOT NULL,
    year_creation      INT               NOT NULL,
    stadium            VARCHAR(100)      NOT NULL,
    coach_name         VARCHAR(100)      NOT NULL,
    coach_nationality  VARCHAR(50)       NOT NULL,
    championship       championship_name NOT NULL, -- Param mahazatra
    ranking_points     INT               NOT NULL DEFAULT 0,
    scored_goals       INT               NOT NULL DEFAULT 0,
    conceded_goals     INT               NOT NULL DEFAULT 0,
    clean_sheet_number INT               NOT NULL DEFAULT 0,
    last_sync          TIMESTAMP         NOT NULL,
    FOREIGN KEY (championship) REFERENCES championship (name)
);
