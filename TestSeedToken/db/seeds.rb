# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

#Course.delete_all

Dir.foreach("#{Rails.root}/db/seeddata") do |department|
	next if department == '.' or department == '..'

	file = File.open("#{Rails.root}/db/seeddata/" + department);
	file.read.each_line do |line|
		code, name, units, max_units = line.chomp.split("|")
		#puts code
		#puts name
		#puts units
		#puts max_units

		Course.find_or_create_by_code!(:code => code, :name => name, :units => units, :max_units => max_units)
	end
end


puts "Successfully Seeded Courses. "