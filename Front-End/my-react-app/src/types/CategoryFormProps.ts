import { Category } from "./Category";


export interface CategoryFormProps{
    onClose: () => void;
    onSaved: () => void;
    editing: Category | null;
}