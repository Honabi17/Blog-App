import { Category } from "./Category";


export interface CategoryFormProps{
    editing: Category | null;
    onClose: () => void;
    onSaved: () => void;
}