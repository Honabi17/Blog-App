
export interface Category{
    id:number,
    name:string,
    description:string,
    createdById:number,
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