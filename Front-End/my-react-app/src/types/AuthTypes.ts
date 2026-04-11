export interface AuthContextType {
    user: any;
    token: string | null;
    loading: boolean;
    login:(data:any) => void;
    logout: () => void;
}