-- Club statistics for 2021 season
INSERT INTO club_statistic (club_id, season_year, ranking_points, scored_goals, conceded_goals, clean_sheet_number) VALUES
-- FC Barcelona (C01)
-- Played 2 matches:
--   M2021-001: 3-2 win (3 points)
--   M2021-003: 2-2 draw (1 point)
('C01', 2021, 4, 5, 4, 0),

-- Real Madrid CF (C02)
-- Played 2 matches:
--   M2021-001: 2-3 loss (0 points)
--   M2021-004: 3-0 win (3 points + clean sheet)
('C02', 2021, 3, 5, 3, 1),

-- Atlético de Madrid (C03)
-- Played 2 matches:
--   M2021-002: 1-1 draw (1 point)
--   M2021-004: 0-3 loss (0 points)
('C03', 2021, 1, 1, 4, 0),

-- Sevilla FC (C04)
-- Played 2 matches:
--   M2021-002: 1-1 draw (1 point)
--   M2021-005: 1-0 win (3 points + clean sheet)
('C04', 2021, 4, 2, 1, 1),

-- Real Sociedad (C05)
-- Played 2 matches:
--   M2021-003: 2-2 draw (1 point)
--   M2021-005: 0-1 loss (0 points)
('C05', 2021, 1, 2, 3, 0);