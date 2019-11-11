import {Directive, ElementRef, EventEmitter, HostListener, Input, Output} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ValidationService} from "../validation-service/validation.service";

@Directive({
  selector: '[validateForm]'
})
export class ValidationFormDirective {
  @Input('validateForm')
  form: FormGroup;

  @Output() submitForm = new EventEmitter<any>();
  constructor(private element: ElementRef, private formValidationService: ValidationService) { }

  @HostListener('click')
  onClick() {
    if (this.form) {
      if (this.form.valid) {
        this.submitForm.emit(this.form.value);
      } else {
        this.formValidationService.markInvalidAsTouched(this.form);
        this.scrollFirstInvalidIntoView();
      }
    }
  }

  scrollFirstInvalidIntoView () {
    setTimeout(() => {
      let parent = (this.element.nativeElement as Element).parentElement;
      while (parent && parent.nodeName !== 'FORM') { parent = parent.parentElement; }
    });
  }
}
