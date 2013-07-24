# Description

lambert_ruby is a wrapper to the lambert C library.

# Usage
```ruby
require 'lambert_ruby'

a = Lambert::LambertPoint.new(994272.661,13467.422)
a.wgs84(Lambert::LambertI)

puts "WGS84 Lat:#{a.y} Lon:#{a.x}"
```
#License
Copyright (c) 2013 Yannick Heinrich - Released under the GPLv2 License.