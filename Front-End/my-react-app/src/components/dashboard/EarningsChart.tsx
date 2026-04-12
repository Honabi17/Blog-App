import { useEffect, useState } from "react";
import { getEarningStats } from "../../services/DashboardService";
import { Area, AreaChart, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";


export default function EarningsChart(){

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect (() => {
        async function loadEarnings() {
            try{
                const response = await getEarningStats();
                setData(response);
            }
            finally{
                setLoading(false);
            }
        }

        loadEarnings();
    }, []);

    return(
        <div className="chart-card">
            <h3>Earnings</h3>

            {loading ? (
                <p>Loading chart...</p>
            ) : (
                <ResponsiveContainer width="100%" height={300}>
                    
                    <AreaChart data={data}>

                        <defs>
                            <linearGradient 
                                id="earningsGradient" 
                                x1="0" 
                                y1="0"
                                x2="0"
                                y2="1"
                            >
                                <stop 
                                    offset="5%" 
                                    stopColor="#10b981" 
                                    stopOpacity={0.8}
                                />
                                <stop 
                                    offset="95%" 
                                    stopColor="#10b981" 
                                    stopOpacity={0}
                                />
                            </linearGradient>
                        </defs>

                        <CartesianGrid 
                            strokeDasharray="3 3"
                            opacity={0.2}
                        />

                        <XAxis dataKey="month" />

                        <YAxis />

                        <Tooltip />

                        <Area
                            type="monotone"
                            dataKey="amount"
                            stroke="#10b981"
                            strokeWidth={3}
                            fill="url(#earningsGradient)"                   
                        />

                    </AreaChart>

                </ResponsiveContainer>

            )}
            
        </div>
    );
}