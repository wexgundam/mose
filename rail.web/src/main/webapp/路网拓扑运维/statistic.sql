select t.railway_line_id r,count(t.railway_line_id)
from t_interval_line t 
group by t.railway_line_id
having t.railway_line_id > 8800
order by r;