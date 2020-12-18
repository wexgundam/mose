select t.railwayline_name,t.bureau_code, t.bureau_name,t.station_name,t.station_center_mileage,
a.lat_lng,
'{'
||'"railwaylineName":"'||t.railwayline_name||'",'
||'"bureauId":'||t.bureau_code||','
||'"stationName":"'||t.station_name||'",'
||'"stationLatLng":"'||nvl(a.lat_lng,null)||'"'
||'},'
from aa_lkj t
left outer join aa_station_lkj a on t.station_name = a.lkj_station_name
where
(select count(*) from aa_lkj a where a.railwayline_name = t.railwayline_name) > 1
and a.lat_lng is not null
order by t.railwayline_name,t.station_center_mileage;