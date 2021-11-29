select t.id,t.name,t.code, t.alia from t_railway_line t
--where t.code>8600
where t.name='ÄÏ±¤'
order by t.code;