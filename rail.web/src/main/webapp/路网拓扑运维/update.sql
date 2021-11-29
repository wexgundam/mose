select t.bureau_id as b,r.station_center_mileage as scm,t.name,t.alias,r.station_name,t.epsg4326,t.bureau_id,t.rowid
from t_node t
left outer join aa_lkj r on t.bureau_id = r.bureau_code and instr(t.alias, r.station_name)>0
where r.railwayline_name='ÐÂ¾®'
order by r.station_center_mileage;