import { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Post } from "../types/post/Post";
import { getPostById } from "../services/PostService";
import "../styles/post/PostDetailsPage.css";
import CommentList from "../components/CommentList";
import CommentForm from "../components/CommentForm";
import { CommentService } from "../services/CommentService";
import { Comment } from "../types/comment/Comment";



export default function PostDetailsPage(){
    
    const {id} = useParams();
    const navigate = useNavigate();
    const location = useLocation();

    const [post, setPost] = useState<Post | null>(null);

    const [comments, setComments] = useState<Comment[]>([]);
    const [openCommentForm, setOpenCommentForm] = useState(false);

    const params = new URLSearchParams(location.search);
    const fromCategoryId = params.get("categoryId");

    const loadComments = async () => {
        const data = await CommentService.getByPost(Number(id));
        setComments(data);
    }

    useEffect(() => {
        async function load(){
            const data = await getPostById(Number(id));
            setPost(data);
        }
        load();
        loadComments();
    }, [id]);

    if(!post){
        return <p className="loading">Loading...</p>
    }

    return(
        <div className="page-container">
            <div className="post-details-container">

                <div className="breadcrumb">
                    {fromCategoryId ? (
                        <>
                            <span
                                className="crumb clickable"
                                onClick={() => navigate("/categories")}
                            >
                                Categories
                            </span>
                            <span>›</span>
                            <span
                                className="crumb clickable"
                                onClick={() => navigate(`/posts?categoryId=${fromCategoryId}`)}
                            >
                                {post.category.name}
                            </span>
                            <span>›</span>
                            <span className="crumb">{post.title}</span>
                        </>
                    ) : (
                        <>
                            <span
                                className="crumb clickable"
                                onClick={() => navigate("/post")}
                            >
                                Posts
                            </span>
                            <span>›</span>
                            <span className="crumb">{post.title}</span>
                        </>
                    )}
                </div>

                <button
                    className="back-btn"
                    onClick={() => {
                        if(fromCategoryId){
                            navigate(`/posts?categoryId=${fromCategoryId}`)
                        }
                        else{
                            navigate("/posts");
                        }
                    }}
                >
                    ← Back
                </button>

                <h1 className="post-title">{post.title}</h1>

                <p className="post-meta">
                    By {post.author.username} •{" "}
                    {new Date(post.createdAt + "Z").toLocaleDateString()}
                </p>

                <p className="post-category">
                    Category:{" "}
                    <span
                        className="clickable"
                        onClick={() => navigate(`/posts?categoryId=${post.category.id}`)}
                    >
                        {post.category.name}
                    </span>
                </p>

                <div className="post-content">
                    {post.content}
                </div>

                <div className="comments-section">
                    <div className="comments-header">
                        <h2>Comments ({comments.length})</h2>

                        <button
                            className="add-comment-btn"
                            onClick={() => setOpenCommentForm(true)}
                        >
                            Add Comment ➕
                        </button>
                    </div>

                    <CommentList 
                        comments={comments}
                        reload={loadComments}
                    />

                    {openCommentForm && (
                        <CommentForm
                            postId={Number(id)}
                            onClose={() => setOpenCommentForm(false)}
                            onSaved={() =>{
                                setOpenCommentForm(false);
                                loadComments();
                            }}
                        />
                    )}
                </div>
            </div>
        </div>
    );
}