import { useNavigate } from "react-router-dom";

export default function CategoriesOverviewCard({ count = 0 }) {
  const navigate = useNavigate();

  return (
    <div className="card">
      <div className="card-icon">📂</div>

      <div className="card-content">
        <h3>Categories</h3>
        <p>{count} total categories</p>
      </div>

      <div className="card-footer">
        <button onClick={() => navigate("/categories")}>View Categories</button>
      </div>
    </div>
  );
}
