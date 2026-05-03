import { useState } from "react";
import { CommentFormProps } from "../types/comment/CommentFormProps";
import { CommentService } from "../services/CommentService";
import "../styles/comments/CommentForm.css";



export default function CommentForm({ postId, onClose, onSaved}: CommentFormProps){

    const [content, setContent] = useState("");

    const handleSubmit = async (e:React.FormEvent) => {
        e.preventDefault();

        await CommentService.create({content, postId});
        onSaved();
    }

    return(
        <div className="modal-overlay">
            <div className="modal">
                <h2>Add Comment</h2>

                <form onSubmit={handleSubmit}>
                    <textarea
                        placeholder="Write your comment..."
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                    />
                    
                    <div className="modal-actions">
                        <button type="submit">Save</button>
                        <button 
                            type="button"
                            onClick={onClose}>
                                Cancel
                            </button>
                    </div>
                </form>
            </div>
        </div>
    );
}