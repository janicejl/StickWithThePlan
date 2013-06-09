class Plan < ActiveRecord::Base
  attr_accessible :name, :semesters_attributes

  has_many :semesters, :dependent => :destroy

  accepts_nested_attributes_for :semesters, :reject_if => lambda { |a| a[:lesson_tokens].blank? }, :allow_destroy => true
end
