import { CommentService } from "../services/CommentService.ts";
import { CommentProps } from "../types/comment/CommentProps.ts";
import "../styles/comments/CommentList.css"


export default function CommentList({ comments, reload }: CommentProps){

    const handleDelete = async (id:number) => {
        await CommentService.delete(id);
        reload();
    }

    return(
        <div className="comment-list">
            {comments.length === 0 && (
                <p className="no-comments">No comments yet.</p>
            )}

            {comments.map((c) => (
                <div key={c.id} className="comment-card">
                    <p className="comment-content">{c.content}</p>

                    <p className="comment-meta">
                        By {c.author} •{" "}
                        {new Date(c.createdAt + "Z").toLocaleDateString()}
                    </p>

                    <button
                        className="delete-comment-btn"
                        onClick={() => handleDelete(c.id)}
                    >
                        Delete ✖️
                    </button>
                </div>
            ))}
            </div>
    );
}