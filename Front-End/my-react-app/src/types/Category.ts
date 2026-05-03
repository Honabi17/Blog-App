
export interface Category{
    id:number,
    name:string,
    description:string,
    createdBy:{
        id:number,
        username: string
    },
    createdAt:string,
    updatedAt:string
}

export interface CreateCategoryDTO{
    name:string,
    description:string
}

export interface UpdatedCategoryDTO{
    name?:string,
    description?:string
}