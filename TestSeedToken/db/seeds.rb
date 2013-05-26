# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

Course.delete_all

#CSCI
file = File.open("#{Rails.root}/db/seeddata/CSCI.txt");
file.read.each_line do |line|
	code, name, units, max_units = line.chomp.split("|")
	#puts code
	#puts name
	#puts units
	#puts max_units

	Course.create!(:code => code, :name => name, :units => units, :max_units => max_units)
end

#ACCT
file = File.open("#{Rails.root}/db/seeddata/ACCT.txt")
file.read.each_line do |line|
	code, name, units, max_units = line.chomp.split("|")
	Course.create!(:code => code, :name => name, :units => units, :max_units => max_units)
end

#CTPR
#file = File.open("#{Rails.root}/db/seeddata/CTPR.txt")
#file.read.each_line do |line|
#	code, name, units, max_units = line.chomp.split("|")
#	Course.create!(:code => code, :name => name, :units => units, :max_units => max_units)
#end

#MATH
file = File.open("#{Rails.root}/db/seeddata/MATH.txt")
file.read.each_line do |line|
	code, name, units, max_units = line.chomp.split("|")
	Course.create!(:code => code, :name => name, :units => units, :max_units => max_units)
end