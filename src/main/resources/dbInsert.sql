-- Club inserts (corrected)
INSERT INTO club (id, name, acronym, year_creation, stadium)
VALUES ('C01', 'FC Barcelona', 'FCB', 1899, 'Spotify Camp Nou'),
       ('C02', 'Real Madrid CF', 'RMA', 1902, 'Santiago Bernabéu'),
       ('C03', 'Atlético de Madrid', 'ATM', 1903, 'Cívitas Metropolitano'),
       ('C04', 'Sevilla FC', 'SEV', 1890, 'Ramón Sánchez-Pizjuán'),
       ('C05', 'Real Sociedad', 'RSO', 1909, 'Reale Arena');

-- Player inserts (corrected club_id references)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P01', 'Pedri', 8, 'MIDFIELDER', 'Spain', 21, 'C01'),           -- Changed to C01 (Barcelona)
       ('P02', 'Vinícius Jr', 7, 'STRIKER', 'Brazil', 24, 'C02'),       -- Changed to C02 (Madrid)
       ('P03', 'Antoine Griezmann', 7, 'STRIKER', 'France', 33, 'C03'), -- Changed to C03 (Atlético)
       ('P04', 'Ivan Rakitić', 10, 'MIDFIELDER', 'Croatia', 36, 'C04'), -- Changed to C04 (Sevilla)
       ('P05', 'Mikel Oyarzabal', 10, 'GOAL_KEEPER', 'Spain', 27, 'C05');
-- Changed to C05 (Sociedad)

-- Season inserts (corrected - no issues found)
INSERT INTO season (id, year, alias, status)
VALUES ('S01', 2021, 'La Liga 2021/2022', 'FINISHED'),
       ('S02', 2022, 'La Liga 2022/2023', 'FINISHED'),
       ('S03', 2023, 'La Liga 2023/2024', 'STARTED'),
       ('S04', 2024, 'La Liga 2024/2025', 'NOT_STARTED'),
       ('S05', 2025, 'La Liga 2025/2026', 'NOT_STARTED');


INSERT INTO match (id, season_year, club_home_id, club_away_id, stadium, match_datetime, status, home_score, away_score)
VALUES ('M001', 2023, '1', '2', 'Spotify Camp Nou', '2024-03-10 20:00:00', 'COMPLETED', 2, 1),
       ('M002', 2023, '3', '4', 'Cívitas Metropolitano', '2024-03-11 21:00:00', 'COMPLETED', 1, 1),
       ('M003', 2023, '5', '1', 'Reale Arena', '2024-03-12 19:00:00', 'COMPLETED', 0, 3),
       ('M004', 2024, '2', '3', 'Santiago Bernabéu', '2025-08-18 21:00:00', 'NOT_STARTED', 0, 0),
       ('M005', 2024, '4', '5', 'Ramón Sánchez-Pizjuán', '2025-08-20 19:30:00', 'NOT_STARTED', 0, 0);


INSERT INTO coach (id, name, nationality)
VALUES (1, 'Xavi Hernández', 'Spain'),
       (2, 'Carlo Ancelotti', 'Italy'),
       (3, 'Diego Simeone', 'Argentina'),
       (4, 'Quique Sánchez Flores', 'Spain'),
       (5, 'Imanol Alguacil', 'Spain');

-- Then update each club with their coach
UPDATE club SET coach_id = '1' WHERE id = 'C05';  -- Barcelona → Xavi
UPDATE club SET coach_id = '2' WHERE id = 'C04';  -- Madrid → Ancelotti
UPDATE club SET coach_id = '3' WHERE id = 'C03';  -- Atlético → Simeone
UPDATE club SET coach_id = '4' WHERE id = 'C02';  -- Sevilla → Flores
UPDATE club SET coach_id = '5' WHERE id = 'C01';  -- Sociedad → Alguacil
