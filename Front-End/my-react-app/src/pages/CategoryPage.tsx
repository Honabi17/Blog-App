import { useEffect, useState } from "react";
import { CategoryService } from "../services/CategoryService";
import CategoryForm from "../components/CategoryForm";
import { Category } from "../types/Category";
import "../styles/categories/Category.css";
import { useNavigate } from "react-router-dom";

export default function CategoryPage() {

  const [categories, setCategories] = useState<Category[]>([]);
  const [search, setSearch] = useState("");

  const [openForm, setOpenForm] = useState(false);
  const [editing, setEditing] = useState<Category | null>(null);

  const navigate = useNavigate();

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
  
  const filteredCategories = categories.filter(
      (cat) => cat.name.toLowerCase().includes(search.toLowerCase())
    );

  return (
    <div className="page-container">
      <div className="categories-container">

        <div className="categories-header">
          <h1>Categories</h1>

          <button
            onClick={() => {
              setEditing(null), 
              setOpenForm(true)
            }}
          >
            Add ➕
          </button>
        </div>

        <div className="search-bar">
          <input
            type="text"
            placeholder="Search categories"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />

          {search.length > 0 &&(
            <button
              className="clear-search"
              onClick={() => setSearch("")}
            >
              ✖️
            </button>
          )}
        </div>

        <div className="categories-grid">
          {filteredCategories.map((cat) => (
            <div key={cat.id} className="category-card">
              <div>
                <h3
                  className="clickable"
                  onClick={() => navigate(`/posts?categoryId=${cat.id}`)}
                >
                  {cat.name}
                </h3>

                <p>{cat.description}</p>

                <p className="category-meta">
                  Created by {cat.createdBy.username},{" "}
                  {new Date(cat.createdAt + "Z").toLocaleDateString()}
                </p>
              </div>

              <div className="category-actions">
                <button onClick={() => handleEdit(cat)}>Edit 🖊️</button>
                <button onClick={() => handleDelete(cat.id)}>Delete ✖️</button>
              </div>
            </div>
          ))}
        </div>

        {openForm && (
          <CategoryForm
            onClose={() => setOpenForm(false)}
            onSaved={() => {
              setOpenForm(false), 
              load();
            }}
            editing={editing}
          />
        )}
      </div>
    </div>
  );
}
