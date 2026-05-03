import { useEffect, useState } from "react";
import { Post } from "../types/post/Post";
import { deletePost, getAllPosts, getPostsByCategory } from "../services/PostService";
import PostForm from "../components/PostForm";
import "../styles/post/PostPage.css";
import { useLocation, useNavigate } from "react-router-dom";

export default function PostPage() {

  const [posts, setPosts] = useState<Post[]>([]);
  const [search, setSearch] = useState("")

  const [openForm, setOpenForm] = useState(false);
  const [editing, setEditing] = useState<Post | null>(null);

  const location = useLocation();
  const navigate = useNavigate();

  const params = new URLSearchParams(location.search);
  const categoryId = params.get("categoryId");

  const load = async () => {
    if(categoryId){
      const data = await getPostsByCategory(Number(categoryId));
      setPosts(data);
    }
    else{
      const data = await getAllPosts();
      setPosts(data);
    }
  };

  useEffect(() => {
    load();
  }, [categoryId]);

  const filteredPosts = posts.filter(
      (p) => p.title.toLowerCase().includes(search.toLowerCase())
    );

  const handleEdit = (post: Post) => {
    setEditing(post);
    setOpenForm(true);
  };

  const handleDelete = async (id: number) => {
    await deletePost(id);
    load();
  };

  return (
    <div className="page-container">
      <div className="posts-container">

        <div className="posts-header">
          
          <h1>
            {
              categoryId
              ? `Post in Category: ${posts[0]?.category?.name || ""}`
              : "Posts"
            }
          </h1>
          
          {categoryId && (
            <button
              className="clear-filter-btn"
              onClick={() => navigate("/posts")}
            >
              Clear ✖️
            </button>
          )}

          <button
            className="add-btn"
            onClick={() => {
              setEditing(null);
              setOpenForm(true);
            }}
          >
            Add ➕
          </button>
        </div>

        <div className="search-bar">
          <input
            type="text"
            placeholder="Search posts..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />

          {search.length > 0 && (
            <button  
              className="clear-search"
              onClick={() => setSearch("")}
            >
              ✖️
            </button>
          )}
        </div>

        <div className="posts-list">
          {filteredPosts.map((post) => (
            <div key={post.id} className="post-card">
              <div>
                <h3
                  className="clickable"
                  onClick={()=> navigate(`/posts/${post.id}${categoryId ? `?categoryId=${categoryId}`:""}`)}
                >
                  {post.title}
                </h3>
                <p className="post-meta">
                  By {post.author.username},{" "}
                  {new Date(post.createdAt).toLocaleDateString()}
                </p>
              </div>

              <div className="post-actions">
                <button onClick={() => handleEdit(post)}>Edit</button>
                <button onClick={() => handleDelete(post.id)}>Delete</button>
              </div>
            </div>
          ))}

          {filteredPosts.length === 0 && (
            <p className="no-results">No posts found.</p>
          )}
        </div>

        {openForm && (
          <PostForm
            editing={editing}
            onClose={() => setOpenForm(false)}
            onSaved={() => {
              setOpenForm(false);
              load();
            }}
          />
        )}
      </div>
    </div>
  );
}
