import { useEffect, useState } from "react";
import { CommentService } from "../services/CommentService";
import { Comment } from "../types/comment/Comment";
import "../styles/comments/CommentPage.css";


export default function CommentPage(){

    const [comments, setComments] = useState<Comment[]>([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] =  useState(0);

    const load = async () => {
        const data = await CommentService.getAllPaged(page, 10);
        setComments(data.content);
        setTotalPages(data.totalPages)
    };

    useEffect(() => {
        load();
    }, [page]);

    return(
        <div className="page-container">
            <div className="comments-page-container">
                <h1>All Comments</h1>

                <table className="comments-table">
                    <thead>
                        <tr>
                            <th>Content</th>
                            <th>Author</th>
                            <th>Post</th>
                            <th>Date</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        {comments.map((comment) =>(
                            <tr key={comment.id}>
                                <td>{comment.content}</td>
                                <td>{comment.authorUsername}</td>
                                <td>{comment.postTitle}</td>
                                <td>{new Date(comment.createdAt + "Z").toLocaleDateString()}</td>
                                <td>
                                    <button
                                        className="delete-btn"
                                        onClick={async () => {
                                            await CommentService.delete(comment.id);
                                            load(); 
                                        }}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <div className="pagination">
                    <button
                        disabled={page === 0}
                        onClick={() => setPage((p) => p-1)}
                    >
                        Previous
                    </button>

                    <span>
                        Page {page + 1} of {totalPages}
                    </span>

                    <button
                        disabled={page + 1 >= totalPages}
                        onClick={() => setPage((p) => p+1)}
                    >
                        Next
                    </button>
                </div>
            </div>
        </div>
    );
}