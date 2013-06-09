class Semester < ActiveRecord::Base
  attr_accessible :maximum, :term, :units, :year, :lesson_tokens

  has_many :lessons
  has_many :courses, :through => :lessons

  belongs_to :plan

  attr_reader :lesson_tokens

  def lesson_tokens=(ids) 
  	self.course_ids = ids.split(",")
  end
end
