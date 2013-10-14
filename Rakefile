require 'rake/testtask'
require 'rake/extensiontask'

Rake::ExtensionTask.new('lambert_ruby')

Rake::TestTask.new do |t|
  t.libs << 'test'
end

desc "Run tests"
task :default => :test
