import { Directive, ViewContainerRef } from '@angular/core';
@Directive({
    selector: '[appCrudDisplay]',
})
export class CrudDisplayDirective {
    constructor(public viewContainerRef: ViewContainerRef) { }
}
