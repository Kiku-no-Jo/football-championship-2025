-- 1. Insert Coaches
INSERT INTO coach (id, name, nationality)
VALUES (1, 'Entraîneur 1', 'Français'),
       (2, 'Entraîneur 2', 'Italien'),
       (3, 'Entraîneur 3', 'Allemand');

-- 2. Insert Clubs
INSERT INTO club (id, name, acronym, year_creation, stadium, coach_id)
VALUES ('club-1', 'Club 1', 'C1', 1902, 'Stade 1', 1),
       ('club-2', 'Club 2', 'C2', 1905, 'Stade 2', 2),
       ('club-3', 'Club 3', 'C3', 1910, 'Stade 3', 3);

-- 3. Insert Players
-- Club 1
INSERT INTO player (id, name, number, age, position, nationality, club_id)
VALUES ('p-gk-1', 'Gardien 1', 1, 30, 'GOAL_KEEPER', 'Espagnol', 'club-1'),
       ('p-df-1', 'Défense 1', 2, 25, 'DEFENSE', 'Espagnol', 'club-1'),
       ('p-mf-1', 'Milieu 1', 5, 24, 'MIDFIELDER', 'Espagnol', 'club-1'),
       ('p-st-1', 'Attaquant 1', 7, 17, 'STRIKER', 'Espagnol', 'club-1');

-- Club 2
INSERT INTO player (id, name, number, age, position, nationality, club_id)
VALUES ('p-gk-2', 'Gardien 2', 1, 21, 'GOAL_KEEPER', 'Espagnol', 'club-2'),
       ('p-df-2', 'Défense 2', 2, 34, 'DEFENSE', 'Belge', 'club-2'),
       ('p-mf-2', 'Milieu 2', 5, 29, 'MIDFIELDER', 'Français', 'club-2'),
       ('p-st-2', 'Attaquant 2', 7, 18, 'STRIKER', 'Allemand', 'club-2');

-- Club 3
INSERT INTO player (id, name, number, age, position, nationality, club_id)
VALUES ('p-gk-3', 'Gardien 3', 1, 28, 'GOAL_KEEPER', 'Brésilien', 'club-3'),
       ('p-df-3', 'Défense 3', 2, 21, 'DEFENSE', 'Brésilien', 'club-3'),
       ('p-mf-3', 'Milieu 3', 5, 29, 'MIDFIELDER', 'Français', 'club-3'),
       ('p-st-3', 'Attaquant 3', 7, 23, 'STRIKER', 'Allemand', 'club-3');

-- 4. Insert Season 2024
INSERT INTO season (id, year, alias, status)
VALUES ('season-2024', 2024, '2024', 'NOT_STARTED');

-- 5. Insert Matches (with placeholder datetime)
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, season_year, status, home_score, away_score)
VALUES ('match-1', 'club-1', 'club-2', 'Stade 1', '2024-04-01 18:00', 2024, 'FINISHED', 4, 1),
       ('match-2', 'club-2', 'club-3', 'Stade 2', '2024-04-05 18:00', 2024, 'FINISHED', 0, 1),
       ('match-3', 'club-1', 'club-3', 'Stade 1', '2024-04-10 18:00', 2024, 'FINISHED', 1, 0),
       ('match-4', 'club-3', 'club-2', 'Stade 3', '2024-04-15 18:00', 2024, 'FINISHED', 0, 0),
       ('match-5', 'club-2', 'club-1', 'Stade 2', '2024-04-20 18:00', 2024, 'FINISHED', 1, 0),
       ('match-6', 'club-3', 'club-1', 'Stade 3', '2024-04-25 18:00', 2024, 'FINISHED', 3, 2);


INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal)
VALUES
-- match-1
('goal-1', 'match-1', 'club-1', 'p-st-1', 2, false),
('goal-2', 'match-1', 'club-1', 'p-st-1', 8, false),
('goal-3', 'match-1', 'club-1', 'p-mf-1', 50, false),
('goal-4', 'match-1', 'club-1', 'p-df-1', 60, false),
('goal-5', 'match-1', 'club-2', 'p-gk-1', 1, true),

-- match-2
('goal-6', 'match-2', 'club-2', 'p-st-2', 88, false),
('goal-7', 'match-2', 'club-3', 'p-st-3', 21, false),

-- match-3
('goal-8', 'match-3', 'club-1', 'p-st-1', 69, false),

-- match-5
('goal-9', 'match-5', 'club-2', 'p-st-2', 88, false),

-- match-6
('goal-10', 'match-6', 'club-1', 'p-gk-1', 88, true),
('goal-11', 'match-6', 'club-1', 'p-gk-1', 89, true),
('goal-12', 'match-6', 'club-1', 'p-gk-1', 90, true),
('goal-13', 'match-6', 'club-3', 'p-st-1', 56, false),
('goal-14', 'match-6', 'club-3', 'p-mf-1', 90, false);

INSERT INTO playing_time (id, value, time_unit)
VALUES ('pt-p-st-1-2024', 270, 'MINUTE'), -- 3 matches
       ('pt-p-mf-1-2024', 180, 'MINUTE'), -- 2 matches
       ('pt-p-df-1-2024', 90, 'MINUTE'),  -- 1 match
       ('pt-p-gk-1-2024', 180, 'MINUTE'), -- 2 matches
       ('pt-p-st-2-2024', 180, 'MINUTE'), -- 2 matches
       ('pt-p-st-3-2024', 90, 'MINUTE'); -- 1 match


INSERT INTO player_statistic (player_id, season_year, scored_goals, playing_time_id)
VALUES ('p-st-1', 2024, 4, 'pt-p-st-1-2024'),
       ('p-mf-1', 2024, 2, 'pt-p-mf-1-2024'),
       ('p-df-1', 2024, 1, 'pt-p-df-1-2024'),
       ('p-gk-1', 2024, 0, 'pt-p-gk-1-2024'), -- own goals don't count
       ('p-st-2', 2024, 2, 'pt-p-st-2-2024'),
       ('p-st-3', 2024, 1, 'pt-p-st-3-2024');


-- match-2
    ('goal-6', 'match-2', 'club-2', 'p-st-2', 88, false)
    ,
('goal-7', 'match-2', 'club-3', 'p-st-3', 21, false),

-- match-3
('goal-8', 'match-3', 'club-1', 'p-st-1', 69, false),

-- match-5
('goal-9', 'match-5', 'club-2', 'p-st-2', 88, false),

-- match-6
('goal-10', 'match-6', 'club-1', 'p-gk-1', 88, true),
('goal-11', 'match-6', 'club-1', 'p-gk-1', 89, true),
('goal-12', 'match-6', 'club-1', 'p-gk-1', 90, true),
('goal-13', 'match-6', 'club-3', 'p-st-1', 56, false),
('goal-14', 'match-6', 'club-3', 'p-mf-1', 90, false);

-- 7. Insert Club Statistics (2024 season)
INSERT INTO club_statistic (club_id, season_year, ranking_points, scored_goals, conceded_goals)
VALUES ('club-1', 2024, 6, 7, 2), -- or 5 depending on own goals counted
       ('club-2', 2024, 5, 2, 5), -- or 3 if own goals not counted
       ('club-3', 2024, 5, 1, 4); -- or 4 if own goals counted
