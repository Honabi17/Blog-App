import { useEffect, useState } from "react";
import StatCard from "../components/dashboard/StatCard";
import useAuth from "../contex/useAuth";
import { getDashboardStats } from "../services/getDashboardStats";
import DashboardLayout from "../layouts/DashboardLayout";
import TrafficChart from "../components/dashboard/TrafficChart";
import EarningsChart from "../components/dashboard/EarningsChart";
import RecentPosts from "../components/dashboard/RecentPosts";
import Platforms from "../components/dashboard/Platforms";


export default function Dashboard(){

    const {user} = useAuth();
    const [stats, setStats] = useState({
        pageviews: 0,
        visitors: 0,
        posts: 0,
        comments:0 
    });
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function loadStats(){
            try{
                const data = await getDashboardStats();
                setStats(data);
            }
            finally{
                setLoading(false);
            }
        }
        loadStats();
    }, []);

    return(
       <DashboardLayout>
            <div className="welcome">
                <h1>Hi {user?.username}, Welcome back!</h1>
                <p>Here's your blog statistic summary.</p> 
            </div>

            <div className="stats-grid">
                <StatCard title="Pageviews" value={stats.pageviews}/>
                <StatCard title="Visitors" value={stats.visitors}/>
                <StatCard title="Posts" value={stats.posts}/>
                <StatCard title="Comments" value={stats.comments}/>
            </div>

            <div className="charts-grid">
                <TrafficChart />
                <EarningsChart />
            </div>

            <div className="bottom-grid">
                <RecentPosts />
                <Platforms />
            </div>
       </DashboardLayout>
    );
}