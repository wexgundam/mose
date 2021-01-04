select t.*,
'{'
||'"bureauId":'||t.bureau_code||','
||'"stationName":"'||t.name||'",'
||'"latLngString":"'||nvl(t.lat_lng,null)||'",'
||'"tencentText":"'||t.tencent_text||'"'
||'},'
from aa_station t
--where t.name like '%北京%'
order by t.bureau_code,t.name