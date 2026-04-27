import { useState } from "react";

export interface Earnings{
    month:string,
    amount:number
}

const [data, setData] = useState<Earnings[]>([]);