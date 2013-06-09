class AddPlanIdToSemesters < ActiveRecord::Migration
  def change
    add_column :semesters, :plan_id, :integer
  end
end
