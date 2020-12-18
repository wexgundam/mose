select t.*,
rownum as id,
trim(substr(t.lat_lng,instr(t.lat_lng,',')+1)) as lng,
trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1)) as lat,
'{'
||'"bureauId":'||t.bureau_code||','
||'"latLngIndex":'||t.lat_lng_index||','
||'"id":'||rownum||','
||'"lng":'||trim(substr(t.lat_lng,instr(t.lat_lng,',')+1))||','
||'"lat":'||trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1))
||'},'
from aa_bureau_polygon t
order by t.bureau_code, t.lat_lng_index;
