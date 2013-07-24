require 'test/unit'
require 'lambert_ruby'

class Initialisationtest < Test::Unit::TestCase
  def test_init
    a = Lambert::LambertPoint.new(0.0,0.0)
    puts "WGS84 Lat:#{a.y} Lon:#{a.x}"
    assert_equal a.x ,0.0
    assert_equal a.y ,0.0
    assert_equal a.z ,0.0
  end
end