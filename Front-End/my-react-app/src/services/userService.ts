import {api} from './api'

export async function checkUsername(username: string){

    try{
        const response = await api.get(`users/check-username/${username}`);
        return response.data.exists;
    }
    catch (error){
        return false;
    }
}