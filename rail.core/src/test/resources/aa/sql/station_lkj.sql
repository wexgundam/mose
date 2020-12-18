select t.*,
trim(substr(t.lat_lng,instr(t.lat_lng,',')+1)) as lng,
trim(substr(t.lat_lng,1,instr(t.lat_lng,',')-1)) as lat,
'{'
||'"bureauId":'||t.bureau_code||','
||'"stationName":"'||t.name||'",'
||'"latLngString":"'||nvl(t.lat_lng,null)||'",'
||'"tencentText":"'||t.tencent_text||'",'
||'"lkjStationName":"'||t.lkj_station_name||'"'
||'},'
from aa_station_lkj t
order by t.bureau_code,t.name;