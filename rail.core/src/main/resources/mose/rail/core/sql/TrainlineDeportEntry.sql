select
t.*,
a.lat_lng,
'{'
||'"bureauCode":'||t.bureau_code||','
||'"ddtId":"'||t.ddt_id||'",'
||'"ddtName":"'||t.ddt_name||'",'
||'"queueIndex":"'||t.queue_index||'",'
||'"entryIndex":"'||t.entry_index||'",'
||'"entryText":"'||t.entry_text||'",'
||'"lng":'||trim(substr(a.lat_lng,instr(a.lat_lng,',')+1))||','
||'"lat":'||trim(substr(a.lat_lng,1,instr(a.lat_lng,',')-1))
||'},'
from aa_trainlinedepot_entry t
left outer join aa_station a on a.bureau_code=t.bureau_code and a.name = t.entry_text
where a.lat_lng is not null
order by t.bureau_code,t.ddt_id,t.queue_index,t.entry_index;