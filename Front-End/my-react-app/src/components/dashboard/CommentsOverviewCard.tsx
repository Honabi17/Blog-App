import { useNavigate } from "react-router-dom";

export default function CommentsOverviewCard({ count = 0 }) {
  const navigate = useNavigate();

  return (
    <div className="card">
      <div className="card-icon">💬</div>

      <div className="card-content">
        <h3>Comments</h3>
        <p>{count} total comments</p>
      </div>

      <div className="card-footer">
        <button onClick={() => navigate(`/comments`)}>View Comments</button>
      </div>
    </div>
  );
}
