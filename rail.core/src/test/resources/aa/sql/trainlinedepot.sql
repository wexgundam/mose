select
t.*,
trim(substr(t.lat_lng,instr(t.lat_lng,',')+1)) as lng,
trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1)) as lat
from aa_trainoperationdepot t
where t.lat_lng is not null
order by t.bureau_code,t.id;