select
t.*,
'{'
||'"bureauId":'||t.bureau_code||','
||'"ddtId":"'||t.ddt_id||'",'
||'"ddtName":"'||t.ddt_name||'",'
||'"lng":'||trim(substr(t.lat_lng,instr(t.lat_lng,',')+1))||','
||'"lat":'||trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1))
||'},'
from aa_trainlinedepot t
where t.lat_lng is not null
order by t.bureau_code,t.ddt_id;