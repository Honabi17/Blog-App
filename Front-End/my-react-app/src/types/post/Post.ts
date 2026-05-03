export interface Post {
  id: number;
  title: string;
  content: string;

  author:{
    id:number,
    username:string
  }

  category:{
    id:number,
    name:string
  }

  commentCount:number;
  
  createdAt:string;
  updatedAt:string;
}
  
