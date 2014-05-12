spec = Gem::Specification.load('lambert_ruby.gemspec')

if RUBY_PLATFORM =~ /java/
  require 'rake/javaextensiontask'
  Rake::JavaExtensionTask.new do |ext|
    ext.name = 'lambert_ruby'                # indicate the name of the extension.
    ext.tmp_dir = 'tmp'                     # temporary folder used during compilation.
    ext.source_pattern = "[lambert-java/src/main/**/*.java]"        # monitor file changes to allow simple rebuild.
  end
else
  require 'rake/extensiontask'
  Rake::ExtensionTask.new('lambert_ruby', spec)
end
