import { StatCardsProps } from "../../types/StatCardsProps";

export default function StatCard({ title, value, subtitle }: StatCardsProps) {
  return (
    <div className="stat-card">
      <h3>{title}</h3>
      <h1>{value}-</h1>
      {subtitle && <p>{subtitle}</p>}
    </div>
  );
}
