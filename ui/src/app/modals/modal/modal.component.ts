import {Component, Injector, OnDestroy} from '@angular/core';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnDestroy {

  private modal: NgbModalRef;
  private closing: boolean;

  constructor(private modalService: NgbModal,
              private router: Router,
              private activeRoute: ActivatedRoute) {
    this.openModal();
  }

  ngOnDestroy() {
    if (this.modal) {
      this.closing = true;
      this.modal.dismiss();
      this.modal = null;
    }
  }

  private openModal() {
    this.modal = this.modalService.open(this.activeRoute.snapshot.data.component);
    const changeLocation = () => {
      if (!this.closing) {
        this.router.navigate([{outlets: {modal: null}}]);
      }
    };
    this.modal.result.then(changeLocation, changeLocation);
  }

}
