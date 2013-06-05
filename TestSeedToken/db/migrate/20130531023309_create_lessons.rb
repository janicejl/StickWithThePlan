class CreateLessons < ActiveRecord::Migration
  def change
    create_table :lessons do |t|
      t.integer :course_id, :null => false
      t.integer :semester_id, :null => false

      t.timestamps
    end

    add_index :lessons, :course_id
    add_index :lessons, :semester_id
  end
end
