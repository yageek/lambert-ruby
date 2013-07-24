require 'test/unit'
require 'lambert_ruby'

class HolaTest < Test::Unit::TestCase
  def test_english_hello
    assert_equal "hello world",
      Lambert.hi
  end
end