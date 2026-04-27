import { useEffect, useState } from "react";
import { getRecentPost } from "../../services/DashboardService";
import { Post } from "../../types/Post";

export default function RecentPosts() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(true);

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

  return (
    <div>
      <h3>Recent Posts</h3>

      {loading ? (
        <p>Loading...</p>
      ) : posts.length === 0 ? (
        <p>No posts found.</p>
      ) : (
        <ul>
          <div className="recent-posts-grid">
            {posts.map((post) => (
              <li key={post.id} className="recent-post-card">
                <h4>{post.title}</h4>
                <p>{post.summary}</p>
              </li>
            ))}
          </div>
        </ul>
      )}
    </div>
  );
}
