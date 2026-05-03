import React, { useEffect, useState } from "react";
import { PostProps } from "../types/post/PostProps.ts";
import { Category } from "../types/Category";
import { CategoryService } from "../services/CategoryService";
import { createPost, updatePost } from "../services/PostService";
import { PostPayload } from "../types/post/PostPayload";
import "../styles/post/PostForm.css";

export default function PostForm({ editing, onClose, onSaved }: PostProps) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [categoryId, setCategoryId] = useState<number | null>(null);
  const [categories, setCategories] = useState<Category[]>([]);

  useEffect(() => {
    async function loadCategories() {
      const data = await CategoryService.getAll();
      setCategories(data);
    }
    loadCategories();

    if (editing) {
      setTitle(editing.title);
      setContent(editing.content);
      setCategoryId(editing.category.id);
    }
  }, [editing]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload: PostPayload = {
      title,
      content,
      categoryId: categoryId!,
    };

    if (editing) {
      await updatePost(editing.id, payload);
    } else {
      await createPost(payload);
    }
    onSaved();
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h2>{editing ? "Edit Post" : "Create Post"}</h2>

        <form onSubmit={handleSubmit}>
          <label>Title</label>
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            maxLength={25}
            required
          />
          <label>Content</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows={7}
            required
          />
          <label>Category</label>
          <select
            value={categoryId ?? ""}
            onChange={(e) => setCategoryId(Number(e.target.value))}
            required
          >
            <option value="">Select Category</option>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>
                {c.name}
              </option>
            ))}
          </select>

          <div className="modal-actions">
            <button type="submit">Save</button>
            <button type="button" className="cancel" onClick={onClose}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
