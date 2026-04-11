import {Navigate} from "react-router-dom";
import useAuth from "../contex/useAuth";

export default function ProtectedRoute({children}){
    const {token, loading} = useAuth();
    

    if(loading){
        return(
            <div>
                Loading...
            </div>
        );
    }

    if(!token){
        return <Navigate to="/login" replace />
    }

    return children;

}