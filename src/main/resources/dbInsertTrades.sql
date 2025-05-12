-- 1. Lewandowski from Bayern to Barcelona (summer 2022)
INSERT INTO trade_history (id, player_id, from_club_id, to_club_id, transfer_date)
VALUES ('TH001', 'P05', NULL, 'C01', '2022-07-19 12:00:00');

-- 2. Gavi promoted from Barcelona B team (no from_club)
INSERT INTO trade_history (id, player_id, from_club_id, to_club_id, transfer_date)
VALUES ('TH002', 'P07', NULL, 'C01', '2021-07-01 10:00:00');

-- 3. Vinícius Jr from Flamengo to Real Madrid (2018)
INSERT INTO trade_history (id, player_id, from_club_id, to_club_id, transfer_date)
VALUES ('TH003', 'P02', NULL, 'C02', '2018-07-23 15:30:00');

-- 4. Griezmann from Barcelona back to Atletico (loan return)
INSERT INTO trade_history (id, player_id, from_club_id, to_club_id, transfer_date)
VALUES ('TH004', 'P03', 'C01', 'C03', '2022-09-01 09:45:00');

-- 5. Mbappé from PSG to Real Sociedad (hypothetical)
INSERT INTO trade_history (id, player_id, from_club_id, to_club_id, transfer_date)
VALUES ('TH005', 'P22', NULL, 'C05', '2023-08-15 11:20:00');