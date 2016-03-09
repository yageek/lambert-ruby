spec = Gem::Specification.load('lambert_ruby.gemspec')

if RUBY_PLATFORM =~ /java/
  require 'rake/javaextensiontask'
  Rake::JavaExtensionTask.new('lambert_ruby', spec)
else
  require 'rake/extensiontask'
  Rake::ExtensionTask.new('lambert_ruby', spec)
end
