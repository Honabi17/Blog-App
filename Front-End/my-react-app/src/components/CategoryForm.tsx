import { useEffect, useState } from "react";
import { CategoryFormProps } from "../types/CategoryFormProps";
import { CategoryService } from "../services/CategoryService";
import { CreateCategoryDTO, UpdatedCategoryDTO } from "../types/Category";

export default function CategoryForm({
  onClose,
  onSaved,
  editing,
}: CategoryFormProps) {
  const [name, setName] = useState(editing?.name || "");
  const [description, setDescription] = useState(editing?.description || "");

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
    <div className="category-modal-overlay" onClick={onClose}>
      <div className="category-modal" onClick={(e) => e.stopPropagation()}>
        <h2>{editing ? "Edit" : "Create"}</h2>

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
            maxLength={50}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />

          <button type="submit">{editing ? "Save" : "Create"}</button>
        </form>

        <button className="close-btn" onClick={onClose}>
          Close
        </button>
      </div>
    </div>
  );
}
