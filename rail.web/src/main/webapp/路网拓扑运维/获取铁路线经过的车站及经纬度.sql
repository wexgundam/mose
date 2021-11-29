select
r.station_name as r_s_name,trim(r.railwayline_direction) as r_d,r.station_center_mileage as r_sck,
--row_number() over (order by r.station_center_mileage),
s.name,s.alias,s.epsg4326,
r.rowid,
s.id,
r.railwayline_name as r_n,r.railwayline_code as r_c
from aa_lkj r
left outer join t_node s on ( s.bureau_id= r.bureau_code and r.station_name in
  (select regexp_substr(s.alias,'[^,]+',1,level)
  from dual
  CONNECT BY 
  LEVEL <= LENGTH(s.alias) - LENGTH(REGEXP_REPLACE(s.alias, ',', '')) + 1))
where trim(r.railwayline_name)='ÐÂ¾®'
--and (trim(r.railwayline_direction)='Ë«' or trim(r.railwayline_direction)='ÏÂ')
order by r.station_center_mileage;