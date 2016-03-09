Gem::Specification.new do |s|
  s.name        = 'lambert_ruby'
  s.version     = '1.0.2'
  s.date        = '2014-01-14'
  s.summary     = "Ruby wrapper for the lambert library"
  s.description = "Ruby wrapper for the lambert library"
  s.authors     = ["Yannick Heinrich"]
  s.email       = 'yannick.heinrich@gmail.com'

  files = Dir.glob('lib/**/*.rb') +
            Dir.glob('ext/**/*.{c,h,rb,java}')

  if RUBY_PLATFORM =~/java/
        s.platform = "java"
        files << "lib/lambert_ruby.jar"
  else
         s.extensions = ['ext/lambert_ruby/extconf.rb']
  end
  s.files = files
  s.add_development_dependency "rake-compiler"
  
  s.homepage    ='http://rubygems.org/gems/lambert_ruby'
end
