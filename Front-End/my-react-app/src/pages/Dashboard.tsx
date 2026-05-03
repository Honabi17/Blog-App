import { useEffect, useState } from "react";
import StatCard from "../components/dashboard/StatCard";
import useAuth from "../context/useAuth";
import { getDashboardStats } from "../services/DashboardService";
import DashboardLayout from "../layouts/DashboardLayout";
import TrafficChart from "../components/dashboard/TrafficChart";
import EarningsChart from "../components/dashboard/EarningsChart";
import "../styles/Dashboard.css";
import Section from "../components/Section";
import CategoriesOverviewCard from "../components/dashboard/CategoriesOverviewCard";
import CommentsOverviewCard from "../components/dashboard/CommentsOverviewCard";
import PostOverviewCard from "../components/dashboard/PostOverviewCard";

export default function Dashboard() {
  const { user } = useAuth();
  const [stats, setStats] = useState({
    pageviews: 0,
    visitors: 0,
    categories: 0,
    posts: 0,
    comments: 0,
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadStats() {
      try {
        const data = await getDashboardStats();
        setStats(data);
      } finally {
        setLoading(false);
      }
    }
    loadStats();
  }, []);

  return (
    <DashboardLayout>
      <div className="page-container">
        <div className="welcome">
          <h1>Hi {user?.username}, Welcome back!</h1>
          <p>Here's your blog statistic summary.</p>
        </div>

        <Section title="Statistics">
          <div className="stats-grid">
            <StatCard title="Pageviews" value={stats.pageviews} />
            <StatCard title="Visitors" value={stats.visitors} />
            <StatCard title="Posts" value={stats.posts} />
            <StatCard title="Comments" value={stats.comments} />
          </div>
        </Section>

        <Section title="Traffic & Earnings">
          <div className="charts-grid">
            <TrafficChart />
            <EarningsChart />
          </div>
        </Section>

        <Section title="Overview">
          <div className="card-grid">
            <CategoriesOverviewCard count={stats.categories} />
            <PostOverviewCard count={stats.posts} />
            <CommentsOverviewCard count={stats.comments} />
          </div>
        </Section>
      </div>
    </DashboardLayout>
  );
}
