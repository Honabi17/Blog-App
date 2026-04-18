import { useEffect, useState } from "react";
import { getRecentPost } from "../../services/DashboardService";


export default function RecentPosts(){

    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect (() =>{
        async function load(){
            try{
                const data = await getRecentPost();
                setPosts(data);
            }
            finally{
                setLoading(false);
            }
        }
        load();
    },[]);

    return(
        <div className="recent-posts-card">

            <h3>Recent Posts</h3>

            {loading ? ( 
                <p>Loading...</p>
            ) : posts.length === 0 ?(
                <p>No posts found.</p>
            ) : (
                
            )}
        </div>
    );
}