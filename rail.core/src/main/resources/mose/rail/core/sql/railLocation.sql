select t.*,
case when s.name is not null then 1 end  as station_level,
trim(substr(t.lat_lng,instr(t.lat_lng,',')+1)) as lng,
trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1)) as lat,
'{'
||'"bureauCode":'||t.bureau_code||','
||'"stationName":"'||t.name||'",'
||'"stationLevel":"'||s.name||'",'
||'"latLngString":"'||nvl(t.lat_lng,null)||'",'
||'"tencentText":"'||t.tencent_text||'"'
||'},'
from aa_station t
left join aa_station_level s on s.name = t.name
--where t.name like '%北京%'
where t.lat_lng is not null
and t.name not in (select f.fenjiekou from aa_fenjiekou f)
order by t.bureau_code, t.name;


select t.*,
trim(substr(t.lat_lng,instr(t.lat_lng,',')+1)) as lng,
trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1)) as lat
from aa_fenjiekou t
left join aa_bureau b on b.short_name = t.fenjiekou
where t.lat_lng is not null
order by b.code,t.fenjiekou