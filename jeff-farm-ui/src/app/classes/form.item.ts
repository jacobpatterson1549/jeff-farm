export class FormItem {
    private name: string;
    private type: FormItemType;
    private value: string;

    constructor(name: string, type: FormItemType, value?: string) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
}

// TODO: It would be nice if FormItemType were a member of FormItem.
export enum FormItemType {
    String,
    Boolean,
    Integer,
    Stars,
}