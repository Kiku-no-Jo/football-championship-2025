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

-- Additional players for Club C01 (e.g., FC Barcelona)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P05', 'Robert Lewandowski', 9, 'STRIKER', 'Poland', 35, 'C01'),
       ('P07', 'Gavi', 6, 'MIDFIELDER', 'Spain', 19, 'C01'),
       ('P08', 'Ronald Araújo', 4, 'DEFENSE', 'Uruguay', 24, 'C01'),
       ('P09', 'Marc-André ter Stegen', 1, 'GOAL_KEEPER', 'Germany', 31, 'C01');

-- Additional players for Club C02 (e.g., Real Madrid)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P10', 'Jude Bellingham', 5, 'MIDFIELDER', 'England', 20, 'C02'),
       ('P11', 'Thibaut Courtois', 1, 'GOAL_KEEPER', 'Belgium', 31, 'C02'),
       ('P12', 'David Alaba', 4, 'DEFENSE', 'Austria', 31, 'C02'),
       ('P13', 'Rodrygo', 11, 'STRIKER', 'Brazil', 22, 'C02');

-- Additional players for Club C03 (e.g., Atlético Madrid)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P14', 'Jan Oblak', 13, 'GOAL_KEEPER', 'Slovenia', 30, 'C03'),
       ('P15', 'José Giménez', 2, 'DEFENSE', 'Uruguay', 28, 'C03'),
       ('P16', 'Ángel Correa', 10, 'STRIKER', 'Argentina', 28, 'C03'),
       ('P17', 'Koke', 6, 'MIDFIELDER', 'Spain', 31, 'C03');

-- Additional players for Club C04 (e.g., Sevilla)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P18', 'Yassine Bounou', 13, 'GOAL_KEEPER', 'Morocco', 32, 'C04'),
       ('P19', 'Jesús Navas', 16, 'DEFENSE', 'Spain', 37, 'C04'),
       ('P20', 'Ivan Rakitić', 11, 'MIDFIELDER', 'Croatia', 35, 'C04'),
       ('P21', 'Youssef En-Nesyri', 15, 'STRIKER', 'Morocco', 26, 'C04');

-- Additional players for Club C05 (e.g., PSG)
INSERT INTO player (id, name, number, position, nationality, age, club_id)
VALUES ('P22', 'Kylian Mbappé', 7, 'STRIKER', 'France', 24, 'C05'),
       ('P23', 'Achraf Hakimi', 2, 'DEFENSE', 'Morocco', 24, 'C05'),
       ('P24', 'Marquinhos', 5, 'DEFENSE', 'Brazil', 29, 'C05'),
       ('P25', 'Marco Verratti', 6, 'MIDFIELDER', 'Italy', 30, 'C05');

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
UPDATE club
SET coach_id = '1'
WHERE id = 'C05'; -- Barcelona → Xavi
UPDATE club
SET coach_id = '2'
WHERE id = 'C04'; -- Madrid → Ancelotti
UPDATE club
SET coach_id = '3'
WHERE id = 'C03'; -- Atlético → Simeone
UPDATE club
SET coach_id = '4'
WHERE id = 'C02'; -- Sevilla → Flores
UPDATE club
SET coach_id = '5'
WHERE id = 'C01'; -- Sociedad → Alguacil
