import { useEffect, useState } from "react";
import { getTrafficStats } from "../../services/DashboardService";


export default function TrafficChart(){

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function loadTraffic() {
            try{
                const response = await getTrafficStats();
                setData(response);
            }
            finally{
                setLoading(false);
            }
        }
        loadTraffic();
    }, []);

    return(
        <div className="chart-card">
            <h3>Traffic Stats (Last 12 months)</h3>

            {loading?(
                <p>Loading chart...</p>
                ) : (
                    <ResponsiveContainer width="100%" height={300}>
                        <LineChart data={data}>
                            <CartesianGrid strokeDasharray="3 3" opacity={0.2} />
                            <XAxis dataKey="month"/>
                            <YAxis />
                            <Tooltip />
                            <Line 
                                type="monotone"
                                dataKey="visits"
                                stroke="#4f46e5"
                                strokeWidth={3}
                                dot={{r:4}}
                            />
                        </LineChart>
                    </ResponsiveContainer>
                )}
            
        </div>
    );
}