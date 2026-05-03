import { api } from "../api/api";
import { Post } from "../types/post/Post";

export async function getAllPosts(): Promise<Post[]> {
  const response = await api.get("/posts", {
    params: {
      page: 0,
      size: 50,
      sortBy: "createdAt",
      direction: "desc",
    },
  });
  return response.data.content;
}

export async function getPostById(id: number): Promise<Post> {
  const response = await api.get(`/posts/${id}`);
  return response.data;
}

export async function getPostsByCategory(categoryId: number) {
  const response = await api.get(`/posts/category/${categoryId}`);
  return response.data;
}

export async function createPost(post: Partial<Post>) {
  const response = await api.post("/posts", post);
  return response.data;
}

export async function updatePost(id: number, post: Partial<Post>) {
  const response = await api.put(`/posts/${id}`, post);
  return response.data;
}

export async function deletePost(id: number) {
  await api.delete(`/posts/${id}`);
}
