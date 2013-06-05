class Course < ActiveRecord::Base
  attr_accessible :code, :max_units, :name, :units, :title

  has_many :lessons
  has_many :semesters, :through => :lessons
end
