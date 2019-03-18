export class FormItem {
    public name: string;
    public type: FormItemType;
    public value: string | number | boolean;

    constructor(name: string, type: FormItemType, value?: string | number | boolean) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
}

export enum FormItemType {
    String,
    Boolean,
    Integer,
    Stars,
    Color,
    Password,
}
