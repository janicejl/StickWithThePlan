class Lesson < ActiveRecord::Base
  attr_accessible :course_id, :semester_id

  belongs_to :course
  belongs_to :semester
end
