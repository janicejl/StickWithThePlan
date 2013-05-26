class CreateCourses < ActiveRecord::Migration
  def change
    create_table :courses do |t|
      t.string :code
      t.string :name
      t.decimal :units
      t.decimal :max_units

      t.timestamps
    end
  end
end
