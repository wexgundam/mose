select t.bureau_name,t.bureau_code,t.railwayline_name,t.railwayline_code,t.station_name,t.station_center_mileage,t.railwayline_direction,t.rowid from aa_lkj t
where t.railwayline_name='ÐÂ¾®'
order by t.railwayline_code,t.railwayline_name,t.station_center_mileage;