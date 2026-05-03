import { Comment } from "./Comment"


export interface CommentProps{
    comments:Comment[],
    reload: ()=> void
}