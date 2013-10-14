Gem::Specification.new do |s|
  s.name        = 'lambert_ruby'
  s.version     = '1.0.1'
  s.date        = '2013-10-14'
  s.summary     = "Ruby wrapper for the lambert library"
  s.description = "Ruby wrapper for the lambert library"
  s.authors     = ["Yannick Heinrich"]
  s.email       = 'yannick.heinrich@gmail.com'
  s.files = Dir.glob('lib/**/*.rb') +
            Dir.glob('ext/**/*.{c,h,rb}')
  s.extensions = ['ext/lambert_ruby/extconf.rb']
  s.homepage    ='http://rubygems.org/gems/lambert_ruby'
end
