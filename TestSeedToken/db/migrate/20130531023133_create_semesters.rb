class CreateSemesters < ActiveRecord::Migration
  def change
    create_table :semesters do |t|
      t.integer :year
      t.string :term
      t.decimal :units
      t.decimal :maximum

      t.timestamps
    end
  end
end
