INSERT INTO club (id, name, acronym, year_creation, stadium) VALUES
            ('laliga-1', 'FC Barcelona', 'FCB', 1899, 'Spotify Camp Nou'),
            ('laliga-2', 'Real Madrid CF', 'RMA', 1902, 'Santiago Bernabéu'),
            ('laliga-3', 'Atlético de Madrid', 'ATM', 1903, 'Cívitas Metropolitano'),
            ('laliga-4', 'Sevilla FC', 'SEV', 1890, 'Ramón Sánchez-Pizjuán'),
            ('laliga-5', 'Real Sociedad', 'RSO', 1909, 'Reale Arena');
INSERT INTO player (id, name, number, position, nationality, age, club_id) VALUES
                ('1', 'Pedri', 8, 'MIDFIELDER', 'Spain', 21, 'laliga-1'),
                ('2', 'Vinícius Jr', 7, 'FORWARD', 'Brazil', 24, 'laliga-2'),
                ('3', 'Antoine Griezmann', 7, 'FORWARD', 'France', 33, 'laliga-3'),
                ('4', 'Ivan Rakitić', 10, 'MIDFIELDER', 'Croatia', 36, 'laliga-4'),
                ('5', 'Mikel Oyarzabal', 10, 'FORWARD', 'Spain', 27, 'laliga-5');


INSERT INTO season (id, year, alias, status) VALUES
                ('1', 2021, 'La Liga 2021/2022', 'COMPLETED'),
                ('2', 2022, 'La Liga 2022/2023', 'COMPLETED'),
                ('3', 2023, 'La Liga 2023/2024', 'ONGOING'),
                ('4', 2024, 'La Liga 2024/2025', 'NOT_STARTED'),
                ('5', 2025, 'La Liga 2025/2026', 'NOT_STARTED');


INSERT INTO match (id, season_year, club_home_id, club_away_id, stadium, match_datetime, status, home_score, away_score) VALUES
        ('1', 2023, '1', '2', 'Spotify Camp Nou', '2024-03-10 20:00:00', 'COMPLETED', 2, 1),
        ('2', 2023, '3', '4', 'Cívitas Metropolitano', '2024-03-11 21:00:00', 'COMPLETED', 1, 1),
        ('3', 2023, '5', '1', 'Reale Arena', '2024-03-12 19:00:00', 'COMPLETED', 0, 3),
        ('4', 2024, '2', '3', 'Santiago Bernabéu', '2025-08-18 21:00:00', 'NOT_STARTED', 0, 0),
        ('5', 2024, '4', '5', 'Ramón Sánchez-Pizjuán', '2025-08-20 19:30:00', 'NOT_STARTED', 0, 0);


INSERT INTO coach (id, name, nationality, age, club_id) VALUES
            (1, 'Xavi Hernández', 'Spain', 44, 'laliga-1'),
            (2, 'Carlo Ancelotti', 'Italy', 65, 'laliga-2'),
            (3, 'Diego Simeone', 'Argentina', 54, 'laliga-3'),
            (4, 'Quique Sánchez Flores', 'Spain', 59, 'laliga-4'),
            (5, 'Imanol Alguacil', 'Spain', 53, 'laliga-5');
