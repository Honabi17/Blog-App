import { useEffect, useState } from "react";
import { CategoryService } from "../services/CategoryService";
import CategoryForm from "../components/CategoryForm";
import { Category } from "../types/Category";

export default function CategoryPage() {
  const [categories, setCategories] = useState<Category[]>([]);
  const [openForm, setOpenForm] = useState(false);
  const [editing, setEditing] = useState<Category | null>(null);

  const load = async () => {
    const data = await CategoryService.getAll();
    setCategories(data);
  };

  useEffect(() => {
    load();
  }, []);

  const handleEdit = (category: Category) => {
    setEditing(category);
    setOpenForm(true);
  };

  const handleDelete = async (id: number) => {
    await CategoryService.delete(id);
    load();
  };
  console.log("CATEGORY PAGE ABRIU");

  return (
    <div className="categories-container">
      <div className="categories-header">
        <h1>Categories</h1>
        <button
          onClick={() => {
            (setEditing(null), setOpenForm(true));
          }}
        >
          Add
        </button>
      </div>
      <div className="categories-grid">
        {categories.map((cat) => (
          <div key={cat.id} className="category-card">
            <h3>{cat.name}</h3>
            <p>{cat.description}</p>

            <div className="category-actions">
              <button onClick={() => handleEdit(cat)}>Edit</button>
              <button onClick={() => handleDelete(cat.id)}>Delete</button>
            </div>
          </div>
        ))}
      </div>

      {openForm && (
        <CategoryForm
          onClose={() => setOpenForm(false)}
          onSaved={() => {
            (setOpenForm(false), load());
          }}
          editing={editing}
        />
      )}
    </div>
  );
}
