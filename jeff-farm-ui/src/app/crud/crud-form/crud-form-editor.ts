import { FormType } from '../form.type';
import { CrudItem } from '../crud.item';

export interface CrudFormEditor<T extends CrudItem> {

    setFormType(formType: FormType);

    isValid(): boolean;

    getCrudItem(): T;
}
