if RUBY_PLATFORM =~/java/
  require 'java'
  require_relative 'lambert_ruby.jar'
  com.yageek.lambertruby.LambertRubyService.new.basicLoad(JRuby.runtime)
else
  require 'lambert_ruby/lambert_ruby'
end
