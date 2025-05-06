-- Insert playing_time records first (since player_statistic references them)
INSERT INTO playing_time (id, value, time_unit) VALUES
-- Players with 3 goals (P05)
('PT001', 270.00, 'MINUTE'),
-- Players with 2 goals (P02, P10, P21)
('PT002', 180.00, 'MINUTE'),
('PT003', 180.00, 'MINUTE'),
('PT004', 180.00, 'MINUTE'),
-- Players with 1 goal (P01, P03, P22, P25, P07, P13)
('PT005', 90.00, 'MINUTE'),
('PT006', 90.00, 'MINUTE'),
('PT007', 90.00, 'MINUTE'),
('PT008', 90.00, 'MINUTE'),
('PT009', 90.00, 'MINUTE'),
('PT010', 90.00, 'MINUTE');

-- Insert player_statistic records
INSERT INTO player_statistic (player_id, season_year, scored_goals, playing_time_id) VALUES
-- Player P05 scored 3 goals
('P05', 2021, 3, 'PT001'),
-- Players who scored 2 goals
('P02', 2021, 2, 'PT002'),
('P10', 2021, 2, 'PT003'),
('P21', 2021, 2, 'PT004'),
-- Players who scored 1 goal
('P01', 2021, 1, 'PT005'),
('P03', 2021, 1, 'PT006'),
('P22', 2021, 1, 'PT007'),
('P25', 2021, 1, 'PT008'),
('P07', 2021, 1, 'PT009'),
('P13', 2021, 1, 'PT010');