import { useEffect, useState } from "react";
import { getRecentPost } from "../../services/DashboardService";
import { Post } from "../../types/Post";
import { useNavigate } from "react-router-dom";

export default function RecentPosts() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    async function load() {
      try {
        const data = await getRecentPost();
        setPosts(data);
      } finally {
        setLoading(false);
      }
    }
    load();
  }, []);

  if (loading) {
    return <p>Loading recent posts...</p>;
  }

  if (posts.length === 0) {
    return <p>No recent posts found.</p>;
  }

  return (
    <div className="card-grid">
      {posts.map((post) => (
        <div
          key={post.id}
          className="card recent post-card"
          onClick={() => navigate(`/posts/${post.id}`)}
        >
          <div className="card-icon">📝</div>
          <div className="card-content">
            <h3>{post.title}</h3>
            <p>{post.summary}</p>
          </div>

          <div className="card-content">
            <button onClick={() => navigate(`/posts/$post.id`)}>
              Read More
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}
