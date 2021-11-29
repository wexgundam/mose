select t.id,
t.direction,
t.node_a_id,
(select n.name from T_node n where n.id=t.node_a_id) as nan,
t.node_b_id,
(select n.name from T_node n where n.id=t.node_b_id) as nbn,
t.node_a_mileage,t.node_b_mileage,
t.geo_spatial,
t.railway_line_id,
t.rowid
from t_interval_line t
where t.railway_line_id = 2
order by t.node_a_mileage,t.direction,t.node_a_id