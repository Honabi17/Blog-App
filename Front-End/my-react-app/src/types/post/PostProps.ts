import { Post } from "./Post";

export interface PostProps{
    editing:Post|null;
    onClose:()=>void;
    onSaved:()=>void;
}