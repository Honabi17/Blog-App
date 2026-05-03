import { useNavigate } from "react-router-dom";

interface Props {
  count?: number;
}

export default function PostOverviewCard({ count = 0 }: Props) {
  const navigate = useNavigate();

  return (
    <div className="card">
      <div className="card-icon">📝</div>

      <div className="card-content">
        <h3>Posts</h3>
        <p>{count} total posts</p>
      </div>

      <div className="card-footer">
        <button onClick={() => navigate("/posts")}>View Posts</button>
      </div>
    </div>
  );
}
