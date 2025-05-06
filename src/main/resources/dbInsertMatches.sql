-- Match 1: FC Barcelona (3) vs Real Madrid (2) - El Clásico
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score)
VALUES ('M2021-001', 'C01', 'C02', 'Spotify Camp Nou', '2021-04-10 20:45:00', 'FINISHED', 2021, 3, 2);

INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty)
VALUES ('MS001', 'M2021-001', 'C01', 'P05', 29, false, false), -- Lewandowski
       ('MS002', 'M2021-001', 'C02', 'P02', 35, false, false), -- Vinícius Jr
       ('MS003', 'M2021-001', 'C01', 'P01', 52, false, false), -- Pedri
       ('MS004', 'M2021-001', 'C02', 'P10', 68, false, false), -- Bellingham
       ('MS005', 'M2021-001', 'C01', 'P05', 82, false, false);
-- Lewandowski (brace)

-- Match 2: Atlético Madrid (1) vs Sevilla (1)
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score)
VALUES ('M2021-002', 'C03', 'C04', 'Cívitas Metropolitano', '2021-05-12 19:30:00', 'FINISHED', 2021, 1, 1);

INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty)
VALUES ('MS006', 'M2021-002', 'C03', 'P03', 41, false, false), -- Griezmann
       ('MS007', 'M2021-002', 'C04', 'P21', 76, false, false);
-- En-Nesyri

-- Match 3: Real Sociedad (2) vs FC Barcelona (2)
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score)
VALUES ('M2021-003', 'C05', 'C01', 'Reale Arena', '2021-08-21 18:30:00', 'FINISHED', 2021, 2, 2);

INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty)
VALUES ('MS008', 'M2021-003', 'C05', 'P22', 12, false, false), -- Mbappé
       ('MS009', 'M2021-003', 'C01', 'P05', 24, false, false), -- Lewandowski
       ('MS010', 'M2021-003', 'C05', 'P25', 58, false, false), -- Verratti
       ('MS011', 'M2021-003', 'C01', 'P07', 89, false, false);
-- Gavi

-- Match 4: Real Madrid (3) vs Atlético Madrid (0) - Madrid Derby
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score)
VALUES ('M2021-004', 'C02', 'C03', 'Santiago Bernabéu', '2021-09-18 21:00:00', 'FINISHED', 2021, 3, 0);

INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty)
VALUES ('MS012', 'M2021-004', 'C02', 'P02', 18, false, false), -- Vinícius Jr
       ('MS013', 'M2021-004', 'C02', 'P10', 36, false, false), -- Bellingham
       ('MS014', 'M2021-004', 'C02', 'P13', 72, false, false);
-- Rodrygo

-- Match 5: Sevilla (1) vs Real Sociedad (0)
INSERT INTO match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score)
VALUES ('M2021-005', 'C04', 'C05', 'Ramón Sánchez-Pizjuán', '2021-11-28 17:30:00', 'FINISHED', 2021, 1, 0);

INSERT INTO match_scorers (id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty)
VALUES ('MS015', 'M2021-005', 'C04', 'P21', 64, false, false); -- En-Nesyri