import { useEffect, useState } from "react";
import { CategoryFormProps } from "../types/CategoryFormProps";
import { CategoryService } from "../services/CategoryService";
import { CreateCategoryDTO, UpdatedCategoryDTO } from "../types/Category";
import "../styles/categories/CategoryForm.css";

export default function CategoryForm({
  editing,
  onClose,
  onSaved,
}: CategoryFormProps) {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  useEffect(() => {
    if (editing) {
      setName(editing.name);
      setDescription(editing.description);
    } else {
      setName("");
      setDescription("");
    }
  }, [editing]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (editing) {
      const dto: UpdatedCategoryDTO = { name, description };
      await CategoryService.update(editing.id, dto);
    } else {
      const dto: CreateCategoryDTO = { name, description };
      await CategoryService.create(dto);
    }

    onSaved();
  };

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <h2>{editing ? "Edit Category" : "Create Category"}</h2>

        <form onSubmit={handleSubmit}>
          <label>Name</label>
          <input
            maxLength={20}
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />

          <label>Description</label>
          <input
            maxLength={60}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />

          <div className="modal-actions">
            <button type="submit">{editing ? "Save" : "Create"}</button>
            <button className="close-btn" onClick={onClose}>
              Close
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
