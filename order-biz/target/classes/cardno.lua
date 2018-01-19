local no = redis.call("hincrby",KEYS[1],KEYS[2],1);
local time = redis.call("hget",KEYS[1],KEYS[3]);
if(time == false) then
  redis.call("hset",KEYS[1],KEYS[3],ARGV[1]);
elseif(time==ARGV[1]) then
	return no;
  else
	redis.call("hset",KEYS[1],KEYS[3],ARGV[1]);
	local random = tonumber(ARGV[2]);
	no = redis.call("hincrby",KEYS[1],KEYS[2],random);
end
return no;