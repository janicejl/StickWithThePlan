class Course < ActiveRecord::Base
  attr_accessible :code, :max_units, :name, :units
end
